package org.cmms.modules.khlc.csgl.csml.model;


import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 部门表 存储树结构数据的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 */
public class PmaFParamMenuTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private String title;


    private boolean isLeaf;


    private String id;
    private String dirName;
    private String parentDirId;
    private String remark;


    private List<PmaFParamMenuTreeModel> children = new ArrayList<>();


    /**
     * @param
     */
	public PmaFParamMenuTreeModel(PmaFParamMenu menu) {
		this.key = menu.getId();
        this.value = menu.getId();
        this.title = menu.getDirName();
        this.remark = menu.getRemark();
        this.id = menu.getId();
        this.dirName=menu.getDirName();
        this.parentDirId=menu.getParentDirId();
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isleaf) {
         this.isLeaf = isleaf;
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


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getParentDirId() {
        return parentDirId;
    }

    public void setParentDirId(String parentDirId) {
        this.parentDirId = parentDirId;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public List<PmaFParamMenuTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<PmaFParamMenuTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public PmaFParamMenuTreeModel() { }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}

        PmaFParamMenuTreeModel model = (PmaFParamMenuTreeModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(dirName, model.dirName) &&
                Objects.equals(parentDirId, model.parentDirId) &&
                Objects.equals(remark, model.remark)&&
                Objects.equals(children, model.children);
    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, dirName, parentDirId,remark, children);
    }

}
