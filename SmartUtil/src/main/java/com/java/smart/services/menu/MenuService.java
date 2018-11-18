package com.java.smart.services.menu;

import com.java.smart.mybatis.mapper.MenuMapper;
import com.java.smart.mybatis.mapper.UserMapper;
import com.java.smart.mybatis.model.User;
import com.java.smart.mybatis.model.Menu;
import com.java.smart.pojo.MenuRes;
import com.java.smart.pojo.UserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {

    private static Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Resource
    MenuMapper menuMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 拉取用户菜单
     * @param menuRoleId
     * @return
     */
    public List<MenuRes> getUserMenu(String menuRoleId){

        List<MenuRes> menuResList = new ArrayList<MenuRes>();

        List<Menu> resultList = new ArrayList<Menu>();

        List<Menu>  menuList = menuMapper.getMenuListByMenuRole(menuRoleId);

        if(null == menuList){
            return menuResList;
        }

        resultList = bulidMenunTree(menuList);

        menuResList = convert(resultList);

        return menuResList;

    }


    public List<MenuRes> getMenuList(String id){
        List<MenuRes> menuList = new ArrayList<MenuRes>();
        List<Menu>  list = new ArrayList<Menu>();
        User user = userMapper.getUserInfoById(id);
        if("0".equals(user.getPermissions())){
            list = menuMapper.getAllMenuList();
        }else{
            list = menuMapper.getMenuListByMenuRole(user.getPermissions());
        }

        menuList = convert(list);


        return menuList;
    }


    /**
     * 创建菜单树
     * @param menuList
     * @return
     */
    public List<Menu> bulidMenunTree(List<Menu>  menuList){
        List<Menu> resultList = new ArrayList<Menu>();
        for(Menu menu:menuList){
            if(menu.getMenuLevel().equals("1")){
                List<Menu> childs = buildTreeGridChilds(menu, menuList);
                menu.setChildren(childs);
                resultList.add(menu);
            }
        }

        return resultList;
    }

    /**
     * 获取子节点
     * @param menu
     * @param menuList
     * @return
     */
    public List<Menu> getChilds(Menu menu, List<Menu>  menuList) {
        List<Menu> childNodes = new ArrayList<Menu>();
        for(Menu node : menuList){
            //System.out.println(node.getParentId()+"-------"+parentNode.getId());
            if(null == node.getMenuParentId() || "".equals(node.getMenuParentId())){
                continue;
            }
            if (node.getMenuParentId().equals(menu.getMenuId())) {
                childNodes.add(node);
            }
        }
        return childNodes;
    }

    /**
     * 创建树下的节点。
     * @param menu
     * @param menuList
     * @return
     */
    public List<Menu> buildTreeGridChilds(Menu menu, List<Menu>  menuList){
        List<Menu> list = new ArrayList<Menu>();
        List<Menu> childNodes = getChilds(menu,menuList);
        for(Menu childNode: childNodes){
            List<Menu> childs = buildTreeGridChilds(childNode, menuList);
            childNode.setChildren(childs);
            list.add(childNode);
        }

        return list;
    }


    /**
     * 转换输出的菜单menu
     * @param menuList
     * @return
     */
    private List<MenuRes> convert(List<Menu> menuList){
        if(menuList == null || menuList.size() == 0){
            return null;
        }

        //取上级菜单名
        Map<String,String> map = new HashMap<String, String>();
        for(Menu menu: menuList){
            map.put(menu.getMenuId(),menu.getMenuName());
        }

        List<MenuRes> menuResList = new ArrayList<MenuRes>();
        for (Menu menu:menuList){
            MenuRes menuRes = new MenuRes();
            menuRes.setMenuId(menu.getMenuId());
            menuRes.setMenuName(menu.getMenuName());
            menuRes.setMenuUrl(menu.getMenuUrl());
            menuRes.setMenuImg(menu.getMenuImg());
            menuRes.setMenuLevel(menu.getMenuLevel());
            menuRes.setMenuParentId(menu.getMenuParentId());
            menuRes.setMenuRole(menu.getMenuRole());
            menuRes.setPk1(menu.getPk1());
            menuRes.setMenuParentName(map.get(menu.getMenuParentId()));
            menuRes.setMenuLimit(menu.getMenuLimit());
            if( menu.getChildren() != null &&  menu.getChildren().size()>0){
                menuRes.setChildren(convert(menu.getChildren()));
            }
            menuResList.add(menuRes);
        }
        return menuResList;
    }
}
