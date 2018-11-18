package com.java.smart.controller;

import com.java.smart.annotation.Token;
import com.java.smart.mybatis.model.Menu;
import com.java.smart.pojo.MenuRes;
import com.java.smart.services.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Token
    @RequestMapping(value = "/menu" ,method = RequestMethod.GET)
    public String menu(HttpServletRequest req, Model model){

        String userId = (String) req.getAttribute("id");
        List<MenuRes> menuList = menuService.getMenuList(userId);

        model.addAttribute("menuList",menuList);
        return "/menu/menu";

    }

    @Token
    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public String list(HttpServletRequest req, Model model){

        String userId = (String) req.getAttribute("id");
        List<MenuRes> menuList = menuService.getMenuList(userId);

        model.addAttribute("menuList",menuList);

        return "/menu/menu";

    }

}
