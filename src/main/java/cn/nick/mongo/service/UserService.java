package cn.nick.mongo.service;

import cn.nick.mongo.entity.User;
import cn.nick.mongo.repository.mongo.UserMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mick on 2015/12/21.
 */
@Service
public class UserService {
    @Autowired
    private UserMongo userMongo;

    public void saveAll(List<User> users) {
        userMongo.saveAll(users);
    }

    public void save(User user) {
        this.userMongo.save(user);
    }

    public User findById(String id) {
        return this.userMongo.findById(id);
    }

    public List<User> findByName(String name, String sort, Integer page, Integer size) {
        return userMongo.findByName(name, sort, page, size);
    }

    public User findByLoginName(String loginName) {
        return userMongo.findByLoginName(loginName);
    }

    public void update(User user) {
        userMongo.update(user);
    }

    public void remove(String id) {
        userMongo.remove(id);
    }
}
