package com.codexplo.todo.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 1/7/13
 * Time: 12:19 AM
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public @ResponseBody String hello(){
        return "hello";
    }
}
