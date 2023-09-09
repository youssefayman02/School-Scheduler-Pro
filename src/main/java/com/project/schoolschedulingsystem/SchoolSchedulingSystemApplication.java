package com.project.schoolschedulingsystem;

import com.github.javafaker.Faker;
import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Class.ClassRepository;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Grade.GradeRepository;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.School.SchoolRepository;
import com.project.schoolschedulingsystem.School.SchoolType;
import com.project.schoolschedulingsystem.Slot.Slot;
import com.project.schoolschedulingsystem.Slot.SlotRepository;
import com.project.schoolschedulingsystem.Slot.SlotService;
import com.project.schoolschedulingsystem.Student.Gender;
import com.project.schoolschedulingsystem.Student.Student;
import com.project.schoolschedulingsystem.Student.StudentRepository;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Subject.SubjectRepository;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignment;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignmentId;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignmentRepository;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import com.project.schoolschedulingsystem.Teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
public class SchoolSchedulingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolSchedulingSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TeacherRepository teacherRepository, SchoolRepository schoolRepository
			, SubjectRepository subjectRepository, SubjectTeacherAssignmentRepository subjectTeacherAssignmentRepository,
										GradeRepository gradeRepository, ClassRepository classRepository, SlotRepository slotRepository,
										SlotService slotService)
	{
		return args -> {

		};
	}

}
