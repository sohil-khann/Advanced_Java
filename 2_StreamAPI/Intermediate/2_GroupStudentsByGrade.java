import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupStudentsByGrade {
    static class Student {
        String name;
        char grade;
        Student(String name, char grade) {
            this.name = name;
            this.grade = grade;
        }
        public String toString() { return name + " (" + grade + ")"; }
    }
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Priya", 'A'),
            new Student("Rahul", 'B'),
            new Student("Arjun", 'A'),
            new Student("Sameer", 'C'),
            new Student("Isha", 'F'),
            new Student("Karan", 'B'),
            new Student("Meera", 'A')
        );
        Map<Character, List<Student>> grouped = students.stream()
            .collect(Collectors.groupingBy(s -> s.grade));
        grouped.forEach((grade, list) ->
            System.out.println("Grade " + grade + ": " + list)
        );
    }
}
