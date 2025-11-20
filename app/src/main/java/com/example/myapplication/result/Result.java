package com.example.myapplication.result;

public class Result {
    private final boolean isValid;
    private final Error error;

    protected Result(boolean isValid, Error error) {
        this.isValid = isValid;
        this.error = error;
    }

    public static Result success() {
        return new Result(true, null);
    }

    public static Result failure(String errorMessage) {
        return new Result(false, new Error(errorMessage));
    }

    public static Result failure(Error error) {
        return new Result(false, error);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public boolean isFailure() {
        return !this.isValid;
    }

    public Error getError() {
        if (isValid()) {
            throw new RuntimeException("Não é possível acessar o erro de um Result válido.");
        }
        return this.error;
    }
}
