//package by.epam.web.command;
//
//import by.epam.web.dao.DaoHelperFactory;
//import by.epam.web.data.entity.Course;
//import by.epam.web.data.entity.User;
//import by.epam.web.service.AdminService;
//import by.epam.web.service.TrainingsService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//public class AdminCommand implements Command {
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        DaoHelperFactory factory = new DaoHelperFactory();
//        AdminService service = new AdminService(factory);
//        List<User> users = service.createListOfCourses();
//        request.setAttribute("users", users);
//        return "/WEB-INF/view/admin.jsp";
//    }
//}
//
//нарисовал дизайн
//порефактрил некоторые страницы, привёл их с большего к своему дизайну нарисованному, только пока нет адаптива
//начал делать админку, но потом немного тормознул и сейчас работаю над проработкой и конспектированием
//вопросов к собеседованию
//к следующему занятию планирую все вопросы проработать. если останется время на проект, то доделаю админку