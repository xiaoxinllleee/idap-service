package org.cmms.modules.khgl.clkhxxgl.vo;

import java.util.List;

import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Data
public class ClgrkhjbxxPage {

	/**手机号码*/
  	@Excel(name = "手机号码", width = 15)
	private String sjhm;
	/**固定电话*/
  	@Excel(name = "固定电话", width = 15)
	private String gddh;
	/**电子邮箱*/
  	@Excel(name = "电子邮箱", width = 15)
	private String dzyx;
	/**住址*/
  	@Excel(name = "住址", width = 15)
	private String zz;
	/**最高学历*/
  	@Excel(name = "最高学历", width = 15)
	private String zgxl;
	/**最高学位*/
  	@Excel(name = "最高学位", width = 15)
	private String zgxw;
	/**行业分类*/
  	@Excel(name = "行业分类", width = 15)
	private String hyfl;
	/**职业*/
  	@Excel(name = "职业", width = 15)
	private String zy;
	/**邮编*/
  	@Excel(name = "邮编", width = 15)
	private String yb;
	/**个人性格*/
  	@Excel(name = "个人性格", width = 15)
	private String grxg;
	/**兴趣爱好*/
  	@Excel(name = "兴趣爱好", width = 15)
	private String xqah;
	/**客户重要程度*/
  	@Excel(name = "客户重要程度", width = 15)
	private String khzycd;
	/**客户潜在业务*/
  	@Excel(name = "客户潜在业务", width = 15)
	private String khqzyw;
	/**录入人*/
  	@Excel(name = "录入人", width = 15)
	private String lrr;
	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**录入时间*/
  	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**ID*/
	private String id;
	/**ECIF 客户编号*/
  	@Excel(name = "ECIF 客户编号", width = 15)
	private String custId;
	/**组织标识*/
  	@Excel(name = "组织标识", width = 15)
	private String zzbz;
	/**机构代码*/
  	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**客户编号*/
  	@Excel(name = "客户编号", width = 15)
	private String khbh;
	/**所属营销单元*/
  	@Excel(name = "所属营销单元", width = 15)
	private String ssyxdy;
	/**客户姓名*/
  	@Excel(name = "客户姓名", width = 15)
	private String khmc;
	/**证件类型*/
  	@Excel(name = "证件类型", width = 15)
	private String zjlx;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户类型*/
  	@Excel(name = "客户类型", width = 15)
	private String custType;
	/**客户类型1*/
  	@Excel(name = "客户类型1", width = 15)
	private String custType1;
	/**客户类型2*/
  	@Excel(name = "客户类型2", width = 15)
	private String custType2;
	/**客户类型3*/
  	@Excel(name = "客户类型3", width = 15)
	private String custType3;
	/**性别*/
  	@Excel(name = "性别", width = 15)
	private String xb;
	/**民族*/
  	@Excel(name = "民族", width = 15)
	private String mz;
	/**出生日期*/
  	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date csrq;
	/**工作单位名称*/
  	@Excel(name = "工作单位名称", width = 15)
	private String gzdwmc;
	/**单位地址*/
  	@Excel(name = "单位地址", width = 15)
	private String dwdz;
	/**单位电话*/
  	@Excel(name = "单位电话", width = 15)
	private String dwdh;
	/**婚姻状况*/
  	@Excel(name = "婚姻状况", width = 15)
	private String hyzk;

	@ExcelCollection(name="存量个人客户家庭成员")
	private List<Clgrkhjtcy> clgrkhjtcyList;
	@ExcelCollection(name="存量个人客户资料信息")
	private List<Clkhzlxx> clgrkhzlxxList;
	@ExcelCollection(name="存量个人客户业务往来信息")
	private Clkhywwlxx clgrkhywwlxx;

}
