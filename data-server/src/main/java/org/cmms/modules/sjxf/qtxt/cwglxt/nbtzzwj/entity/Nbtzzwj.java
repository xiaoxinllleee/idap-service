package org.cmms.modules.sjxf.qtxt.cwglxt.nbtzzwj.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 内部台账主文件
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_in_mst")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_in_mst对象", description="内部台账主文件")
public class Nbtzzwj {
    
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	private String opnBrNo;
	/**账户ID*/
	@Excel(name = "账户ID", width = 15)
    @ApiModelProperty(value = "账户ID")
	private String acId;
	/**账户序号*/
	@Excel(name = "账户序号", width = 15)
    @ApiModelProperty(value = "账户序号")
	private String acSeqn;
	/**产品代码*/
	@Excel(name = "产品代码", width = 15)
    @ApiModelProperty(value = "产品代码")
	private String prdtNo;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal bal;
	/**昨日余额*/
	@Excel(name = "昨日余额", width = 15)
    @ApiModelProperty(value = "昨日余额")
	private java.math.BigDecimal ysBal;
	/**明细余额*/
	@Excel(name = "明细余额", width = 15)
    @ApiModelProperty(value = "明细余额")
	private java.math.BigDecimal hstBal;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String sts;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String opnDate;
	/**起息日期*/
	@Excel(name = "起息日期", width = 15)
    @ApiModelProperty(value = "起息日期")
	private String icDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String endDate;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15)
    @ApiModelProperty(value = "销户日期")
	private String clsDate;
	/**上笔发生日期*/
	@Excel(name = "上笔发生日期", width = 15)
    @ApiModelProperty(value = "上笔发生日期")
	private String lstDate;
	/**计息类型*/
	@Excel(name = "计息类型", width = 15)
    @ApiModelProperty(value = "计息类型")
	private String intstKnd;
	/**利率(%)*/
	@Excel(name = "利率(%)", width = 15)
    @ApiModelProperty(value = "利率(%)")
	private java.math.BigDecimal rate;
	/**逾期利率*/
	@Excel(name = "逾期利率", width = 15)
    @ApiModelProperty(value = "逾期利率")
	private java.math.BigDecimal overRate;
	/**利息积数*/
	@Excel(name = "利息积数", width = 15)
    @ApiModelProperty(value = "利息积数")
	private java.math.BigDecimal intstAcm;
	/**明细帐总笔数*/
	@Excel(name = "明细帐总笔数", width = 15)
    @ApiModelProperty(value = "明细帐总笔数")
	private String hstCnt;
	/**明细帐满页页数*/
	@Excel(name = "明细帐满页页数", width = 15)
    @ApiModelProperty(value = "明细帐满页页数")
	private String hstPg;
	/**冻结余额*/
	@Excel(name = "冻结余额", width = 15)
    @ApiModelProperty(value = "冻结余额")
	private java.math.BigDecimal holdAmt;
	/**透支标志*/
	@Excel(name = "透支标志", width = 15)
    @ApiModelProperty(value = "透支标志")
	private String odInd;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String name;
	/**指标体系代码*/
	@Excel(name = "指标体系代码", width = 15)
    @ApiModelProperty(value = "指标体系代码")
	private String calCode;
	/**密押*/
	@Excel(name = "密押", width = 15)
    @ApiModelProperty(value = "密押")
	private String mac;
	/**支付金额*/
	@Excel(name = "支付金额", width = 15)
    @ApiModelProperty(value = "支付金额")
	private java.math.BigDecimal zfBal;
	/**存期(按天)*/
	@Excel(name = "存期(按天)", width = 15)
    @ApiModelProperty(value = "存期(按天)")
	private String term;
	/**上次计提日期*/
	@Excel(name = "上次计提日期", width = 15)
    @ApiModelProperty(value = "上次计提日期")
	private String lJtDate;
	/**上次起息日期*/
	@Excel(name = "上次起息日期", width = 15)
    @ApiModelProperty(value = "上次起息日期")
	private String lIcDate;
	/**下次付息日期*/
	@Excel(name = "下次付息日期", width = 15)
    @ApiModelProperty(value = "下次付息日期")
	private String fxDate;
	/**是否能提前取款*/
	@Excel(name = "是否能提前取款", width = 15)
    @ApiModelProperty(value = "是否能提前取款")
	private String isTeller;
	/**是否允许到期转存*/
	@Excel(name = "是否允许到期转存", width = 15)
    @ApiModelProperty(value = "是否允许到期转存")
	private String isDeposit;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**开户部门*/
	@Excel(name = "开户部门", width = 15)
    @ApiModelProperty(value = "开户部门")
	private String opnDepNo;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String rateType;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
