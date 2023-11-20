package org.cmms.modules.sjxf.hxxt.jjk.zzsbjbxx.entity;

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
 * @Description: 自助设备基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbsc_termvteller")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbsc_termvteller对象", description="自助设备基本信息")
public class Zzsbjbxx {

	/**终端号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "终端号")
	private String id;
	/**终端类型*/
	@Excel(name = "终端类型", width = 15)
    @ApiModelProperty(value = "终端类型")
	private String idType;
	/**终端所属机构*/
	@Excel(name = "终端所属机构", width = 15)
    @ApiModelProperty(value = "终端所属机构")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	private Integer branch;
	/**终端对应虚拟柜员*/
	@Excel(name = "终端对应虚拟柜员", width = 15)
    @ApiModelProperty(value = "终端对应虚拟柜员")
	private String vteller;
	/**在途现金BGL*/
	@Excel(name = "在途现金BGL", width = 15)
    @ApiModelProperty(value = "在途现金BGL")
	private String bglAccount;
	/**受理机构*/
	@Excel(name = "受理机构", width = 15)
    @ApiModelProperty(value = "受理机构")
	private String acquirer;
	/**卡片接受方*/
	@Excel(name = "卡片接受方", width = 15)
    @ApiModelProperty(value = "卡片接受方")
	private String acceptor;
	/**终端名称和所在位置*/
	@Excel(name = "终端名称和所在位置", width = 15)
    @ApiModelProperty(value = "终端名称和所在位置")
	private String nameLocation;
	/**终端所属行*/
	@Excel(name = "终端所属行", width = 15)
    @ApiModelProperty(value = "终端所属行")
	private String owner;
	/**终端使用状态*/
	@Excel(name = "终端使用状态", width = 15)
    @ApiModelProperty(value = "终端使用状态")
	private String activity;
	/**最近维护日期*/
    @ApiModelProperty(value = "最近维护日期")
	private Date lastMaintainDate;
	/**最近维护标识*/
	@Excel(name = "最近维护标识", width = 15)
    @ApiModelProperty(value = "最近维护标识")
	private String lastMaintainFlag;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
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
