package org.cmms.modules.khgl.ckkh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工管户数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Data
@ApiModel(value="TB_CK_YGGHCKSJMX对象", description="员工管户数据明细")
public class TbCkYgghcksjmxVo {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**子账号*/
	@Excel(name = "子账号", width = 15)
    @ApiModelProperty(value = "子账号")
	private String ckzh;
	/**主账号*/
	@Excel(name = "主账号", width = 15)
    @ApiModelProperty(value = "主账号")
	private String mastAcct;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String khrq;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
    @ApiModelProperty(value = "本期起息日")
	private String intFrmDt;
	/**本期截止日*/
	@Excel(name = "本期截止日", width = 15)
    @ApiModelProperty(value = "本期截止日")
	private String intToDt;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@Dict(dicCode = "zjhm",dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "khbh" ,ds = "eweb")
	private String zjhm;
	/**账户组别*/
	@Excel(name = "账户组别", width = 15)
    @ApiModelProperty(value = "账户组别")
	private String acctGrp;
	/**账户处理标志*/
	@Excel(name = "账户处理标志", width = 15)
    @ApiModelProperty(value = "账户处理标志")
	private String acctDesc;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String endDate;
	/**定期最大存期*/
	@Excel(name = "定期最大存期", width = 15)
    @ApiModelProperty(value = "定期最大存期")
	private String cq;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private String ll;
	/**期末余额*/
	@Excel(name = "期末余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private java.math.BigDecimal qmye;
	/**存量余额*/
	@Excel(name = "存量余额", width = 15)
    @ApiModelProperty(value = "存量余额")
	private java.math.BigDecimal clye;
	/**存量月日平1*/
	@Excel(name = "存量月日平1", width = 15)
    @ApiModelProperty(value = "存量月日平1")
	private java.math.BigDecimal clYrp1;
	/**存量月日平2*/
	@Excel(name = "存量月日平2", width = 15)
    @ApiModelProperty(value = "存量月日平2")
	private java.math.BigDecimal clYrp2;
	/**存量月日平3*/
	@Excel(name = "存量月日平3", width = 15)
    @ApiModelProperty(value = "存量月日平3")
	private java.math.BigDecimal clYrp3;
	/**月日平1*/
	@Excel(name = "月日平1", width = 15)
    @ApiModelProperty(value = "月日平1")
	private java.math.BigDecimal yrp1;
	/**月日平2*/
	@Excel(name = "月日平2", width = 15)
    @ApiModelProperty(value = "月日平2")
	private java.math.BigDecimal yrp2;
	/**月日平3*/
	@Excel(name = "月日平3", width = 15)
    @ApiModelProperty(value = "月日平3")
	private java.math.BigDecimal yrp3;
	/**存量季日平1*/
	@Excel(name = "存量季日平1", width = 15)
    @ApiModelProperty(value = "存量季日平1")
	private java.math.BigDecimal clJrp1;
	/**存量季日平2*/
	@Excel(name = "存量季日平2", width = 15)
    @ApiModelProperty(value = "存量季日平2")
	private java.math.BigDecimal clJrp2;
	/**存量季日平3*/
	@Excel(name = "存量季日平3", width = 15)
    @ApiModelProperty(value = "存量季日平3")
	private java.math.BigDecimal clJrp3;
	/**季日平1*/
	@Excel(name = "季日平1", width = 15)
    @ApiModelProperty(value = "季日平1")
	private java.math.BigDecimal jrp1;
	/**季日平2*/
	@Excel(name = "季日平2", width = 15)
    @ApiModelProperty(value = "季日平2")
	private java.math.BigDecimal jrp2;
	/**季日平3*/
	@Excel(name = "季日平3", width = 15)
    @ApiModelProperty(value = "季日平3")
	private java.math.BigDecimal jrp3;
	/**存量年日平1*/
	@Excel(name = "存量年日平1", width = 15)
    @ApiModelProperty(value = "存量年日平1")
	private java.math.BigDecimal clNrp1;
	/**存量年日平2*/
	@Excel(name = "存量年日平2", width = 15)
    @ApiModelProperty(value = "存量年日平2")
	private java.math.BigDecimal clNrp2;
	/**存量年日平3*/
	@Excel(name = "存量年日平3", width = 15)
    @ApiModelProperty(value = "存量年日平3")
	private java.math.BigDecimal clNrp3;
	/**年日平1*/
	@Excel(name = "年日平1", width = 15)
    @ApiModelProperty(value = "年日平1")
	private java.math.BigDecimal nrp1;
	/**年日平2*/
	@Excel(name = "年日平2", width = 15)
    @ApiModelProperty(value = "年日平2")
	private java.math.BigDecimal nrp2;
	/**年日平3*/
	@Excel(name = "年日平3", width = 15)
    @ApiModelProperty(value = "年日平3")
	private java.math.BigDecimal nrp3;
	/**模拟利润*/
	@Excel(name = "模拟利润", width = 15)
    @ApiModelProperty(value = "模拟利润")
	private java.math.BigDecimal mnlr;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String currStatus;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String acNo;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
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
	/**上月余额*/
	@Excel(name = "上月余额", width = 15)
    @ApiModelProperty(value = "上月余额")
	private java.math.BigDecimal syye;
	/**账户性质*/
	@Excel(name = "账户性质", width = 15)
    @ApiModelProperty(value = "账户性质")
	private String zhlx;
	/**营销类型*/
	@Excel(name = "营销类型", width = 15)
    @ApiModelProperty(value = "营销类型")
	private Integer yxlx;
	/**利率差*/
	@Excel(name = "利率差", width = 15)
    @ApiModelProperty(value = "利率差")
	private java.math.BigDecimal llc;
	/**日利率差*/
	@Excel(name = "日利率差", width = 15)
    @ApiModelProperty(value = "日利率差")
	private java.math.BigDecimal rllc;
	/**成本率*/
	@Excel(name = "成本率", width = 15)
    @ApiModelProperty(value = "成本率")
	private java.math.BigDecimal cbl;
	/**cpdl*/
	@Excel(name = "cpdl", width = 15)
    @ApiModelProperty(value = "cpdl")
	private String cpdl;
	/**cpzl*/
	@Excel(name = "cpzl", width = 15)
    @ApiModelProperty(value = "cpzl")
	private String cpzl;
	/**cpdm*/
	@Excel(name = "cpdm", width = 15)
    @ApiModelProperty(value = "cpdm")
	private String cpdm;
	/**khbh*/
	@Excel(name = "khbh", width = 15)
    @ApiModelProperty(value = "khbh")
	private String khbh;
	/**khbh*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**较上日*/
	@Excel(name = "jsr", width = 15)
    @ApiModelProperty(value = "jsr")
	private String jsr;
	/**较上月*/
	@Excel(name = "jsy", width = 15)
    @ApiModelProperty(value = "jsy")
	private String jsy;

	/**
	 * 较年初
	 */
	@Excel(name = "jnc", width = 15)
	@ApiModelProperty(value = "jnc")
	private String jnc;
	/**
	 * 较季初
	 */
	@Excel(name = "jnc", width = 15)
	@ApiModelProperty(value = "jnc")
	private String jjc;

	@Excel(name = "qmyeS", width = 15)
	@ApiModelProperty(value = "qmyeS")
	private String qmyeS;

	@Excel(name = "qmyeE", width = 15)
	@ApiModelProperty(value = "qmyeE")
	private String qmyeE;

	@Excel(name = "ghr", width = 15)
	@ApiModelProperty(value = "ghr")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String ghr;



}
