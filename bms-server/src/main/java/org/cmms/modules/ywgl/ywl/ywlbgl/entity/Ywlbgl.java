package org.cmms.modules.ywgl.ywl.ywlbgl.entity;

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
 * @Description: 业务类别管理
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YWLBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YWLBXX对象", description="业务类别管理")
public class Ywlbgl {

	/**业务类别代码*/
	@Excel(name = "业务类别代码", width = 15)
    @ApiModelProperty(value = "业务类别代码")
	@ExcelVerify(notNull = true)
	private String ywlbdm;
	/**业务类别名称*/
	@Excel(name = "业务类别名称", width = 15)
    @ApiModelProperty(value = "业务类别名称")
	@ExcelVerify(notNull = true)
	private String ywlbmc;
	/**对应交易码*/
	@Excel(name = "对应交易码", width = 15)
    @ApiModelProperty(value = "对应交易码")
	@ExcelVerify(notNull = true)
	private String dyjym;
	/**折算笔数*/
	@Excel(name = "折算笔数", width = 15)
    @ApiModelProperty(value = "折算笔数")
	private java.math.BigDecimal zsbs;
	/**折算计算式*/
	@Excel(name = "折算计算式", width = 15)
    @ApiModelProperty(value = "折算计算式")
	private String zsbseval;
	/**分配标志*/
	@Excel(name = "分配标志", width = 15, dicCode = "fpbz")
    @ApiModelProperty(value = "分配标志")
	@Dict(dicCode = "fpbz")
	private Integer fpbz;
	/**分配比例*/
	@Excel(name = "分配比例", width = 15)
    @ApiModelProperty(value = "分配比例")
	private java.math.BigDecimal fpbl;
	/**有效标志 0无效 1有效*/
	@Excel(name = "有效标志", width = 15, dicCode = "sfqy")
    @ApiModelProperty(value = "有效标志")
	@Dict(dicCode = "sfqy")
	private Integer yxbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15, dicCode = "username", dictTable = "sys_user", dicText = "realname")
    @ApiModelProperty(value = "录入操作员")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String lrczy;
}
