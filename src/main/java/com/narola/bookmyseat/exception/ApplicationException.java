package com.narola.bookmyseat.exception;

import java.util.Map;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String viewName;
    private Map<String, String> errorList;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, String> getErrorList() {
        return errorList;
    }

    public void setErrorList(Map<String, String> errorList) {
        this.errorList = errorList;
    }

    public ApplicationException(String viewName, Map<String, String> errorList) {
        super();
        this.viewName = viewName;
        this.errorList = errorList;
    }

    public ApplicationException(String message, String viewName, Map<String, String> errorList) {
        super(message);
        this.viewName = viewName;
        this.errorList = errorList;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
