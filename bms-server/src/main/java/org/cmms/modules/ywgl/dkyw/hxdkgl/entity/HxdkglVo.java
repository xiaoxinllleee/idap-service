package org.cmms.modules.ywgl.dkyw.hxdkgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 核销贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YHXDK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YHXDK对象", description="核销贷款管理")
public class HxdkglVo {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String org;

	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String jobnumber;

	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String acctNo;

	/**核销日期*/
	@Excel(name = "核销日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "核销日期")
	private Date hxrq;

}
