package org.cmms.modules.khlc.khfagl.model;

import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class SchemeMenulTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private String title;

    private String fjlj;

    private String fjmc;

    private boolean isLeaf;


    private String menuId;
    private String menuName;
    private String parentMenuId;
    private String remark;



    private Date validDate;
    private Date invalidDate;



    private List<SchemeMenulTreeModel> children = new ArrayList<>();


    /**
     * @param
     */
	public SchemeMenulTreeModel(PmaASchemeMenu menu) {
		this.key = menu.getMenuId();
        this.value = menu.getMenuId();
        this.title = menu.getMenuName();
        this.remark = menu.getRemark();
        this.fjlj = menu.getFjlj();
        this.fjmc = menu.getFjmc();
        this.menuId = menu.getMenuId();
        this.menuName=menu.getMenuName();
        this.parentMenuId=menu.getParentMenuId();
        this.validDate=menu.getValidDate();
        this.invalidDate=menu.getInvalidDate();
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


	public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String id) {
        this.menuId = id;
    }



    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }


    public List<SchemeMenulTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<SchemeMenulTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public SchemeMenulTreeModel() { }

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

        SchemeMenulTreeModel model = (SchemeMenulTreeModel) o;
        return Objects.equals(menuId, model.menuId) &&
                Objects.equals(menuName, model.menuName) &&
                Objects.equals(parentMenuId, model.parentMenuId) &&
                Objects.equals(remark, model.remark)&&
                Objects.equals(fjlj, model.fjlj)&&
                Objects.equals(fjmc, model.fjmc)&&
                Objects.equals(validDate, model.validDate)&&
                Objects.equals(invalidDate, model.invalidDate)&&
                Objects.equals(children, model.children);
    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName, parentMenuId,remark,fjlj,fjmc, children,validDate,invalidDate);
    }

}
