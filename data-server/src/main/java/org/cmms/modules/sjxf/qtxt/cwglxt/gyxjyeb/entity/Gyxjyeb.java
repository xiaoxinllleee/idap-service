package org.cmms.modules.sjxf.qtxt.cwglxt.gyxjyeb.entity;

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
 * @Description: 柜员现金余额薄
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_cash_tel_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_cash_tel_reg对象", description="柜员现金余额薄")
public class Gyxjyeb {

	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String tel;
	/**财务现金额*/
	@Excel(name = "财务现金额", width = 15)
    @ApiModelProperty(value = "财务现金额")
	private java.math.BigDecimal cashBal;
	/**财务现金限额*/
	@Excel(name = "财务现金限额", width = 15)
    @ApiModelProperty(value = "财务现金限额")
	private java.math.BigDecimal limitBal;
	/**操作员所属部门*/
	@Excel(name = "操作员所属部门", width = 15)
    @ApiModelProperty(value = "操作员所属部门")
	private String depNo;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**所属联社*/
	@Excel(name = "所属联社", width = 15)
    @ApiModelProperty(value = "所属联社")
	private String upBrNo;
	/**控制级别*/
	@Excel(name = "控制级别", width = 15)
    @ApiModelProperty(value = "控制级别")
	private String controllvl;
	/**是否财务现金操作员*/
	@Excel(name = "是否财务现金操作员", width = 15)
    @ApiModelProperty(value = "是否财务现金操作员")
	private String isCashtel;
	/**账务部门*/
	@Excel(name = "账务部门", width = 15)
    @ApiModelProperty(value = "账务部门")
	private String dcBrNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
