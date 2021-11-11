package com.custom.exception;

public class CustomExceptionApplication {

    private Login_Credentials s;

    public static void main(String... args) {
        new CustomExceptionApplication();
    }

    private CustomExceptionApplication() {
        run();
    }

    private void run() {
        try {
            scenarioOne_MeetsRequirement();
            Thread.sleep(1000);
            scenarioTwo_EmptyLogin();
            Thread.sleep(1000);
            scenarioThree_TooLongPassword();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void scenarioOne_MeetsRequirement() {
        System.out.print("Scenario 1: ");
        try {
            s = new Login_Credentials("Temp_userName", "Pum@!");
            if (s.getPasswordLength() < 3 || s.getPasswordLength() > 10) {
                throw new PasswordException("NO EXCEPTION SHALL BE THROWN HERE!");
            }
            System.out.print("PASSED!\n");
        } catch (PasswordException e) {
            e.printPasswordException();
        }
    }

    private void scenarioTwo_EmptyLogin() {
        try {
            System.out.println("Scenario 2: ");
            s = new Login_Credentials("", "");
            if (s.getPasswordLength() < 3) {
                throw new PasswordException(String.format("Password is too short: %d", s.getPasswordLength()));
            }
        } catch (PasswordException e) {
            e.printPasswordException();
        }
    }

    private void scenarioThree_TooLongPassword() {
        System.out.println("Scenario 3: ");
        try {
            s = new Login_Credentials("UserName", "A1ph@Num3ric5");
            if (s.getPasswordLength() > 10) {
                throw new PasswordException(String.format("Password is too long:\nUserName: %s\nPassword Length: (%d)",
                        s.getUserName(), s.getPasswordLength()));
            }
        } catch (PasswordException e) {
            e.printPasswordException();
        }
    }
}

class PasswordException extends RuntimeException {
    private static final long serialVersionUID = -5869374917887216031L;

    private String exceptionStr;

    public PasswordException(String exceptionStr) {
        this.exceptionStr = exceptionStr;
    }

    public void printPasswordException() {
        System.err.println(exceptionStr);
    }
}

class Login_Credentials {

    private String username;
    private String secret;

    public Login_Credentials(String username, String secret) {
        this.username = username;
        this.secret = secret;
    }

    public int getPasswordLength() {
        return secret.length();
    }

    public String getUserName() {
        return username;
    }
}