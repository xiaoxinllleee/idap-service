package org.cmms.modules.report.sgtzgl.dbpgldkdjb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 担保品关联贷款登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_DBPGLDKDJB对象", description="担保品关联贷款登记簿")
public class SgztDbpgldkdjbVO {

	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgbm;
	/**担保人名称*/
	@Excel(name = "担保人名称", width = 15)
    @ApiModelProperty(value = "担保人名称")
	private String dbrmc;
	/**担保品编号*/
	@Excel(name = "担保品编号", width = 15)
    @ApiModelProperty(value = "担保品编号")
	private String dbpbh;
	/**担保品大类*/
	@Excel(name = "担保品大类", width = 15)
    @ApiModelProperty(value = "担保品大类")
	private String dbpdl;
	/**担保品名称*/
	@Excel(name = "担保品名称", width = 15)
    @ApiModelProperty(value = "担保品名称")
	private String dbpmc;
	/**担保品合同号*/
	@Excel(name = "担保品合同号", width = 15)
    @ApiModelProperty(value = "担保品合同号")
	private String dbphth;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String jkrmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**担保金额（元）*/
	@Excel(name = "担保金额（元）", width = 15)
    @ApiModelProperty(value = "担保金额（元）")
	private java.math.BigDecimal dbje;
	/**合同开始日期*/
	@Excel(name = "合同开始日期", width = 15)
    @ApiModelProperty(value = "合同开始日期")
	private String htksrq;
	/**合同终止日期*/
	@Excel(name = "合同终止日期", width = 15)
    @ApiModelProperty(value = "合同终止日期")
	private String htzzrq;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
    @ApiModelProperty(value = "贷款余额（元）")
	private java.math.BigDecimal dkye;
	/**担保品状态*/
	@Excel(name = "担保品状态", width = 15)
    @ApiModelProperty(value = "担保品状态")
	//@ExcelVerify(interHandler = true)
	private String dbpzt;
}
