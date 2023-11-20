package org.cmms.modules.xdgl.grdkgl.vo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
public class GrdkglPage {

	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
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
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
	private String sskhjl;
	/**所属客户经理*/
	@Excel(name = "是否授信对象", width = 15)
	private String sfsxdx;
	/**hmcId*/
	private String hmcId;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
	private String ssyxdy;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
	private String sfhz;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	private String khlx;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**性别*/
	@Excel(name = "性别", width = 15)
	private String xb;
	/**ID*/
	private String id;
	/**客户名称*/
  	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户项目*/
  	@Excel(name = "客户项目", width = 15)
	private String khxm;
	/**从事行业*/
  	@Excel(name = "从事行业", width = 15)
	private String cshy;
	/**客户品行，1良好，2较好，3一般，4差*/
  	@Excel(name = "客户品行，1良好，2较好，3一般，4差", width = 15)
	private String khpx;

	/**民族*/
	@Excel(name = "民族", width = 15)
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
	private String hyzk;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**出生年月*/
	@Excel(name = "出生年月", width = 15)
	private String csny;
	/**从事职业*/
	private String cszy;
	/**户籍地址*/
	private String hjdz;
	/**手机号码*/
	private String sjhm;


	/**对外担保*/
	private String dwdb;
	/**担保对象*/
	private String dbdx;
	/**其他用信*/
	private String qtyx;
	/**担保备注*/
	private String dbbz;
	/**共同借款人*/
	private String gtjkr;
	/**共同借款人手机*/
	private String gtjkrsj;
	/**共同借款人证件号码*/
	private String gtjkrzjhm;
	/**指定签收人*/
	private String zdqsr;
	/**签收人手机*/
	private String qsrsj;
	/**签收人传真*/
	private String qsrcz;
	/**签收人邮箱*/
	private String qsryx;
	/**签收人微信*/
	private String qsrwx;
	/**其他签收方式*/
	private String qtqsfs;
	/**是否有失信人或被执行人记录，1是，2否*/
	private String sfsxrhbzxr;
	/**网站查询是否有风险，1是，2否*/
	private String sfyfxxx;
	/**失信人或被执行人说明*/
	private String sfrqksm;
	/**风险信息说明*/
	private String fxxxqksm;
	/**固定资产合计*/
	private java.math.BigDecimal gdzcHj;
	/**现金和存款*/
	private java.math.BigDecimal xjck;
	/**原材料*/
	private java.math.BigDecimal ycl;
	/**半成品*/
	private java.math.BigDecimal bcp;
	/**产成品*/
	private java.math.BigDecimal ccp;
	/**应收账款*/
	private java.math.BigDecimal yszk;
	/**预付账款*/
	private java.math.BigDecimal yfzk;
	/**流动资产合计*/
	private java.math.BigDecimal ldzcHj;
	/**资产总额合计*/
	private java.math.BigDecimal zczeHj;
	/**负债总额合计*/
	private java.math.BigDecimal fzzeHj;
	/**其他借款*/
	private java.math.BigDecimal qtjk;
	/**应付账款*/
	private java.math.BigDecimal fzyf;
	/**预收账款*/
	private java.math.BigDecimal fzys;
	/**净资产合计*/
	private java.math.BigDecimal jzcHj;
	/**去年产值*/
	private java.math.BigDecimal qncz;
	/**销售额*/
	private java.math.BigDecimal xse;
	/**税金*/
	private java.math.BigDecimal sj;
	/**净利润*/
	private java.math.BigDecimal jlr;
	/**发放工资*/
	private java.math.BigDecimal ffgz;
	/**截止调查日产值*/
	private java.math.BigDecimal zzdcrcz;
	/**截止调查日利润*/
	private java.math.BigDecimal zzdcrlr;
	/**预计今年全年产值*/
	private java.math.BigDecimal yjjnqncz;
	/**预计净利润*/
	private java.math.BigDecimal yjjlr;
	/**市场前景，1良好，2较好，3一般，4差*/
	private String scqj;
	/**申请金额*/
	private String sqje;
	/**借款用途*/
	private String jkyt;
	/**借款期限*/
	private String jkqx;
	/**借款方式，1福农卡，2便民卡*/
	private String jkfs;
	/**其他借款方式*/
	private String qtjkfs;
	/**还款方式*/
	private String hkfs;
	/**还款计划*/
	private String hkjh;
	/**第一还款来源*/
	private String dyhkly;
	/**第二还款来源*/
	private String dehkly;
	/**贷款风险点分析及防范措施*/
	private String dkfxdjfycs;

	@ApiModelProperty(value = "评定等级")
	private String pddj;
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	@ApiModelProperty(value = "调查结论")
	private String dcjl;

	@ApiModelProperty(value = "风险经理意见")
	private String fxjlspyj;
	@ApiModelProperty(value = "风险经理审批状态")
	private String fxjlspzt;

	/**procDefId*/
	@ApiModelProperty(value = "procDefId")
	private String procDefId;
	/**procInstId*/
	@ApiModelProperty(value = "procInstId")
	private String procInstId;
	/**bussinessId*/
	@ApiModelProperty(value = "bussinessId")
	private String bussinessId;
	/**title*/
	@ApiModelProperty(value = "title")
	private String title;
	/**status*/
	@ApiModelProperty(value = "status")
	@Dict(dicCode = "lczt")
	private Integer status;

	// 家庭成员信息
	private List<Jtcyxx> jtcyxxList;
	// 关联企业信息
	private List<Glqy> glqyList;
	// 资产负债情况-房屋信息
	private List<Fwxx> fwxxList;
	// 资产负债情况-厂房信息
	private List<Cfxx> cfxxList;
	// 资产负债情况-车辆信息
	private List<Clxx> clxxList;
	// 资产负债情况-其他固定资产
	private List<Qtglzc> qtglzcList;
	// 资产负债情况-银行贷款
	private List<Yhdk> yhdkList;
	// 担保方式-保证担保
	private List<Bzdb> bzdbList;
	// 担保方式-抵押担保
	private List<Dydb> dydbList;
	// 担保方式-质押担保
	private List<Zydb> zydbList;
	// 担保方式-信用担保
	private List<Xydb> xydbList;

	private List<GrdkBzxx> bzxxList;
	private List<GrdkDbxx> dbxxList;

	private JSONObject imgdate;

	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;

	@ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;

	@ApiModelProperty(value = "当年存款日平")
	private java.math.BigDecimal cknrpye;

	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;

	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;

	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;

	@ApiModelProperty(value = "表外不良贷款")
	private java.math.BigDecimal bwbldkye;

	@ApiModelProperty(value = "手机银行")
	private java.math.BigDecimal sjyhsl;

	@ApiModelProperty(value = "网上银行")
	private java.math.BigDecimal wsyhsl;

	@ApiModelProperty(value = "社保卡")
	private java.math.BigDecimal sbksl;

	@ApiModelProperty(value = "ETC")
	private java.math.BigDecimal etcsl;

	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;

	@ApiModelProperty(value = "福农卡金额")
	private java.math.BigDecimal fnkjkje;

	@ApiModelProperty(value = "便民卡金额")
	private java.math.BigDecimal bmkjkje;

	@ApiModelProperty(value = "担保金额")
	private java.math.BigDecimal dbjkje;

	/**spid*/
	@ApiModelProperty(value = "spid")
	private String spid;

}
