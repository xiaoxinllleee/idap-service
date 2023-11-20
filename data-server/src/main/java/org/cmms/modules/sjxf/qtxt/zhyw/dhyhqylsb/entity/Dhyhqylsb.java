package org.cmms.modules.sjxf.qtxt.zhyw.dhyhqylsb.entity;

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
 * @Description: 电话银行签约流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Fbuss_tel_signlog")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Fbuss_tel_signlog对象", description="电话银行签约流水表")
public class Dhyhqylsb {
    
	/**变更日期*/
	@Excel(name = "变更日期", width = 15)
    @ApiModelProperty(value = "变更日期")
	private String changedate;
	/**变更时间*/
	@Excel(name = "变更时间", width = 15)
    @ApiModelProperty(value = "变更时间")
	private String changetime;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String newacctno;
	/**变更类型*/
	@Excel(name = "变更类型", width = 15)
    @ApiModelProperty(value = "变更类型")
	private String changetype;
	/**签约功能*/
	@Excel(name = "签约功能", width = 15)
    @ApiModelProperty(value = "签约功能")
	private String signfunction;
	/**单笔限额*/
	@Excel(name = "单笔限额", width = 15)
    @ApiModelProperty(value = "单笔限额")
	private java.math.BigDecimal singlelimit;
	/**单日限额*/
	@Excel(name = "单日限额", width = 15)
    @ApiModelProperty(value = "单日限额")
	private java.math.BigDecimal daylimit;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String identtype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identno;
	/**经办人证件类型*/
	@Excel(name = "经办人证件类型", width = 15)
    @ApiModelProperty(value = "经办人证件类型")
	private String operidenttype;
	/**经办人证件号码*/
	@Excel(name = "经办人证件号码", width = 15)
    @ApiModelProperty(value = "经办人证件号码")
	private String operidentno;
	/**变更渠道*/
	@Excel(name = "变更渠道", width = 15)
    @ApiModelProperty(value = "变更渠道")
	private String changechannel;
	/**变更机构*/
	@Excel(name = "变更机构", width = 15)
    @ApiModelProperty(value = "变更机构")
	private String changebranchid;
	/**变更柜员*/
	@Excel(name = "变更柜员", width = 15)
    @ApiModelProperty(value = "变更柜员")
	private String changetellerno;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**预留字段1*/
	@Excel(name = "预留字段1", width = 15)
    @ApiModelProperty(value = "预留字段1")
	private String reserved1;
	/**预留字段2*/
	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
	private String reserved2;
	/**预留字段3*/
	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
	private String reserved3;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
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
