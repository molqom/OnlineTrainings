package by.epam.web.command;

public class CommandFactory {
    public static Command create(String command){
        switch (command){
            case "login" : return new LoginCommand();
            case "registration" : return new RegistrationCommand();
            case "info" : return new InfoCommand();
            case "main" : return new MainCommand();
            case "contacts" : return new ContactsCommand();
            case "news" : return new NewsCommand();
            case "teachers" : return new TeachersCommand();
            case "trainings" : return new TrainingsCommand();
            case "logout" : return new LogoutCommand();
            case "localization" : return new LocalizationCommand();
            default: throw new IllegalArgumentException();
        }
    }
}
