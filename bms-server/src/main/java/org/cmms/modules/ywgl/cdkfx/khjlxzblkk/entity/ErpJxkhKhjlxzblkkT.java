package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity;

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
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Data
@TableName("Erp_jxkh_khjlxzblkk_t")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_jxkh_khjlxzblkk_t对象", description="客户经理新增不良扣款")
public class ErpJxkhKhjlxzblkkT {

	/**考核月份*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	@ExcelVerify(notNull = true)
	private Date khyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ExcelVerify(notNull = true)
	private String yggh;

	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;





	/**岗位名称*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;

	/**2019年前不良余额*/
	@Excel(name = "2019年前不良余额", width = 15)
    @ApiModelProperty(value = "2019年前不良余额")
	@TableField(value = "blye_1")
	private java.math.BigDecimal blye1;
	/**2019年前本期应计扣款档次(元)*/
	@Excel(name = "2019年前本期应计扣款档次(元)", width = 15)
    @ApiModelProperty(value = "2019年前本期应计扣款档次(元)")
	@TableField(value = "yjkh_1")
	private java.math.BigDecimal yjkh1;
	/**2019年后第一责任人不良余额(万元)*/
	@Excel(name = "2019年后第一责任人不良余额(万元)", width = 15)
    @ApiModelProperty(value = "2019年后第一责任人不良余额(万元)")
	private java.math.BigDecimal dyzrrblye;
	/**2019年后管理责任人不良余额(万元)*/
	@Excel(name = "2019年后管理责任人不良余额(万元)", width = 15)
    @ApiModelProperty(value = "2019年后管理责任人不良余额(万元)")
	private java.math.BigDecimal glzrrblye;
	/**2019年后本期应计扣款金额(万元)*/
	@Excel(name = "2019年后本期应计扣款金额(万元)", width = 15)
    @ApiModelProperty(value = "2019年后本期应计扣款金额(万元)")
	@TableField(value = "yjkh_2")
	private java.math.BigDecimal yjkh2;
	/**本期应计扣款总额(元)*/
	@Excel(name = "本期应计扣款总额(元)", width = 15)
    @ApiModelProperty(value = "本期应计扣款总额(元)")
	private java.math.BigDecimal bqyjze;
	/**上期实扣总额(元)*/
	@Excel(name = "上期实扣总额(元)", width = 15)
    @ApiModelProperty(value = "上期实扣总额(元)")
	private java.math.BigDecimal sqsk;
	/**本期应扣金额(元)*/
	@Excel(name = "本期应扣金额(元)", width = 15)
    @ApiModelProperty(value = "本期应扣金额(元)")
	private java.math.BigDecimal bqyk;
	/**本期应退金额(元)*/
	@Excel(name = "本期应退金额(元)", width = 15)
    @ApiModelProperty(value = "本期应退金额(元)")
	private java.math.BigDecimal bqyt;

	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;


	/**本期实扣*/
	@Excel(name = "本期实扣(元)", width = 15)
	@ApiModelProperty(value = "本期实扣(元)")
	@ExcelVerify(notNull = true,interHandler = true)
	private java.math.BigDecimal bqsk;


}
