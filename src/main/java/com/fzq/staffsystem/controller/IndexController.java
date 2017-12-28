package com.fzq.staffsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"})
    //@ResponseBody
    public String index(Model model) {
        return "homepage";
    }
}
