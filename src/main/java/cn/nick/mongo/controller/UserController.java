package cn.nick.mongo.controller;

import cn.nick.mongo.entity.User;
import cn.nick.mongo.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mick on 2015/12/21.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private String getRandomName() {
        String str = RandomStringUtils.random(10, true, true);
        str += RandomUtils.nextLong(1, 100000000);
        return str;
    }

    @RequestMapping(value = "/saveRandom", method = RequestMethod.GET)
    public String saveRandom() {
        String name = getRandomName();
        User user = createUser(name);
        userService.save(user);
        return "saved a random user";
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.GET)
    public String saveAll(int quantity, int from) {
        List<User> users = new ArrayList<User>(quantity);
        for (long i = 0; i < quantity; i++) {
            String name = "mongotest" + (from + i);
            User user = createUser(name);
            users.add(user);
        }

        userService.saveAll(users);

        return "saved " + quantity + " users";
    }

    // http://localhost:8080/mongotest/user/save?name=mongotest1
    // http://localhost:8080/mongotest/user/save?name=mongotest2
    // http://localhost:8080/mongotest/user/save?name=mongotest3
    // http://localhost:8080/mongotest/user/save?name=mongotest4
    // http://localhost:8080/mongotest/user/save?name=mongotest5
    // http://localhost:8080/mongotest/user/save?name=mongotest6
    // 新增
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public User save(String name) {
        User user = createUser(name);
        userService.save(user);
        return user;
    }

    private User createUser(String name) {
        User user = new User();
        user.setAge(18);
        user.setName(name);
        user.setAddress("北京市五环五颗松旁豪华小区1栋1单元101号:" + name);
        Date now = new Date();
        user.setBirthTime(now);
        user.setCreateTime(now);
        user.setDeptId(1l);
        user.setEmail(name + "@microsoft.com");
        user.setLoginName(name);
        user.setMobile("13812345678");
        user.setPassword("password");
        user.setSex(1);
        user.setUpdateTime(now);
        return user;
    }

    // http://localhost:8080/mongotest/user/findByName?name=mongotest1
    // 查询
    @RequestMapping(value = "/findByLoginName", method = RequestMethod.GET)
    public User findByLoginName(String loginName) {
        return userService.findByLoginName(loginName);
    }

    // http://localhost:8080/mongotest/user/findByName?name=mongotest1@RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public List<User> findByName(String name, String sort, Integer page, Integer size) {
        return userService.findByName(name, sort, page, size);
    }

    // http://localhost:8080/mongotest/user/findById?id=1450684381789
    // 查询
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public User findById(String id) {
        return userService.findById(id);
    }

    // http://localhost:8080/mongotest/user/update?id=1450684381789&name=mongotest11&age=19
    // 修改
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public User update(User user) {
        userService.update(user);
        return user;
    }

    // http://localhost:8080/mongotest/user/remove?id=1450687022525
    // 删除
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(String id) {
        userService.remove(id);
        return "deleted";
    }
}
