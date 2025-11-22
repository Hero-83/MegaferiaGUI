/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.utils;

/**
 *
 * @author keinerthd
 */
public class Response<T> {

    private boolean success;
    private Status statusCode;
    private String message;
    private T data;

    public Response(boolean success, Status statusCode, String message, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // Fábricas estáticas

    public static <T> Response<T> ok(T data) {
        return new Response<>(true, Status.OK, "Operación exitosa", data);
    }

    public static <T> Response<T> ok(String message, T data) {
        return new Response<>(true, Status.OK, message, data);
    }

    public static <T> Response<T> badRequest(String message) {
        return new Response<>(false, Status.BAD_REQUEST, message, null);
    }

    public static <T> Response<T> notFound(String message) {
        return new Response<>(false, Status.NOT_FOUND, message, null);
    }

    public static <T> Response<T> conflict(String message) {
        return new Response<>(false, Status.CONFLICT, message, null);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(false, Status.ERROR, message, null);
    }

    public boolean isOk() {
        return success && statusCode == Status.OK;
    }

    public boolean isSuccess() {
        return success;
    }

    public Status getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}