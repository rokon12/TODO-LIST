package com.codexplo.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 1/7/13
 * Time: 1:25 AM
 */
@Controller
@RequestMapping("/api/todo")
public class TodoController {

    @RequestMapping(value = "get")
    public String index(Model uiModel){
        uiModel.addAttribute("message", "Hello, here were are going fetch all our todos");
        return "index";
    }


}
