package by.epam.web.validator;

import by.epam.web.exception.CredentialValidException;

public class RegistrationValidator {
    private static final String USERNAME_REGEX = "\\w{4,16}";
    private static final String PASSWORD_REGEX = "\\w{8,32}";

    public boolean valid(String username,
                         String password,
                         String repeatPassword)
            throws CredentialValidException {
        if (username == null) {
            throw new CredentialValidException("Username field can not be empty");

        }
        if (password == null) {
            throw new CredentialValidException("Password field can not be empty");

        }
        if (repeatPassword == null) {
            throw new CredentialValidException("Repeat password field can not be empty");

        }
        if (!username.matches(USERNAME_REGEX)){
            throw new CredentialValidException("Invalid username. Username must contain 4-16 symbols");
        }
        if (!password.matches(PASSWORD_REGEX)){
            throw new CredentialValidException("Invalid password. Password must contain 8-32 symbols");
        }
        if (!password.equals(repeatPassword)){
            throw new CredentialValidException("Repeat password not equals to password");
        }
        return true;
    }
}
