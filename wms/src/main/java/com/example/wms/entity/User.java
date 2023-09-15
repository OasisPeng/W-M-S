package com.example.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms
 * @since 2023-07-27
 */
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("账号")
    private String no;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    private Integer age;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("角色 0超级管理员 1 管理员 2普通账号")
    private Integer roleId;

    @ApiModelProperty("是否有效，Y有效，其他无效")
    @TableField("isValid")
    private String isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "User{" +
            "id = " + id +
            ", no = " + no +
            ", name = " + name +
            ", password = " + password +
            ", age = " + age +
            ", sex = " + sex +
            ", phone = " + phone +
            ", roleId = " + roleId +
            ", isValid = " + isValid +
        "}";
    }
}
