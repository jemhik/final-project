import org.junit.jupiter.api.Test;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    public void adminLoginTest(){
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setUserEmail("user1@gmail.com")
                .setPassword("123")
                .build();
        assertEquals("Admin", userDao.validate(user));
    }

    @Test
    public void teacherLoginTest(){
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setUserEmail("user2@gmail.com")
                .setPassword("123")
                .build();
        assertEquals("Teacher", userDao.validate(user));
    }

    @Test
    public void studentLoginTest(){
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setUserEmail("user3@gmail.com")
                .setPassword("123")
                .build();
        assertEquals("Student", userDao.validate(user));
    }

    @Test
    public void userNotFound(){
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setUserEmail("user11234@gmail.com")
                .setPassword("123")
                .build();
        assertEquals("User not registered in system", userDao.validate(user));
    }
}
