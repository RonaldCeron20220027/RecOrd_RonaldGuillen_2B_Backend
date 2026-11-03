package com.example.demo.ApiResponse;

public class ApiResponse<T>{

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private boolean success;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private String message;
    private T data;


}
