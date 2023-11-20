package org.cmms.modules.khgl.ckkh.entity;

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
 * @Description: 存款账号管户信息
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_ckzhghxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_ckzhghxx对象", description="存款账号管户信息")
public class KhgxglCkzhghxx {
    
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**营销类型(1:主动营销,2:自然增长)*/
	@Excel(name = "营销类型(1:主动营销,2:自然增长)", width = 15, dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型(1:主动营销,2:自然增长)")
	@Dict(dicCode = "yxlx")
	private Integer yxlx;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**账号类型*/
	@Excel(name = "账号类型", width = 15)
    @ApiModelProperty(value = "账号类型")
	private String zhlx;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal ll;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**拓展人*/
	@Excel(name = "拓展人", width = 15, dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "拓展人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String tzr;
	/**拓展日期*/
	@Excel(name = "拓展日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "拓展日期")
	private Date tzrq;
	/**当年认领金额*/
	@Excel(name = "当年认领金额", width = 15)
    @ApiModelProperty(value = "当年认领金额")
	private java.math.BigDecimal dnrlje;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
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
	private Date lrsj;
	/**存期*/
	@Excel(name = "存期", width = 15)
    @ApiModelProperty(value = "存期")
	private String cq;
	/**拓展比例*/
	@Excel(name = "拓展比例", width = 15)
    @ApiModelProperty(value = "拓展比例")
	private String tzbl;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**上年存款年日均*/
	@Excel(name = "上年存款年日均", width = 15)
    @ApiModelProperty(value = "上年存款年日均")
	private java.math.BigDecimal sncknrj;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**存款月日平*/
	@Excel(name = "存款月日平", width = 15)
    @ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckyrp;
}
