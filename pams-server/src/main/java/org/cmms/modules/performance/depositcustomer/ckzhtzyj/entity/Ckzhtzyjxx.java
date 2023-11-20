package org.cmms.modules.performance.depositcustomer.ckzhtzyj.entity;

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
 * @Description: 存款账号拓展移交信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Data
@TableName("v_khgxgl_ckzhtzyjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgxgl_ckzhtzyjxx对象", description="存款账号拓展移交信息")
public class Ckzhtzyjxx {
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;

	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private java.lang.String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**存款账户*/
	@Excel(name = "存款账户", width = 15)
    @ApiModelProperty(value = "存款账户")
	private java.lang.String ckzh;
	/**zhlx*/
	@Excel(name = "账号类型", width = 15, dicCode = "ckzhlx")
    @ApiModelProperty(value = "账号类型")
	@Dict(dicCode = "ckzhlx")
	private java.lang.String zhlx;
	/**营销类型*/
	@Excel(name = "营销类型", width = 15, dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型")
	@Dict(dicCode = "yxlx")
	private java.lang.String yxlx;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private java.util.Date khrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private java.util.Date dqrq;
	/**管户类型*/
	@Excel(name = "管户类型", width = 15)
    @ApiModelProperty(value = "管户类型")
	private java.lang.Integer ghlx;
	/**管户人*/
	@Excel(name = "管户人", width = 15)
    @ApiModelProperty(value = "管户人")
	private java.lang.String ghr;
	/**管户比例(%)*/
	@Excel(name = "管户比例(%)", width = 15)
    @ApiModelProperty(value = "管户比例(%)")
	private java.math.BigDecimal ghbl;
}
