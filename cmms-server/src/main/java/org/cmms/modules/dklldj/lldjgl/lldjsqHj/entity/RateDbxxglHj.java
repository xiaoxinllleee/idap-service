package org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity;

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
 * @Description: 利率定价担保信息
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Data
@TableName("rate_dbxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_dbxxgl对象", description="RateGzbdsxx")
public class RateDbxxglHj {

	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**身份证号或机构代码证编号*/
	@Excel(name = "身份证号或机构代码证编号", width = 15)
    @ApiModelProperty(value = "身份证号或机构代码证编号")
	private String zjhm;

	/**所属支行*/
	@Excel(name = "组织标识", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;

	/**担保物*/
	@Excel(name = "担保物", width = 15, dicCode = "dbw")
	@ApiModelProperty(value = "担保物")
	@Dict(dicCode = "dbw")
	private String dbw;

	/**所有权人*/
	@Excel(name = "所有权人", width = 15)
    @ApiModelProperty(value = "所有权人")
	private String syqr;
	/**评估价值*/
	@Excel(name = "评估价值", width = 15)
    @ApiModelProperty(value = "评估价值")
	private Long pgjz;
	/**担保率*/
	@Excel(name = "担保率", width = 15)
    @ApiModelProperty(value = "担保率")
	private Integer dbl;
	/**实际担保金额*/
	@Excel(name = "实际担保金额", width = 15)
    @ApiModelProperty(value = "实际担保金额")
	private Long sjdbje;

	/**担保类型*/
	@Excel(name = "担保类型", width = 15,dicCode = "dblx")
    @ApiModelProperty(value = "担保类型")
	@Dict(dicCode = "dblx")
	private String dblx;

	/**担保物描述*/
	@Excel(name = "担保物描述", width = 15)
    @ApiModelProperty(value = "担保物描述")
	private String dbwms;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
}
