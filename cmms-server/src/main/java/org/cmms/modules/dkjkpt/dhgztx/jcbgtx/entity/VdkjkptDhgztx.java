package org.cmms.modules.dkjkpt.dhgztx.jcbgtx.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 检查报告提醒
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@Data
@TableName("v_dkjkpt_dhgztx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_dkjkpt_dhgztx对象", description="检查报告提醒")
public class VdkjkptDhgztx {

	/**统计年份*/
	@Excel(name = "统计年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjnf")
	private Date tjnf;
	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**dhdkje*/
	@Excel(name = "单户贷款金额", width = 15)
    @ApiModelProperty(value = "dhdkje")
	private Long dhdkje;
	/**dhdkye*/
	@Excel(name = "单户贷款余额", width = 15)
    @ApiModelProperty(value = "dhdkye")
	private Long dhdkye;
	/**zxjkrq*/
	@Excel(name = "最新借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "zxjkrq")
	private Date zxjkrq;
	/**txlx*/
	@Excel(name = "附件类型", width = 15,dicCode="dhjcfjlx")
    @ApiModelProperty(value = "txlx")
	@Dict(dicCode="dhjcfjlx")
	private Integer txlx;
	/**dqrq*/
	@Excel(name = "检查到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "dqrq")
	private Date dqrq;
	/**wsc*/
	@Excel(name = "未上传报告类型", width = 15)
    @ApiModelProperty(value = "wsc")
	private String wsc;
	/**khjlyggh*/
	@Excel(name = "客户经理", width = 15,dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
    @ApiModelProperty(value = "khjlyggh")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String khjlyggh;
}
