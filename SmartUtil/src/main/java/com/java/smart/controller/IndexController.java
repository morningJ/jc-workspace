package com.java.smart.controller;


import com.java.smart.annotation.Token;
import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.pojo.MenuRes;
import com.java.smart.pojo.UserRes;
import com.java.smart.services.menu.MenuService;
import com.java.smart.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Token
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public
    String index(HttpServletRequest req, Model model){

        try{
            //用户信息
            String id = (String) req.getAttribute("id");
            UserRes user = userService.getUserInfoById(id);
            model.addAttribute("code", ResultCode.susscess.getCode());
            model.addAttribute("msg", ResultCode.susscess.getMsg());
            model.addAttribute("user",user);

            //用户菜单
            List<MenuRes> menuLists= menuService.getUserMenu(user.getPermissions());
            model.addAttribute("menuList",menuLists);

        }catch (SmartException e){
            model.addAttribute("code", e.getCode());
            model.addAttribute("msg",e.getMsg());
        }

        return "/index";
    }
}
