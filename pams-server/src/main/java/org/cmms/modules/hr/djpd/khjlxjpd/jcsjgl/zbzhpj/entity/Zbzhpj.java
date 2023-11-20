package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.entity;

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
 * @Description: 总部综合评价
 * @Author: jeecg-boot
 * @Date:   2021-09-09
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_ZBPJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_ZBPJ对象", description="总部综合评价")
public class Zbzhpj {
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位名称", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
    @ApiModelProperty(value = "岗位名称")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**员工姓名*/
	@Excel(name = "客户经理名称", width = 15)
    @ApiModelProperty(value = "客户经理名称")
	@ExcelVerify(notNull = true)
	private String ygxm;
	/**评价年份*/
	@Excel(name = "评价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评价年份")
	@ExcelVerify(notNull = true)
	private Date pjnf;
	/**优秀得票数*/
	@Excel(name = "优秀得票数", width = 15)
    @ApiModelProperty(value = "优秀得票数")
	private Integer yxdps;
	/**良好得票数*/
	@Excel(name = "良好得票数", width = 15)
    @ApiModelProperty(value = "良好得票数")
	private Integer lhdps;
	/**一般得票数*/
	@Excel(name = "一般得票数", width = 15)
    @ApiModelProperty(value = "一般得票数")
	private Integer ybdps;
	/**扣分*/
	@Excel(name = "扣分", width = 15)
    @ApiModelProperty(value = "扣分")
	@ExcelVerify(interHandler = true)
	private java.math.BigDecimal kf;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
