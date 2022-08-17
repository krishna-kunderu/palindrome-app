package com.cme;

import com.cme.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.io.File;

public class BaseTestHelper {
    @Inject
    protected ObjectMapper objectMapper;

    @SneakyThrows
    private File getFile(String path) {
        return  new File(getClass().getClassLoader().getResource(path).toURI());
    }

    @SneakyThrows
    protected User getPalindromePayload() {

        return objectMapper.readValue(getFile("payloads/palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getPalindromeResponse() {
        return objectMapper.readValue(getFile("responses/palindromeResponse.json"), User.class);
    }

    @SneakyThrows
    protected User getNotPalindromePayload() {
        return objectMapper.readValue(getFile("payloads/not-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getAlphaNumbericPalindromePayload() {
        return objectMapper.readValue(getFile("payloads/alphanumeric-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getGetPalindromeResponse() {
        return objectMapper.readValue(getFile("responses/get-palindromeResponse.json"), User.class);
    }

    @SneakyThrows
    protected User getNumberPalindromePayload() {
        return objectMapper.readValue(getFile("payloads/number-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getNullPalindromePayload() {
        return objectMapper.readValue(getFile("payloads/null-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getEmptyPalindromePayload() {
        return objectMapper.readValue(getFile("payloads/empty-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getAlphaNumericPalindromeResponse() {
        return objectMapper.readValue(getFile("responses/alphanumeric-palindromeResponse.json"), User.class);
    }

    @SneakyThrows
    protected User getSpacePalindrome() {
        return objectMapper.readValue(getFile("payloads/space-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getLeadSpacePalindrome() {
        return objectMapper.readValue(getFile("payloads/leadspace-palindrome.json"), User.class);
    }

    @SneakyThrows
    protected User getTrailSpacePalindrome() {
        return objectMapper.readValue(getFile("payloads/trailspace-palindrome.json"), User.class);
    }

}
