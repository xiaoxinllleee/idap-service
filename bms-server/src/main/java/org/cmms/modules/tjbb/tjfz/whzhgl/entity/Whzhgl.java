package org.cmms.modules.tjbb.tjfz.whzhgl.entity;

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
 * @Description: 外汇账户管理
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Data
@TableName("TJBB_TJFZ_WHZHGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_TJFZ_WHZHGL对象", description="外汇账户管理")
public class Whzhgl {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**类型*/
	@Excel(name = "类型", width = 15)
    @ApiModelProperty(value = "类型")
	private String lx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**账号*/
	@Excel(name = "账号", width = 15)
	@ApiModelProperty(value = "账号")
	@ExcelVerify(notNull = true,interHandler = true)
	private String zh;
}
