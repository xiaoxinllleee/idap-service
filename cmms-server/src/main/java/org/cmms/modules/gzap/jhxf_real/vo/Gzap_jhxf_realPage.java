package org.cmms.modules.gzap.jhxf_real.vo;

import java.util.List;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_real;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
public class Gzap_jhxf_realPage {

	/**主键id*/
	private String id;
	/**支行名称*/
  	@Excel(name = "支行名称", width = 15)
	private String zhmc;
	/**客户经理*/
  	@Excel(name = "客户经理", width = 15)
	private String khmc;
	/**工作类型*/
  	@Excel(name = "工作类型", width = 15)
	private String gzlx;
	/**计划开始日期*/
  	@Excel(name = "计划开始日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划完成日期*/
  	@Excel(name = "计划完成日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhwcsj;
	/**实际完成日期*/
  	@Excel(name = "实际完成日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sjwcrq;
	/**概述*/
  	@Excel(name = "概述", width = 15)
	private String gs;
	/**内容*/
  	@Excel(name = "内容", width = 15)
	private String nr;
	/**状态*/
  	@Excel(name = "状态", width = 15)
	private String zt;
	/**新增人*/
  	@Excel(name = "新增人", width = 15)
	private String xzr;
	/**新增时间*/
  	@Excel(name = "新增时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xzsj;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String bz;

	@ExcelCollection(name="客户明细")
	private List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList;

}
