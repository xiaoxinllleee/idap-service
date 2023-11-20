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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人信贷采集表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRXDCJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRXDCJXX对象", description="个人信贷采集表")
public class CamsZcsxGrxdcjxx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**1是，2否*/
	@Excel(name = "1是，2否", width = 15)
    @ApiModelProperty(value = "1是，2否")
	private String sfsxdx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**评定等级*/
	@Excel(name = "评定等级", width = 15)
    @ApiModelProperty(value = "评定等级")
	private String pddj;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**调查结论*/
	@Excel(name = "调查结论", width = 15)
    @ApiModelProperty(value = "调查结论")
	private String dcjl;
	/**流程id*/
	@Excel(name = "流程id", width = 15)
    @ApiModelProperty(value = "流程id")
	private String spid;
	/**福农卡借款金额*/
	@Excel(name = "福农卡借款金额", width = 15)
    @ApiModelProperty(value = "福农卡借款金额")
	private java.math.BigDecimal fnkjkje;
	/**便民卡借款金额*/
	@Excel(name = "便民卡借款金额", width = 15)
    @ApiModelProperty(value = "便民卡借款金额")
	private java.math.BigDecimal bmkjkje;
	/**担保借款金额*/
	@Excel(name = "担保借款金额", width = 15)
    @ApiModelProperty(value = "担保借款金额")
	private java.math.BigDecimal dbjkje;
	/**电子签名1*/
	@Excel(name = "电子签名1", width = 15)
    @ApiModelProperty(value = "电子签名1")
	private String sign1;
	/**电子签名2*/
	@Excel(name = "电子签名2", width = 15)
    @ApiModelProperty(value = "电子签名2")
	private String sign2;
	/**电子签名3*/
	@Excel(name = "电子签名3", width = 15)
    @ApiModelProperty(value = "电子签名3")
	private String sign3;
	/**固定资产合计*/
	@Excel(name = "固定资产合计", width = 15)
    @ApiModelProperty(value = "固定资产合计")
	private java.math.BigDecimal gdzcHj;
	/**现金和存款*/
	@Excel(name = "现金和存款", width = 15)
    @ApiModelProperty(value = "现金和存款")
	private java.math.BigDecimal xjck;
	/**原材料*/
	@Excel(name = "原材料", width = 15)
    @ApiModelProperty(value = "原材料")
	private java.math.BigDecimal ycl;
	/**半成品*/
	@Excel(name = "半成品", width = 15)
    @ApiModelProperty(value = "半成品")
	private java.math.BigDecimal bcp;
	/**产成品*/
	@Excel(name = "产成品", width = 15)
    @ApiModelProperty(value = "产成品")
	private java.math.BigDecimal ccp;
	/**应收账款*/
	@Excel(name = "应收账款", width = 15)
    @ApiModelProperty(value = "应收账款")
	private java.math.BigDecimal yszk;
	/**预付账款*/
	@Excel(name = "预付账款", width = 15)
    @ApiModelProperty(value = "预付账款")
	private java.math.BigDecimal yfzk;
	/**流动资产合计*/
	@Excel(name = "流动资产合计", width = 15)
    @ApiModelProperty(value = "流动资产合计")
	private java.math.BigDecimal ldzcHj;
	/**资产总额合计*/
	@Excel(name = "资产总额合计", width = 15)
    @ApiModelProperty(value = "资产总额合计")
	private java.math.BigDecimal zczeHj;
	/**负债总额合计*/
	@Excel(name = "负债总额合计", width = 15)
    @ApiModelProperty(value = "负债总额合计")
	private java.math.BigDecimal fzzeHj;
	/**其他借款*/
	@Excel(name = "其他借款", width = 15)
    @ApiModelProperty(value = "其他借款")
	private java.math.BigDecimal qtjk;
	/**应付账款*/
	@Excel(name = "应付账款 ", width = 15)
    @ApiModelProperty(value = "应付账款 ")
	private java.math.BigDecimal fzyf;
	/**预收账款*/
	@Excel(name = "预收账款", width = 15)
    @ApiModelProperty(value = "预收账款")
	private java.math.BigDecimal fzys;
	/**净资产合计*/
	@Excel(name = "净资产合计", width = 15)
    @ApiModelProperty(value = "净资产合计")
	private java.math.BigDecimal jzcHj;
	/**去年产值*/
	@Excel(name = "去年产值", width = 15)
    @ApiModelProperty(value = "去年产值")
	private java.math.BigDecimal qncz;
	/**销售额*/
	@Excel(name = "销售额", width = 15)
    @ApiModelProperty(value = "销售额")
	private java.math.BigDecimal xse;
	/**税金*/
	@Excel(name = "税金", width = 15)
    @ApiModelProperty(value = "税金")
	private java.math.BigDecimal sj;
	/**净利润*/
	@Excel(name = "净利润", width = 15)
    @ApiModelProperty(value = "净利润")
	private java.math.BigDecimal jlr;
	/**发放工资*/
	@Excel(name = "发放工资", width = 15)
    @ApiModelProperty(value = "发放工资")
	private java.math.BigDecimal ffgz;
	/**截止调查日产值*/
	@Excel(name = "截止调查日产值", width = 15)
    @ApiModelProperty(value = "截止调查日产值")
	private java.math.BigDecimal zzdcrcz;
	/**截止调查日利润*/
	@Excel(name = "截止调查日利润", width = 15)
    @ApiModelProperty(value = "截止调查日利润")
	private java.math.BigDecimal zzdcrlr;
	/**预计今年全年产值*/
	@Excel(name = "预计今年全年产值 ", width = 15)
    @ApiModelProperty(value = "预计今年全年产值")
	private java.math.BigDecimal yjjnqncz;
	/**预计净利润*/
	@Excel(name = "预计净利润", width = 15)
    @ApiModelProperty(value = "预计净利润")
	private java.math.BigDecimal yjjlr;
	/**市场前景，1良好，2较好，3一般，4差*/
	@Excel(name = "市场前景，1良好，2较好，3一般，4差", width = 15)
    @ApiModelProperty(value = "市场前景，1良好，2较好，3一般，4差")
	private String scqj;
	/**申请金额*/
	@Excel(name = "申请金额", width = 15)
    @ApiModelProperty(value = "申请金额")
	private String sqje;
	/**借款用途*/
	@Excel(name = "借款用途 ", width = 15)
    @ApiModelProperty(value = "借款用途")
	private String jkyt;
	/**借款期限*/
	@Excel(name = "借款期限", width = 15)
    @ApiModelProperty(value = "借款期限")
	private String jkqx;
	/**借款方式，1福农卡，2便民卡*/
	@Excel(name = "借款方式，1福农卡，2便民卡", width = 15)
    @ApiModelProperty(value = "借款方式，1福农卡，2便民卡")
	private String jkfs;
	/**其他借款方式*/
	@Excel(name = "其他借款方式", width = 15)
    @ApiModelProperty(value = "其他借款方式")
	private String qtjkfs;
	/**还款方式*/
	@Excel(name = "还款方式", width = 15)
    @ApiModelProperty(value = "还款方式")
	private String hkfs;
	/**还款计划*/
	@Excel(name = "还款计划", width = 15)
    @ApiModelProperty(value = "还款计划")
	private String hkjh;
	/**第一还款来源*/
	@Excel(name = "第一还款来源", width = 15)
    @ApiModelProperty(value = "第一还款来源")
	private String dyhkly;
	/**第二还款来源*/
	@Excel(name = "第二还款来源", width = 15)
    @ApiModelProperty(value = "第二还款来源")
	private String dehkly;
	/**贷款风险点分析及防范措施*/
	@Excel(name = "贷款风险点分析及防范措施", width = 15)
    @ApiModelProperty(value = "贷款风险点分析及防范措施")
	private String dkfxdjfycs;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户项目*/
	@Excel(name = "客户项目", width = 15)
    @ApiModelProperty(value = "客户项目")
	private String khxm;
	/**从事行业*/
	@Excel(name = "从事行业", width = 15)
    @ApiModelProperty(value = "从事行业")
	private String cshy;
	/**客户品行，1良好，2较好，3一般，4差*/
	@Excel(name = "客户品行，1良好，2较好，3一般，4差 ", width = 15)
    @ApiModelProperty(value = "客户品行，1良好，2较好，3一般，4差")
	private String khpx;
	/**对外担保*/
	@Excel(name = "对外担保", width = 15)
    @ApiModelProperty(value = "对外担保")
	private String dwdb;
	/**担保对象*/
	@Excel(name = "担保对象", width = 15)
    @ApiModelProperty(value = "担保对象")
	private String dbdx;
	/**担保备注*/
	@Excel(name = "担保备注", width = 15)
	@ApiModelProperty(value = "担保备注")
	private String dbbz;
	/**其他用信*/
	@Excel(name = "其他用信", width = 15)
    @ApiModelProperty(value = "其他用信")
	private String qtyx;
	/**共同借款人*/
	@Excel(name = "共同借款人", width = 15)
    @ApiModelProperty(value = "共同借款人")
	private String gtjkr;
	/**共同借款人手机*/
	@Excel(name = "共同借款人手机", width = 15)
	@ApiModelProperty(value = "共同借款人手机")
	private String gtjkrsj;
	/**共同借款人证件号码*/
	@Excel(name = "共同借款人证件号码", width = 15)
	@ApiModelProperty(value = "共同借款人证件号码")
	private String gtjkrzjhm;
	/**指定签收人*/
	@Excel(name = "指定签收人", width = 15)
    @ApiModelProperty(value = "指定签收人")
	private String zdqsr;
	/**签收人手机*/
	@Excel(name = "签收人手机", width = 15)
    @ApiModelProperty(value = "签收人手机")
	private String qsrsj;
	/**签收人传真*/
	@Excel(name = "签收人传真", width = 15)
    @ApiModelProperty(value = "签收人传真")
	private String qsrcz;
	/**签收人邮箱*/
	@Excel(name = "签收人邮箱", width = 15)
    @ApiModelProperty(value = "签收人邮箱")
	private String qsryx;
	/**签收人微信*/
	@Excel(name = "签收人微信", width = 15)
	@ApiModelProperty(value = "签收人微信")
	private String qsrwx;
	/**其他签收方式*/
	@Excel(name = "其他签收方式", width = 15)
	@ApiModelProperty(value = "其他签收方式")
	private String qtqsfs;
	/**是否有失信人或被执行人记录，1是，2否*/
	@Excel(name = "是否有失信人或被执行人记录，1是，2否", width = 15)
	@ApiModelProperty(value = "是否有失信人或被执行人记录，1是，2否")
	private String sfsxrhbzxr;
	/**网站查询是否有风险，1是，2否*/
	@Excel(name = "网站查询是否有风险，1是，2否", width = 15)
	@ApiModelProperty(value = "网站查询是否有风险，1是，2否")
	private String sfyfxxx;
	/**失信人或被执行人说明*/
	@Excel(name = "失信人或被执行人说明", width = 15)
	@ApiModelProperty(value = "失信人或被执行人说明")
	private String sfrqksm;
	/**风险信息说明*/
	@Excel(name = "风险信息说明", width = 15)
	@ApiModelProperty(value = "风险信息说明")
	private String fxxxqksm;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;
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
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
