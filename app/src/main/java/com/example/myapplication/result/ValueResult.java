package com.example.myapplication.result;

public class ValueResult<T> extends Result{
    private final T value;

    protected ValueResult(boolean isValid, T value, Error error) {
        super(isValid, error);
        this.value = value;
    }

    public static <T> ValueResult<T> success(T value) {
        return new ValueResult<>(true, value, null);
    }

    public static <T> ValueResult<T> valueFailure(String errorMessage) {
        return new ValueResult<>(false, null, new Error(errorMessage));
    }

    public static <T> ValueResult<T> valueFailure(Error error) {
        return new ValueResult<>(false, null, error);
    }

    public T getValue() {
        if (!isValid()) {
            throw new RuntimeException("Não é possível acessar o valor de um Result inválido.");
        }
        return this.value;
    }
}
