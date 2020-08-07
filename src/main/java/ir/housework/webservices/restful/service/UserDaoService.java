package ir.housework.webservices.restful.service;

import ir.housework.webservices.restful.models.Post;
import ir.housework.webservices.restful.models.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        User ahmad = new User(1, "Ahmad",new Date());
        ahmad.addPost(new Post(1, "Ahmad_post1", new Date(),"this is Ahmad_post1"));
        ahmad.addPost(new Post(2, "Ahmad_post2", new Date(),"this is Ahmad_post1"));
        ahmad.addPost(new Post(3, "Ahmad_post3", new Date(),"this is Ahmad_post1"));
        users.add(ahmad);

        User alireza = new User(2, "Alireza",new Date());
        alireza.addPost(new Post(4, "Alireza_post1", new Date(),"this is Alireza_post1"));
        alireza.addPost(new Post(5, "Alireza_post2", new Date(),"this is Alireza_post1"));
        users.add(alireza);

        User ehsan = new User(3, "Ehsan",new Date());
        ehsan.addPost(new Post(6, "Ehsan_post1", new Date(),"this is Ehsan_post1"));
        users.add(ehsan);
    }
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public Optional<User> findOne(int id){
        for (User u: users){
            if (u.getId().equals(id))
                return Optional.ofNullable(u);
        }
        return Optional.ofNullable(null);
    }

    public Optional<User> deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId().equals(id)) {
                iterator.remove();
                return Optional.of(user);
            }
        }
        return Optional.ofNullable(null);
    }
}
