import org.junit.jupiter.api.Test;
import ua.training.model.dao.AdminDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Teacher;
import ua.training.model.entity.TeacherBuilder;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminDaoTest {
    @Test
    public void getTeachersTest(){
        UserDao userDao = new UserDao();
        AdminDao adminDao = new AdminDao();
        List<Teacher> users = adminDao.getTeachers();
        for(int i = 0; i < 6; i++) {
            User user = new UserBuilder()
                    .setFirstName("Test")
                    .setLastName("Test")
                    .setUserEmail("test" + i + "@test.com")
                    .setPassword("123")
                    .setRole("Teacher")
                    .build();
            users.add(new TeacherBuilder()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setEmail(user.getEmail())
                    .build());
             userDao.insert(user);
        }
        List<Teacher> teachersFromDB = adminDao.getTeachers();
        for (int i = 0; i < 6; i++){
            User user = new UserBuilder()
                    .setFirstName("Test")
                    .setLastName("Test")
                    .setUserEmail("test" + i + "@test.com")
                    .setPassword("123")
                    .setRole("Teacher")
                    .build();
            userDao.deleteUser(user);
        }
        List<String> emailes1 = new ArrayList<>();
        List<String> emailes2 = new ArrayList<>();
        for (Teacher user : users) {
            emailes1.add(user.getEmail());
        }
        for (Teacher teacher : teachersFromDB) {
                emailes2.add(teacher.getEmail());
        }
        assertArrayEquals(emailes1.toArray(), emailes2.toArray());

    }
}
