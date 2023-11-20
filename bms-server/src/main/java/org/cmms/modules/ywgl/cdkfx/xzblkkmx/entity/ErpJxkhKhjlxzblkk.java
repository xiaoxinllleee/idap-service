package org.cmms.modules.ywgl.cdkfx.xzblkkmx.entity;

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
 * @Description: 新增不良扣款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_JXKH_KHJLXZBLKK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_JXKH_KHJLXZBLKK对象", description="新增不良扣款明细")
public class ErpJxkhKhjlxzblkk {
	/**考核月份*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	private Date khyf;
	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String zhjgdm;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**岗位*/
	@Excel(name = "岗位", width = 15)
    @ApiModelProperty(value = "岗位")
	private Integer postid;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String custid;
	/**本期不良余额(元)*/
	@Excel(name = "本期不良余额(元)", width = 15)
	@ApiModelProperty(value = "本期不良余额(元)")
	private java.math.BigDecimal bqbldkye;
	/**本期扣款档次(元)*/
	@Excel(name = "本期扣款档次(元)", width = 15)
	@ApiModelProperty(value = "本期扣款档次(元)")
	private java.math.BigDecimal bqkkjedc;
	/**上期应扣(元)*/
	@Excel(name = "上期应扣(元)", width = 15)
	@ApiModelProperty(value = "上期应扣(元)")
	private java.math.BigDecimal sqykje;
	/**上期实扣(元)*/
	@Excel(name = "上期实扣(元)", width = 15)
	@ApiModelProperty(value = "上期实扣(元)")
	private java.math.BigDecimal sqskje;
	/**上期未扣(元)*/
	@Excel(name = "上期未扣(元)", width = 15)
	@ApiModelProperty(value = "上期未扣(元)")
	private java.math.BigDecimal sqsyykje;
	/**本期应扣(元)*/
	@Excel(name = "本期应扣(元)", width = 15)
	@ApiModelProperty(value = "本期应扣(元)")
	private java.math.BigDecimal bqykje;
	/**本期实扣(元)*/
	@Excel(name = "本期实扣(元)", width = 15)
	@ApiModelProperty(value = "本期实扣(元)")
	private java.math.BigDecimal bqskje;
	/**本期未扣(元)*/
	@Excel(name = "本期未扣(元)", width = 15)
	@ApiModelProperty(value = "本期未扣(元)")
	private java.math.BigDecimal bqsyykje;
	/**本期退回(元)*/
	@Excel(name = "本期退回(元)", width = 15)
	@ApiModelProperty(value = "本期退回(元)")
	private java.math.BigDecimal ythkk;
	/**已扣不良金额(元)*/
	@Excel(name = "已扣不良金额(元)", width = 15)
	@ApiModelProperty(value = "已扣不良金额(元)")
	private java.math.BigDecimal ykblje;
	/**本金不良*/
	@Excel(name = "本金不良", width = 15)
	@ApiModelProperty(value = "本金不良")
	private java.math.BigDecimal bjbl;
	/**利息不良*/
	@Excel(name = "利息不良", width = 15)
	@ApiModelProperty(value = "利息不良")
	private java.math.BigDecimal lxbl;
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

	/**sqbldkye*/
    @ApiModelProperty(value = "sqbldkye")
	private java.math.BigDecimal sqbldkye;
	/**sqkkjedc*/
    @ApiModelProperty(value = "sqkkjedc")
	private java.math.BigDecimal sqkkjedc;
	/**dflag*/
    @ApiModelProperty(value = "dflag")
	private Integer dflag;



}
