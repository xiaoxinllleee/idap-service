package org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 关联账号信息
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_GLZHXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_GLZHXX对象", description="关联账号信息")
public class Glzhxx {

	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	@ExcelVerify(notNull = true)
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	@ExcelVerify(notNull = true)
	private String shmc;
	/**账户户名*/
	@Excel(name = "账户户名", width = 15)
	@ApiModelProperty(value = "账户户名")
	@ExcelVerify(notNull = true)
	private String zhmc;

	/**对应新账号*/
	@Excel(name = "对应新账号", width = 15)
	@ApiModelProperty(value = "对应新账号")
	private String newCkzh;
	/**账号类型*/
	@Excel(name = "账号类型", width = 15,dicCode = "zhlx")
    @ApiModelProperty(value = "账号类型")
	@Dict(dicCode = "zhlx")
	private Integer zhlx;
	/**关联日期*/
	@Excel(name = "关联日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "关联日期")
	private Date glrq;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrrq;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改日期")
	private Date xgrq;

	/**存款账号/卡号*/
	@Excel(name = "存款账号/卡号", width = 15)
	@ApiModelProperty(value = "存款账号/卡号")
	@ExcelVerify(notNull = true,interHandler = true)
	private String ckzh;
}
