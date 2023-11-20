package org.cmms.modules.dklldj.csszgl.fdcsdzb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 浮动查算对照表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Data
@TableName("Rate_fdcsdzb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_fdcsdzb对象", description="浮动查算对照表")
public class FdcsdzbVo {
    //定价得分,贷款期限,贷款授信金额(起),贷款授信金额(止)(含),对应浮动幅度(%),按LPR加基点(BP)

	/**定价得分*/
	@Excel(name = "定价得分", width = 15)
    @ApiModelProperty(value = "定价得分")
	@ExcelVerify(notNull = true)
	private Integer djdf;
	/**贷款期限(1.1年期/2.5年期)*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqxly")
	@ExcelVerify(notNull = true)
	private Integer dkqx;
	/**贷款授信+承兑敞口金额（起）*/
	@Excel(name = "贷款授信金额（起）", width = 15)
    @ApiModelProperty(value = "贷款授信金额（起）")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal dksxjeBegin;
	/**贷款授信+承兑敞口金额（止）*/
	@Excel(name = "贷款授信金额（止）(含)", width = 15)
    @ApiModelProperty(value = "贷款授信金额（止）(含)")
	@ExcelVerify(notNull = true)
	private java.math.BigDecimal dksxjeEnd;
	/**对应浮动幅度*/
	@Excel(name = "对应浮动幅度(%)", width = 15)
    @ApiModelProperty(value = "对应浮动幅度(%)")
	private Integer dyfdfd;
	/**按LPR加基点（BP）*/
	@Excel(name = "按LPR加基点（BP）", width = 15)
    @ApiModelProperty(value = "按LPR加基点（BP）")
	@ExcelVerify(interHandler = true)
	private java.math.BigDecimal dyjdbp;


}
