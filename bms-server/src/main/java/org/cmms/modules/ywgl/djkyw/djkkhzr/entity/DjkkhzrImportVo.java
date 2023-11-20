package org.cmms.modules.ywgl.djkyw.djkkhzr.entity;

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
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DJKKHZR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DJKKHZR对象", description="贷记卡考核责任")
public class DjkkhzrImportVo {


	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String djkkh;

	/**责任人工号*/
	@Excel(name = "责任人工号", width = 15)
	@ApiModelProperty(value = "责任人工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String jobnumberzr;

	/**追责标志*/
	@Excel(name = "追责标志", width = 15,dicCode = "zzbs")
	@ApiModelProperty(value = "追责标志")
	@Dict(dicCode = "zzbs")
	private Integer zzbz;
	/**追责日期*/
	@Excel(name = "追责日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "追责日期")
	private Date zzrq;

}
