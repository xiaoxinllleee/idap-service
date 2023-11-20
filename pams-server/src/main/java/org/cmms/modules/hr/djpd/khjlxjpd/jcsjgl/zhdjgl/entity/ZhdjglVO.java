package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 支行等级管理
 * @Author: jeecg-boot
 * @Date:   2021-09-03
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_ZHDJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_ZHDJ对象", description="支行等级管理")
public class ZhdjglVO {

	/**评定周期*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	@ExcelVerify(notNull = true)
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	@ExcelVerify(notNull = true)
	private Date pdrq;
	/**组织标识*/
	@Excel(name = "支行名称", width = 15,dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "支行名称")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**等级编号*/
	@Excel(name = "等级编号", width = 15)
    @ApiModelProperty(value = "等级编号")
	@ExcelVerify(notNull = true)
	private String djbh;
	/**等级描述*/
	@Excel(name = "等级描述", width = 15)
    @ApiModelProperty(value = "等级描述")
	@ExcelVerify(interHandler = true)
	private String djms;

}
