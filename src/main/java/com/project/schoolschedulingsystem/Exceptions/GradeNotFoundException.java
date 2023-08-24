package com.project.schoolschedulingsystem.Exceptions;

public class GradeNotFoundException extends RuntimeException{

    public GradeNotFoundException()
    {
        super();
    }

    public GradeNotFoundException(String message)
    {
        super(message);
    }
}
