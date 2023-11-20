package org.cmms.modules.sjxf.qtxt.cwglxt.bzb.entity;

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
 * @Description: 币种表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_currency")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_currency对象", description="币种表")
public class Bzb {

	/**币种内部编码*/
	@Excel(name = "币种内部编码", width = 15)
    @ApiModelProperty(value = "币种内部编码")
	private String curNo;
	/**币种国际编码*/
	@Excel(name = "币种国际编码", width = 15)
    @ApiModelProperty(value = "币种国际编码")
	private String curEpn;
	/**币种名称*/
	@Excel(name = "币种名称", width = 15)
    @ApiModelProperty(value = "币种名称")
	private String curName;
	/**使用标志 Y 是 N 否*/
	@Excel(name = "使用标志 Y 是 N 否", width = 15)
    @ApiModelProperty(value = "使用标志 Y 是 N 否")
	private String useYn;
	/**显示标志 Y 是 N 否*/
	@Excel(name = "显示标志 Y 是 N 否", width = 15)
    @ApiModelProperty(value = "显示标志 Y 是 N 否")
	private String dispYn;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String curSeqn;
	/**币种符号*/
	@Excel(name = "币种符号", width = 15)
    @ApiModelProperty(value = "币种符号")
	private String curSbl;
	/**币种对应年终状态顺序位*/
	@Excel(name = "币种对应年终状态顺序位", width = 15)
    @ApiModelProperty(value = "币种对应年终状态顺序位")
	private String curBitSeqn;
	/**是否折算标志 Y 是 N 否*/
	@Excel(name = "是否折算标志 Y 是 N 否", width = 15)
    @ApiModelProperty(value = "是否折算标志 Y 是 N 否")
	private String zsYn;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**天入库表编号-对不同的表名唯一*/
/*	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;
	*//**dttime*//*
	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
