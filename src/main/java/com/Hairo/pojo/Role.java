package com.Hairo.pojo;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/6 16:56
 * 作用描述:
 * 用户角色
 */

public class Role {


    private Integer role_id;
    private String roleName;
    private String roleDescribe;

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", roleName='" + roleName + '\'' +
                ", roleDescribe='" + roleDescribe + '\'' +
                '}';
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }
}
