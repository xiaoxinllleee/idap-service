package org.cmms.modules.sjxf.qtxt.cwglxt.gdzcdjb.entity;

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
 * @Description: 固定资产登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_dep_mst")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_dep_mst对象", description="固定资产登记簿")
public class Gdzcdjb {
    
	/**资产编号*/
	@Excel(name = "资产编号", width = 15)
    @ApiModelProperty(value = "资产编号")
	private String capNo;
	/**源资产编号*/
	@Excel(name = "源资产编号", width = 15)
    @ApiModelProperty(value = "源资产编号")
	private String oCapNo;
	/**卡片序号*/
	@Excel(name = "卡片序号", width = 15)
    @ApiModelProperty(value = "卡片序号")
	private Integer acSeqn;
	/**资产性质*/
	@Excel(name = "资产性质", width = 15)
    @ApiModelProperty(value = "资产性质")
	private String captype;
	/**资产名称*/
	@Excel(name = "资产名称", width = 15)
    @ApiModelProperty(value = "资产名称")
	private String capName;
	/**取得方式*/
	@Excel(name = "取得方式", width = 15)
    @ApiModelProperty(value = "取得方式")
	private String rootIn;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.math.BigDecimal price;
	/**入账日期*/
	@Excel(name = "入账日期", width = 15)
    @ApiModelProperty(value = "入账日期")
	private Integer openDate;
	/**登记部门*/
	@Excel(name = "登记部门", width = 15)
    @ApiModelProperty(value = "登记部门")
	private String regDepNo;
	/**资产种类*/
	@Excel(name = "资产种类", width = 15)
    @ApiModelProperty(value = "资产种类")
	private String capCode;
	/**资产子类*/
	@Excel(name = "资产子类", width = 15)
    @ApiModelProperty(value = "资产子类")
	private String subCapCode;
	/**品牌型号*/
	@Excel(name = "品牌型号", width = 15)
    @ApiModelProperty(value = "品牌型号")
	private String chara;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
	private String unit;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
	private Integer totnum;
	/**原值*/
	@Excel(name = "原值", width = 15)
    @ApiModelProperty(value = "原值")
	private java.math.BigDecimal cost;
	/**折旧后余额（净值）或称账面价值*/
	@Excel(name = "折旧后余额（净值）或称账面价值", width = 15)
    @ApiModelProperty(value = "折旧后余额（净值）或称账面价值")
	private java.math.BigDecimal depBal;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String depSts;
	/**折旧方式*/
	@Excel(name = "折旧方式", width = 15)
    @ApiModelProperty(value = "折旧方式")
	private String depMode;
	/**净残值率(%)*/
	@Excel(name = "净残值率(%)", width = 15)
    @ApiModelProperty(value = "净残值率(%)")
	private java.math.BigDecimal leaveRate;
	/**实际利率(%)*/
	@Excel(name = "实际利率(%)", width = 15)
    @ApiModelProperty(value = "实际利率(%)")
	private java.math.BigDecimal actRate;
	/**分摊方式*/
	@Excel(name = "分摊方式", width = 15)
    @ApiModelProperty(value = "分摊方式")
	private String proMode;
	/**折旧周期*/
	@Excel(name = "折旧周期", width = 15)
    @ApiModelProperty(value = "折旧周期")
	private String depCyc;
	/**折旧期限（月）*/
	@Excel(name = "折旧期限（月）", width = 15)
    @ApiModelProperty(value = "折旧期限（月）")
	private Integer depTime;
	/**已使用月数（月）*/
	@Excel(name = "已使用月数（月）", width = 15)
    @ApiModelProperty(value = "已使用月数（月）")
	private Integer useTime;
	/**已折旧次数*/
	@Excel(name = "已折旧次数", width = 15)
    @ApiModelProperty(value = "已折旧次数")
	private Integer afDepCnt;
	/**总工作量*/
	@Excel(name = "总工作量", width = 15)
    @ApiModelProperty(value = "总工作量")
	private Integer tWorkAmt;
	/**本期工作量*/
	@Excel(name = "本期工作量", width = 15)
    @ApiModelProperty(value = "本期工作量")
	private Integer workTime;
	/**折旧率*/
	@Excel(name = "折旧率", width = 15)
    @ApiModelProperty(value = "折旧率")
	private java.math.BigDecimal depRate;
	/**存放地点（座落地）*/
	@Excel(name = "存放地点（座落地）", width = 15)
    @ApiModelProperty(value = "存放地点（座落地）")
	private String addr;
	/**资产使用状况*/
	@Excel(name = "资产使用状况", width = 15)
    @ApiModelProperty(value = "资产使用状况")
	private String capBrf;
	/**上次折旧额*/
	@Excel(name = "上次折旧额", width = 15)
    @ApiModelProperty(value = "上次折旧额")
	private java.math.BigDecimal evedepamt;
	/**土地使用税上次计提日*/
	@Excel(name = "土地使用税上次计提日", width = 15)
    @ApiModelProperty(value = "土地使用税上次计提日")
	private Integer lstDate;
	/**交易笔数*/
	@Excel(name = "交易笔数", width = 15)
    @ApiModelProperty(value = "交易笔数")
	private Integer tdCnt;
	/**减值金额（总额）*/
	@Excel(name = "减值金额（总额）", width = 15)
    @ApiModelProperty(value = "减值金额（总额）")
	private java.math.BigDecimal devalue;
	/**操作员号*/
	@Excel(name = "操作员号", width = 15)
    @ApiModelProperty(value = "操作员号")
	private String tel;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15)
    @ApiModelProperty(value = "所属机构")
	private String opnBrNo;
	/**市场价格*/
	@Excel(name = "市场价格", width = 15)
    @ApiModelProperty(value = "市场价格")
	private java.math.BigDecimal marval;
	/**评估日期*/
	@Excel(name = "评估日期", width = 15)
    @ApiModelProperty(value = "评估日期")
	private Integer evaDate;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**计量日期*/
	@Excel(name = "计量日期", width = 15)
    @ApiModelProperty(value = "计量日期")
	private Integer pubDate;
	/**公允价值*/
	@Excel(name = "公允价值", width = 15)
    @ApiModelProperty(value = "公允价值")
	private java.math.BigDecimal pubBal;
	/**计量方式*/
	@Excel(name = "计量方式", width = 15)
    @ApiModelProperty(value = "计量方式")
	private String comType;
	/**租赁开始日期*/
	@Excel(name = "租赁开始日期", width = 15)
    @ApiModelProperty(value = "租赁开始日期")
	private Integer charterDate;
	/**房产税上次计提日期*/
	@Excel(name = "房产税上次计提日期", width = 15)
    @ApiModelProperty(value = "房产税上次计提日期")
	private Integer turnDate;
	/**处置日期*/
	@Excel(name = "处置日期", width = 15)
    @ApiModelProperty(value = "处置日期")
	private Integer dealDate;
	/**处置金额*/
	@Excel(name = "处置金额", width = 15)
    @ApiModelProperty(value = "处置金额")
	private java.math.BigDecimal dealAmt;
	/**处置费用*/
	@Excel(name = "处置费用", width = 15)
    @ApiModelProperty(value = "处置费用")
	private java.math.BigDecimal dealFee;
	/**转入资本公积金金额*/
	@Excel(name = "转入资本公积金金额", width = 15)
    @ApiModelProperty(value = "转入资本公积金金额")
	private java.math.BigDecimal zrZbgjjBal;
	/**在建工程卡片号*/
	@Excel(name = "在建工程卡片号", width = 15)
    @ApiModelProperty(value = "在建工程卡片号")
	private String projectNo;
	/**在建工程细分的*/
	@Excel(name = "在建工程细分的", width = 15)
    @ApiModelProperty(value = "在建工程细分的")
	private String partNo;
	/**维护费用*/
	@Excel(name = "维护费用", width = 15)
    @ApiModelProperty(value = "维护费用")
	private java.math.BigDecimal prepareFee;
	/**分期付款类型*/
	@Excel(name = "分期付款类型", width = 15)
    @ApiModelProperty(value = "分期付款类型")
	private String installType;
	/**供应单位*/
	@Excel(name = "供应单位", width = 15)
    @ApiModelProperty(value = "供应单位")
	private String sale;
	/**启用日期*/
	@Excel(name = "启用日期", width = 15)
    @ApiModelProperty(value = "启用日期")
	private Integer useDate;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private String usePart;
	/**净额=残值*/
	@Excel(name = "净额=残值", width = 15)
    @ApiModelProperty(value = "净额=残值")
	private java.math.BigDecimal leaveBal;
	/**本期折旧额*/
	@Excel(name = "本期折旧额", width = 15)
    @ApiModelProperty(value = "本期折旧额")
	private java.math.BigDecimal curDepValue;
	/**上期折旧额*/
	@Excel(name = "上期折旧额", width = 15)
    @ApiModelProperty(value = "上期折旧额")
	private java.math.BigDecimal lcurDepValue;
	/**固定资产年初原值*/
	@Excel(name = "固定资产年初原值", width = 15)
    @ApiModelProperty(value = "固定资产年初原值")
	private java.math.BigDecimal btAvbal;
	/**期初原值*/
	@Excel(name = "期初原值", width = 15)
    @ApiModelProperty(value = "期初原值")
	private java.math.BigDecimal qcAvbal;
	/**本年原值累计增加*/
	@Excel(name = "本年原值累计增加", width = 15)
    @ApiModelProperty(value = "本年原值累计增加")
	private java.math.BigDecimal yAddAvbal;
	/**期间原值累计增加*/
	@Excel(name = "期间原值累计增加", width = 15)
    @ApiModelProperty(value = "期间原值累计增加")
	private java.math.BigDecimal termAddAvbal;
	/**本年原值累计减少*/
	@Excel(name = "本年原值累计减少", width = 15)
    @ApiModelProperty(value = "本年原值累计减少")
	private java.math.BigDecimal yDecAvbal;
	/**期间原值累计减少*/
	@Excel(name = "期间原值累计减少", width = 15)
    @ApiModelProperty(value = "期间原值累计减少")
	private java.math.BigDecimal termDecAvbal;
	/**年初累计折旧余额*/
	@Excel(name = "年初累计折旧余额", width = 15)
    @ApiModelProperty(value = "年初累计折旧余额")
	private java.math.BigDecimal yDepBal;
	/**期初累计折旧余额*/
	@Excel(name = "期初累计折旧余额", width = 15)
    @ApiModelProperty(value = "期初累计折旧余额")
	private java.math.BigDecimal termDepBal;
	/**本年累计折旧减少*/
	@Excel(name = "本年累计折旧减少", width = 15)
    @ApiModelProperty(value = "本年累计折旧减少")
	private java.math.BigDecimal yDepDec;
	/**期间累计折旧减少*/
	@Excel(name = "期间累计折旧减少", width = 15)
    @ApiModelProperty(value = "期间累计折旧减少")
	private java.math.BigDecimal termDepDec;
	/**本年累计折旧总额*/
	@Excel(name = "本年累计折旧总额", width = 15)
    @ApiModelProperty(value = "本年累计折旧总额")
	private java.math.BigDecimal yDepamt;
	/**是否房地合一*/
	@Excel(name = "是否房地合一", width = 15)
    @ApiModelProperty(value = "是否房地合一")
	private String blUnity;
	/**关联无形资产编号*/
	@Excel(name = "关联无形资产编号", width = 15)
    @ApiModelProperty(value = "关联无形资产编号")
	private String intCapNo;
	/**资产账号*/
	@Excel(name = "资产账号", width = 15)
    @ApiModelProperty(value = "资产账号")
	private String inAcNo;
	/**记账标志*/
	@Excel(name = "记账标志", width = 15)
    @ApiModelProperty(value = "记账标志")
	private String jzFlag;
	/**上次折旧日期*/
	@Excel(name = "上次折旧日期", width = 15)
    @ApiModelProperty(value = "上次折旧日期")
	private Integer depDate;
	/**土地应缴税额级别*/
	@Excel(name = "土地应缴税额级别", width = 15)
    @ApiModelProperty(value = "土地应缴税额级别")
	private String payTaxInd;
	/**应缴税额*/
	@Excel(name = "应缴税额", width = 15)
    @ApiModelProperty(value = "应缴税额")
	private java.math.BigDecimal payTax;
	/**附件地址*/
	@Excel(name = "附件地址", width = 15)
    @ApiModelProperty(value = "附件地址")
	private String attachAddr;
	/**土地面积*/
	@Excel(name = "土地面积", width = 15)
    @ApiModelProperty(value = "土地面积")
	private java.math.BigDecimal buildArea;
	/**保管人*/
	@Excel(name = "保管人", width = 15)
    @ApiModelProperty(value = "保管人")
	private String manager;
	/**处置数量*/
	@Excel(name = "处置数量", width = 15)
    @ApiModelProperty(value = "处置数量")
	private Integer dealCnt;
	/**处置原因*/
	@Excel(name = "处置原因", width = 15)
    @ApiModelProperty(value = "处置原因")
	private String dealBrf;
	/**资产分类*/
	@Excel(name = "资产分类", width = 15)
    @ApiModelProperty(value = "资产分类")
	private String capKind;
	/**土地位置*/
	@Excel(name = "土地位置", width = 15)
    @ApiModelProperty(value = "土地位置")
	private String landPosition;
	/**土地权证编号*/
	@Excel(name = "土地权证编号", width = 15)
    @ApiModelProperty(value = "土地权证编号")
	private String landCardno;
	/**用地性质*/
	@Excel(name = "用地性质", width = 15)
    @ApiModelProperty(value = "用地性质")
	private String landUsekind;
	/**土地取得日期*/
	@Excel(name = "土地取得日期", width = 15)
    @ApiModelProperty(value = "土地取得日期")
	private Integer landGetdate;
	/**土地到期日期*/
	@Excel(name = "土地到期日期", width = 15)
    @ApiModelProperty(value = "土地到期日期")
	private Integer landExpdate;
	/**结构形式*/
	@Excel(name = "结构形式", width = 15)
    @ApiModelProperty(value = "结构形式")
	private String archStruct;
	/**建筑面积*/
	@Excel(name = "建筑面积", width = 15)
    @ApiModelProperty(value = "建筑面积")
	private java.math.BigDecimal archArea;
	/**机具设备号*/
	@Excel(name = "机具设备号", width = 15)
    @ApiModelProperty(value = "机具设备号")
	private String machineNo;
	/**车辆号*/
	@Excel(name = "车辆号", width = 15)
    @ApiModelProperty(value = "车辆号")
	private String carNo;
	/**房屋应缴税额级别*/
	@Excel(name = "房屋应缴税额级别", width = 15)
    @ApiModelProperty(value = "房屋应缴税额级别")
	private String payTaxLvl;
	/**清理余额*/
	@Excel(name = "清理余额", width = 15)
    @ApiModelProperty(value = "清理余额")
	private java.math.BigDecimal qlAmt;
	/**增加批准文号*/
	@Excel(name = "增加批准文号", width = 15)
    @ApiModelProperty(value = "增加批准文号")
	private String authNo;
	/**土地权证编号*/
	@Excel(name = "土地权证编号", width = 15)
    @ApiModelProperty(value = "土地权证编号")
	private String buildCardno;
	/**用途*/
	@Excel(name = "用途", width = 15)
    @ApiModelProperty(value = "用途")
	private String useKind;
	/**发票号码*/
	@Excel(name = "发票号码", width = 15)
    @ApiModelProperty(value = "发票号码")
	private String fpbh;
	/**条形码*/
	@Excel(name = "条形码", width = 15)
    @ApiModelProperty(value = "条形码")
	private String txm;
	/**盘点状态*/
	@Excel(name = "盘点状态", width = 15)
    @ApiModelProperty(value = "盘点状态")
	private String chkSts;
	/**盘点确定日期*/
	@Excel(name = "盘点确定日期", width = 15)
    @ApiModelProperty(value = "盘点确定日期")
	private Integer chkSDate;
	/**盘点机构*/
	@Excel(name = "盘点机构", width = 15)
    @ApiModelProperty(value = "盘点机构")
	private String chkBrNo;
	/**盘点人描述*/
	@Excel(name = "盘点人描述", width = 15)
    @ApiModelProperty(value = "盘点人描述")
	private String chkTel;
	/**盘点日期*/
	@Excel(name = "盘点日期", width = 15)
    @ApiModelProperty(value = "盘点日期")
	private Integer chkDate;
	/**政府补助编号*/
	@Excel(name = "政府补助编号", width = 15)
    @ApiModelProperty(value = "政府补助编号")
	private String grantNo;
	/**事后确认事项号*/
	@Excel(name = "事后确认事项号", width = 15)
    @ApiModelProperty(value = "事后确认事项号")
	private String cfmAppNo;
	/**事后确认子序号*/
	@Excel(name = "事后确认子序号", width = 15)
    @ApiModelProperty(value = "事后确认子序号")
	private Integer cfmAppNoSeq;
	/**买价*/
	@Excel(name = "买价", width = 15)
    @ApiModelProperty(value = "买价")
	private java.math.BigDecimal payAmt;
	/**安装费*/
	@Excel(name = "安装费", width = 15)
    @ApiModelProperty(value = "安装费")
	private java.math.BigDecimal installAmt;
	/**税费*/
	@Excel(name = "税费", width = 15)
    @ApiModelProperty(value = "税费")
	private java.math.BigDecimal taxAmt;
	/**运费*/
	@Excel(name = "运费", width = 15)
    @ApiModelProperty(value = "运费")
	private java.math.BigDecimal transportAmt;
	/**其他费用*/
	@Excel(name = "其他费用", width = 15)
    @ApiModelProperty(value = "其他费用")
	private java.math.BigDecimal otherAmt;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
	private String usePerson;
	/**购置部门*/
	@Excel(name = "购置部门", width = 15)
    @ApiModelProperty(value = "购置部门")
	private String payDepNo;
	/**购置人*/
	@Excel(name = "购置人", width = 15)
    @ApiModelProperty(value = "购置人")
	private String payPerson;
	/**产权证号*/
	@Excel(name = "产权证号", width = 15)
    @ApiModelProperty(value = "产权证号")
	private String propertyNo;
	/**发动机号*/
	@Excel(name = "发动机号", width = 15)
    @ApiModelProperty(value = "发动机号")
	private String engineNo;
	/**车架号码*/
	@Excel(name = "车架号码", width = 15)
    @ApiModelProperty(value = "车架号码")
	private String frameNo;
	/**立项审批编号*/
	@Excel(name = "立项审批编号", width = 15)
    @ApiModelProperty(value = "立项审批编号")
	private String projectAppNo;
	/**申请编号*/
	@Excel(name = "申请编号", width = 15)
    @ApiModelProperty(value = "申请编号")
	private String appNo;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String suppNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String suppName;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String pactNo;
	/**预付款编号*/
	@Excel(name = "预付款编号", width = 15)
    @ApiModelProperty(value = "预付款编号")
	private String advanceNo;
	/**预付款金额*/
	@Excel(name = "预付款金额", width = 15)
    @ApiModelProperty(value = "预付款金额")
	private java.math.BigDecimal advanceAmt;
	/**当前市场公允价值*/
	@Excel(name = "当前市场公允价值", width = 15)
    @ApiModelProperty(value = "当前市场公允价值")
	private java.math.BigDecimal currPubBal;
	/**租金支付总金额*/
	@Excel(name = "租金支付总金额", width = 15)
    @ApiModelProperty(value = "租金支付总金额")
	private java.math.BigDecimal rentAmt;
	/**未确认融资费用*/
	@Excel(name = "未确认融资费用", width = 15)
    @ApiModelProperty(value = "未确认融资费用")
	private java.math.BigDecimal unrecognizedBal;
	/**租赁期限*/
	@Excel(name = "租赁期限", width = 15)
    @ApiModelProperty(value = "租赁期限")
	private Integer rentTime;
	/**折现利率*/
	@Excel(name = "折现利率", width = 15)
    @ApiModelProperty(value = "折现利率")
	private java.math.BigDecimal cashRate;
	/**股东号*/
	@Excel(name = "股东号", width = 15)
    @ApiModelProperty(value = "股东号")
	private String shareholderNo;
	/**入股股数*/
	@Excel(name = "入股股数", width = 15)
    @ApiModelProperty(value = "入股股数")
	private java.math.BigDecimal shareSize;
	/**每股票面价格*/
	@Excel(name = "每股票面价格", width = 15)
    @ApiModelProperty(value = "每股票面价格")
	private java.math.BigDecimal sharePrice;
	/**资本溢价金额*/
	@Excel(name = "资本溢价金额", width = 15)
    @ApiModelProperty(value = "资本溢价金额")
	private java.math.BigDecimal premiumBal;
	/**捐赠单位/个人*/
	@Excel(name = "捐赠单位/个人", width = 15)
    @ApiModelProperty(value = "捐赠单位/个人")
	private String presentName;
	/**捐赠单位/事由*/
	@Excel(name = "捐赠单位/事由", width = 15)
    @ApiModelProperty(value = "捐赠单位/事由")
	private String presentBrf;
	/**盘盈时间*/
	@Excel(name = "盘盈时间", width = 15)
    @ApiModelProperty(value = "盘盈时间")
	private Integer gainsDate;
	/**低值易耗品类别*/
	@Excel(name = "低值易耗品类别", width = 15)
    @ApiModelProperty(value = "低值易耗品类别")
	private String gainsNo;
	/**盘点经办人*/
	@Excel(name = "盘点经办人", width = 15)
    @ApiModelProperty(value = "盘点经办人")
	private String gainsName;
	/**盘盈确认审批编号*/
	@Excel(name = "盘盈确认审批编号", width = 15)
    @ApiModelProperty(value = "盘盈确认审批编号")
	private String gainsAppNo;
	/**资产状况*/
	@Excel(name = "资产状况", width = 15)
    @ApiModelProperty(value = "资产状况")
	private String useSts;
	/**保证金金额*/
	@Excel(name = "保证金金额", width = 15)
    @ApiModelProperty(value = "保证金金额")
	private java.math.BigDecimal marginAmt;
	/**产品号*/
	@Excel(name = "产品号", width = 15)
    @ApiModelProperty(value = "产品号")
	private String prdtNo;
	/**业务编码*/
	@Excel(name = "业务编码", width = 15)
    @ApiModelProperty(value = "业务编码")
	private String ywNo;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private Integer expDate;
	/**初始取得资产编号*/
	@Excel(name = "初始取得资产编号", width = 15)
    @ApiModelProperty(value = "初始取得资产编号")
	private String srcCapNo;
	/**初始取得资产来源*/
	@Excel(name = "初始取得资产来源", width = 15)
    @ApiModelProperty(value = "初始取得资产来源")
	private String oRootIn;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**管理人*/
	@Excel(name = "管理人", width = 15)
    @ApiModelProperty(value = "管理人")
	private String manPart;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String dataDate;
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
