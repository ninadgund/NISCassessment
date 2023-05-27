import java.util.ArrayList;
import java.util.List;

public class CountOccurrences {
    public static class CharacterOccurrences {
        public char character;
        public int numberOfOccurrences;

        public CharacterOccurrences(char character, int numberOfOccurrences) {
            this.character = character;
            this.numberOfOccurrences = numberOfOccurrences;
        }
    }

    public static CharacterOccurrences[] countOccurrences(String text) {
        int[] counts = new int[128];
        for (char c : text.toCharArray()) {
            counts[c]++;
        }
        List<CharacterOccurrences> occurrencesList = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                CharacterOccurrences occurrence = new CharacterOccurrences((char) i, counts[i]);
                occurrencesList.add(occurrence);
            }
        }
        return occurrencesList.toArray(new CharacterOccurrences[0]);
    }

    public static void test0(String text)
    {
        CharacterOccurrences[] occurrences = countOccurrences(text);
        // Print output
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " result: ");
        for (CharacterOccurrences occurrence : occurrences) {
            System.out.println("Character: \'" + occurrence.character + "\', Occurrences: " + occurrence.numberOfOccurrences);
        }
    }

    public static void test1()
    {
        String text = "Typical Sentence";
        test0(text);
    }

    public static void test2()
    {
        String text = "Hello, world!";
        test0(text);
    }
    
    public static void main(String[] args) {

        test1();
        test2();
    }
}
