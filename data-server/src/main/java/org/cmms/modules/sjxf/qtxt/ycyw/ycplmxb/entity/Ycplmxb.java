package org.cmms.modules.sjxf.qtxt.ycyw.ycplmxb.entity;

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
 * @Description: 烟草批量明细表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_ycdsmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_ycdsmx对象", description="烟草批量明细表")
public class Ycplmxb {
    
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgm;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**处理日期*/
	@Excel(name = "处理日期", width = 15)
    @ApiModelProperty(value = "处理日期")
	private String clrq;
	/**接收流水号*/
	@Excel(name = "接收流水号", width = 15)
    @ApiModelProperty(value = "接收流水号")
	private String jslsh;
	/**明细流水号*/
	@Excel(name = "明细流水号", width = 15)
    @ApiModelProperty(value = "明细流水号")
	private String mxxh;
	/**存款单位代码*/
	@Excel(name = "存款单位代码", width = 15)
    @ApiModelProperty(value = "存款单位代码")
	private String mxbh;
	/**收款账号*/
	@Excel(name = "收款账号", width = 15)
    @ApiModelProperty(value = "收款账号")
	private String mxzh;
	/**收款名称*/
	@Excel(name = "收款名称", width = 15)
    @ApiModelProperty(value = "收款名称")
	private String mxmc;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String mxje;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String mxzt;
	/**废弃字段1*/
	@Excel(name = "废弃字段1", width = 15)
    @ApiModelProperty(value = "废弃字段1")
	private String xydm;
	/**废弃字段2*/
	@Excel(name = "废弃字段2", width = 15)
    @ApiModelProperty(value = "废弃字段2")
	private String xyxx;
	/**批次序号*/
	@Excel(name = "批次序号", width = 15)
    @ApiModelProperty(value = "批次序号")
	private Integer serialNum;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String batchId;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**导入日期*/
    @ApiModelProperty(value = "导入日期")
	private Date loadDate;
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
