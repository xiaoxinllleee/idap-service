package org.cmms.modules.khlc.khfagl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
public class ErpAssessPhjfkVO {
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
    @ApiModelProperty(value = "考核项目")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	private String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
	private Integer gwbz;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标名称")
	@Dict(dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	private String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
    @ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private String zbwd;
	/**指标分值*/
	@Excel(name = "指标分值", width = 15)
    @ApiModelProperty(value = "指标分值")
	private java.math.BigDecimal zbfz;
	/**指标增减符号*/
	@Excel(name = "指标增减符号", width = 15,dicCode = "zjbs")
    @ApiModelProperty(value = "指标增减符号")
	@Dict(dicCode = "zjbs")
	private Integer zbabs;
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
	@Excel(name = "计分制", width = 15, dicCode = "jfz")
	@ApiModelProperty(value = "计分制")
	@Dict(dicCode = "jfz")
	private Integer jfz;
	/**考核类别*/
//	@Excel(name = "考核类别", width = 15,dicCode = "gwkhlb")
    @ApiModelProperty(value = "考核类别")
	@Dict(dicCode = "gwkhlb")
	private String khlb;

}
