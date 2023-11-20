package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 贷款监控平台表内不良_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-05
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_BNBL_XT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_BNBL_XT对象", description="贷款监控平台表内不良_湘潭")
public class DkjlptbnblxtImport {

	/**机构名称*/
	@Excel(name = "支行", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "支行")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**压降任务*/
	@Excel(name = "压降任务", width = 15)
	@ApiModelProperty(value = "压降任务")
	private String yjrw;
	/**拟合计压降*/
	@Excel(name = "拟合计压降", width = 15)
	@ApiModelProperty(value = "拟合计压降")
	private String nhjyj;
	/**实合计压降*/
	@Excel(name = "实合计压降", width = 15)
	@ApiModelProperty(value = "实合计压降")
	private String shjyj;
	/**清收任务*/
	@Excel(name = "清收任务", width = 15)
	@ApiModelProperty(value = "清收任务")
	private String qsrw;
	/**拟合计清收*/
	@Excel(name = "拟合计清收", width = 15)
	@ApiModelProperty(value = "拟合计清收")
	private String nhjqs;
	/**拟合计清收*/
	@Excel(name = "实合计清收", width = 15)
	@ApiModelProperty(value = "实合计清收")
	private String shjqs;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**客户名称*/
	@Excel(name = "户名", width = 15)
	@ApiModelProperty(value = "户名")
	private String khxm;
	/**证件号码*/
	@Excel(name = "证件号", width = 15)
    @ApiModelProperty(value = "证件号")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String dkzh;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
	@ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
	@ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**进关注年月*/
	@Excel(name = "进不良年月", width = 15)
    @ApiModelProperty(value = "进不良年月")
	private String jblny;
	/**贷款形态*/
	@Excel(name = "当前形态", width = 15, dicCode = "dkxt")
    @ApiModelProperty(value = "当前形态")
	@Dict(dicCode = "dkxt")
	private String dkxt;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal ncye;
	/**年初余额*/
	@Excel(name = "当前余额", width = 15)
    @ApiModelProperty(value = "当前余额")
	private java.math.BigDecimal dkye;
	/**当前关注余额*/
	@Excel(name = "当前不良余额", width = 15)
    @ApiModelProperty(value = "当前不良余额")
	private java.math.BigDecimal dqblye;
	/**当前欠息*/
	@Excel(name = "当前欠息", width = 15)
	@ApiModelProperty(value = "当前欠息")
	private java.math.BigDecimal dqqx;
    /**贷款利率（%）*/
	@Excel(name = "利率（%）", width = 15)
    @ApiModelProperty(value = "利率（%）")
	private java.math.BigDecimal dkll;
	/**担保方式*/
	@Excel(name = "担保类型", width = 15,dicCode = "dbfs")
	@ApiModelProperty(value = "担保类型")
	@Dict(dicCode = "dbfs")
	private String dbfs;
	/**责任认定人*/
	@Excel(name = "责任认定人", width = 15)
    @ApiModelProperty(value = "责任认定人")
	private String zrrdr;
	/**贷款人或担保现状*/
	@Excel(name = "贷款人或担保现状", width = 15)
    @ApiModelProperty(value = "贷款人或担保现状")
	private String jkrhdbxz;
	/**诉讼情况*/
	@Excel(name = "诉讼情况", width = 15)
    @ApiModelProperty(value = "诉讼情况")
	private String ssqk;
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String dklx;
	/**信贷贷款品种*/
	//@Excel(name = "信贷贷款品种", width = 15)
    @ApiModelProperty(value = "信贷贷款品种")
	private String xddkpz;
	/**拟清收/盘活/无望*/
	@Excel(name = "拟清收/盘活/无望", width = 15)
	@ApiModelProperty(value = "拟清收/盘活/无望")
	private String nqsphww;
	/**拟清收/盘活金额*/
	@Excel(name = "拟清收/盘活金额", width = 15)
	@ApiModelProperty(value = "拟清收/盘活金额")
	private java.math.BigDecimal nqsphje;
	/**拟到位时间*/
	@Excel(name = "拟到位时间", width = 15)
	@ApiModelProperty(value = "拟到位时间")
	private String ndwsj;
	/**实到位时间*/
	@Excel(name = "实到位时间", width = 15)
	@ApiModelProperty(value = "实到位时间")
	private String sdwsj;
	/**管理责任人*/
	@Excel(name = "管理责任人", width = 15)
	@ApiModelProperty(value = "管理责任人")
	private String glzrr;
	/**风险管理部要求处置进度*/
	@Excel(name = "风险管理部要求处置进度", width = 15)
	@ApiModelProperty(value = "风险管理部要求处置进度")
	private String fxglbyqczjd;
	/**管理责任人*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**1月进度/措施*/
	@Excel(name = "1月进度/措施", width = 15)
	@ApiModelProperty(value = "1月进度/措施")
	private String month1;
	/**2月进度/措施*/
	@Excel(name = "2月进度/措施", width = 15)
	@ApiModelProperty(value = "2月进度/措施")
	private String month2;
	/**3月进度/措施*/
	@Excel(name = "3月进度/措施", width = 15)
	@ApiModelProperty(value = "3月进度/措施")
	private String month3;
	/**4月进度/措施*/
	@Excel(name = "4月进度/措施", width = 15)
	@ApiModelProperty(value = "4月进度/措施")
	private String month4;
	/**5月进度/措施*/
	@Excel(name = "5月进度/措施", width = 15)
	@ApiModelProperty(value = "5月进度/措施")
	private String month5;
	/**6月进度/措施*/
	@Excel(name = "6月进度/措施", width = 15)
	@ApiModelProperty(value = "6月进度/措施")
	private String month6;
	/**7月进度/措施*/
	@Excel(name = "7月进度/措施", width = 15)
	@ApiModelProperty(value = "7月进度/措施")
	private String month7;
	/**8月进度/措施*/
	@Excel(name = "8月进度/措施", width = 15)
	@ApiModelProperty(value = "8月进度/措施")
	private String month8;
	/**9月进度/措施*/
	@Excel(name = "9月进度/措施", width = 15)
	@ApiModelProperty(value = "9月进度/措施")
	private String month9;
	/**10月进度/措施*/
	@Excel(name = "10月进度/措施", width = 15)
	@ApiModelProperty(value = "10月进度/措施")
	private String month10;
	/**11月进度/措施*/
	@Excel(name = "11月进度/措施", width = 15)
	@ApiModelProperty(value = "11月进度/措施")
	private String month11;
	/**12月进度/措施*/
	@Excel(name = "12月进度/措施", width = 15)
	@ApiModelProperty(value = "12月进度/措施")
	private String month12;

}
