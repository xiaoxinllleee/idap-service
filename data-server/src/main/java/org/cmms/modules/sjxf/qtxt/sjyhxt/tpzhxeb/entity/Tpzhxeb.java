package org.cmms.modules.sjxf.qtxt.sjyhxt.tpzhxeb.entity;

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
 * @Description: 贴片账户限额表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Mbs_mb_stkacclimit")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Mbs_mb_stkacclimit对象", description="贴片账户限额表")
public class Tpzhxeb {
    
	/**账号/卡号*/
	@Excel(name = "账号/卡号", width = 15)
    @ApiModelProperty(value = "账号/卡号")
	private String accno;
	/**客户号,用户的唯一标识*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String cstno;
	/**个人设置的单日交易限额*/
	@Excel(name = "个人设置的单日交易限额", width = 15)
    @ApiModelProperty(value = "个人设置的单日交易限额")
	private String daymaxP;
	/**个人设置的单笔交易限额*/
	@Excel(name = "个人设置的单笔交易限额", width = 15)
    @ApiModelProperty(value = "个人设置的单笔交易限额")
	private String singlemaxP;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private String opentime;
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
