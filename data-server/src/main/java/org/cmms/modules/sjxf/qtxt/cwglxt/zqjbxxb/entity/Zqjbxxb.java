package org.cmms.modules.sjxf.qtxt.cwglxt.zqjbxxb.entity;

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
 * @Description: 债券基本信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_bond_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_bond_info对象", description="债券基本信息表")
public class Zqjbxxb {
    
	/**债券代码*/
	@Excel(name = "债券代码", width = 15)
    @ApiModelProperty(value = "债券代码")
	private String bondNo;
	/**交易明细笔次*/
	@Excel(name = "交易明细笔次", width = 15)
    @ApiModelProperty(value = "交易明细笔次")
	private String txCnt;
	/**债券名称*/
	@Excel(name = "债券名称", width = 15)
    @ApiModelProperty(value = "债券名称")
	private String bondName;
	/**债券简称*/
	@Excel(name = "债券简称", width = 15)
    @ApiModelProperty(value = "债券简称")
	private String bondShortname;
	/**债券发行方组织机构代码证号*/
	@Excel(name = "债券发行方组织机构代码证号", width = 15)
    @ApiModelProperty(value = "债券发行方组织机构代码证号")
	private String ctpyNo;
	/**计划发行总额(元)*/
	@Excel(name = "计划发行总额(元)", width = 15)
    @ApiModelProperty(value = "计划发行总额(元)")
	private java.math.BigDecimal planTotalAmt;
	/**实际发行总额(元)*/
	@Excel(name = "实际发行总额(元)", width = 15)
    @ApiModelProperty(value = "实际发行总额(元)")
	private java.math.BigDecimal actualTotalAmt;
	/**发行价格(元/百元面值)*/
	@Excel(name = "发行价格(元/百元面值)", width = 15)
    @ApiModelProperty(value = "发行价格(元/百元面值)")
	private java.math.BigDecimal bondPrice;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**债券发行方种类*/
	@Excel(name = "债券发行方种类", width = 15)
    @ApiModelProperty(value = "债券发行方种类")
	private String bondProperty;
	/**债券子性质*/
	@Excel(name = "债券子性质", width = 15)
    @ApiModelProperty(value = "债券子性质")
	private String bondSubProperty;
	/**发行日期*/
	@Excel(name = "发行日期", width = 15)
    @ApiModelProperty(value = "发行日期")
	private String issueDate;
	/**发行起息日期*/
	@Excel(name = "发行起息日期", width = 15)
    @ApiModelProperty(value = "发行起息日期")
	private String fxQxDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String mtrDate;
	/**利率变动日期*/
	@Excel(name = "利率变动日期", width = 15)
    @ApiModelProperty(value = "利率变动日期")
	private String bdDate;
	/**债券期限(月)*/
	@Excel(name = "债券期限(月)", width = 15)
    @ApiModelProperty(value = "债券期限(月)")
	private String bondTerm;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String rateType;
	/**票面利率(%)*/
	@Excel(name = "票面利率(%)", width = 15)
    @ApiModelProperty(value = "票面利率(%)")
	private java.math.BigDecimal cpnRate;
	/**变动前票面利率*/
	@Excel(name = "变动前票面利率", width = 15)
    @ApiModelProperty(value = "变动前票面利率")
	private java.math.BigDecimal lCpnRate;
	/**付息方式*/
	@Excel(name = "付息方式", width = 15)
    @ApiModelProperty(value = "付息方式")
	private String intstType;
	/**付息周期*/
	@Excel(name = "付息周期", width = 15)
    @ApiModelProperty(value = "付息周期")
	private String intstPerd;
	/**计提利息周期*/
	@Excel(name = "计提利息周期", width = 15)
    @ApiModelProperty(value = "计提利息周期")
	private String intstCycle;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**计提利息方式*/
	@Excel(name = "计提利息方式", width = 15)
    @ApiModelProperty(value = "计提利息方式")
	private String jtType;
	/**计提利息子方式*/
	@Excel(name = "计提利息子方式", width = 15)
    @ApiModelProperty(value = "计提利息子方式")
	private String subJtType;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**债券评级*/
	@Excel(name = "债券评级", width = 15)
    @ApiModelProperty(value = "债券评级")
	private String bondRating;
	/**评级机构*/
	@Excel(name = "评级机构", width = 15)
    @ApiModelProperty(value = "评级机构")
	private String ratingAgency;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**上次起息日期*/
	@Excel(name = "上次起息日期", width = 15)
    @ApiModelProperty(value = "上次起息日期")
	private String lIcDate;
	/**本次起息日期*/
	@Excel(name = "本次起息日期", width = 15)
    @ApiModelProperty(value = "本次起息日期")
	private String icDate;
	/**下次付息日期*/
	@Excel(name = "下次付息日期", width = 15)
    @ApiModelProperty(value = "下次付息日期")
	private String fxDate;
	/**利率变动周期*/
	@Excel(name = "利率变动周期", width = 15)
    @ApiModelProperty(value = "利率变动周期")
	private String ratePerd;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**债券发行方子类*/
	@Excel(name = "债券发行方子类", width = 15)
    @ApiModelProperty(value = "债券发行方子类")
	private String categoryType;
	/**债券发行方名称*/
	@Excel(name = "债券发行方名称", width = 15)
    @ApiModelProperty(value = "债券发行方名称")
	private String issuerName;
	/**托管机构*/
	@Excel(name = "托管机构", width = 15)
    @ApiModelProperty(value = "托管机构")
	private String trustee;
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
