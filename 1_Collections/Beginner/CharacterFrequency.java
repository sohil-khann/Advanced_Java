import java.util.HashMap;

public class CharacterFrequency {
    public static void main(String[] args) {
        String input = " hello world\;
 HashMap<Character, Integer> frequencyMap = new HashMap<>();
 for (char c : input.toCharArray()) {
 if (c != ' ') {
 frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
 }
 }
 System.out.println(\Character Frequencies:\);
 for (char c : frequencyMap.keySet()) {
 System.out.println(c + \: \ + frequencyMap.get(c));
 }
 }
}