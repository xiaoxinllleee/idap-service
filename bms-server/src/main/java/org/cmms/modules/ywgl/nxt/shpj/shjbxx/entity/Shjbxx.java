package org.cmms.modules.ywgl.nxt.shpj.shjbxx.entity;

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
 * @Description: 商户基本信息
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_SHJBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_SHJBXX对象", description="商户基本信息")
public class Shjbxx {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	@ExcelVerify(notNull = true)
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	@ExcelVerify(notNull = true)
	private String shmc;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	@ExcelVerify(notNull = true)
	private String lxdh;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
	@ApiModelProperty(value = "法人代表")
	@ExcelVerify(notNull = true)
	private String frdb;


	/**客户经理标识*/
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入日期*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrrq;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改日期*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgrq;


	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@ExcelVerify(notNull = true,interHandler = true)
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;

}
