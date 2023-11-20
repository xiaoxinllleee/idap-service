package org.cmms.modules.hr.xsgl.gwxssz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * @Description: 岗位系数管理
 * @Author: jeecg-boot
 * @Date:   2021-10-25
 * @Version: V1.0
 */
@Data
@TableName("ERP_POST_KHXS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_POST_KHXS对象", description="岗位系数管理")
public class ErpPostKhxs {

	/**组织标识*/
	@Excel(name = "组织简称", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15, dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode="gwbz",dictTable="hr_bas_post",dicText="gwmc")
	@ExcelVerify(notNull = true)
	private Integer gwbz;
	/**考核系数*/
	@Excel(name = "考核系数", width = 15)
    @ApiModelProperty(value = "考核系数")
	@ExcelVerify(notNull = true, interHandler = true)
	private java.math.BigDecimal khxs;
	/**不参与考核系数*/
	@Excel(name = "不参与考核系数", width = 15)
    @ApiModelProperty(value = "不参与考核系数")
	private java.math.BigDecimal bcykhxs;
	/**总系数*/
	@Excel(name = "总系数", width = 15)
    @ApiModelProperty(value = "总系数")
	private java.math.BigDecimal zxs;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
}
