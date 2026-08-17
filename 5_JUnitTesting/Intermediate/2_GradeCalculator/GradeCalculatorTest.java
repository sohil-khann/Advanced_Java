import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void testGradeA() {
        Student student = new Student("Priya", 95, 90, 92);
        assertEquals("A", calculator.calculateGrade(student));
    }

    @Test
    void testGradeB() {
        Student student = new Student("Rahul", 85, 80, 82);
        assertEquals("B", calculator.calculateGrade(student));
    }

    @Test
    void testGradeC() {
        Student student = new Student("Arjun", 75, 70, 72);
        assertEquals("C", calculator.calculateGrade(student));
    }

    @Test
    void testGradeD() {
        Student student = new Student("Sameer", 65, 60, 62);
        assertEquals("D", calculator.calculateGrade(student));
    }

    @Test
    void testGradeF() {
        Student student = new Student("Isha", 30, 35, 25);
        assertEquals("F", calculator.calculateGrade(student));
    }

    @Test
    void testPercentageCalculation() {
        Student student = new Student("Dev", 80, 90, 100);
        assertEquals(90.0, calculator.calculatePercentage(student), 0.001);
    }

    @Test
    void testAllSubjectsPassed() {
        Student student = new Student("Pooja", 80, 85, 90);
        assertTrue(calculator.isPassed(student));
    }

    @Test
    void testAllSubjectsFailed() {
        Student student = new Student("Ravi", 20, 30, 10);
        assertFalse(calculator.isPassed(student));
    }

    @Test
    void testBoundaryAt90() {
        Student student = new Student("Neha", 90, 90, 90);
        assertEquals("A", calculator.calculateGrade(student));
    }

    @Test
    void testBoundaryAt80() {
        Student student = new Student("Rohan", 80, 80, 80);
        assertEquals("B", calculator.calculateGrade(student));
    }
}
