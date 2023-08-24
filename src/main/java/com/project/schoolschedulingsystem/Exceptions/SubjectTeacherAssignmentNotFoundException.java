package com.project.schoolschedulingsystem.Exceptions;

public class SubjectTeacherAssignmentNotFoundException extends RuntimeException{

    public SubjectTeacherAssignmentNotFoundException()
    {
        super();
    }

    public SubjectTeacherAssignmentNotFoundException(String message)
    {
        super(message);
    }
}
