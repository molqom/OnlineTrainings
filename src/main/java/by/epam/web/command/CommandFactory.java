package by.epam.web.command;

import by.epam.web.command.admin.*;
import by.epam.web.command.teacher.FeedbackCommand;
import by.epam.web.command.teacher.RateCommand;
import by.epam.web.command.teacher.StudentsCommand;
import by.epam.web.command.teacher.TeachersCommand;
import by.epam.web.command.user.*;
import by.epam.web.constant.Cmd;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.service.*;

public class CommandFactory {
    public static Command create(String command){
        switch (command){
            case Cmd.LOGIN :
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case Cmd.REGISTRATION:
                return new RegistrationCommand(new RegistrationService(new DaoHelperFactory()));
            case Cmd. REGISTRATION_PAGE:
                return new RegistrationPageCommand();
            case "info" :
                return new InfoCommand();
            case Cmd.MAIN:
                return new MainCommand();
            case "contacts" :
                return new ContactsCommand();
            case "news" :
                return new NewsCommand();
            case Cmd.TEACHERS:
                return new TeachersCommand(new TrainingsService(new DaoHelperFactory()));
            case Cmd.TRAININGS:
                return new TrainingsCommand(new TrainingsService(new DaoHelperFactory()));
            case Cmd.LOGOUT:
                return new LogoutCommand();
            case Cmd.LOCALIZATION:
                return new LocalizationCommand();
            case Cmd.USER_MANAGE:
                return new UsersManageCommand(new AdminService(new DaoHelperFactory()));
            case Cmd.LOCK:
                return new LockCommand(new AdminService(new DaoHelperFactory()));
            case Cmd.UNLOCK:
                return new UnlockCommand(new AdminService(new DaoHelperFactory()));
            case Cmd.BAN:
                return new BanCommand();
            case Cmd.SUBSCRIBE:
                return new SubscribeCommand(new SubscriptionService(new DaoHelperFactory()));
            case Cmd.SUBSCRIPTIONS:
                return new SubscriptionsCommand(new SubscriptionService(new DaoHelperFactory()));
            case Cmd.ADD_COURSE:
                return new AddCourseCommand(new TrainingsService(new DaoHelperFactory()));
            case Cmd.DELETE_COURSE:
                return new DeleteCourseCommand(new TrainingsService(new DaoHelperFactory()));
            case Cmd.STUDENTS:
                return new StudentsCommand(new SubscriptionService(new DaoHelperFactory()));
            case Cmd.RATE:
                return new RateCommand(new SubscriptionService(new DaoHelperFactory()));
            case Cmd.FEEDBACK:
                return new FeedbackCommand(new SubscriptionService(new DaoHelperFactory()));
            case Cmd.UNSUBSCRIBE:
                return new UnsubscribeCommand(new SubscriptionService(new DaoHelperFactory()));
             default: return new LogoutCommand();
        }
    }
}
