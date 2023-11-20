package org.cmms.modules.bigscreen.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 大屏首页综合信息
 * @Author: jeecg-boot
 * @Date:   2023-11-02
 * @Version: V1.0
 */
@Data
@TableName("DP_INDEX_Zhxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DP_INDEX_Zhxx对象", description="大屏首页综合信息")
public class DpIndexZhxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**手机银行业务*/
	@Excel(name = "手机银行业务", width = 15)
    @ApiModelProperty(value = "手机银行业务")
	private String sjyhYw;
	/**手机银行数量*/
	@Excel(name = "手机银行数量", width = 15)
    @ApiModelProperty(value = "手机银行数量")
	private BigDecimal sjyhSl;
	/**手机银行存款*/
	@Excel(name = "手机银行存款", width = 15)
    @ApiModelProperty(value = "手机银行存款")
	private BigDecimal sjyhCk;
	/**手机银行月交易量*/
	@Excel(name = "手机银行月交易量", width = 15)
    @ApiModelProperty(value = "手机银行月交易量")
	private BigDecimal sjyhYjyl;
	/**支行*/
	@Excel(name = "支行", width = 15)
    @ApiModelProperty(value = "支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**存款存款余额*/
	@Excel(name = "存款存款余额", width = 15)
    @ApiModelProperty(value = "存款存款余额")
	private BigDecimal ckCkye;
	/**存款低息余额*/
	@Excel(name = "存款低息余额", width = 15)
    @ApiModelProperty(value = "存款低息余额")
	private BigDecimal ckDyye;
	/**存款两年定期*/
	@Excel(name = "存款两年定期", width = 15)
    @ApiModelProperty(value = "存款两年定期")
	private BigDecimal ckLndq;
	/**存款三年定期*/
	@Excel(name = "存款三年定期", width = 15)
    @ApiModelProperty(value = "存款三年定期")
	private BigDecimal ckSndq;
	/**存款五年及以上*/
	@Excel(name = "存款五年及以上", width = 15)
    @ApiModelProperty(value = "存款五年及以上")
	private BigDecimal ckWnjys;
	/**贷款贷款*/
	@Excel(name = "贷款贷款", width = 15)
    @ApiModelProperty(value = "贷款贷款")
	private BigDecimal dkDk;
	/**贷款平台*/
	@Excel(name = "贷款平台", width = 15)
    @ApiModelProperty(value = "贷款平台")
	private BigDecimal dkPt;
	/**贷款一般*/
	@Excel(name = "贷款一般", width = 15)
    @ApiModelProperty(value = "贷款一般")
	private BigDecimal dkYb;
	/**贷款扶贫*/
	@Excel(name = "贷款扶贫", width = 15)
    @ApiModelProperty(value = "贷款扶贫")
	private BigDecimal dkFp;
	/**贷款按揭*/
	@Excel(name = "贷款按揭", width = 15)
    @ApiModelProperty(value = "贷款按揭")
	private BigDecimal dkAj;
	/** 1-手机银行 2-存款 3-贷款 **/
	private String type;
	private String jgdm;
	private String zzjc;
}
