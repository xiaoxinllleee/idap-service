package org.cmms.modules.ywgl.dkyw.lsdkghtz.entity;

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
 * @Description: 历史贷款管户台账
 * @Author: jeecg-boot
 * @Date:   2021-09-26
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DKGHTZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DKGHTZ对象", description="历史贷款管户台账")
public class Lsdkghtz {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**第一责任人工号*/
	@Excel(name = "第一责任人工号", width = 15)
    @ApiModelProperty(value = "第一责任人工号")
	private String dyzrrgh;
	/**第一责任人姓名*/
	@Excel(name = "第一责任人姓名", width = 15)
    @ApiModelProperty(value = "第一责任人姓名")
	private String dyzrrxm;
	/**管户人工号*/
	@Excel(name = "管户人工号", width = 15)
    @ApiModelProperty(value = "管户人工号")
	private String ghrgh;
	/**管护人姓名*/
	@Excel(name = "管护人姓名", width = 15)
    @ApiModelProperty(value = "管护人姓名")
	private String ghrxm;
	/**结息方式*/
	@Excel(name = "结息方式", width = 15)
    @ApiModelProperty(value = "结息方式")
	@Dict(dicCode = "jxfsbz")
	private Integer jxfs;
	/** 存款余额*/
	@Excel(name = " 存款余额", width = 15)
    @ApiModelProperty(value = " 存款余额")
	private java.math.BigDecimal ckye;
	/** 当前欠息*/
	@Excel(name = " 当前欠息", width = 15)
    @ApiModelProperty(value = " 当前欠息")
	private java.math.BigDecimal dqqx;
	/** 月季应收利息*/
	@Excel(name = " 月季应收利息", width = 15)
    @ApiModelProperty(value = " 月季应收利息")
	private java.math.BigDecimal yjyslx;
	/**领户五级分类*/
	@Excel(name = "领户五级分类", width = 15)
    @ApiModelProperty(value = "领户五级分类")
	@Dict(dicCode = "wjflbz")
	private java.math.BigDecimal lhwjfl;
	/** 信贷五级分类*/
	@Excel(name = " 信贷五级分类", width = 15)
    @ApiModelProperty(value = " 信贷五级分类")
	@Dict(dicCode = "wjflbz")
	private java.math.BigDecimal xdwjfl;
	/** 不良状态*/
	@Excel(name = " 不良状态", width = 15)
    @ApiModelProperty(value = " 不良状态")
	@Dict(dicCode = "blztbz")
	private java.math.BigDecimal blzt;
	/** 不良起始月份*/
	@Excel(name = " 不良起始月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = " 不良起始月份")
	private Date blqsyf;
	/** 逾期利率*/
	@Excel(name = " 逾期利率", width = 15)
    @ApiModelProperty(value = " 逾期利率")
	private java.math.BigDecimal yqll;
	/** 利率*/
	@Excel(name = " 利率", width = 15)
    @ApiModelProperty(value = " 利率")
	private java.math.BigDecimal ll;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
}
