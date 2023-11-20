package org.cmms.modules.khgl.khzhfx.khzhmx.entity;

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
 * @Description: 贷款信息明细
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Data
@TableName("Khxxgl_khzhfx_dkxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Khxxgl_khzhfx_dkxx对象", description="贷款信息明细")
public class DkxxKhzh {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private java.lang.String sszh;
	/**归属网格*/
	@Excel(name = "归属网格编号", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	@ApiModelProperty(value = "归属网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	private java.lang.String wgbh;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15, dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	@ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private java.lang.String sskhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private java.lang.String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**贷款机构*/
	@Excel(name = "贷款机构", width = 15)
    @ApiModelProperty(value = "贷款机构")
	private java.lang.String dkjg;

	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private java.lang.String dkzh;

	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private java.util.Date dqrq;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private java.lang.String subjNo;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
    @ApiModelProperty(value = "科目名称")
	private java.lang.String subjName;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	private java.lang.String dkpz;
}
