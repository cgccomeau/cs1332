import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;


public class khoiSpecial {

    private ArrayStack<Integer> arrayStack;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void arrayPush() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        for (int i = 0; i < 50; i++) {
            arrayStack.push(i);
        }
        assertEquals(50, arrayStack.size());
    }
}
