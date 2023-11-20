package org.cmms.modules.dkjkpt.dkjkptfxtsh.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 金卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Data
public class DkjkptDjkFmkVo {


	/**序号*/
	private String xh;
	/**姓名*/
	private String xm;
	/**信用额度*/
	@Excel(name = "信用额度", width = 15)
    @ApiModelProperty(value = "信用额度")
	private java.math.BigDecimal xyed;
	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
    @ApiModelProperty(value = "逾期期数")
	private String yqqs;
	/**账单最小还款额*/
	@Excel(name = "账单最小还款额", width = 15)
    @ApiModelProperty(value = "账单最小还款额")
	private java.math.BigDecimal zdzxhke;
	/**透支金额*/
	@Excel(name = "透支金额", width = 15)
    @ApiModelProperty(value = "透支金额")
	private java.math.BigDecimal tzje;
	/**透支本金*/
	@Excel(name = "透支本金", width = 15,groupName = "其中")
    @ApiModelProperty(value = "透支本金")
	private java.math.BigDecimal tzbj;
	/**违约金*/
	@Excel(name = "违约金", width = 15,groupName = "其中")
    @ApiModelProperty(value = "违约金")
	private java.math.BigDecimal wyj;
	/**罚息*/
	@Excel(name = "罚息", width = 15,groupName = "其中")
    @ApiModelProperty(value = "罚息")
	private java.math.BigDecimal fx;
	/**推广人员*/
	@Excel(name = "推广人员", width = 15)
    @ApiModelProperty(value = "推广人员")
	private String tgry;

}
