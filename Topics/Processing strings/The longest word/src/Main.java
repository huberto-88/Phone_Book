import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String[] words = new Scanner(System.in).nextLine().split("\\s+");
        int theLongestLength = 0;
        String theLongestWord = "";

        for (String word : words) {
            if (word.length() > theLongestLength) {
                theLongestLength = word.length();
                theLongestWord = word;
            }
        }
        System.out.println(theLongestWord);
    }
}