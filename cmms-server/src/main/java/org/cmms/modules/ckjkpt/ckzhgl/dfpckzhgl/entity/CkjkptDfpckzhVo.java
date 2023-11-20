package org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @Description: 待分配存款帐号管理
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Data
@TableName("CKJKPT_DFPCKZH")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@ApiModel(value="v_CKJKPT_DFPCKZH对象", description="待分配存款帐号管理")
public class CkjkptDfpckzhVo {
	@Excel(name = "支行代码",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "SJZZJC")
    @Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    private String sjywjgdm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "khzh")
	private String khzh;
    /**卡号*/
    @Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
    private String kh;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**开户日期*/
    @Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
    private Date khrq;
    /**帐号性质*/
    @Excel(name = "帐号性质", width = 15,dicCode = "zhxz")
    @ApiModelProperty(value = "帐号性质")
    @Dict(dicCode = "zhxz")
    private Integer zhxz;
    /**存期*/
    @Excel(name = "存期", width = 15)
    @ApiModelProperty(value = "存期")
    private String cq;
    /**本期起息日*/
    @Excel(name = "本期起息日", width = 15)
    @ApiModelProperty(value = "本期起息日")
    private String bqqxr;
    /**本期截至日*/
    @Excel(name = "本期截至日", width = 15)
    @ApiModelProperty(value = "本期截至日")
    private String bqjzr;
    /**账户余额*/
    @Excel(name = "账户余额", width = 15)
    @ApiModelProperty(value = "账户余额")
    private java.math.BigDecimal zhye;
    /**月存款日平余额*/
    @Excel(name = "月存款日平余额", width = 15)
    @ApiModelProperty(value = "月存款日平余额")
    private java.math.BigDecimal yzhrpye;
    /**年存款日平余额*/
    @Excel(name = "年存款日平余额", width = 15)
    @ApiModelProperty(value = "年存款日平余额")
    private java.math.BigDecimal nzhrpye;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;

	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrczy*/
	@Excel(name = "录入操作员", width = 15)
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;

}
