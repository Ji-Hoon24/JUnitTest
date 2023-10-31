package jhtest.profile;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @Before
    public void create() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got Bonuses?");
        criteria = new Criteria();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // 준비
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        // 실행
        boolean matches = profile.matches(criteria);

        // 단언
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        // 준비
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        // 실행
        boolean matches = profile.matches(criteria);

        // 단언
        assertTrue(matches);
    }

    @Test
    public void matches() {
        // 준비 및 실행
        // must-match 항목이 맞지 않으면 false
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        // 단언
        assertFalse(profile.matches(criteria));

        // 준비 및 실행
        //don't care 항목에 대해서는 true
        criteria = new Criteria();
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        // 단언
        assertTrue(profile.matches(criteria));
    }

    int[] ids(Collection<Answer> answers) {
        return answers.stream().mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void findsAnswersBasedOnPredicate() {
        profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
        profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

        List<Answer> answers = profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class);

        assertThat(ids(answers), equalTo(new int[] {2, 3}));

        List<Answer> answersComplement = profile.find(a -> a.getQuestion().getClass() != PercentileQuestion.class);

        List<Answer> allAnswers = new ArrayList<>();
        allAnswers.addAll(answersComplement);
        allAnswers.addAll(answers);

        assertThat(ids(allAnswers), equalTo(new int[] {1,2,3}));
    }

    @Test
    public void findAnswers() {
        final int DATA_SIZE = 5000;
        for(int i=0; i < DATA_SIZE; i++)
            profile.add(new Answer(new BooleanQuestion(i, String.valueOf(i)), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(DATA_SIZE, String.valueOf(DATA_SIZE), new String[] {}), 0));

        final int NUMBER_OF_TIMES = 1000;
        long elapsedMs = run(NUMBER_OF_TIMES, () -> profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class));

        assertTrue(elapsedMs < 1000);
    }

    private long run(int times, Runnable func) {
        final long START = System.nanoTime();
        for(int i=0; i < times; i++)
            func.run();
        final long STOP = System.nanoTime();
        return (STOP - START) / 1000000;
    }
}
