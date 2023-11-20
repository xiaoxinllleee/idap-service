package org.cmms.modules.ywgl.dkyw.dksjzrgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DKZHKHZR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DKZHKHZR对象", description="贷款数据责任管理")
public class DksjzrglVo {

	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**责任人姓名*/
	@Excel(name = "责任人工号", width = 15)
	@ApiModelProperty(value = "责任人姓名")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String jobnumberzr;
	/**是否异议不接收*/
	@Excel(name = "是否异议不接收", width = 15)
	@ApiModelProperty(value = "是否异议不接收")
	@Dict(dicCode = "sfbz")
	private Integer sfyybjs;
	/**统计月份*/
	@Excel(name = "统计月份(格式:yyyy-MM)", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date DataDate;
	/**追责标记*/
	@Excel(name = "追责标记", width = 15,dicCode = "zzbs")
	@ApiModelProperty(value = "追责标记")
	@Dict(dicCode = "zzbs")
	private String zzbz;
	/**追责日期*/
	@Excel(name = "追责日期(格式:yyyy-MM-dd)", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "追责日期")
	private Date zzrq;



}
