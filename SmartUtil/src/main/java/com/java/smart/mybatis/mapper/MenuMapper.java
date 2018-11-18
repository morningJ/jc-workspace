package com.java.smart.mybatis.mapper;

import com.java.smart.mybatis.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    /**
     * 根据用户角色来拉取对应的菜单
     * @param menuRoleId
     * @return
     */
    public List<Menu> getMenuListByMenuRole(String menuRoleId);


    /**
     * 拉取所有的菜单
     * 默认为拉取角色为0的菜单
     */
    public List<Menu> getAllMenuList();

    /**
     * 新增菜单，只能新增角色权限为0的菜单，然后才能新增其它角色权限的菜单
     * @param menu
     * @return
     */
    public boolean addMenu(Menu menu);

    /**
     * 删除已有的菜单
     * @param menuId,menuRoleId
     * @return
     */
    public boolean deleteMenu(String menuId, String menuRoleId);
}
