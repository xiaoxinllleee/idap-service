package org.cmms.modules.sjxf.qtxt.wsyhxt.lcywlsb.entity;

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
 * @Description: 理财业务流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pbfixeddeposittranflow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pbfixeddeposittranflow对象", description="理财业务流水表")
public class Lcywlsb {
    
	/**网银交易流水号*/
	@Excel(name = "网银交易流水号", width = 15)
    @ApiModelProperty(value = "网银交易流水号")
	private String fdtFlowno;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String fdtCstno;
	/**转出账号*/
	@Excel(name = "转出账号", width = 15)
    @ApiModelProperty(value = "转出账号")
	private String fdtPayacc;
	/**转出账号子账号*/
	@Excel(name = "转出账号子账号", width = 15)
    @ApiModelProperty(value = "转出账号子账号")
	private String fdtPaysubacc;
	/**转入账号*/
	@Excel(name = "转入账号", width = 15)
    @ApiModelProperty(value = "转入账号")
	private String fdtRcvacc;
	/**转入账号子账号*/
	@Excel(name = "转入账号子账号", width = 15)
    @ApiModelProperty(value = "转入账号子账号")
	private String fdtRcvsubacc;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String fdtAcctype;
	/**转出转入标志*/
	@Excel(name = "转出转入标志", width = 15)
    @ApiModelProperty(value = "转出转入标志")
	private String fdtTrantype;
	/**存期*/
	@Excel(name = "存期", width = 15)
    @ApiModelProperty(value = "存期")
	private String fdtSaveperiod;
	/**是否自动转存*/
	@Excel(name = "是否自动转存", width = 15)
    @ApiModelProperty(value = "是否自动转存")
	private String fdtAutosave;
	/**通知日期*/
	@Excel(name = "通知日期", width = 15)
    @ApiModelProperty(value = "通知日期")
	private String fdtNoticedate;
	/**支取日期*/
	@Excel(name = "支取日期", width = 15)
    @ApiModelProperty(value = "支取日期")
	private String fdtDrawdate;
	/**用户提交时间*/
	@Excel(name = "用户提交时间", width = 15)
    @ApiModelProperty(value = "用户提交时间")
	private String fdtSubtime;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal fdtTranamt;
	/**指令状态*/
	@Excel(name = "指令状态", width = 15)
    @ApiModelProperty(value = "指令状态")
	private String fdtStt;
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String cifOpennode;
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
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
