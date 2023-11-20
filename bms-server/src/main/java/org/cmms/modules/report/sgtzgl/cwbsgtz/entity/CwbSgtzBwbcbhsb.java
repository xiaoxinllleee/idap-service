package org.cmms.modules.report.sgtzgl.cwbsgtz.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务部-成本核算表
 * @Author: jeecg-boot
 * @Date:   2023-06-13
 * @Version: V1.0
 */
@Data
@TableName("cwb_sgtz_bwbcbhsb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cwb_sgtz_bwbcbhsb对象", description="财务部-成本核算表")
public class CwbSgtzBwbcbhsb {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private java.lang.String sjrq;
	/**资产收益情况_项目名称*/
	@Excel(name = "资产收益情况_项目名称", width = 15)
    @ApiModelProperty(value = "资产收益情况_项目名称")
	private java.lang.String zcxmmc;
	/**资产收益情况_行号*/
	@Excel(name = "资产收益情况_行号", width = 15)
    @ApiModelProperty(value = "资产收益情况_行号")
	private java.lang.String hh1;
	/**资产收益情况_平均余额(万元)*/
	@Excel(name = "资产收益情况_平均余额(万元)", width = 15)
    @ApiModelProperty(value = "资产收益情况_平均余额(万元)")
	private java.math.BigDecimal zcpjye;
	/**资产收益情况_实收利息额(元)*/
	@Excel(name = "资产收益情况_实收利息额(元)", width = 15)
    @ApiModelProperty(value = "资产收益情况_实收利息额(元)")
	private java.math.BigDecimal zcshlxe;
	/**资产收益情况_收息率(百分比)*/
	@Excel(name = "资产收益情况_收息率(百分比)", width = 15)
    @ApiModelProperty(value = "资产收益情况_收息率(百分比)")
	private java.lang.String zcshl;
	/**资产收益情况_比重(百分比)*/
	@Excel(name = "资产收益情况_比重(百分比)", width = 15)
    @ApiModelProperty(value = "资产收益情况_比重(百分比)")
	private java.lang.String zcbz;
	/**资产收益情况_执行利率加权(百分比)*/
	@Excel(name = "资产收益情况_执行利率加权(百分比)", width = 15)
    @ApiModelProperty(value = "资产收益情况_执行利率加权(百分比)")
	private java.lang.String zczxlljq;
	/**资产收益情况_应收利息额(元)*/
	@Excel(name = "资产收益情况_应收利息额(元)", width = 15)
    @ApiModelProperty(value = "资产收益情况_应收利息额(元)")
	private java.math.BigDecimal zcyslxe;
	/**负债支出及所有者权益情况_项目名称*/
	@Excel(name = "负债支出及所有者权益情况_项目名称", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_项目名称")
	private java.lang.String fzxmmc;
	/**负债支出及所有者权益情况_行号*/
	@Excel(name = "负债支出及所有者权益情况_行号", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_行号")
	private java.lang.String hh2;
	/**负债支出及所有者权益情况_平均余额(万元)*/
	@Excel(name = "负债支出及所有者权益情况_平均余额(万元)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_平均余额(万元)")
	private java.math.BigDecimal fzpjye;
	/**负债支出及所有者权益情况_实付利息额(元)*/
	@Excel(name = "负债支出及所有者权益情况_实付利息额(元)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_实付利息额(元)")
	private java.math.BigDecimal fzsflxe;
	/**负债支出及所有者权益情况_付息率(百分比)*/
	@Excel(name = "负债支出及所有者权益情况_付息率(百分比)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_付息率(百分比)")
	private java.lang.String fzfxl;
	/**负债支出及所有者权益情况_比重(百分比)*/
	@Excel(name = "负债支出及所有者权益情况_比重(百分比)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_比重(百分比)")
	private java.lang.String fzbz;
	/**负债支出及所有者权益情况_执行利率加权(百分比)*/
	@Excel(name = "负债支出及所有者权益情况_执行利率加权(百分比)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_执行利率加权(百分比)")
	private java.lang.String fzzxlljq;
	/**负债支出及所有者权益情况_应付利息额(元)*/
	@Excel(name = "负债支出及所有者权益情况_应付利息额(元)", width = 15)
    @ApiModelProperty(value = "负债支出及所有者权益情况_应付利息额(元)")
	private java.math.BigDecimal fzyslxe;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
}
