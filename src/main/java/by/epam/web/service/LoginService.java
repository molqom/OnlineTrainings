//package by.epam.web.service;
//
//import by.epam.web.data.entity.User;
//
//public class LoginService {
//    public boolean login(String username, String password) {
//        UserRepository repository = UserRepository.getInstance();
//        if (repository.isUsernameExist(username)){
//            User user = repository.getUserByUsername(username);
//            return user.getPassword().equals(password);
//        }
//        return false;
//    }
//}
