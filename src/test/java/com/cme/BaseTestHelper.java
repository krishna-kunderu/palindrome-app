package com.cme;

import com.cme.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.nio.file.Paths;

public class BaseTestHelper {
    @Inject
    protected ObjectMapper objectMapper;

    @SneakyThrows
    protected User getPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getPalindromeResponse() {
        return objectMapper.readValue(Paths.get("src/test/resources/responses/palindromeResponse.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getNotPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/not-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getAlphaNumbericPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/alphanumeric-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getGetPalindromeResponse() {
        return objectMapper.readValue(Paths.get("src/test/resources/responses/get-palindromeResponse.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getNumberPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/number-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getNullPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/null-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getEmptyPalindromePayload() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/empty-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getAlphaNumericPalindromeResponse() {
        return objectMapper.readValue(Paths.get("src/test/resources/responses/alphanumeric-palindromeResponse.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getSpacePalindrome() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/space-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getLeadSpacePalindrome() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/leadspace-palindrome.json").toFile(), User.class);
    }

    @SneakyThrows
    protected User getTrailSpacePalindrome() {
        return objectMapper.readValue(Paths.get("src/test/resources/payloads/trailspace-palindrome.json").toFile(), User.class);
    }

}
