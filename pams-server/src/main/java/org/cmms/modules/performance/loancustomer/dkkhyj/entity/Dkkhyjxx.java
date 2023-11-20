package org.cmms.modules.performance.loancustomer.dkkhyj.entity;

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
 * @Description: 贷款客户移交
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Data
@TableName("v_khgxgl_dkkhyjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgxgl_dkkhyjxx对象", description="贷款客户移交")
public class Dkkhyjxx {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private java.lang.String id;

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private java.lang.String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型(0 其他 1 身份证)*/
	/*@Excel(name = "证件类型(0 其他 1 身份证)", width = 15)
    @ApiModelProperty(value = "证件类型(0 其他 1 身份证)")
	private java.lang.String zjlx;*/
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "cust_type")
	private java.lang.Integer khlx;
	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private java.lang.String cpxx;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private java.lang.String hth;
	/**管户人*/
	@Excel(name = "管户人", width = 15)
    @ApiModelProperty(value = "管户人")
	private java.lang.String ghr;
	/**管户比例(%)*/
	@Excel(name = "管户比例(%)", width = 15)
    @ApiModelProperty(value = "管户比例(%)")
	private java.math.BigDecimal ghbl;
	/**管户类型*/
	@Excel(name = "管户类型", width = 15)
    @ApiModelProperty(value = "管户类型")
    @Dict(dicCode = "manager_acc_types")
	private java.lang.Integer ghlx;
	/**合同余额*/
	@Excel(name = "合同余额", width = 15)
    @ApiModelProperty(value = "合同余额")
	private java.math.BigDecimal htje;
	/**htye*/
	@Excel(name = "htye", width = 15)
    @ApiModelProperty(value = "htye")
	private java.math.BigDecimal htye;
	/**最早合同发放日期*/
	@Excel(name = "最早合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同发放日期")
	private java.util.Date zzhtffrq;
	/**最早合同到期日期*/
	@Excel(name = "最早合同到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早合同到期日期")
	private java.util.Date zzhtdqrq;
}
