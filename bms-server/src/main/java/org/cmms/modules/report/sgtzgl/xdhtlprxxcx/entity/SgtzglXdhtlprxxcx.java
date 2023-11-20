package org.cmms.modules.report.sgtzgl.xdhtlprxxcx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 信贷合同LPR信息查询
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_xdhtlprxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_xdhtlprxx对象", description="信贷合同LPR信息查询")
public class SgtzglXdhtlprxxcx {

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
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String khjg;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**合同签订日*/
	@Excel(name = "合同签订日", width = 15)
    @ApiModelProperty(value = "合同签订日")
	private String htqdr;
	/**合同到期日*/
	@Excel(name = "合同到期日", width = 15)
    @ApiModelProperty(value = "合同到期日")
	private String htdqr;
	/**合同期限*/
	@Excel(name = "合同期限", width = 15)
    @ApiModelProperty(value = "合同期限")
	private String htqx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private String syts;
	/**是否展期*/
	@Excel(name = "是否展期", width = 15)
    @ApiModelProperty(value = "是否展期")
	private String sfzq;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private String htje;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**客户所属行业*/
	@Excel(name = "客户所属行业", width = 15)
    @ApiModelProperty(value = "客户所属行业")
	private String khsshy;
	/**合同利率*/
	@Excel(name = "合同利率", width = 15)
    @ApiModelProperty(value = "合同利率")
	private java.math.BigDecimal htll;
	/**是否启用LPR*/
	@Excel(name = "是否启用LPR", width = 15)
    @ApiModelProperty(value = "是否启用LPR")
	private String sfqylpr;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String lllx;
	/**基点/浮动比例*/
	@Excel(name = "基点/浮动比例", width = 15)
    @ApiModelProperty(value = "基点/浮动比例")
	private java.math.BigDecimal jdfdbl;
	/**有余额贷款笔数*/
	@Excel(name = "有余额贷款笔数", width = 15)
    @ApiModelProperty(value = "有余额贷款笔数")
	private String yyedkbs;
	/**有余额贷款金额*/
	@Excel(name = "有余额贷款金额", width = 15)
    @ApiModelProperty(value = "有余额贷款金额")
	private java.math.BigDecimal yyedkje;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	//@ExcelVerify(interHandler = true)
	private String khjl;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

}
