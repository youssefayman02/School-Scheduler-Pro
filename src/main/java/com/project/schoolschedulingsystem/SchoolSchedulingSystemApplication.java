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

			List<School> schools = List.of(
					 new School(
							"Queen",
							"Cairo",
							"Egypt",
							"01014910296",
							"Youssef",
							 LocalDate.of(1950, 5, 10),
							 SchoolType.IG,
							12L,
							 LocalTime.of(8,15,0),
							 LocalTime.of(1, 30,0),
							 3L

					),
					new School(
							"Manor",
							"Giza",
							"Egypt",
							"21221",
							"Ayman",
							LocalDate.of(1970, 8, 15),
							SchoolType.NATIONAL,
							12L,
							LocalTime.of(8,15,0),
							LocalTime.of(1, 30,0),
							3L
					)
			);

			schoolRepository.saveAll(schools);

			List<Grade> grades = List.of(
					new Grade("Grade1", 5L, schools.get(0)),
//					new Grade("Grade2", 5L, schools.get(0)),
//					new Grade("Grade3", 5L, schools.get(0)),
//					new Grade("Grade4", 5L, schools.get(0)),
//					new Grade("Grade5", 5L, schools.get(0)),
//					new Grade("Grade6", 5L, schools.get(0)),
//					new Grade("Grade7", 5L, schools.get(0)),
//					new Grade("Grade8", 5L, schools.get(0)),
//					new Grade("Grade9", 5L, schools.get(0)),
//					new Grade("Grade10", 5L, schools.get(0)),
//					new Grade("Grade11", 5L, schools.get(0)),
//					new Grade("Grade12", 5L, schools.get(0)),
					new Grade("Grade2", 5L, schools.get(1))
//					new Grade("Grade2", 5L, schools.get(1)),
//					new Grade("Grade3", 5L, schools.get(1)),
//					new Grade("Grade4", 5L, schools.get(1)),
//					new Grade("Grade5", 5L, schools.get(1)),
//					new Grade("Grade6", 5L, schools.get(1)),
//					new Grade("Grade7", 5L, schools.get(1)),
//					new Grade("Grade8", 5L, schools.get(0)),
//					new Grade("Grade9", 5L, schools.get(0)),
//					new Grade("Grade10", 5L, schools.get(0)),
//					new Grade("Grade11", 5L, schools.get(0)),
//					new Grade("Grade12", 5L, schools.get(0))

			);

			schools.get(0).addGrade(grades.get(0));
			schools.get(1).addGrade(grades.get(1));

			gradeRepository.saveAll(grades);


			List<Class> classes = List.of(
				new Class("Grade1A", 1L, 20L, 0L, grades.get(0)),
				new Class("Grade1B", 2L, 20L, 0L, grades.get(0)),
				new Class("Grade1C", 3L, 20L, 0L, grades.get(0)),
				new Class("Grade2A", 1L, 20L, 0L, grades.get(1)),
				new Class("Grade2B", 2L, 20L, 0L, grades.get(1)),
				new Class("Grade2C", 3L, 20L, 0L, grades.get(1))
			);

			grades.get(0).addClass(classes.get(0));
			grades.get(0).addClass(classes.get(1));
			grades.get(0).addClass(classes.get(2));
			grades.get(1).addClass(classes.get(0));
			grades.get(1).addClass(classes.get(1));
			grades.get(1).addClass(classes.get(2));


			classRepository.saveAll(classes);

			List<Subject> subjects = List.of(
					new Subject("Math", "101","Mathematics", grades.get(0)),
					new Subject("English", "102","Languages" ,grades.get(0)),
					new Subject("Science", "103","Applied", grades.get(0)),
					new Subject("Math", "201","Mathematics", grades.get(1)),
					new Subject("English", "202","Languages", grades.get(1)),
					new Subject("Science", "203","Applied", grades.get(1))
			);

			grades.get(0).addSubject(subjects.get(0));
			grades.get(0).addSubject(subjects.get(1));
			grades.get(0).addSubject(subjects.get(2));
			grades.get(1).addSubject(subjects.get(3));
			grades.get(1).addSubject(subjects.get(4));
			grades.get(1).addSubject(subjects.get(5));

			subjectRepository.saveAll(subjects);

			Teacher teacher = new Teacher("Mohamed", "Salama", LocalDate.of(1980, 6, 5), "October", "01021021", "mohamed@gmail.com", Gender.MALE, LocalDate.of(2000, 8, 10), schools.get(0));
			teacher.setYearsOfExperience(teacher.getYearsOfExperience());
			Teacher teacher1 = new Teacher("Ahmed", "Saleh", LocalDate.of(1990, 9, 10), "Haram", "010451021", "ahmed@gmail.com", Gender.MALE, LocalDate.of(2010, 8, 10), schools.get(0));
			teacher1.setYearsOfExperience(teacher1.getYearsOfExperience());
			Teacher teacher2 = new Teacher("Sara", "Amr", LocalDate.of(2000, 6, 5), "Giza", "015155454", "sara@gmail.com", Gender.FEMALE, LocalDate.of(1999, 5, 20), schools.get(0));
			teacher2.setYearsOfExperience(teacher2.getYearsOfExperience());
			Teacher teacher3 = new Teacher("Hossam", "Mohamed", LocalDate.of(1997, 6, 7), "Tagamo3", "014545454", "hossam@gmail.com", Gender.MALE, LocalDate.of(2002, 8, 10), schools.get(1));
			teacher3.setYearsOfExperience(teacher3.getYearsOfExperience());
			Teacher teacher4 = new Teacher("Ahmed", "Wael", LocalDate.of(2002, 6, 5), "October", "01021021", "ahmed@gmail.com", Gender.MALE, LocalDate.of(2015, 8, 10), schools.get(1));
			teacher4.setYearsOfExperience(teacher4.getYearsOfExperience());
			Teacher teacher5 = new Teacher("Nada", "Sharif", LocalDate.of(1980, 6, 5), "October", "01021021", "mohamed@gmail.com", Gender.FEMALE, LocalDate.of(1990, 8, 10), schools.get(1));
			teacher5.setYearsOfExperience(teacher5.getYearsOfExperience());

			List<Teacher> teachers = List.of(
					teacher,
					teacher1,
					teacher2,
					teacher3,
					teacher4,
					teacher5
			);

			schools.get(0).addTeacher(teachers.get(0));
			schools.get(0).addTeacher(teachers.get(1));
			schools.get(0).addTeacher(teachers.get(2));
			schools.get(1).addTeacher(teachers.get(3));
			schools.get(1).addTeacher(teachers.get(4));
			schools.get(1).addTeacher(teachers.get(5));

			teacherRepository.saveAll(teachers);

			SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(1L,1L);
			SubjectTeacherAssignmentId subjectTeacherAssignmentId1 = new SubjectTeacherAssignmentId(2L, 2L);
			SubjectTeacherAssignmentId subjectTeacherAssignmentId2 = new SubjectTeacherAssignmentId(3L, 3L);
			SubjectTeacherAssignmentId subjectTeacherAssignmentId3 = new SubjectTeacherAssignmentId(4L, 4L);
			SubjectTeacherAssignmentId subjectTeacherAssignmentId4 = new SubjectTeacherAssignmentId(5L, 5L);
			SubjectTeacherAssignmentId subjectTeacherAssignmentId5 = new SubjectTeacherAssignmentId(6L, 6L);

			List<SubjectTeacherAssignment> subjectTeacherAssignments = List.of(
					new SubjectTeacherAssignment(subjectTeacherAssignmentId, teachers.get(0), subjects.get(0)),
					new SubjectTeacherAssignment(subjectTeacherAssignmentId1, teachers.get(1), subjects.get(1)),
					new SubjectTeacherAssignment(subjectTeacherAssignmentId2, teachers.get(2), subjects.get(2)),
					new SubjectTeacherAssignment(subjectTeacherAssignmentId3, teachers.get(3), subjects.get(3)),
					new SubjectTeacherAssignment(subjectTeacherAssignmentId4, teachers.get(4), subjects.get(4)),
					new SubjectTeacherAssignment(subjectTeacherAssignmentId5, teachers.get(5), subjects.get(5))
			);

			teachers.get(0).addSubject(subjectTeacherAssignments.get(0));
			teachers.get(1).addSubject(subjectTeacherAssignments.get(1));
			teachers.get(2).addSubject(subjectTeacherAssignments.get(2));
			teachers.get(3).addSubject(subjectTeacherAssignments.get(3));
			teachers.get(4).addSubject(subjectTeacherAssignments.get(4));
			teachers.get(5).addSubject(subjectTeacherAssignments.get(5));

			subjectTeacherAssignmentRepository.saveAll(subjectTeacherAssignments);

//			LocalTime startTime = LocalTime.of(9,45, 0);
//			LocalTime endTime = LocalTime.of(10,45, 0);
//			LocalTime duration = LocalTime.of(1, 30, 0);
//			System.out.println(slotService.check(startTime, endTime, duration));
//			System.out.println(startTime.compareTo(endTime));



		};
	}

}
