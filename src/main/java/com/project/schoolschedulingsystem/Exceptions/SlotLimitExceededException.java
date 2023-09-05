package com.project.schoolschedulingsystem.Exceptions;

public class SlotLimitExceededException extends RuntimeException{

    public SlotLimitExceededException()
    {
        super();
    }

    public SlotLimitExceededException(String message)
    {
        super(message);
    }
}
