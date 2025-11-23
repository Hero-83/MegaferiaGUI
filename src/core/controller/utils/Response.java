package core.controller.utils;

public class Response<T> {
    private boolean ok;
    private String message;
    private T data;

    private Response(boolean ok, String message, T data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(true, null, data);
    }

    public static <T> Response<T> ok(String message, T data) {
        return new Response<>(true, message, data);
    }

    public static <T> Response<T> badRequest(String message) {
        return new Response<>(false, message, null);
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}