import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindTop5Marks {
    static class Student {
        String name;
        int marks;
        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
        public String toString() { return name + " (" + marks + ")"; }
    }
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Priya", 92),
            new Student("Rahul", 78),
            new Student("Arjun", 95),
            new Student("Sameer", 85),
            new Student("Isha", 60),
            new Student("Karan", 88),
            new Student("Meera", 72),
            new Student("Aditya", 91),
            new Student("Saanvi", 55),
            new Student("Rohan", 97)
        );
        List<Student> top5 = students.stream()
            .sorted((s1, s2) -> Integer.compare(s2.marks, s1.marks))
            .limit(5)
            .collect(Collectors.toList());
        System.out.println("Top 5 marks: " + top5);
    }
}
