package org.cmms.modules.ywgl.djkyw.djkkhzr.entity;

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
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DJKKHZR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DJKKHZR对象", description="贷记卡考核责任")
public class Djkkhzr {
    
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	@ExcelVerify(notNull = true)
	private String djkkh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String jobnumber;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String custid;

	/**责任人客户经理标识*/
	@Excel(name = "责任客户经理标识", width = 15)
	@ApiModelProperty(value = "责任客户经理标识")
	private String custidzr;
	/**追责标志*/
	@Excel(name = "追责标志", width = 15,dicCode = "zzbs")
	@ApiModelProperty(value = "追责标志")
	@Dict(dicCode = "zzbs")
	@ExcelVerify(notNull = true)
	private Integer zzbz;
	/**追责日期*/
	@Excel(name = "追责日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "追责日期")
	@ExcelVerify(notNull = true)
	private Date zzrq;



	/**删除标志*/
//	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private Integer dflag;
	/**追责操作员*/
//	@Excel(name = "追责操作员", width = 15)
    @ApiModelProperty(value = "追责操作员")
	private String zzczy;
	/**暂不扣收*/
//	@Excel(name = "暂不扣收", width = 15)
    @ApiModelProperty(value = "暂不扣收")
	private Integer zbks;

	/**责任人工号*/
	@Excel(name = "责任人姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "责任人工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ExcelVerify(notNull = true,interHandler = true)
	private String jobnumberzr;
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
	@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;

}
