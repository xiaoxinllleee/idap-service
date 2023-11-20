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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存量贷款客户信息
 * @Author: penghr
 * @Date:   2023-04-11
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_cldkkhxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_cldkkhxx对象", description="存量贷款客户信息")
public class Cldkkhxx {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型（1：企业 2：个人）", width = 15, dicCode = "xddagl_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xddagl_khlx")
	private Integer khlx;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	private String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
	@ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同余额*/
	@Excel(name = "合同余额", width = 15)
	@ApiModelProperty(value = "合同余额")
	private java.math.BigDecimal htye;
	/**管户人*/
	@Excel(name = "管户人姓名", width = 15)
	@ApiModelProperty(value = "管户人")
	private String ghr;
	/**管户比例*/
	@Excel(name = "管户比例（%）", width = 15)
	@ApiModelProperty(value = "管户比例")
	private String ghbl;
	/**包收人*/
	@Excel(name = "包收人姓名", width = 15)
	@ApiModelProperty(value = "包收人")
	private String bsr;
	/**包收比例*/
	@Excel(name = "包收比例（%）", width = 15)
	@ApiModelProperty(value = "包收比例")
	private String bsbl;
	/**审批人*/
	@Excel(name = "审批人姓名", width = 15)
	@ApiModelProperty(value = "审批人")
	private String spr;
	/**审批比例*/
	@Excel(name = "审批比例（%）", width = 15)
	@ApiModelProperty(value = "审批比例")
	private String spbl;
	/**调查人*/
	@Excel(name = "调查人姓名", width = 15)
	@ApiModelProperty(value = "调查人")
	private String dcr;
	/**调查比例*/
	@Excel(name = "调查比例（%）", width = 15)
	@ApiModelProperty(value = "调查比例")
	private String dcbl;

	/**地址*/
//	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**合同五级分类*/
//	@Excel(name = "合同五级分类", width = 15)
    @ApiModelProperty(value = "合同五级分类")
	private String htwjfl;
}
