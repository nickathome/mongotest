package cn.nick.mongo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mick on 2015/12/17.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public String test() {
        return "hello";
    }
}
