package org.cmms.modules.system.model;

import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.entity.HrBasOrganization;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class DpJdglTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private String title;


    private boolean isLeaf;


    private String id;
    private String jdmc;
    private String sjid;
    private Integer xh;


    private List<DpJdglTreeModel> children = new ArrayList<>();


    /**
     * @param
     */
	public DpJdglTreeModel(DpJdgl jdgl) {
		this.key = jdgl.getId();
        this.value = jdgl.getId();
        this.title = jdgl.getJdmc();
        this.id = jdgl.getId();
        this.jdmc=jdgl.getJdmc();
        this.sjid=jdgl.getSjid();
        this.xh=jdgl.getXh();
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



    public String getSjid() {
        return sjid;
    }

    public void setSjid(String sjid) {
        this.sjid = sjid;
    }


    public String getJdmc() {
        return jdmc;
    }

    public void setJdmc(String jdmc) {
        this.jdmc = jdmc;
    }



    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }



    public List<DpJdglTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<DpJdglTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public DpJdglTreeModel() { }

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

        DpJdglTreeModel model = (DpJdglTreeModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(jdmc, model.jdmc) &&
                Objects.equals(sjid, model.sjid) &&
                Objects.equals(xh, model.xh) &&
                Objects.equals(children, model.children);
    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, jdmc, sjid, xh, children);
    }

}
