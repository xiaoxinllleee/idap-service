package org.cmms.modules.khgl.clkhxxgl.vo;

import java.util.List;
import org.cmms.modules.khgl.clkhxxgl.entity.Clqykhjbxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 存量企业客户基本信息
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
public class ClqykhjbxxPage {

	/**ID*/
	private String id;
	/**组织标识*/
  	@Excel(name = "组织标识", width = 15)
	private String zzbz;
	/**机构代码*/
  	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**所属营销单元*/
  	@Excel(name = "所属营销单元", width = 15)
	private String ssyxdy;
	/**客户编号*/
  	@Excel(name = "客户编号", width = 15)
	private String khbh;
	/**企业名称*/
  	@Excel(name = "企业名称", width = 15)
	private String khmc;
	/**证件类型*/
  	@Excel(name = "证件类型", width = 15)
	private String zjlx;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**组织机构代码*/
  	@Excel(name = "组织机构代码", width = 15)
	private String zzjgdm;
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
	/**账户类型*/
  	@Excel(name = "账户类型", width = 15)
	private String zhlx;
	/**法人代表*/
  	@Excel(name = "法人代表", width = 15)
	private String frdb;
	/**法人联系方式*/
  	@Excel(name = "法人联系方式", width = 15)
	private String frlxfs;
	/**法人证件类型*/
  	@Excel(name = "法人证件类型", width = 15)
	private String frzjlx;
	/**法人证件号码*/
  	@Excel(name = "法人证件号码", width = 15)
	private String frzjhm;
	/**邮政编码*/
  	@Excel(name = "邮政编码", width = 15)
	private String yzbm;
	/**通讯地址*/
  	@Excel(name = "通讯地址", width = 15)
	private String txdz;
	/**联系人*/
  	@Excel(name = "联系人", width = 15)
	private String lxr;
	/**联系电话*/
  	@Excel(name = "联系电话", width = 15)
	private String lxdh;
	/**行业分类*/
  	@Excel(name = "行业分类", width = 15)
	private String hyfl;
	/**行业分类（副营）*/
  	@Excel(name = "行业分类（副营）", width = 15)
	private String hyflfy;
	/**主营业务*/
  	@Excel(name = "主营业务", width = 15)
	private String zyyw;
	/**国籍*/
  	@Excel(name = "国籍", width = 15)
	private String gj;
	/**企业规模*/
  	@Excel(name = "企业规模", width = 15)
	private String qygm;
	/**营业执照号*/
  	@Excel(name = "营业执照号", width = 15)
	private String yyzzh;
	/**注册资本金额*/
  	@Excel(name = "注册资本金额", width = 15)
	private java.math.BigDecimal zczbje;
	/**成立日期*/
  	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date clrq;
	/**税务登记编号*/
  	@Excel(name = "税务登记编号", width = 15)
	private String swdjbh;
	/**经营范围*/
  	@Excel(name = "经营范围", width = 15)
	private String jyfw;
	/**经营状况*/
  	@Excel(name = "经营状况", width = 15)
	private String jyzk;
	/**资产负债*/
  	@Excel(name = "资产负债", width = 15)
	private java.math.BigDecimal zcfz;
	/**传真*/
  	@Excel(name = "传真", width = 15)
	private String cz;
	/**ECIF 客户编号*/
  	@Excel(name = "ECIF 客户编号", width = 15)
	private String custId;
	/**客户重要程度*/
  	@Excel(name = "客户重要程度", width = 15)
	private String khzycd;
	/**客户潜在业务*/
  	@Excel(name = "客户潜在业务", width = 15)
	private String khqzyw;
	/**年营业收入（万元）*/
  	@Excel(name = "年营业收入（万元）", width = 15)
	private java.math.BigDecimal nyysr;
	/**年主营业务成本（万元）*/
  	@Excel(name = "年主营业务成本（万元）", width = 15)
	private java.math.BigDecimal nzyywcb;
	/**年工资（万元）*/
  	@Excel(name = "年工资（万元）", width = 15)
	private java.math.BigDecimal ngz;
	/**年缴纳所得税（万元）*/
  	@Excel(name = "年缴纳所得税（万元）", width = 15)
	private java.math.BigDecimal njnsds;
	/**年缴纳增值税（万元）*/
  	@Excel(name = "年缴纳增值税（万元）", width = 15)
	private java.math.BigDecimal njnzzs;
	/**年主营净利润（万元）*/
  	@Excel(name = "年主营净利润（万元）", width = 15)
	private java.math.BigDecimal nzyjlr;
	/**其他收入（万元）*/
  	@Excel(name = "其他收入（万元）", width = 15)
	private java.math.BigDecimal qtsr;
	/**其他支出（万元）*/
  	@Excel(name = "其他支出（万元）", width = 15)
	private java.math.BigDecimal qtzc;
	/**总收入（万元）*/
  	@Excel(name = "总收入（万元）", width = 15)
	private java.math.BigDecimal zsr;
	/**总支出（万元）*/
  	@Excel(name = "总支出（万元）", width = 15)
	private java.math.BigDecimal zzc;
	/**净利润（万元）*/
  	@Excel(name = "净利润（万元）", width = 15)
	private java.math.BigDecimal jlr;
	/**录入人*/
  	@Excel(name = "录入人", width = 15)
	private String lrr;
	/**录入时间*/
  	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private String lrbz;

	@ExcelCollection(name="存量企业客户资料信息")
	private List<Clkhzlxx> clqykhzlxxList;
	@ExcelCollection(name="存量企业客户业务往来信息")
	private Clkhywwlxx clqykhywwlxx;

}
