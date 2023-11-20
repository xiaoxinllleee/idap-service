package org.cmms.modules.tjfx.shpjsx.zh.entity;

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
 * @Description: 支行评级授信统计-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_ZHPJSXTJBB_SH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_ZHPJSXTJBB_SH对象", description="支行评级授信统计-商户")
public class TjfxZhpjsxtjbbSh {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**评级户数*/
	@Excel(name = "评级户数", width = 15)
    @ApiModelProperty(value = "评级户数")
	private Integer pjhs;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
	@ApiModelProperty(value = "授信户数")
	private Integer sxhs;
	/**系统测算金额*/
	@Excel(name = "系统测算金额", width = 15)
    @ApiModelProperty(value = "系统测算金额")
	private java.math.BigDecimal xtcsje;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
    @ApiModelProperty(value = "客户经理授信额度")
	private java.math.BigDecimal khjlsxed;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}
