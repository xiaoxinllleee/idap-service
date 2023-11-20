package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人贷款采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-17
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRXDCJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRXDCJXX对象", description="个人贷款采集信息")
public class Grdkcjxx {

	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
	@ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**所属客户经理*/
	@Excel(name = "户号编号", width = 15)
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

	/**申请金额*/
	@Excel(name = "申请金额", width = 15)
	@ApiModelProperty(value = "申请金额")
	private String sqje;
	/**借款用途*/
	@Excel(name = "借款用途", width = 15)
	@ApiModelProperty(value = "借款用途 ")
	private String jkyt;
	/**借款期限*/
	@Excel(name = "借款期限", width = 15)
	@ApiModelProperty(value = "借款期限")
	private String jkqx;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;

	@Excel(name = "是否授信对象", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否授信对象")
	private String sfsxdx;

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

	/**签收人微信*/
    @ApiModelProperty(value = "签收人微信")
	private String qsrwx;
	/**其他签收方式*/
    @ApiModelProperty(value = "其他签收方式")
	private String qtqsfs;
	/**是否有失信人或被执行人记录，1是，2否*/
    @ApiModelProperty(value = "是否有失信人或被执行人记录，1是，2否")
	private String sfsxrhbzxr;
	/**网站查询是否有风险，1是，2否*/
    @ApiModelProperty(value = "网站查询是否有风险，1是，2否")
	private String sfyfxxx;
	/**失信人或被执行人说明*/
    @ApiModelProperty(value = "失信人或被执行人说明")
	private String sfrqksm;
	/**风险信息说明*/
    @ApiModelProperty(value = "风险信息说明")
	private String fxxxqksm;
	/**现金和存款*/
    @ApiModelProperty(value = "现金和存款")
	@TableField(value = "xjck", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal xjck;
	/**原材料*/
    @ApiModelProperty(value = "原材料")
	@TableField(value = "ycl", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal ycl;
	/**半成品*/
    @ApiModelProperty(value = "半成品")
	@TableField(value = "bcp", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal bcp;
	/**产成品*/
    @ApiModelProperty(value = "产成品")
	@TableField(value = "ccp", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal ccp;
	/**应收账款*/
    @ApiModelProperty(value = "应收账款")
	@TableField(value = "yszk", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal yszk;
	/**预付账款*/
    @ApiModelProperty(value = "预付账款")
	@TableField(value = "yfzk", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal yfzk;
	/**其他借款*/
    @ApiModelProperty(value = "其他借款")
	@TableField(value = "qtjk", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal qtjk;
	/**应付账款*/
    @ApiModelProperty(value = "应付账款")
	@TableField(value = "fzyf", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal fzyf;
	/**预收账款*/
    @ApiModelProperty(value = "预收账款")
	@TableField(value = "fzys", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal fzys;
	/**固定资产合计*/
	@ApiModelProperty(value = "固定资产合计")
	private java.math.BigDecimal gdzcHj;
	/**流动资产合计*/
	@ApiModelProperty(value = "流动资产合计")
	private java.math.BigDecimal ldzcHj;
	/**资产总额合计*/
	@ApiModelProperty(value = "资产总额合计")
	private java.math.BigDecimal zczeHj;
	/**负债总额合计*/
	@ApiModelProperty(value = "负债总额合计")
	private java.math.BigDecimal fzzeHj;
	/**净资产合计*/
	@ApiModelProperty(value = "净资产合计")
	private java.math.BigDecimal jzcHj;
	/**去年产值*/
    @ApiModelProperty(value = "去年产值")
	@TableField(value = "qncz", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal qncz;
	/**销售额*/
    @ApiModelProperty(value = "销售额")
	@TableField(value = "xse", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal xse;
	/**税金*/
    @ApiModelProperty(value = "税金")
	private java.math.BigDecimal sj;
	/**净利润*/
    @ApiModelProperty(value = "净利润")
	@TableField(value = "jlr", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal jlr;
	/**发放工资*/
    @ApiModelProperty(value = "发放工资")
	private java.math.BigDecimal ffgz;
	/**截止调查日产值*/
    @ApiModelProperty(value = "截止调查日产值")
	@TableField(value = "zzdcrcz", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal zzdcrcz;
	/**截止调查日利润*/
    @ApiModelProperty(value = "截止调查日利润")
	@TableField(value = "zzdcrlr", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal zzdcrlr;
	/**预计今年全年产值*/
    @ApiModelProperty(value = "预计今年全年产值")
	@TableField(value = "yjjnqncz", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal yjjnqncz;
	/**预计净利润*/
    @ApiModelProperty(value = "预计净利润")
	@TableField(value = "yjjlr", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal yjjlr;
	/**市场前景，1良好，2较好，3一般，4差*/
    @ApiModelProperty(value = "市场前景，1良好，2较好，3一般，4差")
	private String scqj;

	/**借款方式，1福农卡，2便民卡*/
    @ApiModelProperty(value = "借款方式，1福农卡，2便民卡")
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
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**客户项目*/
    @ApiModelProperty(value = "客户项目")
	private String khxm;
	/**从事行业*/
    @ApiModelProperty(value = "从事行业")
	private String cshy;
	/**客户品行，1良好，2较好，3一般，4差*/
    @ApiModelProperty(value = "客户品行，1良好，2较好，3一般，4差")
	private String khpx;
    @ApiModelProperty(value = "对外担保")
	private String dwdb;
    @ApiModelProperty(value = "担保对象")
	private String dbdx;
	@ApiModelProperty(value = "担保备注")
	private String dbbz;
    @ApiModelProperty(value = "其他用信")
	private String qtyx;
    @ApiModelProperty(value = "共同借款人")
	private String gtjkr;
	@ApiModelProperty(value = "共同借款人手机")
	private String gtjkrsj;
	@ApiModelProperty(value = "共同借款人证件号码")
	private String gtjkrzjhm;
    @ApiModelProperty(value = "指定签收人")
	private String zdqsr;
    @ApiModelProperty(value = "签收人手机")
	private String qsrsj;
    @ApiModelProperty(value = "签收人传真")
	private String qsrcz;
    @ApiModelProperty(value = "签收人邮箱")
	private String qsryx;
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
	/**审批id*/
	@ApiModelProperty(value = "审批id")
	private String spid;
	@ApiModelProperty(value = "福农卡金额")
	/**
	 * 针对 java.math.BigDecimal 字段在Mybatis-Plus的update方法内无法更新为null 的解决办法
	 * @TableField(value = "COLUMN_NAME", updateStrategy = FieldStrategy.IGNORED, el = "COLUMN_NAME, jdbcType = NUMERIC")
 	 */
	@TableField(value = "FNKJKJE", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal fnkjkje;
	@ApiModelProperty(value = "便民卡金额")
	@TableField(value = "BMKJKJE", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal bmkjkje;
	@ApiModelProperty(value = "担保金额")
	@TableField(value = "DBJKJE", updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal dbjkje;

	@ApiModelProperty(value = "经度")
	private String longitude;
	@ApiModelProperty(value = "纬度")
	private String latitude;
}
