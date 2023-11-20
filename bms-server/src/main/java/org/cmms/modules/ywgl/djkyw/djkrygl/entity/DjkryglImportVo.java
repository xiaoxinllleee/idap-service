package org.cmms.modules.ywgl.djkyw.djkrygl.entity;

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
 * @Description: 贷记卡人员关联
 * @Author: jeecg-boot
 * @Date:   2021-12-03
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DJKYG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DJKYG对象", description="贷记卡人员关联")
public class DjkryglImportVo {
    
	/**贷记卡客户编码*/
	@Excel(name = "贷记卡客户编码", width = 15)
    @ApiModelProperty(value = "贷记卡客户编码")
	private String tgrybh;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;

}
