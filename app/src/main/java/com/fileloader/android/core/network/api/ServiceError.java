package com.fileloader.android.core.network.api;

public class ServiceError {
    private final String errorCode;
    private final String errorMessage;

    public ServiceError(final String errorCode, final String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ServiceError(final String errorMessage) {
        this.errorCode = null;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
