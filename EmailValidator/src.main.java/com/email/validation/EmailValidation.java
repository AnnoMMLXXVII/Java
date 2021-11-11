package com.email.validation;

import java.util.regex.Pattern;

import com.email.validation.shared.Constants;
import com.email.validation.shared.REGEX;
import com.email.validation.shared.SYMBOLS;

public class EmailValidation {

    private boolean isValidDomain = false;
    private boolean isValidEmail = false;
    private boolean isAlphaNumeric = false;
    private boolean isValidPrefix = false;
    private boolean isValidDomainChar = false;

    private static EmailValidation instance;

    // Singleton Design for Instantiation
    public static EmailValidation getInstance() {
        if (instance == null) {
            synchronized (EmailValidation.class) {
                if (instance == null) {
                    return new EmailValidation();
                }
            }
        }
        return instance;
    }

    // Acceptable Symbols --> @, \\. Hyphen,

    public boolean exactlyOneAt(String email) {
        int atCount = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == SYMBOLS.AT.getValue()) {
                atCount++;
                if (atCount == 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidDomain(String email) {
        char[] domain = getDomain(email);
//		printChars(domain);
        return isValidFormat(domain, false);
    }

    public boolean isValidPrefix(String prefix) {
        if (isPrefixAcceptable(prefix)) {
            int i = 0;
            while (true) {


                if (isValidPrefixChar(prefix.charAt(i))) {
                    isValidPrefix = true;
                    break;
                }
                i++;
            }
        }
        return isValidPrefix;
    }

    public boolean isValidEmail(String email) {
        return isValidPrefix(email) && isValidDomain(email);
    }

    public boolean isAlphaNumeric(char c) {
        if (c >= 48 && c <= 57) {
            return true;
        }
        return false;
    }

    public boolean isValidPrefixChar(char c) {
        if (c == 00 || c == 32) {
            return false;
        }
        boolean noSymbolsFound = true;
        if (!compareCharacters(c, SYMBOLS.DASH)) {
            if (!compareCharacters(c, SYMBOLS.PERIOD)) {
                if (!compareCharacters(c, SYMBOLS.UNDERSCORE)) {
                    noSymbolsFound = false;
                } else {
                    return true;

                }
            } else {
                return true;
            }

        } else {
            return true;
        }

        if (!noSymbolsFound) {
            return isAlphaNumeric(c);
        }
        return false;
    }

    public boolean isValidDomainChar(char c) {
        if (compareCharacters(c, SYMBOLS.DASH)) {
            return true;
        }
        if (compareCharacters(c, SYMBOLS.PERIOD)) {
            return true;
        }
        if (compareCharacters(c, SYMBOLS.UNDERSCORE)) {
            return true;
        }
        return false;
    }

    public boolean validateSecondHalfOfDomain(String domain) {
        int i = 0;
        while (true) {
            if (isValidDomainChar(domain.charAt(i))) {
                isValidDomain = true;
                break;
            }
        }
        return isValidDomain;
    }

    public boolean prefixContainsExpectedSymbols(String prefix) {
        return (prefix.contains(SYMBOLS.UNDERSCORE.getCharToString())
                || prefix.contains(SYMBOLS.PERIOD.getCharToString()) || prefix.contains(SYMBOLS.DASH.getCharToString())
                || prefix.contains(Constants.ALPHANUMERIC));

    }

    public boolean isPrefixAcceptable(String string) {
        if (string.length() == 0) {
            return false;
        }
        boolean pound = foundRegex(string, SYMBOLS.POUND.getCharToString());
        boolean ddash = foundRegex(string, REGEX.INVALID_DOUBLE_DASH.getValue());
        boolean dunder = foundRegex(string, REGEX.INVALID_DOUBLE_UNDERSCORE.getValue());
        boolean dperiod = foundRegex(string, REGEX.INVALID_DOUBLE_PERIOD.getValue());
        boolean firstLast = !firstAndLastCharsAlphaNumeric(string.charAt(0), string.charAt(string.length() - 1));
        boolean validRegex = foundRegex(string, REGEX.ALPHANUMERIC_AFTER_SYMBOL.getValue());

        if (pound || ddash || dunder || dperiod || firstLast) {
            isAlphaNumeric = false;
        } else if (validRegex) {
            isAlphaNumeric = true;
        }
        return isAlphaNumeric;
    }

    public boolean isValidFormat(char[] chars, boolean isPrefix) {
        boolean isValid = false;
        if (chars.length == 0) {
            return false;
        }
        int size = chars.length;
        if (isPrefix) {
            // Prefix Format
            isValid = true;

        } else {
            isValid = invalidPeriod(chars);
            int i = 0;
            while (i++ < chars.length - 1) {
                if (!isValidDomainChar(chars[i])) {
                    isValid = false;
                }
                ;
            }
        }
        return isValid;
    }

    private char[] getPrefix(String email) {
        return email.toCharArray();
    }

    private char[] getDomain(String email) {
        return email.toCharArray();
    }

    private boolean firstAndLastCharsAlphaNumeric(char first, char last) {
        return isAlphaNumeric(first) && isAlphaNumeric(last);
    }

    private boolean foundRegex(String string, String regex) {
        return Pattern.compile(regex).matcher(string).find();
    }

    private boolean compareCharacters(char c, SYMBOLS s) {
//		System.out.printf(" %s - %s = %s\n", c, s.getCharToString(), c - s.getValue());
        return c - s.getValue() == 0;
    }

    private boolean invalidPeriod(char[] chars) {
        boolean isValid = false;
        if (compareCharacters(chars[0], SYMBOLS.PERIOD)) {
            return isValid;
        }

        int size = chars.length;
        int i = 0;
        int j = i + 1;
        while (i++ < size - 1 && j++ < size) {
            if (compareCharacters(chars[i], SYMBOLS.PERIOD)) {
                isValid = isAlphaNumeric(chars[i - 1]) && isAlphaNumeric(chars[j]);
                if (isValid) {
                    return true;
                }
            }
        }
        return isValid;
    }

    private void printChars(char[] chars) {
        int i = 0;
        while (i++ < chars.length - 1) {
            System.out.printf("%s, ", chars[i]);
        }
        System.out.println();
    }

}