package org.cmms.modules.dklldj.tjfxgl.zhsftj.entity;

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
 * @Description: 支行上浮统计
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Data
@TableName("rate_lldj_zhsftj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lldj_zhsftj对象", description="支行上浮统计")
public class Zhsftj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**最高上浮幅度*/
	@Excel(name = "最高上浮幅度(%)", width = 15)
	@ApiModelProperty(value = "最高上浮幅度")
	private java.math.BigDecimal zgsffd;
	/**最低上浮幅度*/
	@Excel(name = "最低上浮幅度(%)", width = 15)
	@ApiModelProperty(value = "最低上浮幅度")
	private java.math.BigDecimal zdsffd;
	/**平均上浮幅度*/
	@Excel(name = "平均上浮幅度(%)", width = 15)
	@ApiModelProperty(value = "平均上浮幅度")
	private java.math.BigDecimal pjsffd;

	/**测算笔数*/
	//@Excel(name = "测算笔数", width = 15)
	@ApiModelProperty(value = "测算笔数")
	private java.math.BigDecimal csbs;
	/**发放笔数*/
	//@Excel(name = "发放笔数", width = 15)
	@ApiModelProperty(value = "发放笔数")
	private java.math.BigDecimal ffbs;
	/**最高得分*/
	//@Excel(name = "最高得分", width = 15)
    @ApiModelProperty(value = "最高得分")
	private java.math.BigDecimal zgdf;
	/**最低得分*/
	//@Excel(name = "最低得分", width = 15)
    @ApiModelProperty(value = "最低得分")
	private java.math.BigDecimal zddf;
	/**平均得分*/
	//@Excel(name = "平均得分", width = 15)
    @ApiModelProperty(value = "平均得分")
	private java.math.BigDecimal pjdf;
	/**最高基点(加/减)BP*/
	//@Excel(name = "最高基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "最高基点(加/减)BP")
	private java.math.BigDecimal zgjdbp;
	/**最低基点(加/减)BP*/
	//@Excel(name = "最低基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "最低基点(加/减)BP")
	private java.math.BigDecimal zdjdbp;
	/**平均基点(加/减)BP*/
	//@Excel(name = "平均基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "平均基点(加/减)BP")
	private java.math.BigDecimal pjjdbp;
}
