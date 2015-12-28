package cn.nick.mongo.repository.mongo;

import cn.nick.mongo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by mick on 2015/12/17.
 */
@Repository
public class UserMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveAll(List<User> users) {
        mongoTemplate.insertAll(users);
    }

    public void save(User user) {
        mongoTemplate.save(user);
    }

    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    // mongodb的模糊查询效率非常低，甚至不如mysql，不建议使用
    // 模糊查询应结合Lucence/Sphinx，效率会高一些。或者做分词
    public List<User> findByName(String name, String sort, Integer page, Integer size) {
        String regex = MessageFormat.format("^.*{0}.*$", name);

        Query query = new Query(Criteria.where("name").regex(regex));

        // 排序
        if (StringUtils.isNotEmpty(sort)) {
            query.with(new Sort(Sort.Direction.ASC, sort));
        }

        // 分页
        if (page != null && size != null) {
            query.with(new PageRequest(page, size));
        }

        return mongoTemplate.find(query, User.class);
    }

    public User findByLoginName(String loginName) {
        Query query = new Query(Criteria.where("loginName").is(loginName));

        return mongoTemplate.findOne(query, User.class);
    }

    public void update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));

        mongoTemplate.updateFirst(query, new Update().set("name", user.getName()).set("age", user.getAge()), User.class);
    }

    public void remove(String id) {
        Query query = new Query(Criteria.where("id").is(id));

        mongoTemplate.remove(query, User.class);
    }
}
