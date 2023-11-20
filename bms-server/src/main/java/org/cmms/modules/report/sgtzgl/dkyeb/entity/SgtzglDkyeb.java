package org.cmms.modules.report.sgtzgl.dkyeb.entity;

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
 * @Description: 贷款余额表
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_DKYEB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_DKYEB对象", description="贷款余额表")
public class SgtzglDkyeb {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//  @ApiModelProperty(value = "主键ID")
//	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private String dkqx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private String syts;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15)
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal dkye;
	/**表内应计利息（元）*/
	@Excel(name = "表内应计利息（元）", width = 15)
    @ApiModelProperty(value = "表内应计利息（元）")
	private java.math.BigDecimal bnyjlx;
	/**表内应收利息（元）*/
	@Excel(name = "表内应收利息（元）", width = 15)
    @ApiModelProperty(value = "表内应收利息（元）")
	private java.math.BigDecimal bnyslx;
	/**表外应计利息（元）*/
	@Excel(name = "表外应计利息（元）", width = 15)
    @ApiModelProperty(value = "表外应计利息（元）")
	private java.math.BigDecimal bwyjlx;
	/**表外应收利息（元）*/
	@Excel(name = "表外应收利息（元）", width = 15)
    @ApiModelProperty(value = "表外应收利息（元）")
	private java.math.BigDecimal bwyslx;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**科目编码*/
	@Excel(name = "科目编码", width = 15)
    @ApiModelProperty(value = "科目编码")
	private String kmbh;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
    @ApiModelProperty(value = "科目名称")
	private String kmmc;
	/**产品编号*/
	@Excel(name = "产品编号", width = 15)
    @ApiModelProperty(value = "产品编号")
	private String cpbh;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**客户所属行业类型*/
	@Excel(name = "客户所属行业类型", width = 15)
    @ApiModelProperty(value = "客户所属行业类型")
	private String khsshylx;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String qygm;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
    @ApiModelProperty(value = "贷款形态")
	private String dkxt;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String dklx;
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
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**利率(%)*/
	@Excel(name = "利率(%)", width = 15)
    @ApiModelProperty(value = "利率(%)")
	private String ll;
	/**还款期限*/
	@Excel(name = "还款期限", width = 15)
    @ApiModelProperty(value = "还款期限")
	private String hkqx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	private String dktx2;
	/**延期还本付息标识*/
	@Excel(name = "延期还本付息标识", width = 15)
    @ApiModelProperty(value = "延期还本付息标识")
	@ExcelVerify(interHandler = true)
	private String yqhbfxbs;
//	/**创建人*/
//	@Excel(name = "创建人", width = 15)
//    @ApiModelProperty(value = "创建人")
//	private String createBy;
//	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "创建时间")
//	private Date createTime;
//	/**修改人*/
//	@Excel(name = "修改人", width = 15)
//    @ApiModelProperty(value = "修改人")
//	private String updateBy;
//	/**修改时间*/
//	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "修改时间")
//	private Date updateTime;
}
