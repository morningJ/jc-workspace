package com.java.smart.controller;

import com.java.smart.annotation.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class PageController {

    @Token
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String smart(){

        return "login";
    }

    @Token
    @RequestMapping(value="/admin", method= RequestMethod.GET)
    public String user(){

        return "/admin/user";
    }

    @Token
    @RequestMapping(value="/menu", method= RequestMethod.GET)
    public String menu(){

        return "/menu/menu";
    }

    @Token
    @RequestMapping(value="/channel", method= RequestMethod.GET)
    public String channel(){

        return "/channel/channel";
    }
}
