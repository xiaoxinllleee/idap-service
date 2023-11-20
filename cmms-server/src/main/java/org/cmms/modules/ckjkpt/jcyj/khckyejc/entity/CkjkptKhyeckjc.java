package org.cmms.modules.ckjkpt.jcyj.khckyejc.entity;

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
 * @Description: 客户余额存款监测查询
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_khyeckjc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_khyeckjc对象", description="客户余额存款监测查询")
public class CkjkptKhyeckjc {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**上月末余额*/
	@Excel(name = "上月余额", width = 15)
	@ApiModelProperty(value = "上月末余额")
	private java.math.BigDecimal syye;
	/**上月末月日平*/
	@Excel(name = "上月月日平", width = 15)
	@ApiModelProperty(value = "上月末月日平")
	private java.math.BigDecimal syyrp;
	/**上月末年日平*/
	@Excel(name = "上月年日平", width = 15)
	@ApiModelProperty(value = "上月末年日平")
	private java.math.BigDecimal synrp;
	/**期末余额*/
	@Excel(name = "本月余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private java.math.BigDecimal qmye;
	/**期末月日平*/
	@Excel(name = "本月月日平", width = 15)
    @ApiModelProperty(value = "期末月日平")
	private java.math.BigDecimal qmyrp;
	/**期末年日平*/
	@Excel(name = "本月年日平", width = 15)
    @ApiModelProperty(value = "期末年日平")
	private java.math.BigDecimal qmnrp;
	/**月日平增减比例*/
	@Excel(name = "余额占上月日平比例(%)", width = 15)
    @ApiModelProperty(value = "月日平增减比例")
	private java.math.BigDecimal yrpzjbl;
	/**月日平增减标识*/
	@Excel(name = "月日平增减标识", width = 15,dicCode = "zjbz")
    @ApiModelProperty(value = "月日平增减标识")
	@Dict(dicCode = "zjbs")
	private Integer yrpzjbs;
	/**年日平增减比例*/
	@Excel(name = "余额占上月年日平比例(%)", width = 15)
    @ApiModelProperty(value = "年日平增减比例")
	private java.math.BigDecimal nrpzjbl;
	/**年日平增减标识*/
	@Excel(name = "年日平增减标识", width = 15,dicCode = "zjbz")
    @ApiModelProperty(value = "年日平增减标识")
	@Dict(dicCode = "zjbs")
	private Integer nrpzjbs;
}
