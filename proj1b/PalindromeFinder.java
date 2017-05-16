/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("words.txt");

        CharacterComparator cc = new OffByN(5);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && Palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    }
} 
