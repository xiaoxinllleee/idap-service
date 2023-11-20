package org.cmms.modules.gzap.rwxf.vo;

import java.util.List;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxfJxz;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
public class GzapRwxfJxzPage {
	
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
  	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**主键*/
	private String id;
	/**任务名称*/
  	@Excel(name = "任务名称", width = 15)
	private String rwmc;
	/**开始时间*/
  	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date kssj;
	/**结束时间*/
  	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jssj;
	/**任务类型*/
  	@Excel(name = "任务类型", width = 15)
	private String rwlx;
	/**重要级别*/
  	@Excel(name = "重要级别", width = 15)
	private String zyjb;
	/**任务值*/
  	@Excel(name = "任务值", width = 15)
	private Long rwz;
	/**完成值*/
  	@Excel(name = "完成值", width = 15)
	private Long wcz;
	/**任务属性*/
  	@Excel(name = "任务属性", width = 15)
	private String rwsx;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cjsj;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String cjr;
	/**状态*/
  	@Excel(name = "状态", width = 15)
	private String zt;
	/**任务对象*/
  	@Excel(name = "任务对象", width = 15)
	private String rwdx;
	
	@ExcelCollection(name="任务对象管理")
	private List<GzapRwxf_Rwgl> gzapRwxf_RwglList;
	
}
