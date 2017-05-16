/**
 * Created by mwang on 5/16/17.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> list = new ArrayDequeSolution<>();
        for (int i = 0; i < word.length(); i++) {
            list.addLast(word.charAt(i));
        }
        return list;
    }

    public static boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        else {
            int length = word.length();
            if (word.charAt(0) != word.charAt(length - 1)) {
                return false;
            }
            return isPalindrome(word.substring(1, length - 1));
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        else {
            int length = word.length();
            if (! cc.equalChars(word.charAt(0), word.charAt(length - 1))) {
                return false;
            }
            return isPalindrome(word.substring(1, length - 1), cc);
        }
    }

}
