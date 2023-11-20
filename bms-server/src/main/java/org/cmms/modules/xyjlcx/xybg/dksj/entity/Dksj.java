package org.cmms.modules.xyjlcx.xybg.dksj.entity;

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
 * @Description: 贷款数据
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Data
@TableName("CREDIT_DKSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CREDIT_DKSJ对象", description="贷款数据")
public class Dksj {

	/**账号类型*/
	@Excel(name = "账号类型", width = 15)
    @ApiModelProperty(value = "账号类型")
	private java.lang.String zhlx;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private java.lang.String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private java.lang.String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private java.util.Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private java.util.Date dqrq;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**核心余额*/
	@Excel(name = "核心余额", width = 15)
    @ApiModelProperty(value = "核心余额")
	private java.math.BigDecimal hxye;
	/**最小欠息日*/
	@Excel(name = "最小欠息日", width = 15)
    @ApiModelProperty(value = "最小欠息日")
	private java.lang.String zxqxr;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	@Dict(dicCode = "wjflbz")
	private java.lang.String wjflbz;
	/**累计欠息次数*/
	@Excel(name = "累计欠息次数", width = 15)
    @ApiModelProperty(value = "累计欠息次数")
	private java.lang.Integer ljqxcs;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private java.lang.String ywzl;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	@Dict(dicCode = "account_status")
	private java.lang.String zhzt;
	/**欠息未结清*/
	@Excel(name = "欠息未结清", width = 15)
    @ApiModelProperty(value = "欠息未结清")
	private java.math.BigDecimal qxwjq;
	/**欠息合计*/
	@Excel(name = "欠息合计", width = 15)
    @ApiModelProperty(value = "欠息合计")
	private java.math.BigDecimal qxhj;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	@Dict(dicCode = "khjlbh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String khjl;
	/**担保人证件号码*/
	@Excel(name = "担保人证件号码", width = 15)
    @ApiModelProperty(value = "担保人证件号码")
	private java.lang.String dbrzjhm;
}
