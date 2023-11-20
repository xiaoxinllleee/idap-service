package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人贷款审批流程
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_GRXD_SPLC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGL_GRXD_SPLC对象", description="个人贷款审批流程")
public class Grdksplc {
    
	/**ID*/

	@Excel(name = "所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;

	@Excel(name = "镇", width = 15,dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "一级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "村", width = 15,dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "二级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "组", width = 15,dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "三级营销单元编号")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdybh;


	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String sskhjl;


	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;

	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户项目*/
    @ApiModelProperty(value = "客户项目")
	private String khxm;
	/**从事行业*/
    @ApiModelProperty(value = "从事行业")
	private String cshy;
	/**客户品行*/
	@Excel(name = "客户品行", width = 15,dicCode = "grdk_khpx")
    @ApiModelProperty(value = "客户品行")
	private String khpx;

	/**性别*/
	@Excel(name = "性别", width = 15,dicCode ="sex")
	@ApiModelProperty(value = "性别")
	private String xb;
	/**民族*/
	@ApiModelProperty(value = "民族")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15 ,dicCode = "hyzk_cj")
	@ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**机构代码*/
	@ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**出生年月*/
	@ApiModelProperty(value = "出生年月")
	private String csny;
	/**从事职业*/
	@Excel(name = "从事职业", width = 15,dicCode = "cszy")
	@ApiModelProperty(value = "从事职业")
	private String cszy;
	/**户籍地址*/
	@ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String sjhm;

	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;

	/**评定等级*/
	@Excel(name = "评定等级", width = 15,dicCode = "grdk_pddj")
	@ApiModelProperty(value = "评定等级")
	@Dict(dicCode = "grdk_pddj")
	private String pddj;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**调查结论*/
	@Excel(name = "调查结论", width = 20)
	@ApiModelProperty(value = "调查结论")
	private String dcjl;

	/**调查结论*/
	@Excel(name = "风险经理意见", width = 20)
	@ApiModelProperty(value = "风险经理意见")
	private String fxjlspyj;

	/**status*/
	@Excel(name = "流程状态", width = 15,dicCode = "lczt")
	@ApiModelProperty(value = "status")
	@Dict(dicCode = "lczt")
	private Integer status;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;


	/**对外担保*/
    @ApiModelProperty(value = "对外担保")
	private String dwdb;
	/**担保对象*/
    @ApiModelProperty(value = "担保对象")
	private String dbdx;
	/**其他用信*/
    @ApiModelProperty(value = "其他用信")
	private String qtyx;
	/**共同借款人*/
    @ApiModelProperty(value = "共同借款人")
	private String gtjkr;
	/**指定签收人*/
    @ApiModelProperty(value = "指定签收人")
	private String zdqsr;
	/**签收人手机*/
    @ApiModelProperty(value = "签收人手机")
	private String qsrsj;
	/**签收人传真*/
    @ApiModelProperty(value = "签收人传真")
	private String qsrcz;
	/**签收人邮箱*/
    @ApiModelProperty(value = "签收人邮箱")
	private String qsryx;
	/**签收人微信*/
    @ApiModelProperty(value = "签收人微信")
	private String qsrwx;
	/**其他签收方式*/
    @ApiModelProperty(value = "其他签收方式")
	private String qtqsfs;
	/**是否有失信人或被执行人记录*/
    @ApiModelProperty(value = "是否有失信人或被执行人记录")
	private String sfsxrhbzxr;
	/**网站查询是否有风险*/
    @ApiModelProperty(value = "网站查询是否有风险")
	private String sfyfxxx;
	/**失信人或被执行人说明*/
    @ApiModelProperty(value = "失信人或被执行人说明")
	private String sfrqksm;
	/**风险信息说明*/
    @ApiModelProperty(value = "风险信息说明")
	private String fxxxqksm;
	/**固定资产合计*/
    @ApiModelProperty(value = "固定资产合计")
	private java.math.BigDecimal gdzcHj;
	/**现金和存款*/
    @ApiModelProperty(value = "现金和存款")
	private java.math.BigDecimal xjck;
	/**原材料*/
    @ApiModelProperty(value = "原材料")
	private java.math.BigDecimal ycl;
	/**半成品*/
    @ApiModelProperty(value = "半成品")
	private java.math.BigDecimal bcp;
	/**产成品*/
    @ApiModelProperty(value = "产成品")
	private java.math.BigDecimal ccp;
	/**应收账款*/
    @ApiModelProperty(value = "应收账款")
	private java.math.BigDecimal yszk;
	/**预付账款*/
    @ApiModelProperty(value = "预付账款")
	private java.math.BigDecimal yfzk;
	/**流动资产合计*/
    @ApiModelProperty(value = "流动资产合计")
	private java.math.BigDecimal ldzcHj;
	/**资产总额合计*/
    @ApiModelProperty(value = "资产总额合计")
	private java.math.BigDecimal zczeHj;
	/**负债总额合计*/
    @ApiModelProperty(value = "负债总额合计")
	private java.math.BigDecimal fzzeHj;
	/**其他借款*/
    @ApiModelProperty(value = "其他借款")
	private java.math.BigDecimal qtjk;
	/**应付账款*/
    @ApiModelProperty(value = "应付账款")
	private java.math.BigDecimal fzyf;
	/**预收账款*/
    @ApiModelProperty(value = "预收账款")
	private java.math.BigDecimal fzys;
	/**净资产合计*/
    @ApiModelProperty(value = "净资产合计")
	private java.math.BigDecimal jzcHj;
	/**去年产值*/
    @ApiModelProperty(value = "去年产值")
	private java.math.BigDecimal qncz;
	/**销售额*/
    @ApiModelProperty(value = "销售额")
	private java.math.BigDecimal xse;
	/**税金*/
    @ApiModelProperty(value = "税金")
	private java.math.BigDecimal sj;
	/**净利润*/
    @ApiModelProperty(value = "净利润")
	private java.math.BigDecimal jlr;
	/**发放工资*/
    @ApiModelProperty(value = "发放工资")
	private java.math.BigDecimal ffgz;
	/**截止调查日产值*/
    @ApiModelProperty(value = "截止调查日产值")
	private java.math.BigDecimal zzdcrcz;
	/**截止调查日利润*/
    @ApiModelProperty(value = "截止调查日利润")
	private java.math.BigDecimal zzdcrlr;
	/**预计今年全年产值*/
    @ApiModelProperty(value = "预计今年全年产值")
	private java.math.BigDecimal yjjnqncz;
	/**预计净利润*/
    @ApiModelProperty(value = "预计净利润")
	private java.math.BigDecimal yjjlr;
	/**市场前景*/
    @ApiModelProperty(value = "市场前景")
	private String scqj;
	/**申请金额*/
    @ApiModelProperty(value = "申请金额")
	private String sqje;
	/**借款用途*/
    @ApiModelProperty(value = "借款用途")
	private String jkyt;
	/**借款期限*/
    @ApiModelProperty(value = "借款期限")
	private String jkqx;
	/**借款方式*/
    @ApiModelProperty(value = "借款方式")
	private String jkfs;
	/**其他借款方式*/
    @ApiModelProperty(value = "其他借款方式")
	private String qtjkfs;
	/**还款方式*/
    @ApiModelProperty(value = "还款方式")
	private String hkfs;
	/**还款计划*/
    @ApiModelProperty(value = "还款计划")
	private String hkjh;
	/**第一还款来源*/
    @ApiModelProperty(value = "第一还款来源")
	private String dyhkly;
	/**第二还款来源*/
    @ApiModelProperty(value = "第二还款来源")
	private String dehkly;
	/**贷款风险点分析及防范措施*/
    @ApiModelProperty(value = "贷款风险点分析及防范措施")
	private String dkfxdjfycs;

	/**更新人*/
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**所属客户经理*/


	/**sfsxdx*/
    @ApiModelProperty(value = "sfsxdx")
	private String sfsxdx;

	/**hmcId*/
    @ApiModelProperty(value = "hmcId")
	private String hmcId;
	/**所属支行*/

	/**所属营销单元*/
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;

	/**与户主关系*/
    @ApiModelProperty(value = "与户主关系")
	private String yhzgx;
	/**是否户主*/
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**客户类型*/
    @ApiModelProperty(value = "客户类型")
	private String khlx;


/*

	*/
/**procDefId*//*

    @ApiModelProperty(value = "procDefId")
	private String procDefId;
	*/
/**procInstId*//*

    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	*/
/**bussinessId*//*

    @ApiModelProperty(value = "bussinessId")
	private String bussinessId;
	*/
/**title*//*

    @ApiModelProperty(value = "title")
	private String title;
*/

	//t5.ckye,t5.dqckye,t5.cknrpye,t5.dkje,t5.dkye,t5.bldkye,t5.bwbldkye,t5.sjyhsl,t5.wsyhsl,t5.etcsl,t5.sbksl,

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


	@ApiModelProperty(value = "审查结论")
	@Dict(dicCode = "grdk_fxsp")
	private String scjl;

	@ApiModelProperty(value = "福农卡金额")
	private java.math.BigDecimal fnkjkje;

	@ApiModelProperty(value = "便民卡金额")
	private java.math.BigDecimal bmkjkje;


	@ApiModelProperty(value = "担保金额")
	private java.math.BigDecimal dbjkje;

	@Excel(name = "定价日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "定价日期")
	private Date djrq;
	@ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	@ApiModelProperty(value = "贷款期限")
	private String dkqx;
	@ApiModelProperty(value = "得分合计")
	private java.math.BigDecimal dfhj;
	@ApiModelProperty(value = "LPR基点")
	private java.math.BigDecimal jdbp;
	@ApiModelProperty(value = "优惠后LPR基点")
	private java.math.BigDecimal yhhjdbp;
	@ApiModelProperty(value = "对应档次LPR")
	private java.math.BigDecimal lprll;
	@ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	@ApiModelProperty(value = "优惠后执行利率")
	private java.math.BigDecimal yhhzxll;
	@ApiModelProperty(value = "上年授信的")
	private java.math.BigDecimal snsxed;
	@ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;
//'djrq','sxed','dkqx','dfhj','jdbp','yhhjdbp','lprll','zxll','yhhzxll'


	@ApiModelProperty(value = "提交审批状态")
	@Dict(dicCode = "grdk_fxspzt")
	private String tjspzt;

	@ApiModelProperty(value = "审批id")
	private String spid;
}
