package org.cmms.modules.dklldj.tjfxgl.zhsftjfx.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 支行上浮统计(浏阳-Version)
 * @Author: jeecg-boot
 * @Date:   2022-01-19
 * @Version: V1.0
 */
@Data
@TableName("rate_tjfx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_tjfx对象", description="支行上浮统计(浏阳-Version)")
public class RateTjfx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**统计类型(YYYY 年 Q 季 MM 月)*/
	@Excel(name = "统计类型", width = 15, dicCode = "rqwd")
	@ApiModelProperty(value = "统计类型(YYYY 年 Q 季 MM 月)")
	@Dict(dicCode = "rqwd")
	private String tjlx;
	/**组织类别*/
	@Excel(name = "组织类别", width = 15, dicCode = "zzlb")
	@ApiModelProperty(value = "组织类别")
	@Dict(dicCode = "zzlb")
	private Integer zzlb;
	/**组织标志*/
	@Excel(name = "组织简称", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**最高上浮幅度*/
	@Excel(name = "最高上浮幅度", width = 15)
	@ApiModelProperty(value = "最高上浮幅度")
	private java.math.BigDecimal zgsffd;
	/**最低上浮幅度*/
	@Excel(name = "最低上浮幅度", width = 15)
	@ApiModelProperty(value = "最低上浮幅度")
	private java.math.BigDecimal zdsffd;
	/**平均上浮幅度*/
	@Excel(name = "平均上浮幅度", width = 15)
	@ApiModelProperty(value = "平均上浮幅度")
	private java.math.BigDecimal pjsffd;
	/**最高基点(加/减)BP*/
	@Excel(name = "最高基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "最高基点(加/减)BP")
	private java.math.BigDecimal zgjdbp;
	/**最低基点(加/减)BP*/
	@Excel(name = "最低基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "最低基点(加/减)BP")
	private java.math.BigDecimal zdjdbp;
	/**平均基点(加/减)BP*/
	@Excel(name = "平均基点(加/减)BP", width = 15)
	@ApiModelProperty(value = "平均基点(加/减)BP")
	private java.math.BigDecimal pjjdbp;
}
