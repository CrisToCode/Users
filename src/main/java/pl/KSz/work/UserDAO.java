package pl.KSz.work;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.KSz.work.model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private List<User> users;

    UserDAO() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    void insertUser(User user) {
        users.add(user);
    }

    static String getJSON(String path) throws IOException {
        String contents = "";
        String line;

        FileReader fileReader = new FileReader(path);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            contents = contents.concat(line);
        }

        bufferedReader.close();
        return contents;
    }

   public  void load(String filePath) throws IOException {
        String json = getJSON(filePath);
        this.users = new ObjectMapper().readValue(json, new TypeReference<List<User>>(){});
    }

    public void save(String filePath) throws IOException {
        new ObjectMapper().writeValue(new File(filePath), users);
    }
}
