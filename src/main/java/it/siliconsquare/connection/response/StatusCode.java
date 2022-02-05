package it.siliconsquare.connection.response;

public class StatusCode {

    /**
     * Standard response for successful HTTP requests.
     */
    public static final int OK = 200;

    /**
     * The request requires user authentication.
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * The server understood the request, but is refusing to fulfill it.
     */
    public static final int FORBIDDEN = 403;

    /**
     * The server has not found anything matching the Request-URI.
     */
    public static final int NOT_FOUND = 404;

    /**
     * The server encountered an unexpected condition which
     * prevented it from fulfilling the request.
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
}
