package org.cmms.modules.khdj.khdjpdsjx.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
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
 * @Description: 客户等级评定数据项
 * @Author: cmms
 * @Date:   2019-10-24
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_DJPDSJX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHDJ_DJPDSJX对象", description="客户等级评定数据项")
public class KhdjpdSjx {

    /**区域代码*/
    //@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
    private String qydm;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
    /**数据项维度(DD.天/MM.月/Q.季/YYYY.年)*/
    @Excel(name = "数据项维度(月/季/年)", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "数据项维度")
    @Dict(dicCode = "rqwd")
    private String sjxwd;
	/**数据项ID*/
	@Excel(name = "数据项ID", width = 15)
    @ApiModelProperty(value = "数据项ID")
	private String sjxid;
	/**数据项名称*/
	@Excel(name = "数据项名称", width = 15)
    @ApiModelProperty(value = "数据项名称")
	private String sjxmc;
	/**数据项SQL*/
	@Excel(name = "数据项SQL", width = 15)
    @ApiModelProperty(value = "数据项SQL")
	private String sjxsql;
	/**折算系数*/
	@Excel(name = "折算系数", width = 15)
    @ApiModelProperty(value = "折算系数")
	private java.math.BigDecimal zsxs;
    /**执行顺序*/
    @Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
    private Integer zxsx;
    /**是否启用(1.启用/2.停用)*/
    @Excel(name = "是否启用(启用/禁用)", width = 15, dicCode = "qybz")
    @ApiModelProperty(value = "是否启用(1.启用/2.禁用)")
    @Dict(dicCode = "qybz")
    private String sfqy;
    /**是否评定规则(1.是/2.否)*/
    @Excel(name = "是否评定规则(是/否)", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否评定规则(1.是/2.否)")
    @Dict(dicCode = "sfbz")
    private String sfpdgz;
	/**数据来源(0.系统取数/1.人工录入)*/
//	@Excel(name = "数据来源(系统取数/人工录入)", width = 15, dicCode = "sjxsjly")
    @ApiModelProperty(value = "数据来源(0.系统取数/1.人工录入)")
    @Dict(dicCode = "sjxsjly")
	private String sjly;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
    /**录入标志(0.导入/1.录入/2.修改)*/
    //@Excel(name = "录入标志(导入/录入/修改)", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志(0.导入/1.录入/2.修改)")
    @Dict(dicCode = "lrbz")
    private String lrbz;
	/**录入时间*/
	//@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	//@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;

}
