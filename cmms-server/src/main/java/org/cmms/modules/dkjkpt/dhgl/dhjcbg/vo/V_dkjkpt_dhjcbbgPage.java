package org.cmms.modules.dkjkpt.dhgl.dhjcbg.vo;

import java.util.List;

import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Data
public class V_dkjkpt_dhjcbbgPage {
	
	/**jgdm*/
  	@Excel(name = "jgdm", width = 15)
	private String jgdm;
	/**khmc*/
  	@Excel(name = "khmc", width = 15)
	private String khmc;
	/**zjhmId*/
  	@Excel(name = "zjhm", width = 15)
	private String zjhm;
	/**dhdkje*/
  	@Excel(name = "dhdkje", width = 15)
	private Integer dhdkje;
	/**dhdkye*/
  	@Excel(name = "dhdkye", width = 15)
	private Integer dhdkye;
	/**zxjkrq*/
  	@Excel(name = "zxjkrq", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date zxjkrq;
	/**wjid*/
  	@Excel(name = "wjid", width = 15)
	private String wjid;
	/**fjlx*/
  	@Excel(name = "fjlx", width = 15)
	private Integer fjlx;
	/**fjnf*/
  	@Excel(name = "fjnf", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fjnf;
	/**fjsjjd*/
  	@Excel(name = "fjsjjd", width = 15)
	private Integer fjsjjd;
	/**wjm*/
  	@Excel(name = "wjm", width = 15)
	private String wjm;
	
	@ExcelCollection(name="附件信息")
	private List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList;
	
}
