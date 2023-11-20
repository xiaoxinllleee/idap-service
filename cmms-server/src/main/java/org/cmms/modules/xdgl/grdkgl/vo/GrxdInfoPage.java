package org.cmms.modules.xdgl.grdkgl.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
public class GrxdInfoPage {

	/**ID*/
	private String id;
	/**花名册ID*/
	private String hmcId;
	/**是否授信对象*/
	private String sfsxdx;
	/**所属支行*/
	private String sszh;
	/**所属营销单元*/
	private String ssyxdy;
	/**所属客户经理*/
	private String sskhjl;
	/**户号编码*/
	private String hhbm;
	/**是否户主*/
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**与户主关系*/
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**客户类型*/
	private String khlx;
	/**地址*/
	private String dz;
	/*经度*/
	private String longitude;
	/*纬度*/
	private String latitude;
	/**性别*/
	@Dict(dicCode = "sex")
	private String xb;
	/**客户名称*/
	private String khmc;
	/**证件号码*/
	private String zjhm;
	/**客户项目*/
	private String khxm;
	/**从事行业*/
	private String cshy;
	/**客户品行，1良好，2较好，3一般，4差*/
	@Dict(dicCode = "grdk_khpx")
	private String khpx;
	/**民族*/
	@Dict(dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**机构代码*/
	private String jgdm;
	/**出生年月*/
	private String csny;
	/**从事职业*/
	private String cszy;
	/**户籍地址*/
	private String hjdz;
	/**手机号码*/
	private String sjhm;
	/**备注*/
	private String bz;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
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

	private List<KhglKhhmcxxGrxd> khhmcxxGrxdList;

	private List<Grdkcjxx> grdkcjxxList;

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
	// 个人贷款保证信息
	private List<GrdkBzxx> bzxxList;
	// 个人贷款担保信息
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
}
