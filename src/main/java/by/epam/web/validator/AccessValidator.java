package by.epam.web.validator;

import by.epam.web.constant.Cmd;
import by.epam.web.enums.Role;
import by.epam.web.exception.IllegalCommandException;

public class AccessValidator {

    private final String role;

    public AccessValidator(String role) {
        this.role = role;
    }
    public boolean isValid(String command) throws IllegalCommandException {
        if (command == null) {
            return true;
        }
        switch (command) {
            case Cmd.BAN:
            case Cmd.LOGIN:
            case Cmd.REGISTRATION:
            case Cmd.REGISTRATION_PAGE:
                return role == null;
            case Cmd.MAIN:
            case Cmd.LOGOUT:
            case Cmd.TRAININGS:
                return role != null;
            case Cmd.LOCALIZATION:
                return true;
        }
        if (role == null){
            return false;
        }
        switch (command) {
            case Cmd.ADD_COURSE:
            case Cmd.DELETE_COURSE:
            case Cmd.LOCK:
            case Cmd.UNLOCK:
            case Cmd.USER_MANAGE:
                return role.equals(Role.ADMIN.toString());
            case Cmd.FEEDBACK:
            case Cmd.RATE:
            case Cmd.STUDENTS:
            case Cmd.TEACHERS:
                return role.equals(Role.TEACHER.toString());
            case Cmd.SUBSCRIPTIONS:
            case Cmd.SUBSCRIBE:
            case Cmd.UNSUBSCRIBE:
                return role.equals(Role.USER.toString());
            default:
                throw new IllegalCommandException("Invalid command");
        }
    }
}
