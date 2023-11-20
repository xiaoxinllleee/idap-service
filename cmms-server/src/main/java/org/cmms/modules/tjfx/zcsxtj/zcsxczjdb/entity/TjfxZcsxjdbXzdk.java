package org.cmms.modules.tjfx.zcsxtj.zcsxczjdb.entity;

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
 * @Description: 整村授信-新增贷款
 * @Author: jeecg-boot
 * @Date:   2023-08-02
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zcsxjdb_xzdk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zcsxjdb_xzdk对象", description="整村授信-新增贷款")
public class TjfxZcsxjdbXzdk {

	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**wgbh*/
	@Excel(name = "网格编号", width = 15,dicCode = "id", dictTable = "v_yxdygl_main", dicText = "wgmc_show")
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode = "id", dictTable = "v_yxdygl_main", dicText = "wgmc_show")
	private String wgbh;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**hth*/
	@Excel(name = "贷款业务合同号", width = 15)
    @ApiModelProperty(value = "hth")
	private String hth;
	/**businessNo*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "businessNo")
	private String businessNo;
	/**businessPhase*/
	@Excel(name = "业务阶段", width = 15)
    @ApiModelProperty(value = "businessPhase")
	private String businessPhase;
	/**htffrq*/
	@Excel(name = "合同发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "htffrq")
	private Date htffrq;
	/**htdqrq*/
	@Excel(name = "合同到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "htdqrq")
	private Date htdqrq;
	/**htje*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "htje")
	private java.math.BigDecimal htje;
	/**sfhnkd*/
	@Excel(name = "是否惠农快贷", width = 15)
    @ApiModelProperty(value = "sfhnkd")
	private Integer sfhnkd;
	/**dkzh*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "dkzh")
	private String dkzh;
	/**dkje*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "dkje")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**dkffrq*/
	@Excel(name = "贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dkffrq")
	private Date dkffrq;
	/**dkdqrq*/
	@Excel(name = "贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dkdqrq")
	private Date dkdqrq;
	/**ljdkye*/
	@Excel(name = "累计贷款余额", width = 15)
    @ApiModelProperty(value = "ljdkye")
	private java.math.BigDecimal ljdkye;
	/**ljts*/
	@Excel(name = "累计天数", width = 15)
    @ApiModelProperty(value = "ljts")
	private Integer ljts;
	/**sfyxyx*/
	@Excel(name = "是否有效用信", width = 15)
    @ApiModelProperty(value = "sfyxyx")
	private Integer sfyxyx;
	/**yxyxrq*/
	@Excel(name = "有效用信日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "yxyxrq")
	private Date yxyxrq;
	/**累计日期*/
	@Excel(name = "累计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "累计日期")
	private Date ljrq;
}
