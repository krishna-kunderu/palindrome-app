package com.cme.entities;

import com.cme.converters.ListToJsonConverter;
import com.cme.validators.ValidatePalindrome;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "\"User\"")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends PanacheEntityBase {

    @Id
    @Schema(title = "unique identifier", required = true)
    private String username;

    @Column(columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<String> values = Lists.newArrayList();

    @ValidatePalindrome
    @Transient
    @Schema(title = "Not null or Not an empty value", required = true)
    private String value;

    @Transient
    private boolean isPalindrome;

    @Transient
    private String message;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @JsonbProperty("isPalindrome")
    public boolean isPalindrome() {
        return isPalindrome;
    }

    @JsonbProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setIsPalindrome(boolean isPalindrome) {
        this.isPalindrome = isPalindrome;
    }


    public void addToPalindromeSet(String value) {
        if(!values.stream().anyMatch(value::equalsIgnoreCase)) {
            values.add(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username.toLowerCase(), user.username.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username.toLowerCase());
    }

}