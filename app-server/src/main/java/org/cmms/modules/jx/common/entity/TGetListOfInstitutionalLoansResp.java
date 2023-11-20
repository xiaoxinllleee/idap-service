package org.cmms.modules.jx.common.entity;


import java.util.List;

/**
 * @Description //TODO
 * @Date 2020/11/8 9:59
 * @Author huangwb
 **/
public class TGetListOfInstitutionalLoansResp {
    private String zzbz;//组织标志
    private String jgdm;//机构代码
    private String jgmc;//机构名称
    private Integer qmdkhs;//期末贷款户数
    private Integer ncdkhs;//年初贷款户数
    private Integer xzdkhs;//新增贷款户数
    private Integer jzdkhs;//净增贷款户数
    private List<Item> items;

    public static class Item {
        private String zzbz;//组织标识
        private String yggh;//员工工号
        private String gwbz;//岗位标志
        private Integer ghs;//管户数
        private Integer jzghs;//净增管户数
        private Integer xzghs;//新增管户数
        private String ygxm;//员工姓名

        public String getYgxm() {
            return ygxm;
        }

        public void setYgxm(String ygxm) {
            this.ygxm = ygxm;
        }

        public String getZzbz() {
            return zzbz;
        }

        public void setZzbz(String zzbz) {
            this.zzbz = zzbz;
        }

        public String getYggh() {
            return yggh;
        }

        public void setYggh(String yggh) {
            this.yggh = yggh;
        }

        public String getGwbz() {
            return gwbz;
        }

        public void setGwbz(String gwbz) {
            this.gwbz = gwbz;
        }

        public Integer getGhs() {
            return ghs;
        }

        public void setGhs(Integer ghs) {
            this.ghs = ghs;
        }

        public Integer getJzghs() {
            return jzghs;
        }

        public void setJzghs(Integer jzghs) {
            this.jzghs = jzghs;
        }

        public Integer getXzghs() {
            return xzghs;
        }

        public void setXzghs(Integer xzghs) {
            this.xzghs = xzghs;
        }
    }

    public String getZzbz() {
        return zzbz;
    }

    public void setZzbz(String zzbz) {
        this.zzbz = zzbz;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public Integer getQmdkhs() {
        return qmdkhs;
    }

    public void setQmdkhs(Integer qmdkhs) {
        this.qmdkhs = qmdkhs;
    }

    public Integer getNcdkhs() {
        return ncdkhs;
    }

    public void setNcdkhs(Integer ncdkhs) {
        this.ncdkhs = ncdkhs;
    }

    public Integer getXzdkhs() {
        return xzdkhs;
    }

    public void setXzdkhs(Integer xzdkhs) {
        this.xzdkhs = xzdkhs;
    }

    public Integer getJzdkhs() {
        return jzdkhs;
    }

    public void setJzdkhs(Integer jzdkhs) {
        this.jzdkhs = jzdkhs;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
