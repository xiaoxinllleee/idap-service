package org.cmms.modules.report.sgtzgl.ywzkbxjrmb.entity;

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
 * @Description: 浏阳农商行_业务状况表月报_现金人民币
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_ywzkyb_xjrmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_ywzkyb_xjrmb对象", description="浏阳农商行_业务状况表月报_现金人民币")
public class SgtzYwzkbXjrmb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String branchNo;
	/**机构名称(1)*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String branchName;
	/**机构名称(2)*/
	@Excel(name = "机构名称(1)", width = 15)
    @ApiModelProperty(value = "机构名称(1)")
	private String branchName1;
	/**现金:期初借方余额*/
	@Excel(name = "现金:期初借方余额", width = 15)
    @ApiModelProperty(value = "现金:期初借方余额")
	private java.math.BigDecimal xjQcjfye;
	/**现金:期初贷方余额*/
	@Excel(name = "现金:期初贷方余额", width = 15)
    @ApiModelProperty(value = "现金:期初贷方余额")
	private java.math.BigDecimal xjQcdfye;
	/**现金:本期借方发生额*/
	@Excel(name = "现金:本期借方发生额", width = 15)
    @ApiModelProperty(value = "现金:本期借方发生额")
	private java.math.BigDecimal xjBqjffse;
	/**现金:本期贷方发生额*/
	@Excel(name = "现金:本期贷方发生额", width = 15)
    @ApiModelProperty(value = "现金:本期贷方发生额")
	private java.math.BigDecimal xjBqdffse;
	/**现金:期末借方余额*/
	@Excel(name = "现金:期末借方余额", width = 15)
    @ApiModelProperty(value = "现金:期末借方余额")
	private java.math.BigDecimal xjQmjfye;
	/**现金:期末贷方余额*/
	@Excel(name = "现金:期末贷方余额", width = 15)
    @ApiModelProperty(value = "现金:期末贷方余额")
	private java.math.BigDecimal xjQmdfye;
	/**应收利息:期初借方余额*/
	@Excel(name = "应收利息:期初借方余额", width = 15)
    @ApiModelProperty(value = "应收利息:期初借方余额")
	private java.math.BigDecimal yslxQcjfye;
	/**应收利息:期初贷方余额*/
	@Excel(name = "应收利息:期初贷方余额", width = 15)
    @ApiModelProperty(value = "应收利息:期初贷方余额")
	private java.math.BigDecimal yslxQcdfye;
	/**应收利息:本期借方发生额*/
	@Excel(name = "应收利息:本期借方发生额", width = 15)
    @ApiModelProperty(value = "应收利息:本期借方发生额")
	private java.math.BigDecimal yslxBqjffse;
	/**应收利息:本期贷方发生额*/
	@Excel(name = "应收利息:本期贷方发生额", width = 15)
    @ApiModelProperty(value = "应收利息:本期贷方发生额")
	private java.math.BigDecimal yslxBqdffse;
	/**应收利息:期末借方余额*/
	@Excel(name = "应收利息:期末借方余额", width = 15)
    @ApiModelProperty(value = "应收利息:期末借方余额")
	private java.math.BigDecimal yslxQmjfye;
	/**应收利息:期末贷方余额*/
	@Excel(name = "应收利息:期末贷方余额", width = 15)
    @ApiModelProperty(value = "应收利息:期末贷方余额")
	private java.math.BigDecimal yslxQmdfye;
	/**贴现资产:期初借方余额*/
	@Excel(name = "贴现资产:期初借方余额", width = 15)
    @ApiModelProperty(value = "贴现资产:期初借方余额")
	private java.math.BigDecimal txzcQcjfye;
	/**贴现资产:期初贷方余额*/
	@Excel(name = "贴现资产:期初贷方余额", width = 15)
    @ApiModelProperty(value = "贴现资产:期初贷方余额")
	private java.math.BigDecimal txzcQcdfye;
	/**贴现资产:本期借方发生额*/
	@Excel(name = "贴现资产:本期借方发生额", width = 15)
    @ApiModelProperty(value = "贴现资产:本期借方发生额")
	private java.math.BigDecimal txzcBqjffse;
	/**贴现资产:本期贷方发生额*/
	@Excel(name = "贴现资产:本期贷方发生额", width = 15)
    @ApiModelProperty(value = "贴现资产:本期贷方发生额")
	private java.math.BigDecimal txzcBqdffse;
	/**贴现资产:期末借方余额*/
	@Excel(name = "贴现资产:期末借方余额", width = 15)
    @ApiModelProperty(value = "贴现资产:期末借方余额")
	private java.math.BigDecimal txzcQmjfye;
	/**贴现资产:期末贷方余额*/
	@Excel(name = "贴现资产:期末贷方余额", width = 15)
    @ApiModelProperty(value = "贴现资产:期末贷方余额")
	private java.math.BigDecimal txzcQmdfye;
	/**各项贷款:期初借方余额*/
	@Excel(name = "各项贷款:期初借方余额", width = 15)
    @ApiModelProperty(value = "各项贷款:期初借方余额")
	private java.math.BigDecimal gxdkQcjfye;
	/**各项贷款:期初贷方余额*/
	@Excel(name = "各项贷款:期初贷方余额", width = 15)
    @ApiModelProperty(value = "各项贷款:期初贷方余额")
	private java.math.BigDecimal gxdkQcdfye;
	/**各项贷款:本期借方发生额*/
	@Excel(name = "各项贷款:本期借方发生额", width = 15)
    @ApiModelProperty(value = "各项贷款:本期借方发生额")
	private java.math.BigDecimal gxdkBqjffse;
	/**各项贷款:本期贷方发生额*/
	@Excel(name = "各项贷款:本期贷方发生额", width = 15)
    @ApiModelProperty(value = "各项贷款:本期贷方发生额")
	private java.math.BigDecimal gxdkBqdffse;
	/**各项贷款:期末借方余额*/
	@Excel(name = "各项贷款:期末借方余额", width = 15)
    @ApiModelProperty(value = "各项贷款:期末借方余额")
	private java.math.BigDecimal gxdkQmjfye;
	/**各项贷款:期末贷方余额*/
	@Excel(name = "各项贷款:期末贷方余额", width = 15)
    @ApiModelProperty(value = "各项贷款:期末贷方余额")
	private java.math.BigDecimal gxdkQmdfye;
	/**农业贷款小计:期初借方余额*/
	@Excel(name = "农业贷款小计:期初借方余额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:期初借方余额")
	private java.math.BigDecimal nydkxjQcjfye;
	/**农业贷款小计:期初贷方余额*/
	@Excel(name = "农业贷款小计:期初贷方余额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:期初贷方余额")
	private java.math.BigDecimal nydkxjQcdfye;
	/**农业贷款小计:本期借方发生额*/
	@Excel(name = "农业贷款小计:本期借方发生额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:本期借方发生额")
	private java.math.BigDecimal nydkxjBqjffse;
	/**农业贷款小计:本期贷方发生额*/
	@Excel(name = "农业贷款小计:本期贷方发生额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:本期贷方发生额")
	private java.math.BigDecimal nydkxjBqdffse;
	/**农业贷款小计:期末借方余额*/
	@Excel(name = "农业贷款小计:期末借方余额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:期末借方余额")
	private java.math.BigDecimal nydkxjQmjfye;
	/**农业贷款小计:期末贷方余额*/
	@Excel(name = "农业贷款小计:期末贷方余额", width = 15)
    @ApiModelProperty(value = "农业贷款小计:期末贷方余额")
	private java.math.BigDecimal nydkxjQmdfye;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
