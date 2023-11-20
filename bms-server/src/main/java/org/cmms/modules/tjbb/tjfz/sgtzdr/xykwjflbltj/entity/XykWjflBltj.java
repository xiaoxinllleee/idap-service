package org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.entity;

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
 * @Description: 手工台账：信用卡五级分类及不良统计
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_xykwjflbltj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_xykwjflbltj对象", description="手工台账：信用卡五级分类及不良统计")
public class XykWjflBltj {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date dataDate;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private java.lang.String jgbm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private java.lang.String jgmc;
	/**卡类型(1 标卡/2 非标卡)*/
	@Excel(name = "卡类型(1 标卡/2 非标卡)", width = 15)
    @ApiModelProperty(value = "卡类型(1 标卡/2 非标卡)")
	private java.lang.String cardType;
	/**透支本金合计*/
	@Excel(name = "透支本金合计", width = 15)
    @ApiModelProperty(value = "透支本金合计")
	private java.math.BigDecimal tzbjhj;
	/**正常*/
	@Excel(name = "正常", width = 15)
    @ApiModelProperty(value = "正常")
	private java.math.BigDecimal zc;
	/**关注*/
	@Excel(name = "关注", width = 15)
    @ApiModelProperty(value = "关注")
	private java.math.BigDecimal gz;
	/**次级*/
	@Excel(name = "次级", width = 15)
    @ApiModelProperty(value = "次级")
	private java.math.BigDecimal cj;
	/**可疑*/
	@Excel(name = "可疑", width = 15)
    @ApiModelProperty(value = "可疑")
	private java.math.BigDecimal ky;
	/**损失*/
	@Excel(name = "损失", width = 15)
    @ApiModelProperty(value = "损失")
	private java.math.BigDecimal ss;
	/**不良本金合计*/
	@Excel(name = "不良本金合计", width = 15)
    @ApiModelProperty(value = "不良本金合计")
	private java.math.BigDecimal blbjhj;
	/**不良率*/
	@Excel(name = "不良率", width = 15)
    @ApiModelProperty(value = "不良率")
	private java.math.BigDecimal bll;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private java.lang.Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
}
