package org.cmms.modules.dkjkpt.dhgztxxt.entity;

import com.alibaba.fastjson.JSONArray;
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
 * @Description: 湘潭贷后检查提醒
 * @Author: jeecg-boot
 * @Date:   2023-09-06
 * @Version: V1.0
 */
@Data
@TableName("FXGLJC_DHJCTX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FXGLJC_DHJCTX对象", description="湘潭贷后检查提醒")
public class FxgljcDhjctxVO {

	/**提醒年份*/
	@Excel(name = "提醒年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "提醒年份")
	private Date tjnf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**客户经理工号*/
	@Excel(name = "客户经理", width = 15,dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "客户经理工号")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String khjlgh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**单户贷款金额*/
	@Excel(name = "单户贷款金额", width = 15)
    @ApiModelProperty(value = "单户贷款金额")
	private java.math.BigDecimal dkje;
	/**单户贷款余额*/
	@Excel(name = "单户贷款余额", width = 15)
    @ApiModelProperty(value = "单户贷款余额")
	private java.math.BigDecimal dkye;
	/**最新借款日期*/
	@Excel(name = "最新借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最新借款日期")
	private Date jkrq;
	/**检查到期日期*/
	@Excel(name = "检查到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "检查到期日期")
	private Date dqrq;
	/**附件类型*/
	@Excel(name = "附件类型", width = 15,dicCode="dhjcfjlx")
    @ApiModelProperty(value = "附件类型")
	@Dict(dicCode="dhjcfjlx")
	private String txlx;
	/**未上传报告类型*/
	@Excel(name = "未上传报告类型", width = 15)
    @ApiModelProperty(value = "未上传报告类型")
	private String wsc;
	/**本次年检是否已完成*/
	@Excel(name = "本次年检是否已完成", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "本次年检是否已完成")
	@Dict(dicCode = "sfbz")
	private String bcnjsfywc;

	@ApiModelProperty(value = "资料类型")
	@Dict(dicCode = "id",dictTable = "DKJKPT_DHJCBGJDCS",dicText = "name")
	private String zllx;

	@ApiModelProperty(value = "备注")
	private String bz;
	/**提醒年份*/
	@Excel(name = "提醒年份", width = 15)
	@ApiModelProperty(value = "提醒年份")
	private String fjnf;
	/**附件数据*/
	@Excel(name = "附件数据", width = 15)
	@ApiModelProperty(value = "附件数据")
	private JSONArray imgdate;


}
