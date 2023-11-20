package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zbzhpj.entity;

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
 * @Description: 总部综合评价
 * @Author: jeecg-boot
 * @Date:   2021-09-09
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_ZBPJ对象", description="总部综合评价")
public class ZbzhpjVo {
    //机构名称,客户经理标识,客户经理名称,评价年份,优秀得票数,良好得票数,一般得票数,扣分
	/**组织标识*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**员工姓名*/
	@Excel(name = "客户经理名称", width = 15)
    @ApiModelProperty(value = "客户经理名称")
	private String ygxm;
	/**评价年份*/
	@Excel(name = "评价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评价年份")
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
	private java.math.BigDecimal kf;
}
