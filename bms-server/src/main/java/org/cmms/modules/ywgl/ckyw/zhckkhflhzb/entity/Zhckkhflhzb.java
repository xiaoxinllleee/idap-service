package org.cmms.modules.ywgl.ckyw.zhckkhflhzb.entity;

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
 * @Description: 支行存款客户分类汇总表
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@Data
@TableName("ERP_CKYW_ZHCKKHFLHZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_CKYW_ZHCKKHFLHZ对象", description="支行存款客户分类汇总表")
public class Zhckkhflhzb {
    
	/**lxfs*/

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "dkjkpt_khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**客户名称*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**最早开户日*/
	@Excel(name = "最早开户日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日")
	private Date zzkhr;
	/**存款余额*/
	@Excel(name = "存款金额", width = 15)
    @ApiModelProperty(value = "存款金额")
	private java.math.BigDecimal ckye;
	/**ckrpye*/
	@Excel(name = "存款月日平", width = 15)
	@ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckrpye;
	/**cknrpye*/
	@Excel(name = "存款年日平", width = 15)
	@ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal cknrpye;
	/**存款类型*/
	@Excel(name = "存款类型", width = 15,dicCode = "ckcpxx")
    @ApiModelProperty(value = "存款类型")
	@Dict(dicCode = "ckcpxx")
	private String cklx;
	/**账户数*/
	@Excel(name = "账户数", width = 15)
    @ApiModelProperty(value = "账户数")
	private Integer zhs;


}
