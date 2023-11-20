package org.cmms.modules.jylrhs.csgl.zhdjpd.entity;

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
 * @Description: 支行等级评定
 * @Author: jeecg-boot
 * @Date:   2023-09-18
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_ywjg_rank")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_ywjg_rank对象", description="支行等级评定")
public class JylrhsYwjgRank {

	/**等级评定年份*/
	@Excel(name = "等级评定年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "等级评定年份")
	private Date djpdnf;
	/**支行机构代码*/
	@Excel(name = "支行", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "支行机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**本年度存款日平*/
	@Excel(name = "本年度存款日平", width = 15)
    @ApiModelProperty(value = "本年度存款日平")
	private java.math.BigDecimal bndCkrp;
	/**本年度贷款日平*/
	@Excel(name = "本年度贷款日平", width = 15)
    @ApiModelProperty(value = "本年度贷款日平")
	private java.math.BigDecimal bndDkrp;
	/**本年度存贷款日平之和*/
	@Excel(name = "本年度存贷款日平之和", width = 15)
    @ApiModelProperty(value = "本年度存贷款日平之和")
	private java.math.BigDecimal bndCdkrpSum;
	/**支行排名*/
	@Excel(name = "支行排名", width = 15)
    @ApiModelProperty(value = "支行排名")
	private Integer rank;
	/**支行等级*/
	@Excel(name = "支行等级", width = 15, dicCode = "jylrhs_zhdj")
	@ApiModelProperty(value = "支行等级")
	@Dict(dicCode = "jylrhs_zhdj")
	private Integer levels;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**统计时间*/
	@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计时间")
	private Date createTime;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
}
