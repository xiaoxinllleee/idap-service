package org.cmms.modules.system.model;

import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;

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
public class JczbsTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private String title;

    private boolean isLeaf;


    private String id;
    private String typeName;
    private String patentId;
    private String dirType;


    private List<JczbsTreeModel> children = new ArrayList<>();


    /**
     * @param
     */
	public JczbsTreeModel(VpmaFBaseIndexType jdgl) {
		this.key = jdgl.getId();
        this.value = jdgl.getId();
        this.title = jdgl.getTypeName();
        this.id = jdgl.getId();
        this.typeName=jdgl.getTypeName();
        this.patentId=jdgl.getParentId();
        this.dirType=jdgl.getDirType();

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



    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getDirType() {
        return dirType;
    }

    public void setDirType(String dirType) {
        this.dirType = dirType;
    }



    public List<JczbsTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<JczbsTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public JczbsTreeModel() { }

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

        JczbsTreeModel model = (JczbsTreeModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(typeName, model.typeName) &&
                Objects.equals(patentId, model.patentId) &&
                Objects.equals(children, model.children) &&
                Objects.equals(dirType, model.dirType);

    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, patentId, children,dirType);
    }

}
