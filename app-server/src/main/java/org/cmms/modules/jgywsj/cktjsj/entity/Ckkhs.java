package org.cmms.modules.jgywsj.cktjsj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 存款客户数
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
public class Ckkhs {
	//统计日期
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;
	//前端展示日期
	private String date;
	//在档客户
	private Integer zdkh = 0;
	//有效客户
	private Integer yxkh = 0;
	//优质客户
	private Integer yzkh = 0;
}
