package com.project.schoolschedulingsystem.Exceptions;

public class GradeLimitExceededException extends RuntimeException{

    public GradeLimitExceededException()
    {
        super();
    }

    public GradeLimitExceededException(String message)
    {
        super(message);
    }
}
