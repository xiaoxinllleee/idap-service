package org.cmms.modules.dkjkpt.dksjjk.yzdjhtz.zdjhtzdr.entity;

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
 * @Description: 已制定计划台账导入
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_ZDJHTZDR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_ZDJHTZDR对象", description="已制定计划台账导入")
public class DkjkptZdjhtzdrVO {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**签订类型*/
	@Excel(name = "签订类型", width = 15,dicCode = "qdlx")
    @ApiModelProperty(value = "签订类型")
	@Dict(dicCode = "qdlx")
	private String qdlx;
	/**还款频率*/
	@Excel(name = "还款频率", width = 15,dicCode = "ywltj_tjwd")
	@ApiModelProperty(value = "还款频率")
	@Dict(dicCode = "ywltj_tjwd")
	private String hkpl;
	/**计划还款金额*/
	@Excel(name = "计划还款金额", width = 15)
    @ApiModelProperty(value = "计划还款金额")
	private java.math.BigDecimal jhkkjg;
	/**计划还款截止日期*/
	@Excel(name = "计划还款截止日期", width = 15)
    @ApiModelProperty(value = "计划还款截止日期")
	private String jhhkjzrq;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	@ExcelVerify(interHandler = true)
	private String bz;

}
