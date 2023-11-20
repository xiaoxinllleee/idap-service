package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_GRXD")
public class Grdkgl implements Serializable {
    private static final long serialVersionUID = 1L;


	private String hmcId;
	/**所属支行*/
	@Excel(name = "所属网点", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属网点")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;

	@Excel(name = "镇", width = 15,dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "镇")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdybh;
	@Excel(name = "村", width = 15,dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "村")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdybh;
	@Excel(name = "组", width = 15,dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "组")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdybh;
	/**所属客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String sskhjl;

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**出生年月*/
	private String csny;
	/**从事职业*/
	@Excel(name = "从事职业", width = 15,dicCode = "cszy")
	private String cszy;
	/**户籍地址*/
	private String hjdz;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	private String sjhm;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk_cj")
	private String hyzk;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode = "sex")
	@Dict(dicCode = "sex")
	private String xb;
	/**民族*/
	private String mz;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15,format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**所属营销单元*/
	private String ssyxdy;
	/**户号编码*/
	private String hhbm;
	/**与户主关系*/
	private String yhzgx;
	/**是否户主*/
	private String sfhz;
	/**客户类型*/
	private String khlx;
	/**机构代码*/
	private String jgdm;
	@ApiModelProperty(value = "是否授信对象")
	@Dict(dicCode = "sfbz")
	private String sfsxdx;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户项目*/
	private String khxm;
	/**从事行业*/
	private String cshy;
	/**客户品行，1良好，2较好，3一般，4差*/
	private String khpx;
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
	/**预计今年全年产值
*/
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


	@ApiModelProperty(value = "福农卡金额")
	private java.math.BigDecimal fnkjkje;

	@ApiModelProperty(value = "便民卡金额")
	private java.math.BigDecimal bmkjkje;

	@ApiModelProperty(value = "担保金额")
	private java.math.BigDecimal dbjkje;

}
