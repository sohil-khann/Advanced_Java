import java.util.*;
import java.util.stream.Collectors;

public class ResultProcessingSystem {
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Student s1 = new Student("Priya", "CSE");
        Student s2 = new Student("Rahul", "CSE");
        Student s3 = new Student("Arjun", "ECE");
        Student s4 = new Student("Anjali", "CSE");

        Subject math = new Subject("Mathematics", 100);
        Subject physics = new Subject("Physics", 100);
        Subject chem = new Subject("Chemistry", 100);

        s1.addSubject(math); s1.addSubject(physics); s1.addSubject(chem);
        s2.addSubject(math); s2.addSubject(physics); s2.addSubject(chem);
        s3.addSubject(math); s3.addSubject(physics); s3.addSubject(chem);
        s4.addSubject(math); s4.addSubject(physics); s4.addSubject(chem);

        s1.getSubjects().get(0).setMarks(95); s1.getSubjects().get(1).setMarks(88); s1.getSubjects().get(2).setMarks(92);
        s2.getSubjects().get(0).setMarks(78); s2.getSubjects().get(1).setMarks(82); s2.getSubjects().get(2).setMarks(76);
        s3.getSubjects().get(0).setMarks(65); s3.getSubjects().get(1).setMarks(70); s3.getSubjects().get(2).setMarks(68);
        s4.setAbsent(true);

        students.addAll(Arrays.asList(s1, s2, s3, s4));

        testGradeCalculation();
        generateRankList();
        System.out.println("CSE Topper: " + findDepartmentTopper("CSE").getName());
    }

    public static void testGradeCalculation() {
        System.out.println("=== Grade Tests ===");
        System.out.println("95 marks -> " + Grade.calculateGrade(95, 100));
        System.out.println("82 marks -> " + Grade.calculateGrade(82, 100));
        System.out.println("45 marks -> " + Grade.calculateGrade(45, 100));
        System.out.println("30 marks -> " + Grade.calculateGrade(30, 100));
    }

    public static void generateRankList() {
        System.out.println("\n=== Rank List ===");
        students.stream()
            .filter(s -> !s.isAbsent())
            .sorted(Comparator.comparingDouble(Student::getAverageMarks).reversed())
            .forEach(s -> System.out.printf("%s (%s): %.2f%%%n", s.getName(), s.getDepartment(), s.getAverageMarks()));
    }

    public static Student findDepartmentTopper(String dept) {
        return students.stream()
            .filter(s -> s.getDepartment().equals(dept) && !s.isAbsent())
            .max(Comparator.comparingDouble(Student::getAverageMarks))
            .orElse(null);
    }
}
