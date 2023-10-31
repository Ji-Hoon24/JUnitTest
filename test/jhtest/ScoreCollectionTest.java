package jhtest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class ScoreCollectionTest {
   ScoreCollection collection;
   @Before
   public void scoreCollection() {
      collection = new ScoreCollection();
   }
   @Test
   public void answersArithmeticMeanOfTwoNumbers() {
      // 준비
      collection.add(() -> 5);
      collection.add(() -> 7);

      // 실행
      int actualResult = collection.arithmeticMean();

      // 단언
      assertThat(actualResult, equalTo(6));
   }

   @Test
   public void test() {
//      fail("Not yet implemented");
   }

   @Test(expected = IllegalArgumentException.class)
   public void throwsExceptionWhenAddingNull() {
      collection.add(null);
   }

   @Test
   public void answersZeroWhenNoElementsAdded() {
      assertThat(collection.arithmeticMean(), equalTo(0));
   }

   @Test
   public void dealsWithIntegerOverflow() {
      collection.add(() -> Integer.MAX_VALUE);
      collection.add(() -> 1);

      assertThat(collection.arithmeticMean(), equalTo(1073741824));
   }

   @Test
   public void doesNotProperlyHandleIntegerOverflow() {
      collection.add(() -> Integer.MAX_VALUE);
      collection.add(() -> 1);

//      assertTrue(collection.arithmeticMean() < 0);
      assertFalse(collection.arithmeticMean() < 0);
   }
}
