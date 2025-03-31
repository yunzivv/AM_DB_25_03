package org.example.exception;

// SQL에서 출력되는 오류를 출력 시켜주는 클래스
public class SQLErrorException extends RuntimeException {
    private Exception origin;

    public SQLErrorException(String message, Exception origin) {
        super(message);
        this.origin = origin;
    }

    public Exception getOrigin() {
        return origin;
    }
}