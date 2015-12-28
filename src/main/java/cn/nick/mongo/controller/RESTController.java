package cn.nick.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Set;

/**
 * Created by mick on 2015/12/24.
 */
@RestController
@RequestMapping("/rest")
public class RESTController {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(method = RequestMethod.GET)
    public Set<RequestMappingInfo> getAllEndpoints() {
        return requestMappingHandlerMapping.getHandlerMethods().keySet();
    }
}
