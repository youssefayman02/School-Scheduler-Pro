package com.project.schoolschedulingsystem.Exceptions;

public class ClassLimitExceededException extends RuntimeException{

    public ClassLimitExceededException()
    {
        super();
    }

    public ClassLimitExceededException(String message)
    {
        super(message);
    }
}
