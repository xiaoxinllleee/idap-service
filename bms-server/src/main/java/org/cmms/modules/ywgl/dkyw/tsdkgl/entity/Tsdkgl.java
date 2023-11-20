package org.cmms.modules.ywgl.dkyw.tsdkgl.entity;

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
 * @Description: 特殊贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-26
 * @Version: V1.0
 */
@Data
@TableName("V_ERP_BAS_TSDKGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_ERP_BAS_TSDKGL对象", description="特殊贷款管理")
public class Tsdkgl {
    
	/**jgdm*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**custName*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String custName;
	/**dkzh*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**dkje*/
	@Excel(name = "贷款金额", width = 15)
	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**jkrq*/
	@Excel(name = "借款日期", width = 15)
	@ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**dqrq*/
	@Excel(name = "到期日期", width = 15)
	@ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**custType*/
//	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkkhlx")
	private String custType;
	/**dkbq*/
	@Excel(name = "贷款标签", width = 15, dicCode = "dkbq")
    @ApiModelProperty(value = "贷款标签")
	@Dict(dicCode = "dkbq")
	private String dkbq;

	/**ll*/
//	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal ll;
	/**dbfs*/
//	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
	private String dbfs;
	/**dkzt*/
//	@Excel(name = "贷款状态", width = 15)
    @ApiModelProperty(value = "贷款状态")
	@Dict(dicCode = "wjflbz")
	private String dkzt;

	/**ygkh*/
//	@Excel(name = "员工考核", width = 15)
    @ApiModelProperty(value = "员工考核")
	@Dict(dicCode = "khzt")
	private String ygkh;
	/**jgkh*/
//	@Excel(name = "机构考核", width = 15)
    @ApiModelProperty(value = "机构考核")
	@Dict(dicCode = "khzt")
	private String jgkh;
	/**bz*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**zkbl*/
//	@Excel(name = "折扣比例", width = 15)
    @ApiModelProperty(value = "折扣比例")
	private java.math.BigDecimal zkbl;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**lrbz*/
//	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**xgr*/
//	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**xgsj*/
//	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
}
