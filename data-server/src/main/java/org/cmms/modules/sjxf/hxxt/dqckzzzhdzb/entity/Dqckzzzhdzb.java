package org.cmms.modules.sjxf.hxxt.dqckzzzhdzb.entity;

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
 * @Description: 定期存款主子账户对照表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbs_invt")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_invt对象", description="定期存款主子账户对照表")
public class Dqckzzzhdzb {
    
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String socNo;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**次数号*/
	@Excel(name = "次数号", width = 15)
    @ApiModelProperty(value = "次数号")
	private String volumeNo;
	/**顺序号*/
	@Excel(name = "顺序号", width = 15)
    @ApiModelProperty(value = "顺序号")
	private String sequenceNo;
	/**存折号*/
	@Excel(name = "存折号", width = 15)
    @ApiModelProperty(value = "存折号")
	private String pbNo;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String subAcctNo;
	/**货币类型*/
	@Excel(name = "货币类型", width = 15)
    @ApiModelProperty(value = "货币类型")
	private String currency;
	/**子账户状态值*/
	@Excel(name = "子账户状态值", width = 15)
    @ApiModelProperty(value = "子账户状态值")
	private String activeFlag;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
	private String fil01;
	/**最近交易日期*/
	@Excel(name = "最近交易日期", width = 15)
    @ApiModelProperty(value = "最近交易日期")
	private String lastMaintDate;
	/**最近交易状态*/
	@Excel(name = "最近交易状态", width = 15)
    @ApiModelProperty(value = "最近交易状态")
	private String lastMaintStat;
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
