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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 集体审批书
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRXDJTSP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRXDJTSP对象", description="集体审批书")
public class GrdkJtsp {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
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
	@Excel(name = "更新时间 ", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间 ")
	private Date updateTime;
	/**集体审批时间*/
	@Excel(name = "集体审批时间", width = 15)
    @ApiModelProperty(value = "集体审批时间")
	@TableField(value = "JTSPSJ",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.VARCHAR)
	private String jtspsj;
	/**集体审批地点*/
	@Excel(name = "集体审批地点", width = 15)
    @ApiModelProperty(value = "集体审批地点")
	@TableField(value = "JTSPDD",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.VARCHAR)
	private String jtspdd;
	/**集体审批记录人*/
	@Excel(name = "集体审批记录人", width = 15)
    @ApiModelProperty(value = "集体审批记录人")
	@TableField(value = "JTSPJLR",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.VARCHAR)
	private String jtspjlr;
	/**上年授信总额*/
	@Excel(name = "上年授信总额", width = 15)
    @ApiModelProperty(value = "上年授信总额")
	private java.math.BigDecimal snsxze;
	/**上年授信总额中贷款*/
	@Excel(name = "上年授信总额中贷款", width = 15)
    @ApiModelProperty(value = "上年授信总额中贷款")
	private java.math.BigDecimal snsxzezdk;
	/**上年授信总额中便民卡*/
	@Excel(name = "上年授信总额中便民卡", width = 15)
    @ApiModelProperty(value = "上年授信总额中便民卡")
	private java.math.BigDecimal snsxzezbmk;
	/**上年授信总额中福农卡*/
	@Excel(name = "上年授信总额中福农卡", width = 15)
    @ApiModelProperty(value = "上年授信总额中福农卡")
	private java.math.BigDecimal snsxzezfnk;
	/**上年授信总额中其他*/
	@Excel(name = "上年授信总额中其他", width = 15)
    @ApiModelProperty(value = "上年授信总额中其他")
	private java.math.BigDecimal snsxzezqt;
	/**现有用信余额*/
	@Excel(name = "现有用信余额", width = 15)
    @ApiModelProperty(value = "现有用信余额")
	private java.math.BigDecimal xyyxye;
	/**现有贷款余额*/
	@Excel(name = "现有贷款余额", width = 15)
    @ApiModelProperty(value = "现有贷款余额")
	private java.math.BigDecimal xydkye;
	/**现有贷款到期日期*/
	@Excel(name = "现有贷款到期日期", width = 15)
    @ApiModelProperty(value = "现有贷款到期日期")
	private String xydkdqrq;
	/**现有贷款福农卡余额*/
	@Excel(name = "现有贷款福农卡余额", width = 15)
    @ApiModelProperty(value = "现有贷款福农卡余额")
	private java.math.BigDecimal xydkfnkye;
	/**现有贷款其他*/
	@Excel(name = "现有贷款其他", width = 15)
    @ApiModelProperty(value = "现有贷款其他")
	private java.math.BigDecimal xydkqt;
	/**现申请授信金额*/
	@Excel(name = "现申请授信金额", width = 15)
    @ApiModelProperty(value = "现申请授信金额")
	private java.math.BigDecimal xsqsxje;
	/**现担保方式*/
	@Excel(name = "现担保方式", width = 15)
    @ApiModelProperty(value = "现担保方式")
	private String xdbfs;
	/**现抵押物价值*/
	@Excel(name = "现抵押物价值", width = 15)
    @ApiModelProperty(value = "现抵押物价值")
	private java.math.BigDecimal xdywjz;
	/**参审人员*/
	@Excel(name = "参审人员", width = 15)
    @ApiModelProperty(value = "参审人员")
	@TableField(value = "CSRY",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.VARCHAR)
	private String csry;
	/**审贷记录*/
	@Excel(name = "审贷记录", width = 15)
    @ApiModelProperty(value = "审贷记录")
	@TableField(value = "SDJL",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.VARCHAR)
	private String sdjl;
	/**主调查人签名*/
	@Excel(name = "主调查人签名", width = 15)
    @ApiModelProperty(value = "主调查人签名")
	private String ztcrqm;
	/**协调查人签名*/
	@Excel(name = "协调查人签名", width = 15)
    @ApiModelProperty(value = "协调查人签名")
	private String xtcrqm;
	/**主调查人签名时间*/
	@Excel(name = "主调查人签名时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "主调查人签名时间")
	private Date zdcrqmsj;
	/**审批责任人签名*/
	@Excel(name = "审批责任人签名", width = 15)
    @ApiModelProperty(value = "审批责任人签名")
	private String spzrrqm;
	/**审批责任人签名时间*/
	@Excel(name = "审批责任人签名时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "审批责任人签名时间")
	private Date spzrrqmsj;
	/**授信总额*/
	@Excel(name = "授信总额", width = 15)
    @ApiModelProperty(value = "授信总额")
	@TableField(value = "SXZE",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal sxze;
	/**授信期限*/
	@Excel(name = "授信期限", width = 15)
    @ApiModelProperty(value = "授信期限")
	@TableField(value = "SXQX",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal sxqx;
	/**利率年期*/
	@Excel(name = "利率年期", width = 15)
    @ApiModelProperty(value = "利率年期")
	@TableField(value = "LLNQ",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal llnq;
	/**利率基点*/
	@Excel(name = "利率基点", width = 15)
    @ApiModelProperty(value = "利率基点")
	@TableField(value = "LLJD",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal lljd;
	/**贷款授信额度*/
	@Excel(name = "贷款授信额度", width = 15)
    @ApiModelProperty(value = "贷款授信额度")
	@TableField(value = "DKSXED",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal dksxed;
	/**便民卡授信额度*/
	@Excel(name = "便民卡授信额度", width = 15)
    @ApiModelProperty(value = "便民卡授信额度")
	@TableField(value = "BMKSXED",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal bmksxed;
	/**担保授信额度*/
	@Excel(name = "担保授信额度", width = 15)
    @ApiModelProperty(value = "担保授信额度")
	@TableField(value = "DBSXED",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal dbsxed;
	/**福农卡授信额度*/
	@Excel(name = "福农卡授信额度", width = 15)
    @ApiModelProperty(value = "福农卡授信额度")
	@TableField(value = "FNKSXED",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private java.math.BigDecimal fnksxed;
	/**附加条件*/
	@Excel(name = "附加条件", width = 15)
    @ApiModelProperty(value = "附加条件")
	@TableField(value = "FJTJ",updateStrategy = FieldStrategy.IGNORED,jdbcType = JdbcType.DECIMAL)
	private String fjtj;
	/**审批小组组长签字*/
	@Excel(name = "审批小组组长签字", width = 15)
    @ApiModelProperty(value = "审批小组组长签字")
	private String spxzzzqz;
	/**集体审批最终状态*/
	@Excel(name = "集体审批最终状态", width = 15)
    @ApiModelProperty(value = "集体审批最终状态")
	@Dict(dicCode = "sfbz")
	private String jtspzzzt;

	@Excel(name = "调查责任人意见", width = 15)
	@ApiModelProperty(value = "调查责任人意见")
	private String dczrryj;

	@Excel(name = "审批责任人意见", width = 15)
	@ApiModelProperty(value = "审批责任人意见")
	private String spzrryj;



}
