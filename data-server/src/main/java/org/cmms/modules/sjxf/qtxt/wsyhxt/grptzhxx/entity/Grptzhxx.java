package org.cmms.modules.sjxf.qtxt.wsyhxt.grptzhxx.entity;

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
 * @Description: 个人普通账号信息
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_accinf")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_accinf对象", description="个人普通账号信息")
public class Grptzhxx {
    
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String aifCstno;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String aifAccno;
	/**网银状态(只存在状态01)*/
	@Excel(name = "网银状态(只存在状态01)", width = 15)
    @ApiModelProperty(value = "网银状态(只存在状态01)")
	private String aifStt;
	/**核心开户网点*/
	@Excel(name = "核心开户网点", width = 15)
    @ApiModelProperty(value = "核心开户网点")
	private String aifOpennode;
	/**核心开户网点名称*/
	@Excel(name = "核心开户网点名称", width = 15)
    @ApiModelProperty(value = "核心开户网点名称")
	private String aifBranchname;
	/**个人设置的单笔交易限额*/
	@Excel(name = "个人设置的单笔交易限额", width = 15)
    @ApiModelProperty(value = "个人设置的单笔交易限额")
	private java.math.BigDecimal aliSinglemaxP;
	/**个人设置的单日交易限额*/
	@Excel(name = "个人设置的单日交易限额", width = 15)
    @ApiModelProperty(value = "个人设置的单日交易限额")
	private java.math.BigDecimal aliDaymaxP;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String cifHostno;
	/**账号签约网点*/
	@Excel(name = "账号签约网点", width = 15)
    @ApiModelProperty(value = "账号签约网点")
	private String aifRegistnode;
	/**最后更新柜员*/
	@Excel(name = "最后更新柜员", width = 15)
    @ApiModelProperty(value = "最后更新柜员")
	private String aifModifyteller;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String aifModifytime;
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
	/**aliChannel*/
	@Excel(name = "aliChannel", width = 15)
    @ApiModelProperty(value = "aliChannel")
	private String aliChannel;
}
