package org.cmms.modules.sjxf.qtsjcx.jylscx.jycx.entity;

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
 * @Description: 交易查询
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("V_jyls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_jyls对象", description="交易查询")
public class Jycx {
    
	/**custName*/
	@Excel(name = "交易户名", width = 15)
    @ApiModelProperty(value = "交易户名")
	private String custName;
	/**accNo*/
	@Excel(name = "交易账号", width = 15)
    @ApiModelProperty(value = "交易账号")
	private String accNo;
	/**trnDate*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String trnDate;
	/**trnTime*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String trnTime;
	/**brNo*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brNo;
	/**brName*/
	@Excel(name = "交易机构名称", width = 15)
    @ApiModelProperty(value = "交易机构名称")
	private String brName;
	/**oppAccNo*/
	@Excel(name = "对方账号", width = 15)
    @ApiModelProperty(value = "对方账号")
	private String oppAccNo;
	/**oppAccName*/
	@Excel(name = "对方户名", width = 15)
    @ApiModelProperty(value = "对方户名")
	private String oppAccName;
	/**oppBrNo*/
	@Excel(name = "对方机构号", width = 15)
    @ApiModelProperty(value = "对方机构号")
	private String oppBrNo;
	/**oppBrName*/
	@Excel(name = "对方机构名称", width = 15)
    @ApiModelProperty(value = "对方机构名称")
	private String oppBrName;
	/**trxCode*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String trxCode;
	/**trxName*/
	@Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
	private String trxName;
	/**amount*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal amount;
	/**tellName*/
	@Excel(name = "柜员名称", width = 15)
    @ApiModelProperty(value = "柜员名称")
	private String tellName;
}
