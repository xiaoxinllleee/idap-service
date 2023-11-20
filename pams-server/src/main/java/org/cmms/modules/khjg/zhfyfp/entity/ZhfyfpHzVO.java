package org.cmms.modules.khjg.zhfyfp.entity;

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
 * @Description: 支行费用分配
 * @Author: jeecg-boot
 * @Date:   2023-04-04
 * @Version: V1.0
 */
@Data
@TableName("BASIC_WAGE_ZHFYFP_HZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BASIC_WAGE_ZHFYFP_HZ对象", description="支行费用分配")
public class ZhfyfpHzVO {
    

	/**分配月份*/
	@Excel(name = "分配月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分配月份")
	private Date fpyf;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**费用类型*/
	@Excel(name = "费用类型", width = 15,dicCode = "fylx")
    @ApiModelProperty(value = "费用类型")
	@Dict(dicCode = "fylx")
	private Integer fylx;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	private java.math.BigDecimal zje;

}
