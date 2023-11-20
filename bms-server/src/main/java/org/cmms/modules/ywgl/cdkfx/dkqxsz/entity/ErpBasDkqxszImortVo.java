package org.cmms.modules.ywgl.cdkfx.dkqxsz.entity;

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
 * @Description: Erp_bas_dkqxsz
 * @Author: jeecg-boot
 * @Date:   2021-06-08
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_dkqxsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_dkqxsz对象", description="Erp_bas_dkqxsz")
public class ErpBasDkqxszImortVo {


	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;

	/**单笔贷款限额*/
	@Excel(name = "单笔贷款限额", width = 15)
    @ApiModelProperty(value = "单笔贷款限额")
	private java.math.BigDecimal dbdkxe;


	/**单户贷款限额*/
	@Excel(name = "单户贷款限额", width = 15)
    @ApiModelProperty(value = "单户贷款限额")
	private java.math.BigDecimal dhdkxe;
	/**有效标识*/
	@Excel(name = "有效标识", width = 15,dicCode = "yxbz")
    @ApiModelProperty(value = "有效标识")
	@Dict(dicCode = "yxbz")
	private String yxbz;

}
