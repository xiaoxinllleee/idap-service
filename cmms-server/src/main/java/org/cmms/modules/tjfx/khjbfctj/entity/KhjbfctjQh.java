package org.cmms.modules.tjfx.khjbfctj.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 客户级别分层统计_全行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHDJFCTJ_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHDJFCTJ_QH对象", description="客户级别分层统计_全行")
public class KhjbfctjQh {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
    /**统计维度*/
    @Excel(name = "统计维度", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "统计维度")
    @Dict(dicCode = "rqwd")
    private String tjwd;
    /**客户级别*/
    @Excel(name = "客户级别", width = 15, dicCode = "DJBH", dictTable = "KHDJ_KHDJSZ", dicText = "DJMC")
    @ApiModelProperty(value = "客户级别")
    @Dict(dicCode = "DJBH", dictTable = "KHDJ_KHDJSZ", dicText = "DJMC")
    private String khdj;
	/**客户数量*/
	@Excel(name = "客户数量", width = 15)
    @ApiModelProperty(value = "客户数量")
	private Long khs;
    /**总人数*/
    @Excel(name = "总人数", width = 15)
    @ApiModelProperty(value = "总人数")
    private Long zrs;
    /**人数占比*/
    @Excel(name = "人数占比", width = 15)
    @ApiModelProperty(value = "人数占比")
    private java.math.BigDecimal rszb;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
}
