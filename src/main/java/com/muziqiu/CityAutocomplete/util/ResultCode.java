package com.muziqiu.CityAutocomplete.util;

public enum ResultCode {
    SUCCESS(100, "Request is successful"),
    FAIL(101, "Request is unsuccessful"),
    INCOMPLETE_QUERY(102, "The location info(latitude and longitude) is not complete"),
    NO_REQUIRED_PARAM(103, "no required param q");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
