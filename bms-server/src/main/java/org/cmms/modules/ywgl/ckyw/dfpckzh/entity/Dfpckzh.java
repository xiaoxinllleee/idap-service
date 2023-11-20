package org.cmms.modules.ywgl.ckyw.dfpckzh.entity;

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
 * @Description: 待分配存款账号管理
 * @Author: jeecg-boot
 * @Date:   2021-10-11
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_DFPCKZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_DFPCKZH对象", description="待分配存款账号管理")
public class Dfpckzh {
    
	/**dz*/
	@Excel(name = "dz", width = 15)
    @ApiModelProperty(value = "dz")
	private String dz;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**月账户日平*/
	@Excel(name = "月账户日平", width = 15)
    @ApiModelProperty(value = "月账户日平")
	private java.math.BigDecimal yzhrpye;
	/**年账户日平*/
	@Excel(name = "年账户日平", width = 15)
    @ApiModelProperty(value = "年账户日平")
	private java.math.BigDecimal nzhrpye;
	/**账户余额*/
	@Excel(name = "账户余额", width = 15)
    @ApiModelProperty(value = "账户余额")
	private java.math.BigDecimal zhye;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "客户账号")
	private String khzh;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**账号性质*/
	@Excel(name = "账号性质", width = 15)
    @ApiModelProperty(value = "账号性质")
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
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
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrczy;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
}
