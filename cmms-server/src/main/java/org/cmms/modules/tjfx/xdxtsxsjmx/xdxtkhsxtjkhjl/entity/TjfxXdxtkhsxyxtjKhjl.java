package org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjkhjl.entity;

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
 * @Description: 客户经理授信用信数据
 * @Author: jeecg-boot
 * @Date:   2020-08-13
 * @Version: V1.0
 */
@Data
@TableName("TJFX_XDXTKHSXYXTJ_KHJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_XDXTKHSXYXTJ_KHJL对象", description="客户经理授信用信数据")
public class TjfxXdxtkhsxyxtjKhjl {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**用信额度*/
	@Excel(name = "用信额度", width = 15)
    @ApiModelProperty(value = "用信额度")
	private java.math.BigDecimal yxed;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**合同可用金额*/
	@Excel(name = "合同可用金额", width = 15)
    @ApiModelProperty(value = "合同可用金额")
	private java.math.BigDecimal htkyje;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Long sxhs;
	/**用信户数排名*/
	@Excel(name = "用信户数排名", width = 15)
    @ApiModelProperty(value = "用信户数排名")
	private Long yxhspm;
}
