package org.cmms.modules.performance.loancustomer.dkhtzhxx.vo;

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
 * @Description: 存量贷款客户信息导入（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_cldkkhxx_imp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_cldkkhxx_imp对象", description="存量贷款客户信息导入（数据导入临时表）")
public class CldkkhxxImp {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
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
//	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	@ExcelVerify(notNull = true)
	private String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal htje;
	/**合同余额*/
	@Excel(name = "合同余额", width = 15)
    @ApiModelProperty(value = "合同余额")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal htye;
	/**合同五级分类*/
//	@Excel(name = "合同五级分类", width = 15)
    @ApiModelProperty(value = "合同五级分类")
	private String htwjfl;
	/**管户人姓名*/
	@Excel(name = "管户人姓名", width = 15)
    @ApiModelProperty(value = "管户人姓名")
	@ExcelVerify(notNull = false)
	private String ghr;
	/**管户比例（%）*/
	@Excel(name = "管户比例（%）", width = 15)
    @ApiModelProperty(value = "管户比例（%）")
	@ExcelVerify(notNull = false)
	private String ghbl;
	/**包收人姓名*/
	@Excel(name = "包收人姓名", width = 15)
    @ApiModelProperty(value = "包收人姓名")
	@ExcelVerify(notNull = false)
	private String bsr;
	/**包收比例（%）*/
	@Excel(name = "包收比例（%）", width = 15)
    @ApiModelProperty(value = "包收比例（%）")
	@ExcelVerify(notNull = false)
	private String bsbl;
	/**审批人姓名*/
	@Excel(name = "审批人姓名", width = 15)
    @ApiModelProperty(value = "审批人姓名")
	@ExcelVerify(notNull = false)
	private String spr;
	/**审批比例（%）*/
	@Excel(name = "审批比例（%）", width = 15)
    @ApiModelProperty(value = "审批比例（%）")
	@ExcelVerify(notNull = false)
	private String spbl;
	/**调查人姓名*/
	@Excel(name = "调查人姓名", width = 15)
    @ApiModelProperty(value = "调查人姓名")
	@ExcelVerify(notNull = false)
	private String dcr;
	/**调查比例（%）*/
	@Excel(name = "调查比例（%）", width = 15)
    @ApiModelProperty(value = "调查比例（%）")
	@ExcelVerify(notNull = false)
	private String dcbl;

	/**导入数据是否有效（1：是 2：否）*/
	@Excel(name = "导入是否成功", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "导入数据是否有效（1：是 2：否）")
	private Integer isValid;

	/**导入失败原因*/
	@Excel(name = "导入失败原因", width = 15)
	@ApiModelProperty(value = "导入失败原因")
	private String remarks;
}
