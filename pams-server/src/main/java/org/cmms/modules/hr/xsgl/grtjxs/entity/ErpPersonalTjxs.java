package org.cmms.modules.hr.xsgl.grtjxs.entity;

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
 * @Description: 个人调节系数
 * @Author: jeecg-boot
 * @Date:   2023-10-30
 * @Version: V1.0
 */
@Data
@TableName("ERP_PERSONAL_TJXS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_PERSONAL_TJXS对象", description="个人调节系数")
public class ErpPersonalTjxs {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	@ExcelVerify(notNull = true)
	private String yggh;
	/**调节类型*/
	@Excel(name = "调节类型", width = 15, dicCode = "xstjlx")
    @ApiModelProperty(value = "调节类型")
	@Dict(dicCode = "xstjlx")
	@ExcelVerify(notNull = true)
	private String tjlx;
	/**调节系数*/
	@Excel(name = "调节系数", width = 15)
    @ApiModelProperty(value = "调节系数")
	private java.math.BigDecimal tjxs;
	/**生效日期起*/
	@Excel(name = "生效日期起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生效日期起")
	@ExcelVerify(notNull = true)
	private Date sxrqBegin;
	/**生效日期止*/
	@Excel(name = "生效日期止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生效日期止")
	private Date sxrqEnd;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	@ExcelVerify(interHandler = true)
	private String bz;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
