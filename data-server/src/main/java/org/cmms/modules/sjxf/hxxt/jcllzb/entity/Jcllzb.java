package org.cmms.modules.sjxf.hxxt.jcllzb.entity;

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
 * @Description: 基础利率主表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_pm_basm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_pm_basm对象", description="基础利率主表")
public class Jcllzb {

	/**socNo*/
	@Excel(name = "socNo", width = 15)
    @ApiModelProperty(value = "socNo")
	private String socNo;
	/**areaId*/
	@Excel(name = "地区编号", width = 15)
    @ApiModelProperty(value = "areaId")
	private String areaId;
	/**baseId*/
	@Excel(name = "baseId", width = 15)
    @ApiModelProperty(value = "baseId")
	private String baseId;
	/**rateId*/
	@Excel(name = "利率编号", width = 15)
    @ApiModelProperty(value = "rateId")
	private String rateId;
	/**basmDate*/
	@Excel(name = "basmDate", width = 15)
    @ApiModelProperty(value = "basmDate")
	private Integer basmDate;
	/**basmTime*/
	@Excel(name = "basmTime", width = 15)
    @ApiModelProperty(value = "basmTime")
	private Integer basmTime;
	/**rate*/
	@Excel(name = "rate", width = 15)
    @ApiModelProperty(value = "rate")
	private java.math.BigDecimal rate;
	/**status*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "status")
	private String status;
	/**keyPointer*/
	@Excel(name = "keyPointer", width = 15)
    @ApiModelProperty(value = "keyPointer")
	private String keyPointer;
	/**maturityDate*/
	@Excel(name = "maturityDate", width = 15)
    @ApiModelProperty(value = "maturityDate")
	private Integer maturityDate;
	/**recalcInd*/
	@Excel(name = "recalcInd", width = 15)
    @ApiModelProperty(value = "recalcInd")
	private String recalcInd;
	/**recalcDate*/
	@Excel(name = "recalcDate", width = 15)
    @ApiModelProperty(value = "recalcDate")
	private Integer recalcDate;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
	private String description;
	/**tellerNo1*/
	@Excel(name = "tellerNo1", width = 15)
    @ApiModelProperty(value = "tellerNo1")
	@TableField(value = "teller_no_1")
	private Integer tellerNo1;
	/**tellerNo2*/
	@Excel(name = "tellerNo2", width = 15)
    @ApiModelProperty(value = "tellerNo2")
	@TableField(value = "teller_no_2")
	private Integer tellerNo2;
	/**branchNo*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "branchNo")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private Integer branchNo;
	/**terminalNo*/
	@Excel(name = "terminalNo", width = 15)
    @ApiModelProperty(value = "terminalNo")
	private Integer terminalNo;
	/**lastDateChanged*/
	@Excel(name = "lastDateChanged", width = 15)
    @ApiModelProperty(value = "lastDateChanged")
	private Integer lastDateChanged;
	/**lastTimeChanged*/
	@Excel(name = "lastTimeChanged", width = 15)
    @ApiModelProperty(value = "lastTimeChanged")
	private Integer lastTimeChanged;
	/**lastMaintDate*/
	@Excel(name = "lastMaintDate", width = 15)
    @ApiModelProperty(value = "lastMaintDate")
	private String lastMaintDate;
	/**lastMaintStat*/
	@Excel(name = "lastMaintStat", width = 15)
    @ApiModelProperty(value = "lastMaintStat")
	private String lastMaintStat;
	/**sDate*/
	@Excel(name = "sDate", width = 15)
    @ApiModelProperty(value = "sDate")
	private String sDate;
	/**eDate*/
	@Excel(name = "eDate", width = 15)
    @ApiModelProperty(value = "eDate")
	private String eDate;
	/**loadDate*/
    @ApiModelProperty(value = "loadDate")
	private Date loadDate;
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
