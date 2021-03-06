package com.java.smart.mybatis.model;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

    /**
     * 主键id
     */
    private String pk1;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 菜单名字
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单图片
     */
    private String menuImg;

    /**
     * 菜单等级
     */
    private String menuLevel;

    /**
     * 上级菜单编号
     */
    private String menuParentId;

    /**
     * 用户级别
     */
    private String menuRole;

    /**
     * 用户限制
     */
    private String menuLimit;

    @Transient
    private List<Menu> children;

    public String getPk1() {
        return pk1;
    }

    public void setPk1(String pk1) {
        this.pk1 = pk1;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuRole() {
        return menuRole;
    }

    public void setMenuRole(String menuRole) {
        this.menuRole = menuRole;
    }

    public String getMenuLimit() {
        return menuLimit;
    }

    public void setMenuLimit(String menuLimit) {
        this.menuLimit = menuLimit;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
