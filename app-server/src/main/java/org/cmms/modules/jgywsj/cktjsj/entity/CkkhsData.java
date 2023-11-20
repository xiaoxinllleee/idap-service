package org.cmms.modules.jgywsj.cktjsj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 存款客户数
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
public class CkkhsData {
	/**组织标识*/
	@ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**年初存款业务*/
    @ApiModelProperty(value = "年初存款客户数")
	private Ckkhs ckkhsNc;
	/**月初存款业务*/
	@ApiModelProperty(value = "月初存款客户数")
	private Ckkhs ckkhsYc;
	/**月初存款业务*/
	@ApiModelProperty(value = "前四天存款客户数")
	private Ckkhs ckkhsFrontFourDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前三天存款客户数")
	private Ckkhs ckkhsFrontThreeDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前二天存款客户数")
	private Ckkhs ckkhsFrontTwoDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前一天存款客户数")
	private Ckkhs ckkhsFrontOneDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "当天存款客户数")
	private Ckkhs ckkhsTheDay;
}
