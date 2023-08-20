package com.project.schoolschedulingsystem;

import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Class.ClassRepository;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Grade.GradeRepository;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.School.SchoolRepository;
import com.project.schoolschedulingsystem.School.SchoolType;
import com.project.schoolschedulingsystem.Slot.Slot;
import com.project.schoolschedulingsystem.Slot.SlotRepository;
import com.project.schoolschedulingsystem.Student.Gender;
import com.project.schoolschedulingsystem.Student.Student;
import com.project.schoolschedulingsystem.Student.StudentRepository;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Subject.SubjectRepository;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import com.project.schoolschedulingsystem.Teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class SchoolSchedulingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolSchedulingSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TeacherRepository teacherRepository, SchoolRepository schoolRepository
			, SubjectRepository subjectRepository)
	{
		return args -> {
			School school = new School(
				"Queen",
				"Cairo",
				"Egypt",
				"01014910296",
				"Youssef",
				LocalDate.of(2010, 5, 10),
				SchoolType.IG,
				50L
			);

			Grade grade = new Grade("2010", 50L, school);

			Class aClass = new Class("Grade 1A", 12L, 25L, 12L, grade);

			school.addGrade(grade);
			grade.addClass(aClass);

			Teacher teacher = new Teacher(
					"Mostafa",
					"Adel",
					LocalDate.now(),
					"asasasas",
					"010149202",
					"mostafaadel@gmail.com",
					Gender.MALE,
					LocalDate.now(),
					school
			);
		};
	}

}
