package org.cmms.modules.sjxf.hxxt.zkgsb.entity;

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
 * @Description: 重空挂失表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_vplr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_vplr对象", description="重空挂失表")
public class Zkgsb {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String instNo;
	/**省代码*/
	@Excel(name = "省代码", width = 15)
    @ApiModelProperty(value = "省代码")
	private String provinceCode;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String ibdType;
	/**凭证前缀及凭证号码*/
	@Excel(name = "凭证前缀及凭证号码", width = 15)
    @ApiModelProperty(value = "凭证前缀及凭证号码")
	private String srlNoPrefix;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private Long serialNo;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private Integer recNo;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**挂失日期*/
	@Excel(name = "挂失日期", width = 15)
    @ApiModelProperty(value = "挂失日期")
	private Integer trnDate;
	/**挂失时间*/
	@Excel(name = "挂失时间", width = 15)
    @ApiModelProperty(value = "挂失时间")
	private String trnTime;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String trnTelrNo;
	/**交易机构*/
	@Excel(name = "交易机构", width = 15)
    @ApiModelProperty(value = "交易机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String trnBrchNo;
	/**挂失类型：1－口头挂失 2－书面挂失*/
	@Excel(name = "挂失类型：1－口头挂失 2－书面挂失", width = 15)
    @ApiModelProperty(value = "挂失类型：1－口头挂失 2－书面挂失")
	private String lstType;
	/**挂失开始日期*/
	@Excel(name = "挂失开始日期", width = 15)
    @ApiModelProperty(value = "挂失开始日期")
	private Integer lstStartDate;
	/**挂失终止日期*/
	@Excel(name = "挂失终止日期", width = 15)
    @ApiModelProperty(value = "挂失终止日期")
	private Integer lstEndDate;
	/**挂失人*/
	@Excel(name = "挂失人", width = 15)
    @ApiModelProperty(value = "挂失人")
	private String proposer;
	/**重空签发日期*/
	@Excel(name = "重空签发日期", width = 15)
    @ApiModelProperty(value = "重空签发日期")
	private Integer issueDate;
	/**挂失金额*/
	@Excel(name = "挂失金额", width = 15)
    @ApiModelProperty(value = "挂失金额")
	private java.math.BigDecimal issueAmt;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**当挂失的存单、存折挂失换折后，该标识位会置为1*/
	@Excel(name = "当挂失的存单、存折挂失换折后，该标识位会置为1", width = 15)
    @ApiModelProperty(value = "当挂失的存单、存折挂失换折后，该标识位会置为1")
	private String procFlag;
	/**挂失人身份证类型*/
	@Excel(name = "挂失人身份证类型", width = 15)
    @ApiModelProperty(value = "挂失人身份证类型")
	private String idType;
	/**挂失人身份证号码*/
	@Excel(name = "挂失人身份证号码", width = 15)
    @ApiModelProperty(value = "挂失人身份证号码")
	private String idNo;
	/**挂失原因*/
	@Excel(name = "挂失原因", width = 15)
    @ApiModelProperty(value = "挂失原因")
	private String lstReason;
	/**挂失代理人身份证类型*/
	@Excel(name = "挂失代理人身份证类型", width = 15)
    @ApiModelProperty(value = "挂失代理人身份证类型")
	private String agentIdType;
	/**挂失代理人身份证号码*/
	@Excel(name = "挂失代理人身份证号码", width = 15)
    @ApiModelProperty(value = "挂失代理人身份证号码")
	private String agentIdNo;
	/**解挂网点号*/
	@Excel(name = "解挂网点号", width = 15)
    @ApiModelProperty(value = "解挂网点号")
	private String lstCancelBr;
	/**解挂柜员号*/
	@Excel(name = "解挂柜员号", width = 15)
    @ApiModelProperty(value = "解挂柜员号")
	private String lstCancelTelr;
	/**解挂日期*/
	@Excel(name = "解挂日期", width = 15)
    @ApiModelProperty(value = "解挂日期")
	private Integer lstCancelDt;
	/**解挂时间*/
	@Excel(name = "解挂时间", width = 15)
    @ApiModelProperty(value = "解挂时间")
	private String lstCancelTime;
	/**解挂流水号*/
	@Excel(name = "解挂流水号", width = 15)
    @ApiModelProperty(value = "解挂流水号")
	private Integer lstCancelJrnl;
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
