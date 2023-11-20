package org.cmms.modules.ywgl.djkyw.djkxxgl.entity;

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
 * @Description: 贷记卡信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-05
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DJKBLXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DJKBLXX对象", description="贷记卡信息管理")
public class DjkxxglImportVo {



	/**推广人员编号*/
	@Excel(name = "推广人员编号", width = 15)
    @ApiModelProperty(value = "推广人员编号")
	private String tgh;

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;

	/**卡号*/
	@Excel(name = "卡号", width = 15)
	@ApiModelProperty(value = "卡号")
	private String acctNo;
	/**种类*/
	@Excel(name = "种类", width = 15)
	@ApiModelProperty(value = "种类")
	private String jzkm;


	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String ctfcCd;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String custName;



	/**余额*/
	@Excel(name = "余额", width = 15)
	@ApiModelProperty(value = "余额")
	private java.math.BigDecimal balance;

	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
	@ApiModelProperty(value = "逾期期数")
	private Long yqqs;




}
