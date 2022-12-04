public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        int len = word.length();
        for (int i = 0; i < len; i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word) {
        int start = 0, end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }



    public boolean isPalindrome(String word, CharacterComparator cc) {
        int start = 0, end = word.length() - 1;
        while (start < end) {
            if (!cc.equalChars(word.charAt(start), word.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
