package org.cmms.modules.sjxf.qtxt.khxxst.jgzjxx.entity;

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
 * @Description: 机构证件信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_ORG_IDENTIFIER")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_ORG_IDENTIFIER对象", description="机构证件信息")
public class Jgzjxx {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**机构证件ID*/
	@Excel(name = "机构证件ID", width = 15)
    @ApiModelProperty(value = "机构证件ID")
	private String identId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**机构证件类型*/
	@Excel(name = "机构证件类型", width = 15)
    @ApiModelProperty(value = "机构证件类型")
	private String identType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**证件户名*/
	@Excel(name = "证件户名", width = 15)
    @ApiModelProperty(value = "证件户名")
	private String identCustName;
	/**证件描述*/
	@Excel(name = "证件描述", width = 15)
    @ApiModelProperty(value = "证件描述")
	private String identDesc;
	/**发证国家或地区*/
	@Excel(name = "发证国家或地区", width = 15)
    @ApiModelProperty(value = "发证国家或地区")
	private String countryOrRegion;
	/**发证机构*/
	@Excel(name = "发证机构", width = 15)
    @ApiModelProperty(value = "发证机构")
	private String identOrg;
	/**证件登记日期*/
	@Excel(name = "证件登记日期", width = 15)
    @ApiModelProperty(value = "证件登记日期")
	private String idenRegDate;
	/**证件年检标志*/
	@Excel(name = "证件年检标志", width = 15)
    @ApiModelProperty(value = "证件年检标志")
	private String identCheckFlag;
	/**证件年检到期日*/
	@Excel(name = "证件年检到期日", width = 15)
    @ApiModelProperty(value = "证件年检到期日")
	private String identCheckingDate;
	/**证件年检日期*/
	@Excel(name = "证件年检日期", width = 15)
    @ApiModelProperty(value = "证件年检日期")
	private String identCheckedDate;
	/**证件有效期*/
	@Excel(name = "证件有效期", width = 15)
    @ApiModelProperty(value = "证件有效期")
	private String identValidPeriod;
	/**证件生效日期*/
	@Excel(name = "证件生效日期", width = 15)
    @ApiModelProperty(value = "证件生效日期")
	private String identEffectiveDate;
	/**证件失效日期*/
	@Excel(name = "证件失效日期", width = 15)
    @ApiModelProperty(value = "证件失效日期")
	private String identExpriedDate;
	/**证件有效标志*/
	@Excel(name = "证件有效标志", width = 15)
    @ApiModelProperty(value = "证件有效标志")
	private String identValidFlag;
	/**是否开户证件*/
	@Excel(name = "是否开户证件", width = 15)
    @ApiModelProperty(value = "是否开户证件")
	private String isOpenAccIdent;
	/**证件修改标志*/
	@Excel(name = "证件修改标志", width = 15)
    @ApiModelProperty(value = "证件修改标志")
	private String openAccIdentModifiedFlag;
	/**证件修改时间*/
	@Excel(name = "证件修改时间", width = 15)
    @ApiModelProperty(value = "证件修改时间")
	private String identModifiedTime;
	/**最后更新系统*/
	@Excel(name = "最后更新系统", width = 15)
    @ApiModelProperty(value = "最后更新系统")
	private String lastUpdateSys;
	/**最后更新人*/
	@Excel(name = "最后更新人", width = 15)
    @ApiModelProperty(value = "最后更新人")
	private String lastUpdateUser;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String lastUpdateTm;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String txSeqNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**加载结束时间*/
	@Excel(name = "加载结束时间", width = 15)
    @ApiModelProperty(value = "加载结束时间")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
//	/**天入库表编号-对不同的表名唯一*/
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
//	/**dttime*/
//	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "dttime")
//	private Date dttime;
}
