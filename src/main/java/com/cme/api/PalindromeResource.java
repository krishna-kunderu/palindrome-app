package com.cme.api;

import com.cme.entities.User;
import com.cme.repository.UserRepository;
import com.cme.schema.UserRequest;
import io.smallrye.mutiny.Uni;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/palindrome")
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class PalindromeResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Operation(summary = "Add or update palindrome resources for a user", description = "This operation creates user with given palindrome value. Updates user to add to existing of list of palindromes the user added in the past")
    @APIResponses(value = {
            @APIResponse(responseCode = "20o", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserRequest.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class))),
            @APIResponse(responseCode = "405", description = "Method Not allowed", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class))),
            @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class)))})

    public Uni<Response> createPalindrome(@Valid UserRequest request) {

        User user = new User();
        user.setValue(request.getValue());
        user.setUsername(request.getUsername());
       return userRepository.addOrUpdateUser(user)
            .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
            .onItem().ifNull().continueWith(Response.ok(user)::build);
    }

    @GET
    @Path("/{username}")
    @Operation(summary = "Get existing lis of values provided by given username. The value indicate the last palindrome value added by the user", description = "This operation gets palindromes for given username value and also the last value added to list")
    @APIResponses(value = {
            @APIResponse(responseCode = "20o", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class))),
            @APIResponse(responseCode = "405", description = "Method Not allowed", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class))),
            @APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Error.class)))})
    public Uni<Response> createPalindrome(@NonNull String username) {

        return userRepository.findByUsername(username)
                .onItem().ifNotNull().transform(entity -> {
                    return Response.ok(entity).build();
                })
                .onItem().ifNull().continueWith(Response.status(NOT_FOUND).entity(String.format("{\"message\": \"Username %s not Found\"}", username)).build());
    }
}