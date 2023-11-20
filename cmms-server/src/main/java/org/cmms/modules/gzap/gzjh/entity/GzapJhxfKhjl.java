package org.cmms.modules.gzap.gzjh.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Data
@TableName("GZAP_JHXF_KHJL")
public class GzapJhxfKhjl implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**完成状态(1.已完成/2.未完成)*/
    @Excel(name = "完成状态(1.已完成/2.未完成)", width = 15)
	private String wczt;
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**计划内容*/
    @Excel(name = "计划内容", width = 15)
	private String nr;
	/**外键ID*/
	private String orderId;
	/**实际完成情况*/
    @Excel(name = "实际完成情况", width = 15)
	private String sjwcqk;
	/**实际完成日期*/
	@Excel(name = "实际完成日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sjwcrq;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**计划对象*/
    @Excel(name = "计划对象", width = 15)
	private String jhdx;

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWczt() {
        return wczt;
    }

    public void setWczt(String wczt) {
        this.wczt = wczt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSjwcqk() {
        return sjwcqk;
    }

    public void setSjwcqk(String sjwcqk) {
        this.sjwcqk = sjwcqk;
    }

    public Date getSjwcrq() {
        return sjwcrq;
    }

    public void setSjwcrq(Date sjwcrq) {
        this.sjwcrq = sjwcrq;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJhdx() {
        return jhdx;
    }

    public void setJhdx(String jhdx) {
        this.jhdx = jhdx;
    }
}
