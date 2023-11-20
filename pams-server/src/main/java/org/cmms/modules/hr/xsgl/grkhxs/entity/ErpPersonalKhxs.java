package org.cmms.modules.hr.xsgl.grkhxs.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人系数管理
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Data
@TableName("ERP_PERSONAL_KHXS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_PERSONAL_KHXS对象", description="个人系数管理")
public class ErpPersonalKhxs {


	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@ExcelVerify(notNull = true)
	@Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String yggh;
	@TableField(exist = false)
	private String ygxm;

	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**考核系数*/
	@Excel(name = "考核系数", width = 15)
    @ApiModelProperty(value = "考核系数")
	@ExcelVerify(notNull = true, interHandler = true)
	private java.math.BigDecimal khxs;
	/**不参与考核系数*/
	@Excel(name = "不参与考核系数", width = 15)
    @ApiModelProperty(value = "不参与考核系数")
	private java.math.BigDecimal bcykhxs;
	/**总系数*/
	@Excel(name = "总系数", width = 15)
    @ApiModelProperty(value = "总系数")
	private java.math.BigDecimal zxs;
	/**有效开始时间*/
//	@Excel(name = "有效开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效开始时间")
	private Date kssj;
	/**有效结束时间*/
//	@Excel(name = "有效结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "有效结束时间")
	private Date jssj;
}
