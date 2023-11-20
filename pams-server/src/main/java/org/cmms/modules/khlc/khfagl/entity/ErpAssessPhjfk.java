package org.cmms.modules.khlc.khfagl.entity;

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
 * @Description: 平衡积分卡考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_PHJFK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_PHJFK对象", description="平衡积分卡考核设置")
public class ErpAssessPhjfk {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
    @ApiModelProperty(value = "方案名称")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	private java.lang.String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	private java.lang.String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
	private java.lang.Integer gwbz;
	@Excel(name = "指标ID", width = 15)
	@TableField(exist = false)
	private java.lang.String zbmc;

	public String getZbmc() {
		return zbid;
	}
	/**指标ID*/
	@Excel(name = "指标名称", width = 15,dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
    @ApiModelProperty(value = "指标名称")
	@Dict(dicCode = "zbid", dictTable = "erp_bas_zbk", dicText = "zbmc")
	private java.lang.String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
    @ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private java.lang.String zbwd;
	/**指标分值*/
	@Excel(name = "指标分值", width = 15)
    @ApiModelProperty(value = "指标分值")
	private java.math.BigDecimal zbfz;
	/**指标增减符号*/
	@Excel(name = "指标增减符号", width = 15,dicCode = "zjbs")
    @ApiModelProperty(value = "指标增减符号")
	@Dict(dicCode = "zjbs")
	private java.lang.Integer zbabs;
	/**加权比率*/
	@Excel(name = "加权比率", width = 15)
    @ApiModelProperty(value = "加权比率")
	private java.math.BigDecimal jqbl;
	/**加权比率分值*/
	@Excel(name = "加权比率分值", width = 15)
    @ApiModelProperty(value = "加权比率分值")
	private java.math.BigDecimal jqblfz;
	/**加权限制分值*/
	@Excel(name = "加权限制分值", width = 15)
    @ApiModelProperty(value = "加权限制分值")
	private java.math.BigDecimal xzfz;
	/**减权比率*/
	@Excel(name = "减权比率", width = 15)
    @ApiModelProperty(value = "减权比率")
	private java.math.BigDecimal kqbl;
	/**减权比率分值*/
	@Excel(name = "减权比率分值", width = 15)
    @ApiModelProperty(value = "减权比率分值")
	private java.math.BigDecimal kqblfz;
	/**减权限制分值*/
	@Excel(name = "减权限制分值", width = 15)
    @ApiModelProperty(value = "减权限制分值")
	private java.math.BigDecimal kqxzfz;
	/**指标权重*/
	@Excel(name = "指标权重", width = 15)
	@ApiModelProperty(value = "指标权重")
	private java.math.BigDecimal zbqz;
	/**计分制(1 百分制 2千分制)*/
	@Excel(name = "计分制", width = 15,dicCode = "jfz")
	@ApiModelProperty(value = "计分制")
	@Dict(dicCode = "jfz")
	private java.lang.Integer jfz;
	/**考核类别*/
//	@Excel(name = "考核类别", width = 15)
    @ApiModelProperty(value = "考核类别")
	@Dict(dicCode = "gwkhlb")
	private java.lang.String khlb;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
}
