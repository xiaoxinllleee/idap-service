package org.cmms.modules.jgywsj.jgkmsj.entity;

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
public class JgkmsjCk {
    
	/**统计日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**存款余额*/
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日均*/
    @ApiModelProperty(value = "存款日均")
	private java.math.BigDecimal ckrj;
	/**低息余额*/
    @ApiModelProperty(value = "低息余额")
	private java.math.BigDecimal dxye;
	/**低息日均*/
    @ApiModelProperty(value = "低息日均")
	private java.math.BigDecimal dxrj;

}
