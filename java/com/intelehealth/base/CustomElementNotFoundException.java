package com.intelehealth.base;

public class CustomElementNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	private String context;

    public CustomElementNotFoundException(String message) {
        super(message);
    }

    public CustomElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomElementNotFoundException(String message, String context) {
        super(message);
        this.context = context;
    }

    public CustomElementNotFoundException(String message, String context, Throwable cause) {
        super(message, cause);
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}

	

