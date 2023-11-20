package org.cmms.modules.sjxf.hxxt.hqckzzzhdzb.entity;

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
 * @Description: 活期存款主子账户对照表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbs_invs")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_invs对象", description="活期存款主子账户对照表")
public class Hqckzzzhdzb {
    
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String socNo;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String mastAcct;
	/**子账号类型*/
	@Excel(name = "子账号类型", width = 15)
    @ApiModelProperty(value = "子账号类型")
	private String subAcctType;
	/**子账户利益范畴*/
	@Excel(name = "子账户利益范畴", width = 15)
    @ApiModelProperty(value = "子账户利益范畴")
	private String subAcctIntCate;
	/**子账号类型*/
	@Excel(name = "子账号类型", width = 15)
    @ApiModelProperty(value = "子账号类型")
	private String subAcctNo;
	/**子货币代码*/
	@Excel(name = "子货币代码", width = 15)
    @ApiModelProperty(value = "子货币代码")
	private String subAcctCurr;
	/**子货币类型*/
	@Excel(name = "子货币类型", width = 15)
    @ApiModelProperty(value = "子货币类型")
	private String subAcctCurrType;
	/**子账户状态*/
	@Excel(name = "子账户状态", width = 15)
    @ApiModelProperty(value = "子账户状态")
	private String subAcctStatus;
	/**关闭子账户流水号*/
	@Excel(name = "关闭子账户流水号", width = 15)
    @ApiModelProperty(value = "关闭子账户流水号")
	private Integer subAcCloseJrnl;
	/**关闭子账户日期*/
	@Excel(name = "关闭子账户日期", width = 15)
    @ApiModelProperty(value = "关闭子账户日期")
	private Integer subAcCloseDate;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
	private String fil01;
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
