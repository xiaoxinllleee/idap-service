package org.cmms.modules.report.sgtzgl.dgckzhyeb.entity;

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
 * @Description: 对公存款账户余额表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_dgckyeb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_dgckyeb对象", description="对公存款账户余额表")
public class SgtzglDgckzhyeb {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String khrq;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	@ApiModelProperty(value = "客户类型")
	private String khlx;
	/**账户名称*/
	@Excel(name = "账户名称", width = 15)
    @ApiModelProperty(value = "账户名称")
	private String zhmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String zzh;
	/**科目编码*/
	@Excel(name = "科目编码", width = 15)
    @ApiModelProperty(value = "科目编码")
	private String kmbm;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
    @ApiModelProperty(value = "科目名称")
	private String kmmc;
	/**产品编码*/
	@Excel(name = "产品编码", width = 15)
    @ApiModelProperty(value = "产品编码")
	private String cpbm;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**账户余额（元）*/
	@Excel(name = "账户余额（元）", width = 15)
    @ApiModelProperty(value = "账户余额（元）")
	private java.math.BigDecimal zhye;
	/**应付利息（元）*/
	@Excel(name = "应付利息（元）", width = 15)
    @ApiModelProperty(value = "应付利息（元）")
	private java.math.BigDecimal yflx;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String tjr;
	/**年平均*/
	@Excel(name = "年平均", width = 15)
    @ApiModelProperty(value = "年平均")
	private java.math.BigDecimal npj;
	/**月平均*/
	@Excel(name = "月平均", width = 15)
    @ApiModelProperty(value = "月平均")
	private java.math.BigDecimal ypj;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal ncye;
	/**比年初*/
	@Excel(name = "比年初", width = 15)
    @ApiModelProperty(value = "比年初")
	private java.math.BigDecimal bnc;
	/**上月余额*/
	@Excel(name = "上月余额", width = 15)
    @ApiModelProperty(value = "上月余额")
	private java.math.BigDecimal syye;
	/**比上月*/
	@Excel(name = "比上月", width = 15)
    @ApiModelProperty(value = "比上月")
	private java.math.BigDecimal bsy;
	/**昨日余额*/
	@Excel(name = "昨日余额", width = 15)
    @ApiModelProperty(value = "昨日余额")
	private java.math.BigDecimal zrye;
	/**比昨日*/
	@Excel(name = "比昨日", width = 15)
    @ApiModelProperty(value = "比昨日")
	private java.math.BigDecimal bzr;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	//@ExcelVerify(interHandler = true)
	private String bz;

	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

}
