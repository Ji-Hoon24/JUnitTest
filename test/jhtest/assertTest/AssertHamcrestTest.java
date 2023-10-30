package jhtest.assertTest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.IsCloseTo.*;
import static org.junit.Assert.*;

public class AssertHamcrestTest {

    @Test
    public void floatingPointCheck() {
//        assertThat(2.32 * 3, equalTo(6.96));
//        assertTrue(Math.abs((2.32 * 3)) - 6.96 < 0.0005);
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }
}
