package org.cmms.modules.system.model;


import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;
import org.cmms.modules.system.entity.DpJdgl;

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
public class JczbModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String key;

    // 主键ID
    private String value;

    // 部门名称
    private String title;


    private String dirType;

    
    List<JczbModel> children = new ArrayList<>();
    
    /**
     * 将HrBasOrganizationTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public JczbModel convert(JczbsTreeModel treeModel) {
        this.key = treeModel.getId();
        this.value = treeModel.getId();
        this.title = treeModel.getTypeName();
        return this;
    }
    


    public List<JczbModel> getChildren() {
        return children;
    }

    public void setChildren(List<JczbModel> children) {
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

    public String getDirType() {
        return dirType;
    }

    public void setDirType(String dirType) {
        this.dirType = dirType;
    }
}
