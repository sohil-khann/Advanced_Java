import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Quiz {
    private List<Student> students;
    private Map<Student, Integer> scores;

    public Quiz() {
        students = new ArrayList<>();
        scores = new HashMap<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addScore(Student student, int score) throws InvalidMarksException {
        if (score < 0 || score > 100) {
            throw new InvalidMarksException("Score must be between 0 and 100.");
        }
        scores.put(student, score);
    }

    public Student getHighestScorer() {
        return scores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Student getLowestScorer() {
        return scores.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public int getHighestScore() {
        return scores.values().stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    public int getLowestScore() {
        return scores.values().stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    public void displayScores() {
        System.out.println("Quiz Scores:");
        for (Map.Entry<Student, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
        System.out.println("Highest Score: " + getHighestScore() + " by " + (getHighestScorer() != null ? getHighestScorer().getName() : "None"));
        System.out.println("Lowest Score: " + getLowestScore() + " by " + (getLowestScorer() != null ? getLowestScorer().getName() : "None"));
    }
}
