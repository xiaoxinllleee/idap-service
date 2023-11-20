package org.cmms.modules.tjfx.tjfxHnkd.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 提统计分析惠农快贷
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Data
@TableName("erp_tjfx_hnkd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_tjfx_hnkd对象", description="提统计分析惠农快贷")
public class TjfxHnkd {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**客户身份证号码*/
	@Excel(name = "客户身份证号码", width = 15)
    @ApiModelProperty(value = "客户身份证号码")
	private String khsfzhm;
	/**额度*/
	@Excel(name = "额度", width = 15)
    @ApiModelProperty(value = "额度")
	private java.math.BigDecimal ey;
	/**导入时间*/
	@Excel(name = "导入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "导入时间")
	private Date drsj;
	/**基点*/
	@Excel(name = "基点", width = 15)
    @ApiModelProperty(value = "基点")
	private java.math.BigDecimal jd;
	/**授信期限(月)*/
	@Excel(name = "授信期限(月)", width = 15)
    @ApiModelProperty(value = "授信期限(月)")
	private String sxqx;
	/**客户分群*/
	@Excel(name = "客户分群", width = 15)
    @ApiModelProperty(value = "客户分群")
	private String khfq;
	/**客户经理工号*/
	@Excel(name = "客户经理工号", width = 15)
    @ApiModelProperty(value = "客户经理工号")
	private String khljgh;
	/**调查责任人一工号*/
	@Excel(name = "调查责任人一工号", width = 15)
    @ApiModelProperty(value = "调查责任人一工号")
	private String dczrrgh1;
	/**调查责任人一比例*/
	@Excel(name = "调查责任人一比例", width = 15)
    @ApiModelProperty(value = "调查责任人一比例")
	private java.math.BigDecimal dczrrbl1;
	/**调查责任人二工号*/
	@Excel(name = "调查责任人二工号", width = 15)
    @ApiModelProperty(value = "调查责任人二工号")
	private String dczrrgh2;
	/**调查责任人二比例*/
	@Excel(name = "调查责任人二比例", width = 15)
    @ApiModelProperty(value = "调查责任人二比例")
	private java.math.BigDecimal dczrrbl2;
	/**管理责任人工号*/
	@Excel(name = "管理责任人工号", width = 15)
    @ApiModelProperty(value = "管理责任人工号")
	private String glzrrgh;
	/**管理责任人比例*/
	@Excel(name = "管理责任人比例", width = 15)
    @ApiModelProperty(value = "管理责任人比例")
	private java.math.BigDecimal glzrrbl;
	/**审查责任人工号*/
	@Excel(name = "审查责任人工号", width = 15)
    @ApiModelProperty(value = "审查责任人工号")
	private String sczrrgh;
	/**审查责任人比例*/
	@Excel(name = "审查责任人比例", width = 15)
    @ApiModelProperty(value = "审查责任人比例")
	private java.math.BigDecimal sczrrbl;
	/**最终审批责任人工号*/
	@Excel(name = "最终审批责任人工号", width = 15)
    @ApiModelProperty(value = "最终审批责任人工号")
	private String zzspzrrgh;
	/**最终审批责任人比例*/
	@Excel(name = "最终审批责任人比例", width = 15)
    @ApiModelProperty(value = "最终审批责任人比例")
	private java.math.BigDecimal zzspzrrbl;
	/**第一责任人工号*/
	@Excel(name = "第一责任人工号", width = 15)
    @ApiModelProperty(value = "第一责任人工号")
	private String dyzrrgh;
	/**第一责任人比例*/
	@Excel(name = "第一责任人比例", width = 15)
    @ApiModelProperty(value = "第一责任人比例")
	private java.math.BigDecimal dyzrrbl;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String khlx2;
	/**乡镇*/
	@Excel(name = "乡镇", width = 15)
    @ApiModelProperty(value = "乡镇")
	private String xz;
	/**有无子女*/
	@Excel(name = "有无子女", width = 15)
    @ApiModelProperty(value = "有无子女")
	private String ywzn;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**配偶姓名*/
	@Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
	private String poxm;
	/**配偶身份证号码*/
	@Excel(name = "配偶身份证号码", width = 15)
    @ApiModelProperty(value = "配偶身份证号码")
	private String posfzhm;
	/**家庭人口*/
	@Excel(name = "家庭人口", width = 15)
    @ApiModelProperty(value = "家庭人口")
	private String jtrk;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15)
    @ApiModelProperty(value = "劳动能力")
	private String ldnl;
	/**居住年限*/
	@Excel(name = "居住年限", width = 15)
    @ApiModelProperty(value = "居住年限")
	private String jznx;
	/**居住状况*/
	@Excel(name = "居住状况", width = 15)
    @ApiModelProperty(value = "居住状况")
	private String jzzk;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String zgxl;
	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
	private String czdz;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String txdz;
	/**行政区划代码*/
	@Excel(name = "行政区划代码", width = 15)
    @ApiModelProperty(value = "行政区划代码")
	private String xzqhdm;
	/**住地邮政编码*/
	@Excel(name = "住地邮政编码", width = 15)
    @ApiModelProperty(value = "住地邮政编码")
	private String zdyzbm;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**是否发送短信提醒*/
	@Excel(name = "是否发送短信提醒", width = 15)
    @ApiModelProperty(value = "是否发送短信提醒")
	private String sffsdxtx;
	/**现工资单位*/
	@Excel(name = "现工资单位", width = 15)
    @ApiModelProperty(value = "现工资单位")
	private String xgzdw;
	/**现担任职务*/
	@Excel(name = "现担任职务", width = 15)
    @ApiModelProperty(value = "现担任职务")
	private String xdrzw;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业")
	private String hy;
	/**经营情况是否正常*/
	@Excel(name = "经营情况是否正常", width = 15)
    @ApiModelProperty(value = "经营情况是否正常")
	private String jyqksfzc;
	/**产品市场需求情况*/
	@Excel(name = "产品市场需求情况", width = 15)
    @ApiModelProperty(value = "产品市场需求情况")
	private String cpscxqqk;
	/**借款人还款意愿*/
	@Excel(name = "借款人还款意愿", width = 15)
    @ApiModelProperty(value = "借款人还款意愿")
	private String jkrhkyy;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String zy;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String zc;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
    @ApiModelProperty(value = "从业年限")
	private String cxnx;
	/**综合评价标志*/
	@Excel(name = "综合评价标志", width = 15)
    @ApiModelProperty(value = "综合评价标志")
	private String zhpjbz;
	/**个体工商户名称*/
	@Excel(name = "个体工商户名称", width = 15)
    @ApiModelProperty(value = "个体工商户名称")
	private String gtgshmc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**家庭年收入*/
	@Excel(name = "家庭年收入", width = 15)
    @ApiModelProperty(value = "家庭年收入")
	private java.math.BigDecimal jtnsr;
	/**个人年收入*/
	@Excel(name = "个人年收入", width = 15)
    @ApiModelProperty(value = "个人年收入")
	private java.math.BigDecimal grnsr;
	/**循环标志*/
	@Excel(name = "循环标志", width = 15)
    @ApiModelProperty(value = "循环标志")
	private String xhbz;
	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
    @ApiModelProperty(value = "信用等级")
	@ExcelVerify(interHandler = true)
	private String xydj;
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
