package org.cmms.modules.report.sgtzgl.dkxttzdjb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款形态调整登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_dkxttzdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_dkxttzdjb对象", description="贷款形态调整登记簿")
public class SgtzglDkxttzdjb {
	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//	@ApiModelProperty(value = "主键ID")
//	private String id;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
    @ApiModelProperty(value = "贷款余额（元）")
	private java.math.BigDecimal dkye;
	/**调整日期*/
	@Excel(name = "调整日期", width = 15)
    @ApiModelProperty(value = "调整日期")
	private String tzrq;
	/**原形态*/
	@Excel(name = "原形态", width = 15)
    @ApiModelProperty(value = "原形态")
	private String yxt;
	/**当前形态*/
	@Excel(name = "当前形态", width = 15)
    @ApiModelProperty(value = "当前形态")
	private String dqxt;
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	//@ExcelVerify(interHandler = true)
	private String dyzrr;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

}
