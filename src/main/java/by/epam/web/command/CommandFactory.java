package by.epam.web.command;

import by.epam.web.command.admin.*;
import by.epam.web.command.teacher.FeedbackCommand;
import by.epam.web.command.teacher.RateCommand;
import by.epam.web.command.teacher.StudentsCommand;
import by.epam.web.command.teacher.TeachersCommand;
import by.epam.web.command.user.*;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.service.*;

public class CommandFactory {
    public static Command create(String command){
        switch (command){
            case "login" :
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "registration" :
                return new RegistrationCommand(new RegistrationService(new DaoHelperFactory()));
            case "info" :
                return new InfoCommand();
            case "main" :
                return new MainCommand();
            case "contacts" :
                return new ContactsCommand();
            case "news" :
                return new NewsCommand();
            case "teachers" :
                return new TeachersCommand(new TrainingsService(new DaoHelperFactory()));
            case "trainings" :
                return new TrainingsCommand(new TrainingsService(new DaoHelperFactory()));
            case "logout" :
                return new LogoutCommand();
            case "localization" :
                return new LocalizationCommand();
            case "admin" :
                return new AdminCommand(new AdminService(new DaoHelperFactory()));
            case "lock" :
                return new LockCommand(new AdminService(new DaoHelperFactory()));
            case "ban" :
                return new BanCommand();
            case "subscribe" :
                return new SubscribeCommand(new SubscriptionService(new DaoHelperFactory()));
            case "subscriptions" :
                return new SubscriptionsCommand(new SubscriptionService(new DaoHelperFactory()));
            case "addCourse" :
                return new AddCourseCommand(new TrainingsService(new DaoHelperFactory()));
            case "deleteCourse" :
                return new DeleteCourseCommand(new TrainingsService(new DaoHelperFactory()));
            case "students" :
                return new StudentsCommand(new SubscriptionService(new DaoHelperFactory()));
            case "rate" :
                return new RateCommand(new SubscriptionService(new DaoHelperFactory()));
            case "feedback" :
                return new FeedbackCommand(new SubscriptionService(new DaoHelperFactory()));
            case "unsubscribe" :
                return new UnsubscribeCommand(new SubscriptionService(new DaoHelperFactory()));
             default: return new LogoutCommand();
        }
    }
}
