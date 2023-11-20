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
public class JgkmsjCkResult {
	/**组织标识*/
	@ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**存款业绩*/
    @ApiModelProperty(value = "存款业绩")
	private String ckyj;
	/**年初存款业务*/
    @ApiModelProperty(value = "年初存款业务")
	private JgkmsjCk ckywNc;
	/**月初存款业务*/
	@ApiModelProperty(value = "月初存款业务")
	private JgkmsjCk ckywYc;
	/**月初存款业务*/
	@ApiModelProperty(value = "前四天存款业务")
	private JgkmsjCk ckywFrontFourDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前三天存款业务")
	private JgkmsjCk ckywFrontThreeDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前二天存款业务")
	private JgkmsjCk ckywFrontTwoDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "前一天存款业务")
	private JgkmsjCk ckywFrontOneDay;
	/**月初存款业务*/
	@ApiModelProperty(value = "当天存款业务")
	private JgkmsjCk ckywTheDay;
}
