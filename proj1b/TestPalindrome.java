import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertEquals(false, palindrome.isPalindrome("persiflage"));
        assertEquals(true, palindrome.isPalindrome("aabbbbaa"));
    }

    @Test
    public void testIsPalindrome2() {
        assertEquals(false, palindrome.isPalindrome("aaaa", offByOne));
        assertEquals(true, palindrome.isPalindrome("aabb", offByOne));
    }
}
