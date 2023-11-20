package org.cmms.modules.jgywsj.dktjsj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 机构科目数据_存款
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
public class DktjsjResult {
	/**组织标识*/
	@ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**贷款业绩*/
    @ApiModelProperty(value = "贷款业绩")
	private String dkyj;
	/**年初贷款业务*/
    @ApiModelProperty(value = "年初贷款业务")
	private Dktjsj dkywNc;
	/**月初贷款业务*/
	@ApiModelProperty(value = "月初贷款业务")
	private Dktjsj dkywYc;
	/**前四天贷款业务*/
	@ApiModelProperty(value = "前四天贷款业务")
	private Dktjsj dkywFrontFourDay;
	/**前三天贷款业务*/
	@ApiModelProperty(value = "前三天贷款业务")
	private Dktjsj dkywFrontThreeDay;
	/**前二天贷款业务*/
	@ApiModelProperty(value = "前二天贷款业务")
	private Dktjsj dkywFrontTwoDay;
	/**前一天贷款业务*/
	@ApiModelProperty(value = "前一天贷款业务")
	private Dktjsj dkywFrontOneDay;
	/**当天贷款业务*/
	@ApiModelProperty(value = "当天贷款业务")
	private Dktjsj dkywTheDay;
}
