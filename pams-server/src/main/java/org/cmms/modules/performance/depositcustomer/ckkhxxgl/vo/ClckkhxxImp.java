package org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * @Description: 存量存款客户信息（数据导入临时表）
 * @Author: penghr
 * @Date:   2023.04.11
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_clckkhxx_imp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_clckkhxx_imp对象", description="存量存款客户信息（数据导入临时表）")
public class ClckkhxxImp {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	@ExcelVerify(notNull = true)
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	@ExcelVerify(notNull = false)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = false)
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型（1：企业 2：个人）", width = 15, dicCode = "xddagl_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xddagl_khlx")
	@ExcelVerify(notNull = false)
	private Integer khlx;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	@ExcelVerify(notNull = false)
	private String dz;
	/**客户余额*/
	@Excel(name = "客户余额", width = 15)
	@ApiModelProperty(value = "客户余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal khye;
	/**客户日平余额*/
	@Excel(name = "客户月日平余额", width = 15)
	@ApiModelProperty(value = "客户日平余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal khrpye;
	/**客户年日平余额*/
	@Excel(name = "客户年日平余额", width = 15)
	@ApiModelProperty(value = "客户年日平余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal khnrpye;
	/**对应贷款管户人*/
	@Excel(name = "对应贷款管户人", width = 15)
	@ApiModelProperty(value = "对应贷款管户人")
	@ExcelVerify(notNull = false)
	private String dydkghr;
	/**对应贷款客户经理*/
	@Excel(name = "对应贷款客户经理", width = 15)
	@ApiModelProperty(value = "对应贷款客户经理")
	@ExcelVerify(notNull = false)
	private String dydkkhjl;
	/**管户人姓名*/
	@Excel(name = "管户人姓名", width = 15)
	@ApiModelProperty(value = "管户人姓名")
	@ExcelVerify(notNull = true)
	private String ghrxm;
	/**管户比例*/
	@Excel(name = "管户比例（%）", width = 15)
	@ApiModelProperty(value = "管户比例")
	@ExcelVerify(notNull = true)
	private String ghbl;
	/**拓展人姓名*/
	@Excel(name = "拓展人姓名", width = 15)
	@ApiModelProperty(value = "拓展人姓名")
	@ExcelVerify(notNull = true)
	private String tzrxm;
	/**拓展比率*/
	@Excel(name = "拓展比例（%）", width = 15)
	@ApiModelProperty(value = "拓展比率")
	@ExcelVerify(notNull = true)
	private String tzbl;

	/**导入数据是否有效（1：是 2：否）*/
	@ApiModelProperty(value = "导入数据是否有效（1：是 2：否）")
	private Integer isValid;
}
