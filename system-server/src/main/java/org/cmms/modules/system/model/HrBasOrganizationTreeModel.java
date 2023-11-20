package org.cmms.modules.system.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.cmms.modules.system.entity.HrBasOrganization;

/**
 * <p>
 * 部门表 存储树结构数据的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 */
public class HrBasOrganizationTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应SysDepart中的id字段,前端数据树中的key*/
    private String key;

    /** 对应SysDepart中的id字段,前端数据树中的value*/
    private String value;

    /** 对应depart_name字段,前端数据树中的title*/
    private String title;


    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同


    private String zzbz;
    private String zzmc;
    private String zzlb;
    private String zzjb;
    private String qybz;
    private String sjzzbz;
    private String zzjc;
    private String ywjgdm;
    private String bbqxjgdm;
    private String ywjglx;
    private String ywjgxz;
    private String ywjgbz;
    private java.math.BigDecimal pxxh;
    /**所在区域*/
    private Integer szqy;
    /**机构经营类型（1：经营型 2：服务型 3：效益型）*/
    private Integer jgjylx;

    private List<HrBasOrganizationTreeModel> children = new ArrayList<>();


    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     * @param hrorgan
     */
	public HrBasOrganizationTreeModel(HrBasOrganization hrorgan) {
		this.key = hrorgan.getZzbz();
        this.value = hrorgan.getZzbz();
        this.title = hrorgan.getZzjc();
        this.zzbz = hrorgan.getZzbz();
        this.zzmc=hrorgan.getZzmc();
        this.zzlb=hrorgan.getZzlb();
        this.zzjb=hrorgan.getZzjb();
        this.qybz=hrorgan.getQybz();
        this.sjzzbz=hrorgan.getSjzzbz();
        this.zzjc=hrorgan.getZzjc();
        this.ywjgdm=hrorgan.getYwjgdm();
        this.bbqxjgdm=hrorgan.getBbqxjgdm();
        this.ywjglx=hrorgan.getYwjglx();
        this.ywjgxz=hrorgan.getYwjgxz();
        this.ywjgbz=hrorgan.getYwjgbz();
        this.pxxh=hrorgan.getPxxh();

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


	public String getZzbz() {
        return zzbz;
    }

    public void setZzbz(String zzbz) {
        this.zzbz = zzbz;
    }

    public String getZzmc() {
        return zzmc;
    }

    public void setZzmc(String zzmc) {
        this.zzmc = zzmc;
    }

    public String getZzlb() {
        return zzlb;
    }

    public void setZzlb(String zzlb) {
        this.zzlb = zzlb;
    }

    public String getZzjb() {
        return zzjb;
    }

    public void setZzjb(String zzjb) {
        this.zzjb = zzjb;
    }

    public String getQybz() {
        return qybz;
    }

    public void setQybz(String qybz) {
        this.qybz = qybz;
    }

    public String getSjzzbz() {
        return sjzzbz;
    }

    public void setSjzzbz(String sjzzbz) {
        this.sjzzbz = sjzzbz;
    }

    public String getZzjc() {
        return zzjc;
    }

    public void setZzjc(String zzjc) {
        this.zzjc = zzjc;
    }

    public String getYwjgdm() {
        return ywjgdm;
    }

    public void setYwjgdm(String ywjgdm) {
        this.ywjgdm = ywjgdm;
    }

    public String getBbqxjgdm() {
        return bbqxjgdm;
    }

    public void setBbqxjgdm(String bbqxjgdm) {
        this.bbqxjgdm = bbqxjgdm;
    }

    public String getYwjglx() {
        return ywjglx;
    }

    public void setYwjglx(String ywjglx) {
        this.ywjglx = ywjglx;
    }

    public String getYwjgxz() {
        return ywjgxz;
    }

    public void setYwjgxz(String ywjgxz) {
        this.ywjgxz = ywjgxz;
    }

    public String getYwjgbz() {
        return ywjgbz;
    }

    public void setYwjgbz(String ywjgbz) {
        this.ywjgbz = ywjgbz;
    }

    public BigDecimal getPxxh() {
        return pxxh;
    }

    public void setPxxh(BigDecimal pxxh) {
        this.pxxh = pxxh;
    }

    public Integer getSzqy() {
        return szqy;
    }

    public void setSzqy(Integer szqy) {
        this.szqy = szqy;
    }

    public Integer getJgjylx() {
        return jgjylx;
    }

    public void setJgjylx(Integer jgjylx) {
        this.jgjylx = jgjylx;
    }



    public List<HrBasOrganizationTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<HrBasOrganizationTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }

    public HrBasOrganizationTreeModel() { }

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

        HrBasOrganizationTreeModel model = (HrBasOrganizationTreeModel) o;
        return Objects.equals(zzbz, model.zzbz) &&
                Objects.equals(zzmc, model.zzmc) &&
                Objects.equals(zzlb, model.zzlb) &&
                Objects.equals(zzjb, model.zzjb) &&
                Objects.equals(qybz, model.qybz) &&
                Objects.equals(sjzzbz, model.sjzzbz) &&
                Objects.equals(zzjc, model.zzjc) &&
                Objects.equals(ywjgdm, model.ywjgdm) &&
                Objects.equals(bbqxjgdm, model.bbqxjgdm) &&
                Objects.equals(ywjglx, model.ywjglx) &&
                Objects.equals(ywjgxz, model.ywjgxz) &&
                Objects.equals(ywjgbz, model.ywjgbz) &&
                Objects.equals(pxxh, model.pxxh) &&
                Objects.equals(children, model.children);
    }
    
    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {

        return Objects.hash(zzbz, zzmc, zzlb, zzjb, qybz,
                sjzzbz, zzjc, ywjgdm, bbqxjgdm, ywjglx, ywjgxz, ywjgbz,
                pxxh, children);
    }

}
