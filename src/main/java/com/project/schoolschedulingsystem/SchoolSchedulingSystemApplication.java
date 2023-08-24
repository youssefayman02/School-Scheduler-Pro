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
										GradeRepository gradeRepository, ClassRepository classRepository)
	{
		return args -> {

			List<School> schools = List.of(
					 new School(
							"Queen",
							"Cairo",
							"Egypt",
							"01014910296",
							"Youssef",
							LocalDate.of(2010, 5, 10),
							SchoolType.IG,
							20L
					),
					new School(
							"Manor",
							"Giza",
							"Egypt",
							"21221",
							"Ayman",
							LocalDate.of(2015, 8, 15),
							SchoolType.NATIONAL,
							20L
					)
			);

			schoolRepository.saveAll(schools);

			List<Grade> grades = List.of(
					new Grade("grade1", 10L, schools.get(0)),
					new Grade("grade2", 10L, schools.get(0)),
					new Grade("grade3", 10L, schools.get(0)),
					new Grade("grade4", 10L, schools.get(0)),
					new Grade("grade5", 10L, schools.get(0)),
					new Grade("grade6", 10L, schools.get(0)),
					new Grade("grade7", 10L, schools.get(0)),
					new Grade("grade1", 10L, schools.get(1)),
					new Grade("grade2", 10L, schools.get(1)),
					new Grade("grade3", 10L, schools.get(1)),
					new Grade("grade4", 10L, schools.get(1)),
					new Grade("grade5", 10L, schools.get(1)),
					new Grade("grade6", 10L, schools.get(1)),
					new Grade("grade7", 10L, schools.get(1))
			);

			gradeRepository.saveAll(grades);

			List<Class> classes = List.of(
				new Class("Tottenham", 123L, 20L, 12L, grades.get(0)),
				new Class("City", 12L, 20L, 8L, grades.get(1)),
				new Class("Milan", 13L, 20L, 10L, grades.get(2)),
				new Class("sass", 23L, 20L, 2L, grades.get(3)),
				new Class("Ahly", 43L, 20L, 14L, grades.get(4)),
				new Class("Zamalek", 53L, 20L, 4L, grades.get(5)),
				new Class("Barcelona", 7L, 20L, 8L, grades.get(6)),
				new Class("Madrid", 32L, 20L, 9L, grades.get(7)),
				new Class("Inter", 33L, 20L, 10L, grades.get(8)),
				new Class("Arsenal", 23L, 20L, 12L, grades.get(9))
			);

			classRepository.saveAll(classes);

//			School school = new School(
//					"Manor",
//					"Giza",
//					"Egypt",
//					"21221",
//					"Ayman",
//					LocalDate.of(2015, 8, 15),
//					SchoolType.NATIONAL,
//					20L
//			);
//
//			schoolRepository.save(school);
//
//			Grade grade = new Grade("asasasa", 10L, school);
//			school.addGrade(grade);
//			gradeRepository.save(grade);
//
//			schoolRepository.findById(1L).ifPresent(
//					school1 -> {
//						System.out.println(school1);
//						System.out.println(school1.getGrades());
//					}
//			);

//			List<School> schools = schoolRepository.findAll();
//			schools.forEach(System.out::println);

//			Grade grade = new Grade("2010", 50L, school);
//
//			Class aClass = new Class("Grade 1A", 12L, 25L, 12L, grade);
//
//			school.addGrade(grade);
//			grade.addClass(aClass);
//
//			Teacher teacher = new Teacher(
//					"Mostafa",
//					"Adel",
//					LocalDate.now(),
//					"asasasas",
//					"010149202",
//					"mostafaadel@gmail.com",
//					Gender.MALE,
//					LocalDate.now(),
//					school
//			);
//
//			Subject subject = new Subject(
//				"Math",
//				"232",
//				"Mathematics"
//			);
//
//			teacherRepository.save(teacher);
//			subjectRepository.save(subject);
//
//			SubjectTeacherAssignment subjectTeacherAssignment = new SubjectTeacherAssignment(
//					new SubjectTeacherAssignmentId(teacher.getId(), subject.getId()),
//					teacher,
//					subject);
//
//			teacher.addSubject(subjectTeacherAssignment);
//			subject.addTeacher(subjectTeacherAssignment);
//
//			subjectTeacherAssignmentRepository.save(subjectTeacherAssignment);

		};
	}

}
