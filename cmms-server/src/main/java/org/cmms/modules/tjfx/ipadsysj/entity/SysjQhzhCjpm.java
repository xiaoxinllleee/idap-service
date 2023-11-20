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
public class SysjQhzhCjpm {
	/**客户经理*/
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**有效走访户数*/
	private Integer yxzfhs;
	/**白名单有效走访户数*/
	private Integer bmdYxzfhs;
	/**授信未用信有效走访户数*/
	private Integer sxwyxYxzfhs;
	/**授信已用信有效走访户数*/
	private Integer sxyyxYxzfhs;
	/**不予授信有效走访户数*/
	private Integer bysxYxzfhs;
	/**存量客户有效走访户数*/
	private Integer clkhYxzfhs;
	/**走访排名*/
	private Integer zfpm;

}
