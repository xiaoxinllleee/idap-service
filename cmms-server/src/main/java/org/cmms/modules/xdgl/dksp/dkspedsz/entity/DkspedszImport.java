package org.cmms.modules.xdgl.dksp.dkspedsz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
public class DkspedszImport {
    
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
	@Dict(dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
	private String zzbz;
	/**审批额度起*/
	@Excel(name = "审批额度起(万元)", width = 15)
	private java.math.BigDecimal spedBegin;
	/**审批额度止*/
	@Excel(name = "审批额度止(万元)", width = 15)
	private java.math.BigDecimal spedEnd;
}
