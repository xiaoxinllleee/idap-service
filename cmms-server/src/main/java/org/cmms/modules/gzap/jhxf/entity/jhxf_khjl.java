package org.cmms.modules.gzap.jhxf.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 计划下发_客户经理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Data
@TableName("GZAP_JHXF_KHJL")
public class jhxf_khjl implements Serializable {
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
	@Dict(dicCode = "gzap_wczt")
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
	/**实际完成时间*/
	@Excel(name = "实际完成时间", width = 15, format = "yyyy-MM-dd")
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
}
