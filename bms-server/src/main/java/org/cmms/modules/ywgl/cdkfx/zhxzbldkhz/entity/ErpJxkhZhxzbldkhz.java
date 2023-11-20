package org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 支行新增不良贷款汇总
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_JXKH_ZHXZBLDKHZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_JXKH_ZHXZBLDKHZ对象", description="支行新增不良贷款汇总")
public class ErpJxkhZhxzbldkhz {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**本期新增不良(万元)*/
	@Excel(name = "本期新增不良(万元)", width = 15)
    @ApiModelProperty(value = "本期新增不良(万元)")
	private java.math.BigDecimal bqxzbl;
	/**表外不良(万元)*/
	@Excel(name = "表外不良(万元)", width = 15)
    @ApiModelProperty(value = "表外不良(万元)")
	private java.math.BigDecimal bwbl;
	/**表内不良(万元)*/
	@Excel(name = "表内不良(万元)", width = 15)
    @ApiModelProperty(value = "表内不良(万元)")
	private java.math.BigDecimal bnbl;
	/**户数*/
	@Excel(name = "户数", width = 15)
    @ApiModelProperty(value = "户数")
	private Long bnxzhs;
	/**本金不良余额(万元)*/
	@Excel(name = "本金不良余额(万元)", width = 15)
    @ApiModelProperty(value = "本金不良余额(万元)")
	private java.math.BigDecimal bnbjbl;
	/**利息不良余额(万元)*/
	@Excel(name = "利息不良余额(万元)", width = 15)
    @ApiModelProperty(value = "利息不良余额(万元)")
	private java.math.BigDecimal bnlxbl;
}
