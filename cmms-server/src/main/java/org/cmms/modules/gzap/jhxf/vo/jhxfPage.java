package org.cmms.modules.gzap.jhxf.vo;

import java.util.List;
import lombok.Data;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Data
public class jhxfPage {

	/**主键*/
	private String id;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
  	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**计划类型(1.月计划/2.周计划)*/
  	@Excel(name = "计划类型(1.月计划/2.周计划)", width = 15)
	private String gzlx;
	/**计划开始日期*/
  	@Excel(name = "计划开始日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划结束日期*/
  	@Excel(name = "计划结束日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhjssj;
	/**计划概述*/
  	@Excel(name = "计划概述", width = 15)
	private String gs;
	/**计划内容*/
  	@Excel(name = "计划内容", width = 15)
	private String nr;
	/**状态(1.待审核/2.已审核)*/
  	@Excel(name = "状态(1.待审核/2.已审核)", width = 15)
	private String zt;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String bz;

	@ExcelCollection(name="计划下发_客户经理")
	private List<jhxf_khjl> jhxf_khjlList;

}
