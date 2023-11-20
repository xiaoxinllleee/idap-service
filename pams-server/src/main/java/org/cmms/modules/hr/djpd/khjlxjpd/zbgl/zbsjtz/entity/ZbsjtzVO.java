package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity;

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
 * @Description: 指标数据调整
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_ZBSJ_TZ对象", description="指标数据调整")
public class ZbsjtzVO {
	/**评定周期*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
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
	/**数据项ID*/
	@Excel(name = "指标标识", width = 15)
	@ApiModelProperty(value = "指标标识")
	private String sjxid;
	/**调整类型(0 新增 1 覆盖 2更新)*/
	@Excel(name = "调整类型", width = 15,dicCode = "tzlx")
	@ApiModelProperty(value = "调整类型")
	@Dict(dicCode = "tzlx")
	private Integer tzlx;
	/**调整值*/
	@Excel(name = "调整值", width = 15)
    @ApiModelProperty(value = "调整值")
	private java.math.BigDecimal tzz;

}
