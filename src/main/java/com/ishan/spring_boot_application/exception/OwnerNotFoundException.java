package com.ishan.spring_boot_application.exception;

public class OwnerNotFoundException extends Exception{

    public static final long serialVersionUID = 1L;

    public OwnerNotFoundException(String message) {
        super(message);
    }
}
