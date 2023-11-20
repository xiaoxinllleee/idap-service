package org.cmms.modules.sjxf.hxxt.tzjymb.entity;

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
 * @Description: 桶子交易码表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_btrn")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_btrn对象", description="桶子交易码表")
public class Tzjymb {

	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	private String instNo;
	/**系统码*/
	@Excel(name = "系统码", width = 15)
    @ApiModelProperty(value = "系统码")
	private String system;
	/**外部交易后两位*/
	@Excel(name = "外部交易后两位", width = 15)
    @ApiModelProperty(value = "外部交易后两位")
	private String origTran;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_01")
	private String prCode01;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_01")
	private String normTrans01;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_01")
	private String corrTrans01;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_01")
	private String value01;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_02")
	private String prCode02;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_02")
	private String normTrans02;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_02")
	private String corrTrans02;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_02")
	private String value02;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_03")
	private String prCode03;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_03")
	private String normTrans03;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_03")
	private String corrTrans03;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_03")
	private String value03;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_04")
	private String prCode04;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_04")
	private String normTrans04;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_04")
	private String corrTrans04;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_04")
	private String value04;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_05")
	private String prCode05;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_05")
	private String normTrans05;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_05")
	private String corrTrans05;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_05")
	private String value05;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_06")
	private String prCode06;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_06")
	private String normTrans06;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_06")
	private String corrTrans06;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_06")
	private String value06;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_07")
	private String prCode07;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_07")
	private String normTrans07;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_07")
	private String corrTrans07;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_07")
	private String value07;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_08")
	private String prCode08;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_08")
	private String normTrans08;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_08")
	private String corrTrans08;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_08")
	private String value08;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_09")
	private String prCode09;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_09")
	private String normTrans09;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_09")
	private String corrTrans09;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_09")
	private String value09;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_10")
	private String prCode10;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_10")
	private String normTrans10;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_10")
	private String corrTrans10;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_10")
	private String value10;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_11")
	private String prCode11;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_11")
	private String normTrans11;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_11")
	private String corrTrans11;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_11")
	private String value11;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_12")
	private String prCode12;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_12")
	private String normTrans12;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_12")
	private String corrTrans12;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_12")
	private String value12;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_13")
	private String prCode13;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_13")
	private String normTrans13;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_13")
	private String corrTrans13;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_13")
	private String value13;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_14")
	private String prCode14;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_14")
	private String normTrans14;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_14")
	private String corrTrans14;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_14")
	private String value14;
	/**桶子标识*/
	@Excel(name = "桶子标识", width = 15)
    @ApiModelProperty(value = "桶子标识")
	@TableField(value = "pr_code_15")
	private String prCode15;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	@TableField(value = "norm_trans_15")
	private String normTrans15;
	/**冲正交易码*/
	@Excel(name = "冲正交易码", width = 15)
    @ApiModelProperty(value = "冲正交易码")
	@TableField(value = "corr_trans_15")
	private String corrTrans15;
	/**不用*/
	@Excel(name = "不用", width = 15)
    @ApiModelProperty(value = "不用")
	@TableField(value = "value_15")
	private String value15;
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
