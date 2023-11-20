package org.cmms.modules.sjxf.qtxt.cwglxt.dzzcdjb.entity;

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
 * @Description: 抵债资产登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_debtasset_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_debtasset_reg对象", description="抵债资产登记簿")
public class Dzzcdjb {

	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
    @ApiModelProperty(value = "资产编号")
	private String capNo;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
	private String capName;
	/**抵债资产取得方式*/
	@Excel(name = "抵债资产取得方式", width = 15)
    @ApiModelProperty(value = "抵债资产取得方式")
	private String obtainType;
	/**抵债协议编号*/
	@Excel(name = "抵债协议编号", width = 15)
    @ApiModelProperty(value = "抵债协议编号")
	private String pactNo;
	/**抵债资产类别*/
	@Excel(name = "抵债资产类别", width = 15)
    @ApiModelProperty(value = "抵债资产类别")
	private String capType;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15)
    @ApiModelProperty(value = "所属机构")
	private String dcDepNo;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**评估价值（公允价值）*/
	@Excel(name = "评估价值（公允价值）", width = 15)
    @ApiModelProperty(value = "评估价值（公允价值）")
	private java.math.BigDecimal pubVal;
	/**补价金额*/
	@Excel(name = "补价金额", width = 15)
    @ApiModelProperty(value = "补价金额")
	private java.math.BigDecimal fillAmt;
	/**营业税金*/
	@Excel(name = "营业税金", width = 15)
    @ApiModelProperty(value = "营业税金")
	private java.math.BigDecimal taxAmt1;
	/**城市维护建设税*/
	@Excel(name = "城市维护建设税", width = 15)
    @ApiModelProperty(value = "城市维护建设税")
	private java.math.BigDecimal taxAmt2;
	/**教育费附加*/
	@Excel(name = "教育费附加", width = 15)
    @ApiModelProperty(value = "教育费附加")
	private java.math.BigDecimal taxAmt3;
	/**契税*/
	@Excel(name = "契税", width = 15)
    @ApiModelProperty(value = "契税")
	private java.math.BigDecimal taxAmt4;
	/**印花税*/
	@Excel(name = "印花税", width = 15)
    @ApiModelProperty(value = "印花税")
	private java.math.BigDecimal taxAmt5;
	/**其他税费*/
	@Excel(name = "其他税费", width = 15)
    @ApiModelProperty(value = "其他税费")
	private java.math.BigDecimal taxAmt6;
	/**税费合计*/
	@Excel(name = "税费合计", width = 15)
    @ApiModelProperty(value = "税费合计")
	private java.math.BigDecimal taxAmt;
	/**运费*/
	@Excel(name = "运费", width = 15)
    @ApiModelProperty(value = "运费")
	private java.math.BigDecimal feeAmt1;
	/**其他费用*/
	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
	private java.math.BigDecimal feeAmt2;
	/**抵债资产入账价值*/
	@Excel(name = "抵债资产入账价值", width = 15)
    @ApiModelProperty(value = "抵债资产入账价值")
	private java.math.BigDecimal bookVal;
	/**存放地点（座落地）*/
	@Excel(name = "存放地点（座落地）", width = 15)
    @ApiModelProperty(value = "存放地点（座落地）")
	private String location;
	/**保管方式*/
	@Excel(name = "保管方式", width = 15)
    @ApiModelProperty(value = "保管方式")
	private String mngeMode;
	/**管理部门*/
	@Excel(name = "管理部门", width = 15)
    @ApiModelProperty(value = "管理部门")
	private String mngerDepNo;
	/**管理人(职工号)*/
	@Excel(name = "管理人(职工号)", width = 15)
    @ApiModelProperty(value = "管理人(职工号)")
	private String mngerTlrno;
	/**接收日期*/
	@Excel(name = "接收日期", width = 15)
    @ApiModelProperty(value = "接收日期")
	private Integer obtainDate;
	/**接收方式*/
	@Excel(name = "接收方式", width = 15)
    @ApiModelProperty(value = "接收方式")
	private String obtainMode;
	/**产权状况*/
	@Excel(name = "产权状况", width = 15)
    @ApiModelProperty(value = "产权状况")
	private String property;
	/**资产发票号码*/
	@Excel(name = "资产发票号码", width = 15)
    @ApiModelProperty(value = "资产发票号码")
	private String billNo;
	/**结构形式*/
	@Excel(name = "结构形式", width = 15)
    @ApiModelProperty(value = "结构形式")
	private String structure;
	/**抵债建筑面积*/
	@Excel(name = "抵债建筑面积", width = 15)
    @ApiModelProperty(value = "抵债建筑面积")
	private java.math.BigDecimal buildArea;
	/**产权证号*/
	@Excel(name = "产权证号", width = 15)
    @ApiModelProperty(value = "产权证号")
	private String no;
	/**土地纳税等级*/
	@Excel(name = "土地纳税等级", width = 15)
    @ApiModelProperty(value = "土地纳税等级")
	private String taxLvl;
	/**抵债土地面积*/
	@Excel(name = "抵债土地面积", width = 15)
    @ApiModelProperty(value = "抵债土地面积")
	private java.math.BigDecimal landArea;
	/**土地使用证号*/
	@Excel(name = "土地使用证号", width = 15)
    @ApiModelProperty(value = "土地使用证号")
	@TableField(value = "no1")
	private String no1;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	@TableField(value = "no2_")
	private String no2;
	/**发动机号码*/
	@Excel(name = "发动机号码", width = 15)
    @ApiModelProperty(value = "发动机号码")
	@TableField(value = "no3")
	private String no3;
	/**车架号码*/
	@Excel(name = "车架号码", width = 15)
    @ApiModelProperty(value = "车架号码")
	@TableField(value = "no4")
	private String no4;
	/**抵债时行程数*/
	@Excel(name = "抵债时行程数", width = 15)
    @ApiModelProperty(value = "抵债时行程数")
	private java.math.BigDecimal km;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
	private String unit;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
	private Integer count;
	/**购建（买）年份*/
	@Excel(name = "购建（买）年份", width = 15)
    @ApiModelProperty(value = "购建（买）年份")
	private Integer year;
	/**尚能使用年限*/
	@Excel(name = "尚能使用年限", width = 15)
    @ApiModelProperty(value = "尚能使用年限")
	private Integer years;
	/**预计处理变现日期*/
	@Excel(name = "预计处理变现日期", width = 15)
    @ApiModelProperty(value = "预计处理变现日期")
	private Integer months;
	/**备注说明*/
	@Excel(name = "备注说明", width = 15)
    @ApiModelProperty(value = "备注说明")
	private String filler;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**抵债资产账号*/
	@Excel(name = "抵债资产账号", width = 15)
    @ApiModelProperty(value = "抵债资产账号")
	private String acNo;
	/**处置日期*/
	@Excel(name = "处置日期", width = 15)
    @ApiModelProperty(value = "处置日期")
	private Integer czDate;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**记账日期*/
	@Excel(name = "记账日期", width = 15)
    @ApiModelProperty(value = "记账日期")
	private Integer jzDate;
	/**资产状况*/
	@Excel(name = "资产状况", width = 15)
    @ApiModelProperty(value = "资产状况")
	private String oCapNo;
	/**房产税上次计提日期*/
	@Excel(name = "房产税上次计提日期", width = 15)
    @ApiModelProperty(value = "房产税上次计提日期")
	private Integer ldateFcs;
	/**土地使用税上次计提日期*/
	@Excel(name = "土地使用税上次计提日期", width = 15)
    @ApiModelProperty(value = "土地使用税上次计提日期")
	private Integer ldateTds;
	/**是否房地合一*/
	@Excel(name = "是否房地合一", width = 15)
    @ApiModelProperty(value = "是否房地合一")
	private String blUnity;
	/**房地合一时关联的土地使用权编号*/
	@Excel(name = "房地合一时关联的土地使用权编号", width = 15)
    @ApiModelProperty(value = "房地合一时关联的土地使用权编号")
	private String intCapNo;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private Integer regDate;
	/**登记操作员*/
	@Excel(name = "登记操作员", width = 15)
    @ApiModelProperty(value = "登记操作员")
	private String regTel;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
	private String userPerson;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private String usePart;
	/**初始取得资产编号*/
	@Excel(name = "初始取得资产编号", width = 15)
    @ApiModelProperty(value = "初始取得资产编号")
	private String srcCapNo;
	/**初始取得资产来源*/
	@Excel(name = "初始取得资产来源", width = 15)
    @ApiModelProperty(value = "初始取得资产来源")
	private String oRootIn;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**资产状况*/
	@Excel(name = "资产状况", width = 15)
    @ApiModelProperty(value = "资产状况")
	private String useSts;
	/**附件编号*/
	@Excel(name = "附件编号", width = 15)
    @ApiModelProperty(value = "附件编号")
	private String annexNo;
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
