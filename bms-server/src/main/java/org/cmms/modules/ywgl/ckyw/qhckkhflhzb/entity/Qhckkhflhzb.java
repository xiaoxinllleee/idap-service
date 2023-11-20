package org.cmms.modules.ywgl.ckyw.qhckkhflhzb.entity;

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
 * @Description: 全行存款客户分类汇总表
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Data
@TableName("ERP_CKYW_QHCKKHFLHZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_CKYW_QHCKKHFLHZ对象", description="全行存款客户分类汇总表")
public class Qhckkhflhzb {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
	@DateTimeFormat(pattern="yyyy-MM")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**客户名称*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**最早开户日*/
	@Excel(name = "最早开户日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日")
	private Date zzkhr;
	/**存款余额*/
	@Excel(name = "存款金额", width = 15)
    @ApiModelProperty(value = "存款金额")
	private java.math.BigDecimal ckje;
	/**ckrpye*/
	@Excel(name = "存款月日平", width = 15)
	@ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckrpye;
	/**cknrpye*/
	@Excel(name = "存款年日平余额", width = 15)
	@ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**账户数*/
	@Excel(name = "账户数", width = 15)
    @ApiModelProperty(value = "账户数")
	private Integer zhs;
}
