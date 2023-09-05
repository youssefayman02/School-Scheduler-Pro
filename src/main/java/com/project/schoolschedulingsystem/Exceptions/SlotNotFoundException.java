package com.project.schoolschedulingsystem.Exceptions;

public class SlotNotFoundException extends RuntimeException{

    public SlotNotFoundException()
    {
        super();
    }

    public SlotNotFoundException(String message)
    {
        super(message);
    }
}
