package org.cmms.modules.khgl.cqjm.vo;

import java.util.List;

import org.cmms.modules.khgl.cqjm.entity.CqjmYwhywwlxx;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcxx;
import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import org.cmms.modules.khgl.cqjm.entity.CqjmFjxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 城区居民基本信息
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Data
public class CqjmJbxxPage {
	
	/**建档完整度*/
  	@Excel(name = "建档完整度", width = 15)
	private java.math.BigDecimal jdwzd;
	/**主键ID*/
	private String id;
	/**所属支行*/
  	@Excel(name = "所属支行", width = 15)
	private String sszh;
	/**所属营销单元*/
  	@Excel(name = "所属营销单元", width = 15)
	private String ssyxdy;
	/**所属社区编码*/
  	@Excel(name = "所属社区编码", width = 15)
	private String sscbm;
	/**所属客户经理*/
  	@Excel(name = "所属客户经理", width = 15)
	private String sskhjl;
	/**档案编号*/
  	@Excel(name = "档案编号", width = 15)
	private String dabh;
	/**户号编码*/
  	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**是否户主*/
  	@Excel(name = "是否户主", width = 15)
	private String sfhz;
	/**与户主关系*/
  	@Excel(name = "与户主关系", width = 15)
	private String yhzgx;
	/**客户名称*/
  	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**年龄*/
  	@Excel(name = "年龄", width = 15)
	private Integer age;
	/**性别*/
  	@Excel(name = "性别", width = 15)
	private String sex;
	/**民族*/
  	@Excel(name = "民族", width = 15)
	private String nation;
	/**文化程度*/
  	@Excel(name = "文化程度", width = 15)
	private String whcd;
	/**婚姻状况*/
  	@Excel(name = "婚姻状况", width = 15)
	private String hyzk;
	/**联系方式*/
  	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**户口性质*/
  	@Excel(name = "户口性质", width = 15)
	private String hkxz;
	/**户籍地址*/
  	@Excel(name = "户籍地址", width = 15)
	private String hjdz;
	/**住址*/
  	@Excel(name = "住址", width = 15)
	private String address;
	/**是否贫困户*/
  	@Excel(name = "是否贫困户", width = 15)
	private String sfpkh;
	/**是否低保户*/
  	@Excel(name = "是否低保户", width = 15)
	private String sfdbh;
	/**从事行业*/
  	@Excel(name = "从事行业", width = 15)
	private String cshy;
	/**工作单位*/
  	@Excel(name = "工作单位", width = 15)
	private String gzdw;
	/**单位地址*/
  	@Excel(name = "单位地址", width = 15)
	private String dwdz;
	/**是否外出务工*/
  	@Excel(name = "是否外出务工", width = 15)
	private String sfwcwg;
	/**最早返乡时间*/
  	@Excel(name = "最早返乡时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date zzfxsj;
	/**客户重要程度*/
  	@Excel(name = "客户重要程度", width = 15)
	private String khzycd;
	/**客户潜在业务*/
  	@Excel(name = "客户潜在业务", width = 15)
	private String khqzyw;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String bz;
	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**录入时间*/
  	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入人*/
  	@Excel(name = "录入人", width = 15)
	private String lrr;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String xgr;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xgsj;

    @ExcelCollection(name="城区居民房产信息")
    private List<CqjmZcxx> cqjmZcxxList;
	@ExcelCollection(name="与我行相关业务")
	private List<CqjmYwhywwlxx> cqjmYwhywwlxxList;
	@ExcelCollection(name="城区居民资产负债情况")
	private List<CqjmZcfzqk> cqjmZcfzqkList;
	@ExcelCollection(name="城区居民附件信息")
	private List<CqjmFjxx> cqjmFjxxList;
	
}
