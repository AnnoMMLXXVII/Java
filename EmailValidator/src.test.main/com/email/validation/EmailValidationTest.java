/**
 *
 */
package com.email.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.email.validation.shared.SYMBOLS;

/**
 * @author Haku Wei
 *
 */
class EmailValidationTest {

    private String[] splitStr;
    private String email;
    private EmailValidation instance;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        instance = EmailValidation.getInstance();
    }

    /**
     * Test method for {@link com.email.validation.EmailValidation#getInstance()}.
     */
    @Test
    void testGetInstance() {
        assertNotNull(instance);
    }

    @Test
    void assertIsValidEmailWhenAtSymbolIsPresent() {
        assertTrue(instance.isValidEmail("example@domain.com"));
    }

    @Test
    void assertIsNotValidEmailWhenAtSymbolIsMissing() {
        assertFalse(instance.isValidEmail("exampleAtdomain.com"));
    }

    @Test
    void assertIsValidPrefixWhenLengthIsGreaterThanOne() {
        email = "example@domain.com";
        String at = SYMBOLS.AT.getCharToString();
        String prefix = email.split(at)[0];
        for (int i = 0; i < prefix.length(); i++) {
            assertTrue(instance.isValidPrefixChar(prefix.charAt(i)));
        }
    }

    @Test
    void assertIsNotValidPrefixWhenLengthIsLessThanOne() {
        email = " @domain.com";
        String prefix = email.split(SYMBOLS.AT.getCharToString())[0];
        assertFalse(instance.isValidPrefixChar(prefix.charAt(0)));
    }

    @Test
    void assertIsValidPrefixWhenUnderScoreIsPresent() {
        email = "e_x_a_mpl_e@domain.com";
        String prefix = email.split(SYMBOLS.AT.getCharToString())[0];
        assertTrue(instance.prefixContainsExpectedSymbols(prefix));
    }

    @Test
    void assertIsValidPrefixWhenDashIsPresent() {
        email = "e-x-a-mpl-e@domain.com";
        String prefix = email.split(SYMBOLS.AT.getCharToString())[0];
        assertTrue(instance.prefixContainsExpectedSymbols(prefix));
    }

    @Test
    void assertIsValidPrefixWhenPeriodIsPresent() {
        email = "e.x.a.mp.l.e@domain.com";
        String prefix = email.split(SYMBOLS.AT.getCharToString())[0];
        assertTrue(instance.prefixContainsExpectedSymbols(prefix));
    }

    @Test
    void assertIsValidPrefixForTheGivenDashSequences() {
        String[] emails = {"abc-d@domain.com", "abc-3d-4d@domain.com", "Bbc-9AFD9dswe@domain.com",
                "abc-DDDDD@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertTrue(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsNotValidPrefixForTheGivenDashSequences() {
        String[] emails = {"-@domain.com", "a--d@domain.com", "abc-@domain.com", "-abc-@domain.com",
                "a#-#d@domain.com", "-#a-s#-d@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertFalse(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsValidPrefixForTheGivenPeriodSequences() {
        String[] emails = {"abc.d@domain.com", "abc.3d.4d@domain.com", "Bbc.9AFD9dswe@domain.com",
                "a. .d@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertTrue(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsNotValidPrefixForTheGivenPeriodSequences() {
        String[] emails = {".@domain.com", "a..d@domain.com", ".abc.@domain.com", "a#.#d@domain.com",
                ".#a.s#.d@domain.com", ".abc.DDDDD@domain.com", "a ..d@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertFalse(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsValidPrefixForTheGivenUnderscoreSequences() {
        String[] emails = {"abc_d@domain.com", "abc_3d_4d@domain.com", "Bbc_9AFD9dswe@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertTrue(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsNotValidPrefixForTheGivenUnderScoreSequences() {
        String[] emails = {"_@domain.com", "a__d@domain.com", "_abc_@domain.com", "a#_#d@domain.com",
                "_#a_s#_d@domain.com", ".abc_DDDDD@domain.com"};
        for (String s : emails) {
            String prefix = s.split(SYMBOLS.AT.getCharToString())[0];
            assertFalse(instance.isPrefixAcceptable(prefix));
        }
    }

    @Test
    void assertIsValidDomainWhenLengthIsGreaterThanOne() {
        email = "example@domain.com";
        String at = SYMBOLS.AT.getCharToString();
        String prefix = email.split(at)[1];
        assertTrue(instance.isValidDomain(prefix));
    }

    @Test
    void assertIsValidPrefixWithProvidedExamples() {
        assertTrue(instance.isValidPrefix("abc def"));
        assertTrue(instance.isValidPrefix("mail.cc"));
    }

    @Test
    void assertIsNOTValidPrefixWithProvidedExamples() {
        assertFalse(instance.isValidPrefix("abc..d"));
        assertFalse(instance.isValidPrefix("abc#d"));
    }

    @Test
    void assertIsValidDomainWithProvidedExamples() {
        assertTrue(instance.isValidDomain("abc-def.ghi"));
        assertTrue(instance.isValidDomain("abc_def.ghi"));

        System.out.println(isValidPrefix("abc-d@mail.cc"));
        System.out.println(isValidPrefix("abc.def@mail-archive.com"));
        System.out.println(isValidPrefix("abc@mail.org"));
        System.out.println(isValidPrefix("abc def@mail.mst.ca"));
        System.out.println(isValidDomain("abc-@mail..com"));
        System.out.println(isValidDomain("abc..d@..com"));
        System.out.println(isValidPrefix("abc#def@email.c9m"));
    }

    @Test
    void assertIsNOTValidDomainWithProvidedExamples() {
        assertFalse(instance.isValidDomain("abc..d"));
        assertFalse(instance.isValidDomain(".com"));
        assertFalse(instance.isValidDomain(".com.com"));
    }

}
