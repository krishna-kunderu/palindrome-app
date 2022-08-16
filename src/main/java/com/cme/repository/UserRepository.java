package com.cme.repository;

import com.cme.entities.User;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import utils.AppUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    private String getMessage(@NotNull User user) {
        return String.format("%s is%spalindrome added by the user %s", user.getValue(), user.isPalindrome() ? " a " : " not a ", user.getUsername());
    }

    public Uni<User> addOrUpdateUser(User user) {
        return Panache
                .withTransaction(() -> User.findById(user.getUsername())
                        .onItem().ifNotNull()
                        .transform(entity -> {
                            // TODO Can create a Object Mapper (Bean Mapping)
                            ((User)entity).addToPalindromeSet(user.getValue());
                            ((User)entity).setValue(user.getValue());
                            user.setIsPalindrome(AppUtils.isPalindrome(user.getValue()));
                            ((User)entity).setIsPalindrome(user.isPalindrome());
                            ((User)entity).setMessage(getMessage(user));
                            return ((User)entity);
                        })
                        .onItem().ifNull().switchTo(() -> {
                            // TODO Can create a Object Mapper (Bean Mapping)
                            user.setIsPalindrome(AppUtils.isPalindrome(user.getValue()));
                            user.setValue(user.getValue());
                            user.addToPalindromeSet(user.getValue());
                            user.setMessage(getMessage(user));
                            return user.persist();
                        }));
    }

    public Uni<User> findByUsername(String username) {
        return Panache
                .withTransaction(() -> User.findById(username)
                        .onItem().ifNotNull()
                        .transform(entity ->{
                            ((User)entity).setIsPalindrome(true);
                            int lastIndex = ((User)entity).getValues().size() - 1;
                            ((User)entity).setValue(((User)entity).getValues().get(lastIndex));
                          return ((User)entity);
                        })
                        .onFailure().recoverWithNull());
    }


}

