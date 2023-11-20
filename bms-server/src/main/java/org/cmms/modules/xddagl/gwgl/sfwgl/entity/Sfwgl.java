package org.cmms.modules.xddagl.gwgl.sfwgl.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_sfwgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_sfwgl对象", description="收发文管理")
public class Sfwgl {

	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
	private String bt;
	/**发文编号*/
	@Excel(name = "发文编号", width = 15)
    @ApiModelProperty(value = "发文编号")
	@ExcelVerify(notNull = true)
	private String fwbh;
	/**呈文单位*/
	@Excel(name = "发文部室标识", width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization_cmms",dicText = "zzjc")
    @ApiModelProperty(value = "发文部室标识")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization_cmms",dicText = "zzjc")
	private String cwdw;
	/**负责人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
	private String fzr;
	/**签发人*/
	@Excel(name = "签发人", width = 15)
    @ApiModelProperty(value = "签发人")
	private String qfr;
	/**签发日期*/
	@Excel(name = "发文日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发文日期")
	private Date qfrq;
	/**内容摘要*/
	@Excel(name = "内容摘要", width = 15)
    @ApiModelProperty(value = "内容摘要")
	private String nrzy;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	@ExcelVerify(interHandler = true)
	private String bz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**收文单位*/
	@Excel(name = "收文单位", width = 15)
    @ApiModelProperty(value = "收文单位")
	private String swdw;
}
