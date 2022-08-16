package com.cme.schema;

import com.cme.validators.ValidatePalindrome;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;


public class UserRequest {

    @Schema(title = "unique identifier", required = true)
    @NotNull
    private String username;

    @Schema(title = "Not null and Not an empty value and should be a palindrome to save", required = true)
    @ValidatePalindrome
    private String value;

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


}
