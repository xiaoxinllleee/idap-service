package org.cmms.modules.sjxf.hxxt.lslscx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 历史流水查询
 * @Author: jeecg-boot
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Data
@TableName("LYZH_JYLS_CHANGE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LYZH_JYLS_CHANGE对象", description="历史流水查询")
public class LyzhJyjsChange {

	/**户名*/
	@Excel(name = "户名", width = 20)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**账号*/
	@Excel(name = "账号", width = 22)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**代理机构*/
	//@Excel(name = "代理机构", width = 20)
    @ApiModelProperty(value = "代理机构")
	private String dljg;
	/**代理机构名称*/
	@Excel(name = "代理机构名称", width = 15)
    @ApiModelProperty(value = "代理机构名称")
	private String dljgmc;
	/**机构代码*/
	//@Excel(name = "机构代码", width = 20)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**机构名称*/
	//@Excel(name = "机构名称", width = 20)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**科目号*/
	//@Excel(name = "科目号", width = 20)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**科目号名称*/
	//@Excel(name = "科目号名称", width = 20)
    @ApiModelProperty(value = "科目号名称")
	private String kmmc;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "交易日期")
	private Date jyrq;
	/**交易时间*/
	//@Excel(name = "交易时间", width = 20)
    @ApiModelProperty(value = "交易时间")
	private String jysj;
	/**交易码*/
	//@Excel(name = "交易码", width = 10)
    @ApiModelProperty(value = "交易码")
	private String jym;
	/**交易码名称*/
	@Excel(name = "交易码名称", width = 20)
    @ApiModelProperty(value = "交易码名称")
	private String jymmc;
	/**交易金额*/
	@Excel(name = "交易金额(元)", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal jyje;
	/**账户余额*/
	@Excel(name = "余额(元)", width = 15)
    @ApiModelProperty(value = "账户余额")
	private java.math.BigDecimal zhye;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 10)
    @ApiModelProperty(value = "借贷标志")
	private String jdbz;
	/**记账柜员号*/
	//@Excel(name = "记账柜员号", width = 20)
    @ApiModelProperty(value = "记账柜员号")
	private String jzgy;
	/**记账柜员名称*/
	@Excel(name = "记账柜员名称", width = 20)
    @ApiModelProperty(value = "记账柜员名称")
	private String jzgymc;
	/**复核柜员号*/
	//@Excel(name = "复核柜员号", width = 20)
    @ApiModelProperty(value = "复核柜员号")
	private String fhgy;
	/**复核柜员名称*/
	//@Excel(name = "复核柜员名称", width = 20)
    @ApiModelProperty(value = "复核柜员名称")
	private String fhgymc;
	/**对应账号*/
	@Excel(name = "对应账号", width = 22)
    @ApiModelProperty(value = "对应账号")
	private String dyzh;
	/**对应账号户名*/
	@Excel(name = "对应账号户名", width = 20)
    @ApiModelProperty(value = "对应账号户名")
	private String dyzhhm;
	/**积数*/
	//@Excel(name = "积数", width = 20)
    @ApiModelProperty(value = "积数")
	private String zhjs;
	/**摘要*/
	//@Excel(name = "摘要", width = 20)
    @ApiModelProperty(value = "摘要")
	private String zy;
	/**柜员流水*/
	//@Excel(name = "柜员流水", width = 20)
    @ApiModelProperty(value = "柜员流水")
	private String gylsh;
}
