package org.cmms.modules.jgywsj.dktjsj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 机构科目数据_存款
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
public class Dktjsj {
    
	/**统计日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**贷款余额*/
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**表内不良贷款余额*/
    @ApiModelProperty(value = "表内不良贷款余额")
	private java.math.BigDecimal bnbl;

}
