package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity;

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
 * @Description: 客户经理等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-16
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUST_HZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUST_HZ对象", description="客户经理等级评定")
public class Khjldjpd {

	/**pdzq*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**pdrq*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**zzbz*/
	@Excel(name = "支行名称", width = 15,dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "sjzzjc")
    @ApiModelProperty(value = "支行名称")
	@Dict(dicCode = "zzbz", dictTable = "v_hr_bas_organization", dicText = "sjzzjc")
	@TableField(exist = false)
	private String zhmc;
	/**zzbz*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "Hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "Hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**gwbz*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**yggh*/
	@Excel(name = "客户经理名称", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "客户经理名称")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**pjdf*/
	@Excel(name = "评级得分", width = 15)
    @ApiModelProperty(value = "评级得分")
	private java.math.BigDecimal pjdf;
	/**ssdj*/
    @ApiModelProperty(value = "所属等级")
	@Dict(dicCode = "csbh",dictTable = "Grade_cust_cssz", dicText = "csmc",ds = "eweb")
	private Integer ssdj;
	/**ywjgxz*/
    @ApiModelProperty(value = "业务机构性质")
	private Integer ywjgxz;
	/**szqy*/
    @ApiModelProperty(value = "所在区域")
	private Integer szqy;
	/**sspjxs*/
    @ApiModelProperty(value = "sspjxs")
	private java.math.BigDecimal sspjxs;
	/**dkqx*/
    @ApiModelProperty(value = "dkqx")
	private String dkqx;
}
