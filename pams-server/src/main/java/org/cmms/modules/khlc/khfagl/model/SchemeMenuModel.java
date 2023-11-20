package org.cmms.modules.khlc.khfagl.model;


import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 封装树结构的部门的名称的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 *
 */
public class SchemeMenuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 部门名称
    private String title;

    private String remark;

    private String fjlj;

    private String fjmc;

    List<SchemeMenuModel> children = new ArrayList<>();
    
    /**
     * 将HrBasOrganizationTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public SchemeMenuModel convert(SchemeMenulTreeModel treeModel) {
        this.key = treeModel.getMenuId();
        this.value = treeModel.getMenuId();
        this.title = treeModel.getMenuName();
        this.remark= treeModel.getRemark();
        this.fjlj = treeModel.getFjlj();
        this.fjmc= treeModel.getFjmc();

        return this;
    }
    
    /**
     * 该方法为用户部门的实现类所使用
     * @param sysDepart
     * @return
     */
    public SchemeMenuModel convertByUserDepart(PmaASchemeMenu sysDepart) {
        this.key = sysDepart.getMenuId();
        this.value = sysDepart.getMenuId();
        this.title = sysDepart.getMenuName();
        this.remark = sysDepart.getRemark();
        this.fjlj = sysDepart.getFjlj();
        this.fjmc = sysDepart.getFjmc();

        return this;
    } 

    public List<SchemeMenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<SchemeMenuModel> children) {
        this.children = children;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFjlj() {
        return fjlj;
    }

    public void setFjlj(String fjlj) {
        this.fjlj = fjlj;
    }

    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }
}
