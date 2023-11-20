package org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjb.entity;

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
 * @Description: 总帐科目数据表(结转前数据)
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Tgls_glsbusiness_info_b")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgls_glsbusiness_info_b对象", description="总帐科目数据表(结转前数据)")
public class Zzkmsjb {
    
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brhmNo;
	/**币种编码*/
	@Excel(name = "币种编码", width = 15)
    @ApiModelProperty(value = "币种编码")
	private String currNo;
	/**科目编码*/
	@Excel(name = "科目编码", width = 15)
    @ApiModelProperty(value = "科目编码")
	private String itemNo;
	/**日初借方金额*/
	@Excel(name = "日初借方金额", width = 15)
    @ApiModelProperty(value = "日初借方金额")
	private java.math.BigDecimal dayBeginDr;
	/**日初贷方金额*/
	@Excel(name = "日初贷方金额", width = 15)
    @ApiModelProperty(value = "日初贷方金额")
	private java.math.BigDecimal dayBeginCr;
	/**日初借方笔数*/
	@Excel(name = "日初借方笔数", width = 15)
    @ApiModelProperty(value = "日初借方笔数")
	private Long dayBeginDrCount;
	/**日初贷方笔数*/
	@Excel(name = "日初贷方笔数", width = 15)
    @ApiModelProperty(value = "日初贷方笔数")
	private Long dayBeginCrCount;
	/**日发生借方金额*/
	@Excel(name = "日发生借方金额", width = 15)
    @ApiModelProperty(value = "日发生借方金额")
	private java.math.BigDecimal dayNetDr;
	/**日发生贷方金额*/
	@Excel(name = "日发生贷方金额", width = 15)
    @ApiModelProperty(value = "日发生贷方金额")
	private java.math.BigDecimal dayNetCr;
	/**日发生借方笔数*/
	@Excel(name = "日发生借方笔数", width = 15)
    @ApiModelProperty(value = "日发生借方笔数")
	private Long dayNetDrCount;
	/**日发生贷方笔数*/
	@Excel(name = "日发生贷方笔数", width = 15)
    @ApiModelProperty(value = "日发生贷方笔数")
	private Long dayNetCrCount;
	/**日末借方金额*/
	@Excel(name = "日末借方金额", width = 15)
    @ApiModelProperty(value = "日末借方金额")
	private java.math.BigDecimal dayEndDr;
	/**日末贷方金额*/
	@Excel(name = "日末贷方金额", width = 15)
    @ApiModelProperty(value = "日末贷方金额")
	private java.math.BigDecimal dayEndCr;
	/**日末借方笔数*/
	@Excel(name = "日末借方笔数", width = 15)
    @ApiModelProperty(value = "日末借方笔数")
	private Long dayEndDrCount;
	/**日末贷方笔数*/
	@Excel(name = "日末贷方笔数", width = 15)
    @ApiModelProperty(value = "日末贷方笔数")
	private Long dayEndCrCount;
	/**月初借方金额*/
	@Excel(name = "月初借方金额", width = 15)
    @ApiModelProperty(value = "月初借方金额")
	private java.math.BigDecimal monthBeginDr;
	/**月初贷方金额*/
	@Excel(name = "月初贷方金额", width = 15)
    @ApiModelProperty(value = "月初贷方金额")
	private java.math.BigDecimal monthBeginCr;
	/**月发生借方金额*/
	@Excel(name = "月发生借方金额", width = 15)
    @ApiModelProperty(value = "月发生借方金额")
	private java.math.BigDecimal monthNetDr;
	/**月发生贷方金额*/
	@Excel(name = "月发生贷方金额", width = 15)
    @ApiModelProperty(value = "月发生贷方金额")
	private java.math.BigDecimal monthNetCr;
	/**季初借方金额*/
	@Excel(name = "季初借方金额", width = 15)
    @ApiModelProperty(value = "季初借方金额")
	private java.math.BigDecimal quarterBeginDr;
	/**季初贷方金额*/
	@Excel(name = "季初贷方金额", width = 15)
    @ApiModelProperty(value = "季初贷方金额")
	private java.math.BigDecimal quarterBeginCr;
	/**季发生借方金额*/
	@Excel(name = "季发生借方金额", width = 15)
    @ApiModelProperty(value = "季发生借方金额")
	private java.math.BigDecimal quarterNetDr;
	/**季发生贷方金额*/
	@Excel(name = "季发生贷方金额", width = 15)
    @ApiModelProperty(value = "季发生贷方金额")
	private java.math.BigDecimal quarterNetCr;
	/**年初借方金额*/
	@Excel(name = "年初借方金额", width = 15)
    @ApiModelProperty(value = "年初借方金额")
	private java.math.BigDecimal yearBeginDr;
	/**年初贷方金额*/
	@Excel(name = "年初贷方金额", width = 15)
    @ApiModelProperty(value = "年初贷方金额")
	private java.math.BigDecimal yearBeginCr;
	/**年发生借方金额*/
	@Excel(name = "年发生借方金额", width = 15)
    @ApiModelProperty(value = "年发生借方金额")
	private java.math.BigDecimal yearNetDr;
	/**年发生贷方金额*/
	@Excel(name = "年发生贷方金额", width = 15)
    @ApiModelProperty(value = "年发生贷方金额")
	private java.math.BigDecimal yearNetCr;
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
}
