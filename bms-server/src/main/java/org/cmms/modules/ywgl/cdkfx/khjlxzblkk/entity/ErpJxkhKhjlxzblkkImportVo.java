package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity;

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
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Data
@TableName("Erp_jxkh_khjlxzblkk_t")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_jxkh_khjlxzblkk_t对象", description="客户经理新增不良扣款")
public class ErpJxkhKhjlxzblkkImportVo {

	/**考核月份*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	private Date khyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;

	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;

	/**本期实扣*/
	@Excel(name = "本期实扣(元)", width = 15)
	@ApiModelProperty(value = "本期实扣(元)")
	private java.math.BigDecimal bqsk;


}
