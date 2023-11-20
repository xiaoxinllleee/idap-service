package org.cmms.modules.system.model;


import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.entity.HrBasOrganization;

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
public class DpJdglModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 部门名称
    private String title;
    
    List<DpJdglModel> children = new ArrayList<>();
    
    /**
     * 将HrBasOrganizationTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public DpJdglModel convert(DpJdglTreeModel treeModel) {
        this.key = treeModel.getId();
        this.value = treeModel.getId();
        this.title = treeModel.getJdmc();
        return this;
    }
    
    /**
     * 该方法为用户部门的实现类所使用
     * @param sysDepart
     * @return
     */
    public DpJdglModel convertByUserDepart(DpJdgl sysDepart) {
        this.key = sysDepart.getId();
        this.value = sysDepart.getId();
        this.title = sysDepart.getJdmc();
        return this;
    } 

    public List<DpJdglModel> getChildren() {
        return children;
    }

    public void setChildren(List<DpJdglModel> children) {
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
}
