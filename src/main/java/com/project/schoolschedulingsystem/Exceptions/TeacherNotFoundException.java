package com.project.schoolschedulingsystem.Exceptions;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException()
    {
        super();
    }

    public TeacherNotFoundException(String message)
    {
        super(message);
    }
}
