package org.cmms.modules.tjfx.ipadsysj.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 平板端首页数据
 * @Author: jeecg-boot
 * @Date:   2020-07-23
 * @Version: V1.0
 */
@Data
public class Zhyxzfpm {


	/**统计维度*/
	private String tjwd;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;

	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;

	/**农户走访户数*/
	private Integer nhzfhs;
	/**商户走访户数*/
	private Integer shzfhs;
	/**走访总数*/
	private Integer zfzs;

	private Integer pm;

}
