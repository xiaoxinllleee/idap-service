package org.cmms.modules.xyjlcx.jkyjgl.hmdckjk.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.dynamic.datasource.annotation.DS;
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
 * @Description: 黑名单存款监控
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@Data
@TableName("V_credit_hmdckjk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_credit_hmdckjk对象", description="黑名单存款监控")
public class Hmdckjk {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private Integer zhye;
	/**不良记录行为描述*/
	@Excel(name = "不良记录行为描述", width = 15,dicCode = "bljlxwms")
    @ApiModelProperty(value = "不良记录行为描述")
	@Dict(dicCode = "bljlxwms")
	private String bljlxwms;
	/**表内贷款余额*/
	@Excel(name = "表内贷款余额", width = 15)
    @ApiModelProperty(value = "表内贷款余额")
	private Integer bndkye;
	/**表外贷款余额*/
	@Excel(name = "表外贷款余额", width = 15)
    @ApiModelProperty(value = "表外贷款余额")
	private Integer bwdkye;
	/**贷记卡余额*/
	@Excel(name = "贷记卡余额", width = 15)
    @ApiModelProperty(value = "贷记卡余额")
	private Integer djkye;
}
