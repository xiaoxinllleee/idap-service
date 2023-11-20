package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Data
@TableName("TJFX_JRPHSJ_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_JRPHSJ_QH对象", description="金融普惠数据汇总")
public class TjfxJrphsjQhimport {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private String tjyf;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private Long zfhs;
	/**其中有效户数*/
	@Excel(name = "其中有效户数", width = 15)
    @ApiModelProperty(value = "其中有效户数")
	private Long qzyxzfhs;
	/**评议户数*/
	@Excel(name = "评议户数", width = 15)
    @ApiModelProperty(value = "评议户数")
	private Long pyhs;
	/**其中电子评议*/
	@Excel(name = "其中电子评议", width = 15)
    @ApiModelProperty(value = "其中电子评议")
	private Long qzdzpy;
	/**其中纸质评议*/
	@Excel(name = "其中纸质评议", width = 15)
    @ApiModelProperty(value = "其中纸质评议")
	private Long qzzzpy;
	/**其中开会评议*/
	@Excel(name = "其中开会评议", width = 15)
    @ApiModelProperty(value = "其中开会评议")
	private Long qzhypy;
	/**其中电话评议*/
	@Excel(name = "其中电话评议", width = 15)
    @ApiModelProperty(value = "其中电话评议")
	private Long qzdhpy;
	/**其中微信评议*/
	@Excel(name = "其中微信评议", width = 15)
    @ApiModelProperty(value = "其中微信评议")
	private Long qzwxpy;
	/**信贷系统授信户数*/
	@Excel(name = "信贷系统授信户数", width = 15)
    @ApiModelProperty(value = "信贷系统授信户数")
	private Long xdxtsxhs;
	/**信贷系统授信金额*/
	@Excel(name = "信贷系统授信金额", width = 15)
    @ApiModelProperty(value = "信贷系统授信金额")
	private java.math.BigDecimal xdxtsxje;
	/**平板授信户数*/
	@Excel(name = "平板授信户数", width = 15)
    @ApiModelProperty(value = "平板授信户数")
	private Long pbdsxhs;
	/**平板端授信金额*/
	@Excel(name = "平板端授信金额", width = 15)
    @ApiModelProperty(value = "平板端授信金额")
	private java.math.BigDecimal pbdsxje;
	/**授信工作评议户数*/
	@Excel(name = "授信工作评议户数", width = 15)
    @ApiModelProperty(value = "授信工作评议户数")
	private Long sxgzpyhs;
	/**公示户数*/
	@Excel(name = "公示户数", width = 15)
    @ApiModelProperty(value = "公示户数")
	private Long gshs;
	/**公示金额*/
	@Excel(name = "公示金额", width = 15)
    @ApiModelProperty(value = "公示金额")
	private java.math.BigDecimal gsje;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**7月贷款净增户*/
	@Excel(name = "7月贷款净增户", width = 15)
    @ApiModelProperty(value = "7月贷款净增户")
	private Long qydkjzh;
	/**其他户数净增*/
	@Excel(name = "其他户数净增", width = 15)
    @ApiModelProperty(value = "其他户数净增")
	private Long qthsjz;
}
