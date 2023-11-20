package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity;

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
 * @Description: 员工关联存款客户明细
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_khfz_ygglkhmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_khfz_ygglkhmx对象", description="员工关联存款客户明细")
public class CkjkptKhfzYgglkhmx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "所属岗位", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc" )
	@ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private String gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**户名*/
	@Excel(name = "户名", width = 15)
	@ApiModelProperty(value = "户名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电环号码*/
	@Excel(name = "联系电话", width = 15)
	@ApiModelProperty(value = "联系电话")
	private String dhhm;
	/**存量余额*/
	@Excel(name = "(年初)存款余额", width = 15)
	@ApiModelProperty(value = "存量余额")
	private java.math.BigDecimal clye;
	/**存量月日平*/
	@Excel(name = "(年初)存款月日平", width = 15)
	@ApiModelProperty(value = "存量月日平")
	private java.math.BigDecimal clyrp;
	/**存量年日平*/
	@Excel(name = "(年初)存款年日平", width = 15)
	@ApiModelProperty(value = "存量年日平")
	private java.math.BigDecimal clnrp;
	/**存款余额*/
	@Excel(name = "(本月)存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款月日平余额*/
	@Excel(name = "(本月)存款月日平", width = 15)
    @ApiModelProperty(value = "存款月日平余额")
	private java.math.BigDecimal ckyrpye;
	/**存款年日平余额*/
	@Excel(name = "(本月)存款年日平", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**折算后存款余额*/
	@Excel(name = "(关联考核数据)存款余额", width = 15)
    @ApiModelProperty(value = "折算后存款余额")
	private java.math.BigDecimal zshckye;
	/**折算后存款月日平余额*/
	@Excel(name = "(关联考核数据)存款月日平", width = 15)
    @ApiModelProperty(value = "折算后存款月日平余额")
	private java.math.BigDecimal zshckyrpye;
	/**折算后存款年日平余额*/
	@Excel(name = "(关联考核数据)存款年日平", width = 15)
    @ApiModelProperty(value = "折算后存款年日平余额")
	private java.math.BigDecimal zhscknrpye;

	/**lrr*/
	//@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**lrsj*/
	//@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrbz*/
	//@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;

}
