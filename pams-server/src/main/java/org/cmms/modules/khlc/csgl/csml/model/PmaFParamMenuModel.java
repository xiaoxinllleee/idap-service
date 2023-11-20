package org.cmms.modules.khlc.csgl.csml.model;



import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;

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
public class PmaFParamMenuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 部门名称
    private String title;

    private String remark;

    List<PmaFParamMenuModel> children = new ArrayList<>();
    
    /**
     * 将HrBasOrganizationTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public PmaFParamMenuModel convert(PmaFParamMenuTreeModel treeModel) {
        this.key = treeModel.getId();
        this.value = treeModel.getId();
        this.title = treeModel.getDirName();
        this.remark= treeModel.getRemark();
        return this;
    }
    
    /**
     * 该方法为用户部门的实现类所使用
     * @param sysDepart
     * @return
     */
    public PmaFParamMenuModel convertByUserDepart(PmaFParamMenu sysDepart) {
        this.key = sysDepart.getId();
        this.value = sysDepart.getId();
        this.title = sysDepart.getDirName();
        this.remark = sysDepart.getRemark();

        return this;
    } 

    public List<PmaFParamMenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<PmaFParamMenuModel> children) {
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
}
