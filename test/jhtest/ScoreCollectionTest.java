package jhtest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class ScoreCollectionTest {

   @Test
   public void answersArithmeticMeanOfTwoNumbers() {
      // 준비
      ScoreCollection collection = new ScoreCollection();
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

}
