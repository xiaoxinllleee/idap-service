package org.cmms.modules.report.sgtzgl.dbpjbxxdjb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 担保品基本信息登记簿
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_dbpjbxxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_dbpjbxxdjb对象", description="担保品基本信息登记簿")
public class SgztDbpjbxxdjbVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**入库日期*/
	@Excel(name = "入库日期", width = 15)
    @ApiModelProperty(value = "入库日期")
	private String rkrq;
	/**出库日期*/
	@Excel(name = "出库日期", width = 15)
    @ApiModelProperty(value = "出库日期")
	private String ckrq;
	/**担保品编号*/
	@Excel(name = "担保品编号", width = 15)
    @ApiModelProperty(value = "担保品编号")
	private String dbpbh;
	/**担保品大类*/
	@Excel(name = "担保品大类", width = 15)
    @ApiModelProperty(value = "担保品大类")
	private String dbpdl;
	/**担保品细类（按三级分类）*/
	@Excel(name = "担保品细类（按三级分类）", width = 15)
    @ApiModelProperty(value = "担保品细类（按三级分类）")
	private String dbpxl;
	/**抵质押人名称*/
	@Excel(name = "抵质押人名称", width = 15)
    @ApiModelProperty(value = "抵质押人名称")
	private String dzyrmc;
	/**抵质押人证件号码*/
	@Excel(name = "抵质押人证件号码", width = 15)
    @ApiModelProperty(value = "抵质押人证件号码")
	private String dzyrzjhm;
	/**担保品状态*/
	@Excel(name = "担保品状态", width = 15)
    @ApiModelProperty(value = "担保品状态")
	private String dbpzt;
	/**登记权利价值之和（元）*/
	@Excel(name = "登记权利价值之和", width = 15)
    @ApiModelProperty(value = "登记权利价值之和")
	private java.math.BigDecimal djqljzzh;
	/**合同开始日期*/
	@Excel(name = "合同开始日期", width = 15)
    @ApiModelProperty(value = "合同开始日期")
	private String htksrq;
	/**合同结束日期*/
	@Excel(name = "合同结束日期", width = 15)
    @ApiModelProperty(value = "合同结束日期")
	private String htjsrq;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String jkrmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款余额（元）*/
	@Excel(name = "贷款余额（元）", width = 15)
    @ApiModelProperty(value = "贷款余额（元）")
	private java.math.BigDecimal dkye;

}
