import java.util.*;
import java.util.stream.Collectors;

public class LearningManagementSystem {
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Instructor> instructors = new ArrayList<>();

    public static void main(String[] args) {
        Instructor i1 = new Instructor("Dr. Sharma", "Computer Science");
        Instructor i2 = new Instructor("Dr. Patil", "Data Science");
        instructors.addAll(Arrays.asList(i1, i2));

        Course c1 = new Course("Java Programming", String.valueOf(i1.getInstructorId()));
        Course c2 = new Course("Data Science", String.valueOf(i2.getInstructorId()));
        courses.addAll(Arrays.asList(c1, c2));

        Student s1 = new Student("Priya", "priya@example.com");
        Student s2 = new Student("Rahul", "rahul@example.com");
        Student s3 = new Student("Arjun", "arjun@example.com");
        students.addAll(Arrays.asList(s1, s2, s3));

        s1.enroll(c1.getCourseId());
        s1.enroll(c2.getCourseId());
        s2.enroll(c1.getCourseId());
        s3.enroll(c2.getCourseId());

        s1.rateCourse(c1.getCourseId(), 5.0);
        s2.rateCourse(c1.getCourseId(), 4.0);
        s3.rateCourse(c2.getCourseId(), 4.5);

        validateRegistrations();
        generateCompletionReport();
        System.out.println("Top rated course: " + findTopRatedCourse());
    }

    public static void validateRegistrations() {
        students.stream().forEach(s -> {
            long valid = s.getEnrolledCourses().stream().filter(cid -> courses.stream().anyMatch(c -> c.getCourseId() == cid)).count();
            System.out.println(s.getName() + " enrolled in " + valid + " valid courses");
        });
    }

    public static void generateCompletionReport() {
        System.out.println("\n=== Completion Report ===");
        courses.stream().forEach(c -> {
            System.out.printf("%s: %d students enrolled, Rating: %.1f%n",
                c.getTitle(), c.getEnrolledStudents().size(), c.getAverageRating());
        });
    }

    public static String findTopRatedCourse() {
        return courses.stream()
            .max(Comparator.comparingDouble(Course::getAverageRating))
            .map(Course::getTitle)
            .orElse("None");
    }
}
