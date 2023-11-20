package org.cmms.modules.xdgl.dksp.dkspedsz.entity;

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
 * @Description: 贷款审批额度设置
 * @Author: jeecg-boot
 * @Date:   2021-11-25
 * @Version: V1.0
 */
@Data
@TableName("cams_dksp_edsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cams_dksp_edsz对象", description="贷款审批额度设置")
public class Dkspedsz {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**审批额度起*/
	@Excel(name = "审批额度起(万元)", width = 15)
    @ApiModelProperty(value = "审批额度起(万元)")
	private java.math.BigDecimal spedBegin;
	/**审批额度止*/
	@Excel(name = "审批额度止(万元)", width = 15)
    @ApiModelProperty(value = "审批额度止(万元)")
	private java.math.BigDecimal spedEnd;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
