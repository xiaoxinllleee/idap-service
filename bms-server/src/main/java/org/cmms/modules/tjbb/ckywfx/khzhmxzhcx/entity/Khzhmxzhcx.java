package org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.entity;

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
 * @Description: 客户账户明细综合查询
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Data
@TableName("tjbb_ckyw_khzhmxzhcx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_ckyw_khzhmxzhcx对象", description="客户账户明细综合查询")
public class Khzhmxzhcx {

	/**账户*/
	@Excel(name = "账户", width = 15)
    @ApiModelProperty(value = "账户")
	private String zh;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String khjg;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "交易日期")
	private Date jyrq;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String jysj;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String lsh;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String jym;
	/**交易名称*/
	@Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
	private String jymc;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal jyje;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal ye;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String czbz;
	/**交易柜员*/
	@Excel(name = "交易柜员号", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String jygy;
	/**交易柜员名*/
	@Excel(name = "交易柜员名", width = 15)
    @ApiModelProperty(value = "交易柜员名")
	private String jygym;
	/**交易机构*/
	@Excel(name = "交易机构", width = 15)
    @ApiModelProperty(value = "交易机构")
	private String jyjg;
	/**交易机构名*/
	@Excel(name = "交易机构名", width = 15)
	@ApiModelProperty(value = "交易机构名")
	private String jyjgm;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String qdlx;
	/**对方账户*/
	@Excel(name = "对方账户", width = 15)
    @ApiModelProperty(value = "对方账户")
	private String dfzh;
	/**对方户名*/
	@Excel(name = "对方户名", width = 15)
    @ApiModelProperty(value = "对方户名")
	private String dfhm;
	/**对方机构号*/
	@Excel(name = "对方机构号", width = 15)
    @ApiModelProperty(value = "对方机构号")
	private String dfjgh;
	/**对方机构名*/
	@Excel(name = "对方机构名", width = 15)
    @ApiModelProperty(value = "对方机构名")
	private String dfjgm;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String zy;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String qd;
	/**渠道流水号*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String qdlsh;
	/**渠道对方账户*/
	@Excel(name = "渠道对方账户", width = 15)
    @ApiModelProperty(value = "渠道对方账户")
	private String qddfzh;
	/**渠道对方户名*/
	@Excel(name = "渠道对方户名", width = 15)
    @ApiModelProperty(value = "渠道对方户名")
	private String qddfhm;
	/**渠道对方机构号*/
	@Excel(name = "渠道对方机构号", width = 15)
    @ApiModelProperty(value = "渠道对方机构号")
	private String qddfjgh;
	/**渠道对方机构名*/
	@Excel(name = "渠道对方机构名", width = 15)
    @ApiModelProperty(value = "渠道对方机构名")
	private String qddfjgm;
	/**渠道备注*/
	@Excel(name = "渠道备注", width = 15)
    @ApiModelProperty(value = "渠道备注")
	private String qdbz;
	/**渠道附言*/
	@Excel(name = "渠道附言", width = 15)
    @ApiModelProperty(value = "渠道附言")
	private String qdfy;
	/**账户类型(1:内部账，2:存款账户)*/
	@Excel(name = "账户类型", width = 15, dicCode = "nbz_zhlx")
	@ApiModelProperty(value = "账户类型")
	@Dict(dicCode = "nbz_zhlx")
	private String lx;

	/**渠道交易金额*/
	// @Excel(name = "渠道交易金额", width = 15)
    @ApiModelProperty(value = "渠道交易金额")
	private java.math.BigDecimal qdjyje;
}
