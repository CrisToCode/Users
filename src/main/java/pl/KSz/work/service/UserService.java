package pl.KSz.work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.KSz.work.UserDAO;
import pl.KSz.work.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class UserService {

    private UserDAO userDAO;
    private List<User> users;
    private String filePath = "src/main/resources/db.json";

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
        try {
            userDAO.load(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = userDAO.getUsers();
    }

    public void addUser(User user) {
        for (User u : users) {
            if (u.getFirstName().equalsIgnoreCase(user.getFirstName()) && u.getLastName().equalsIgnoreCase(user.getLastName())) {
                throw new RuntimeException("User with the given firstName and lastName already exists in file");
            } else user.setKey(generateRandomKey());
        }
        for (User u : users) {
            while (u.getKey().equals(user.getKey())) {
                user.setKey(generateRandomKey());
            }
        }
        users.add(user);
        userDAO.setUsers(users);
        try {
            userDAO.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String key) {
        User user = null;
        validateKey(key);
        for (User u : users) {
            if (u.getKey().equalsIgnoreCase(key)) {
                user = u;
            }
        }
        if (user == null) throw new RuntimeException("User with the given key doesn't exist");
        users.remove(user);
        userDAO.setUsers(users);
        try {
            userDAO.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserByKey(String key) {
        validateKey(key);
        for (User u : users) {
            if (u.getKey().equals(key)) {
                return u;
            }
        }
        throw new RuntimeException("User with the given key doesn't exist");
    }

    public List<User> getUsersWithSalaryHigherThan(Integer salary) {
        List<User> usersWithSalary = new ArrayList<>();
        for (User u : users) {
            if (u.getSalary() > salary) usersWithSalary.add(u);
        }
        return usersWithSalary;
    }

    private void validateKey(String key) {
        if (key.length() != 8) throw new RuntimeException("key has more than 8 characters");
    }

    private String generateRandomKey() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;
        char[] symbols = alphanum.toCharArray();
        char[] buf = new char[8];
        Random random = new Random();
        for (int idx = 0; idx < 8; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
