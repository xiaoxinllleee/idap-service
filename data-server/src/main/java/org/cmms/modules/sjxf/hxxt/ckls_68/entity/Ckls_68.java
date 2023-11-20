package org.cmms.modules.sjxf.hxxt.ckls_68.entity;

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
 * @Description: 存款流水_68
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_inct_tran_68")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_inct_tran_68对象", description="存款流水_68")
public class Ckls_68 {

	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String instNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**该条数据记录日期*/
	@Excel(name = "该条数据记录日期", width = 15)
    @ApiModelProperty(value = "该条数据记录日期")
	private String postDate;
	/**交易发生日期*/
	@Excel(name = "交易发生日期", width = 15)
    @ApiModelProperty(value = "交易发生日期")
	private String trnDate;
	/**系统日期*/
	@Excel(name = "系统日期", width = 15)
    @ApiModelProperty(value = "系统日期")
	private String systemDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	@TableField(value = "system_time")
	private String systemTime;
	/**柜员号和网点*/
	@Excel(name = "柜员号和网点", width = 15)
    @ApiModelProperty(value = "柜员号和网点")
	private String tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private String brterm;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String channel;
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String deli;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String busiType;
	/**圈存金额*/
	@Excel(name = "圈存金额", width = 15)
    @ApiModelProperty(value = "圈存金额")
	private String crAmount;
	/**圈存到期日*/
	@Excel(name = "圈存到期日", width = 15)
    @ApiModelProperty(value = "圈存到期日")
	private String crMatDate;
	/**续存类型*/
	@Excel(name = "续存类型", width = 15)
    @ApiModelProperty(value = "续存类型")
	private String rollType;
	/**圈提转入账号*/
	@Excel(name = "圈提转入账号", width = 15)
    @ApiModelProperty(value = "圈提转入账号")
	private String drAcctNo;
	/**圈提转入账户子账户类别*/
	@Excel(name = "圈提转入账户子账户类别", width = 15)
    @ApiModelProperty(value = "圈提转入账户子账户类别")
	private String drAcctCat;
	/**圈提账户所属模块*/
	@Excel(name = "圈提账户所属模块", width = 15)
    @ApiModelProperty(value = "圈提账户所属模块")
	private String moduleCode;
	/**圈提重空类型*/
	@Excel(name = "圈提重空类型", width = 15)
    @ApiModelProperty(value = "圈提重空类型")
	private String vchrType;
	/**圈提重空号码*/
	@Excel(name = "圈提重空号码", width = 15)
    @ApiModelProperty(value = "圈提重空号码")
	private String vchrNo;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String noteString;
	/**圈存状态*/
	@Excel(name = "圈存状态", width = 15)
    @ApiModelProperty(value = "圈存状态")
	private String crStatus;
	/**圈存日期*/
	@Excel(name = "圈存日期", width = 15)
    @ApiModelProperty(value = "圈存日期")
	private String crDate;
	/**圈存流水号*/
	@Excel(name = "圈存流水号", width = 15)
    @ApiModelProperty(value = "圈存流水号")
	private String crJrnlNo;
	/**圈提日期*/
	@Excel(name = "圈提日期", width = 15)
    @ApiModelProperty(value = "圈提日期")
	private String drDate;
	/**圈提流水号*/
	@Excel(name = "圈提流水号", width = 15)
    @ApiModelProperty(value = "圈提流水号")
	private String drJrnlNo;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
    @ApiModelProperty(value = "修改日期")
	private String chDate;
	/**修改柜员号*/
	@Excel(name = "修改柜员号", width = 15)
    @ApiModelProperty(value = "修改柜员号")
	private String chTlrNo;
	/**原圈存到期日*/
	@Excel(name = "原圈存到期日", width = 15)
    @ApiModelProperty(value = "原圈存到期日")
	@TableField(value = "cr_mat_date_0")
	private String crMatDate0;
	/**原自动圈提标识*/
	@Excel(name = "原自动圈提标识", width = 15)
    @ApiModelProperty(value = "原自动圈提标识")
	@TableField(value = "auto_dr_flag_0")
	private String autoDrFlag0;
	/**原圈提转入账号*/
	@Excel(name = "原圈提转入账号", width = 15)
    @ApiModelProperty(value = "原圈提转入账号")
	@TableField(value = "dr_acct_no_0")
	private String drAcctNo0;
	/**原圈提转入账户子账户类别*/
	@Excel(name = "原圈提转入账户子账户类别", width = 15)
    @ApiModelProperty(value = "原圈提转入账户子账户类别")
	@TableField(value = "dr_acct_cat_0")
	private String drAcctCat0;
	/**原圈提账户所属模块*/
	@Excel(name = "原圈提账户所属模块", width = 15)
    @ApiModelProperty(value = "原圈提账户所属模块")
	@TableField(value = "module_code_0")
	private String moduleCode0;
	/**转出主账户*/
	@Excel(name = "转出主账户", width = 15)
    @ApiModelProperty(value = "转出主账户")
	private String fromMastAcct;
	/**转出子账户类型*/
	@Excel(name = "转出子账户类型", width = 15)
    @ApiModelProperty(value = "转出子账户类型")
	private String fromSubType;
	/**产品大类1*/
	@Excel(name = "产品大类1", width = 15)
    @ApiModelProperty(value = "产品大类1")
	private String sub1Type;
	/**产品子类1*/
	@Excel(name = "产品子类1", width = 15)
    @ApiModelProperty(value = "产品子类1")
	private String sub1Cat;
	/**产品大类2*/
	@Excel(name = "产品大类2", width = 15)
    @ApiModelProperty(value = "产品大类2")
	private String sub2Type;
	/**产品子类2*/
	@Excel(name = "产品子类2", width = 15)
    @ApiModelProperty(value = "产品子类2")
	private String sub2Cat;
	/**填充字段*/
	@Excel(name = "填充字段", width = 15)
    @ApiModelProperty(value = "填充字段")
	private String filler;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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
