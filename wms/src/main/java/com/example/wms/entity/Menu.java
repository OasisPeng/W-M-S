package com.example.wms.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms
 * @since 2023-08-12
 */
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("菜单编码")
    private String menuCode;

    @ApiModelProperty("菜单名字")
    private String menuName;

    @ApiModelProperty("菜单级别")
    private String menuLevel;

    @ApiModelProperty("菜单的父code")
    private String menuParentCode;

    @ApiModelProperty("点击触发的函数")
    private String menuClick;

    @ApiModelProperty("权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用")
    private String menuRight;

    private String menuComponent;

    private String menuIcon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuParentCode() {
        return menuParentCode;
    }

    public void setMenuParentCode(String menuParentCode) {
        this.menuParentCode = menuParentCode;
    }

    public String getMenuClick() {
        return menuClick;
    }

    public void setMenuClick(String menuClick) {
        this.menuClick = menuClick;
    }

    public String getMenuRight() {
        return menuRight;
    }

    public void setMenuRight(String menuRight) {
        this.menuRight = menuRight;
    }

    public String getMenuComponent() {
        return menuComponent;
    }

    public void setMenuComponent(String menuComponent) {
        this.menuComponent = menuComponent;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id = " + id +
            ", menuCode = " + menuCode +
            ", menuName = " + menuName +
            ", menuLevel = " + menuLevel +
            ", menuParentCode = " + menuParentCode +
            ", menuClick = " + menuClick +
            ", menuRight = " + menuRight +
            ", menuComponent = " + menuComponent +
            ", menuIcon = " + menuIcon +
        "}";
    }
}
