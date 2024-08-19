import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoKeysKeyboardTest {
    @Test
    void testMemoization() {
        final TwoKeysKeyboard testObject = new TwoKeysKeyboard();
        assertEquals(3, testObject.minSteps(3));
        assertEquals(0, testObject.minSteps(1));
    }

    @Test
    void testDP() {
        final TwoKeysKeyboard testObject = new TwoKeysKeyboard();
        assertEquals(3, testObject.dynamicProgramming(3));
        assertEquals(0, testObject.dynamicProgramming(1));
    }
}
