package org.cmms.modules.ywgl.nxt.shbb.zhrpbb.entity;

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
 * @Description: 账户日评报表
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_SHZHRPBB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_SHZHRPBB对象", description="账户日评报表")
public class Zhrpbb {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**评定类型*/
	@Excel(name = "评定类型", width = 15,dicCode = "pdlx")
    @ApiModelProperty(value = "评定类型")
	@Dict(dicCode = "pdlx")
	private String pdlx;
	/**评定周期*/
	@Excel(name = "评定周期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定周期")
	private Date pdzq;
	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**客户经理名称*/
	@Excel(name = "客户经理名称", width = 15)
    @ApiModelProperty(value = "客户经理名称")
	private String khjlmc;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String zhmc;
	/**上期评定日平*/
	@Excel(name = "上期评定日平", width = 15)
    @ApiModelProperty(value = "上期评定日平")
	private java.math.BigDecimal sqpdrp;
	/**本期评定日平*/
	@Excel(name = "本期评定日平", width = 15)
    @ApiModelProperty(value = "本期评定日平")
	private java.math.BigDecimal bqpdrp;
}
