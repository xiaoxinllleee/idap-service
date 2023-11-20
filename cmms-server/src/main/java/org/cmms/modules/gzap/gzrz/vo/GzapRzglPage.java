package org.cmms.modules.gzap.gzrz.vo;

import java.util.List;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@Data
public class GzapRzglPage {
	
	/**主键*/
	private String id;
	/**组织标志*/
  	@Excel(name = "组织标志", width = 15)
	private String zzbz;
	/**客户经理*/
  	@Excel(name = "客户经理", width = 15)
	private String khjl;
	/**日期*/
  	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rq;
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
	
	@ExcelCollection(name="客户明细")
	private List<GzapRzglKhgh> gzapRzglKhghList;
	@ExcelCollection(name="今天计划总结")
	private List<GzapRzglJrjhzj> gzapRzglJrjhzjList;
	
}
