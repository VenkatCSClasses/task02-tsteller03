
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    /* =====================================================
       constructorTest
       ===================================================== */

    @Test
    public void constructorValidBalances() {
        BankAccount a = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", a.getEmail());
        assertEquals(200, a.getBalance());

        BankAccount b = new BankAccount("a_b@b.org", 49.7);
        assertEquals(49.7, b.getBalance());

        BankAccount c = new BankAccount("abc@def.com", 0.01);
        assertEquals(0.01, c.getBalance());

        BankAccount d = new BankAccount("abc-123@def.com", 51.4300000000);
        assertEquals(51.43, d.getBalance());

        BankAccount e = new BankAccount("ab.cd@ef.com", 25.0000000000);
        assertEquals(25.0, e.getBalance());
    }

    @Test
    public void constructorInvalidEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("ab$cd@ef.com", 2.00));

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("abc@", 2.00));

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("@def.com", 37.41628));
    }

    @Test
    public void constructorInvalidBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("ab_cd@ef.org", 65.00000001));

        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount("abc@def.com", -65.00));
    }

    /* =====================================================
       getBalanceTest
       ===================================================== */

    @Test
    public void getBalanceValues() {
        assertEquals(0,
                new BankAccount("a@b.com", 0).getBalance());

        assertEquals(200,
                new BankAccount("a@b.com", 200).getBalance());

        assertEquals(26.3,
                new BankAccount("a@b.com", 26.3).getBalance());

        assertEquals(74.29,
                new BankAccount("a@b.com", 74.29).getBalance());

        assertEquals(25.00000,
                new BankAccount("a@b.com", 25.00000).getBalance());

        assertEquals(25.010000000,
                new BankAccount("a@b.com", 25.010000000).getBalance());

        assertEquals(25.100000000,
                new BankAccount("a@b.com", 25.100000000).getBalance());

        assertEquals(25.110000000,
                new BankAccount("a@b.com", 25.110000000).getBalance());

        assertEquals(0.01,
                new BankAccount("a@b.com", 0.01).getBalance());

        assertEquals(7982166439.46,
                new BankAccount("a@b.com", 7982166439.46).getBalance());
    }

    /* =====================================================
       isAmountValidTest
       ===================================================== */

    @Test
    public void validAmounts() {
        assertTrue(BankAccount.isAmountValid(0));
        assertTrue(BankAccount.isAmountValid(100));
        assertTrue(BankAccount.isAmountValid(48.3));
        assertTrue(BankAccount.isAmountValid(44.0));
        assertTrue(BankAccount.isAmountValid(50.00));
        assertTrue(BankAccount.isAmountValid(62.10));
        assertTrue(BankAccount.isAmountValid(77.02));

        assertTrue(BankAccount.isAmountValid(77.00000));
        assertTrue(BankAccount.isAmountValid(52.0700000000));
        assertTrue(BankAccount.isAmountValid(1.10000000));
        assertTrue(BankAccount.isAmountValid(748.290000));
        assertTrue(BankAccount.isAmountValid(1.0000000000000000));
    }

    @Test
    public void invalidAmounts() {
        assertFalse(BankAccount.isAmountValid(-1));
        assertFalse(BankAccount.isAmountValid(-91.4));
        assertFalse(BankAccount.isAmountValid(-68.23));
        assertFalse(BankAccount.isAmountValid(-68.20000));

        assertFalse(BankAccount.isAmountValid(49.09600));
        assertFalse(BankAccount.isAmountValid(124.1507));
        assertFalse(BankAccount.isAmountValid(31.0049));
        assertFalse(BankAccount.isAmountValid(1.005));
        assertFalse(BankAccount.isAmountValid(1.000000000000001));
    }

    /* =====================================================
       depositTest
       ===================================================== */

    @Test
    public void depositValidCases() {
        BankAccount acc = new BankAccount("a@b.com", 200);
        acc.deposit(37);
        assertEquals(237, acc.getBalance());

        acc.deposit(0);
        assertEquals(237, acc.getBalance());

        acc.deposit(18.2);
        assertEquals(255.2, acc.getBalance());

        acc.deposit(0.01);
        assertEquals(255.21, acc.getBalance());

        acc.deposit(4.69);
        assertEquals(259.90, acc.getBalance());

        acc.deposit(1.020000000000);
        assertEquals(260.92, acc.getBalance());

        acc.deposit(3.5000000000);
        assertEquals(264.42, acc.getBalance());

        acc.deposit(35.4800000000);
        assertEquals(299.90, acc.getBalance());
    }

    @Test
    public void depositInvalidCases() {
        BankAccount acc = new BankAccount("a@b.com", 237);

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(27.1985));

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(-0.01));

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(-112));

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(0.00000000000001));

        assertThrows(IllegalArgumentException.class,
                () -> acc.deposit(-4930.8311405));
    }

    /* =====================================================
       withdrawTest
       ===================================================== */

    @Test
    public void withdrawValidCases() throws Exception {
        BankAccount acc = new BankAccount("a@b.com", 200);

        acc.withdraw(100);
        assertEquals(100, acc.getBalance());

        acc.withdraw(64.1);
        assertEquals(35.9, acc.getBalance());

        acc.withdraw(0.01);
        assertEquals(35.89, acc.getBalance());

        acc.withdraw(1.02000000000);
        assertEquals(34.87, acc.getBalance());

        acc.withdraw(3.5000000000);
        assertEquals(31.37, acc.getBalance());

        acc.withdraw(31.370000000);
        assertEquals(0, acc.getBalance());
    }

    @Test
    public void withdrawInvalidCases() {
        BankAccount acc = new BankAccount("a@b.com", 100);

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(27.1984));

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(-0.01));

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(-18.49));

        assertThrows(IllegalArgumentException.class,
                () -> acc.withdraw(31.360000000001));
    }

    @Test
    public void withdrawInsufficientFunds() {
        BankAccount acc = new BankAccount("a@b.com", 31.37);

        assertThrows(InsufficientFundsException.class,
                () -> acc.withdraw(31.38));

        assertThrows(InsufficientFundsException.class,
                () -> acc.withdraw(628));

        BankAccount zero = new BankAccount("a@b.com", 0);

        assertThrows(InsufficientFundsException.class,
                () -> zero.withdraw(0.01));
    }

    /* =====================================================
       transferTest
       ===================================================== */

    @Test
    public void transferValidCases() throws Exception {
        BankAccount from = new BankAccount("a@b.com", 36.00);
        BankAccount to = new BankAccount("c@d.com", 55.50);

        from.transfer(to, 6);
        assertEquals(30.00, from.getBalance());
        assertEquals(61.50, to.getBalance());

        from.transfer(to, 0);
        assertEquals(30.00, from.getBalance());

        from.transfer(to, 0.1);
        assertEquals(29.90, from.getBalance());

        from.transfer(to, 2.9);
        assertEquals(27.00, from.getBalance());

        from.transfer(to, 0.01);
        assertEquals(26.99, from.getBalance());

        from.transfer(to, 3.99);
        assertEquals(23.00, from.getBalance());
    }

    @Test
    public void transferInvalidCases() {
        BankAccount from = new BankAccount("a@b.com", 23.00);
        BankAccount to = new BankAccount("c@d.com", 68.50);

        assertThrows(IllegalArgumentException.class,
                () -> from.transfer(to, 21.94183172423941));

        assertThrows(IllegalArgumentException.class,
                () -> from.transfer(to, 0.0100000001));

        assertThrows(IllegalArgumentException.class,
                () -> from.transfer(to, -0.01));

        assertThrows(IllegalArgumentException.class,
                () -> from.transfer(to, -24.3));
    }

    @Test
    public void transferInsufficientFunds() {
        BankAccount from = new BankAccount("a@b.com", 30.00);
        BankAccount to = new BankAccount("c@d.com", 61.50);

        assertThrows(InsufficientFundsException.class,
                () -> from.transfer(to, 30.01));

        BankAccount zero = new BankAccount("a@b.com", 0);

        assertThrows(InsufficientFundsException.class,
                () -> zero.transfer(to, 2.14));
    }

    /* =====================================================
       isEmailValidTest
       ===================================================== */

    @Test
    public void validEmails() {
        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("a@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccount.isEmailValid("a-bc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("ab.cde_f@mail.com"));
        assertTrue(BankAccount.isEmailValid("ab.cd-e_f@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
    }

    @Test
    public void invalidEmails() {
        assertFalse(BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid(null));
        assertFalse(BankAccount.isEmailValid("-abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc.@mail.com"));
        assertFalse(BankAccount.isEmailValid("_abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc_@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcde--f@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab__cdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("a!bcdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcd$ef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc^def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcde+f@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcd=ef@mail.com"));
        assertFalse(BankAccount.isEmailValid("a~bcdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcde?f@mail.com"));
        assertFalse(BankAccount.isEmailValid("a'bcdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcde@f@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab%cdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc*def@mail.com"));
        assertFalse(BankAccount.isEmailValid("@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse(BankAccount.isEmailValid("abc@@mail.com"));
        assertFalse(BankAccount.isEmailValid(" abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab c@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@mail .com"));
        assertFalse(BankAccount.isEmailValid("a b c @ m a i l . c o m"));
        assertFalse(BankAccount.isEmailValid(" abc@mail.co m"));
    }
}
