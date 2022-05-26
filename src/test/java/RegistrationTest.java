
import javax.naming.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegistrationTest {

    @Test
    public void testNewUserRegistration(){
        UserDao userDao = new UserDao();
        User user = new UserBuilder()
                .setFirstName("Test")
                .setLastName("Test")
                .setUserEmail("test@test.com")
                .setPassword("123")
                .setRole("Student")
                .build();

        boolean isUnique = !userDao.isUnique(user);
        userDao.insert(user);
        User userFromDB = userDao.findUser(user);
        userDao.deleteUser(user);

        assertTrue(isUnique);
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getEmail(), userFromDB.getEmail());
        assertEquals(user.getRole(), userFromDB.getRole());

    }
}
