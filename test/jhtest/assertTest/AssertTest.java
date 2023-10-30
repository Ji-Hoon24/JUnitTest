package jhtest.assertTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AssertTest {

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @Before
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
    }

    @Test
    public void hasPositiveBalanceEqualTo() {
        // 준비
        // @Before에서 준비가 끝남

        // 실행
        account.deposit(50);

        // 단언
        assertThat(account.getBalance(), equalTo(50));
    }

    @Test
    public void depositIncreasesBalanceEqualTo() {
        // 준비
        int initialBalance = account.getBalance();

        // 실행
        account.deposit(100);

        // 단언
        assertThat(account.getBalance() > initialBalance, is(true));
    }

    @Test
    public void accountNameCheck() {
        assertThat(account.getName(), startsWith("an"));
    }

    @Test
    public void hamcrestTest() {
//        assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
//        assertThat(Arrays.asList(new String[] {"a"}), equalTo(Arrays.asList(new String[] {"a", "ab"})));
        assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
        assertThat(Arrays.asList(new String[] {"a"}), equalTo(Arrays.asList(new String[] {"a"})));
        assertThat(new String[] {"a", "b"}, is(new String[] {"a", "b"}));
        assertThat(Arrays.asList(new String[] {"a"}), is(Arrays.asList(new String[] {"a"})));
        assertThat(account.getName(), not(equalTo("plunderings")));
        assertThat(account.getName(), not(is("plunderings")));
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));
//        assertThat(account.getName(), equalTo("my big fat acct"));
    }

    @Test
    public void testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }

    @Test(expected = InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch() {
        account.withdraw(100);
    }

    @Test
    public void throwsWhenWithdrawingTooMuchTryCatch() {
        try {
            account.withdraw(100);
            fail();
        } catch (InsufficientFundsException expected) {
            assertThat(expected.getMessage(), equalTo("balance only 0"));
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionRule() {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");

        account.withdraw(100);
    }

    @Test
    public void readsFromTestFile() throws IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
//        throw new IOException("test");
    }
}
