package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 农户评级授信信息
 * @Author: cmms
 * @Date:   2019-12-03
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHPJSXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHPJSXXX对象", description="农户评级授信信息")
public class NhPjsxxx {

	/**他行借款数量*/
	@Excel(name = "他行借款数量", width = 15)
    @ApiModelProperty(value = "他行借款数量")
	private String thjksl;
	/**他行借款详情说明*/
	@Excel(name = "他行借款详情说明", width = 15)
    @ApiModelProperty(value = "他行借款详情说明")
	private String thjkxqsm;
	/**信用卡债权人*/
	@Excel(name = "信用卡债权人", width = 15)
    @ApiModelProperty(value = "信用卡债权人")
	private String xykzqr;
	/**信用卡数量*/
	@Excel(name = "信用卡数量", width = 15)
    @ApiModelProperty(value = "信用卡数量")
	private String xyksl;
	/**信用卡详情说明*/
	@Excel(name = "信用卡详情说明", width = 15)
    @ApiModelProperty(value = "信用卡详情说明")
	private String xykxqsm;
	/**其他负债债权人*/
	@Excel(name = "其他负债债权人", width = 15)
    @ApiModelProperty(value = "其他负债债权人")
	private String qtfzzqr;
	/**其他负债数量*/
	@Excel(name = "其他负债数量", width = 15)
    @ApiModelProperty(value = "其他负债数量")
	private String qtfzsl;
	/**其他负债详情说明*/
	@Excel(name = "其他负债详情说明", width = 15)
    @ApiModelProperty(value = "其他负债详情说明")
	private String qtfzxqsm;
	/**所有负债合计*/
	@Excel(name = "所有负债合计", width = 15)
    @ApiModelProperty(value = "所有负债合计")
	private String ssfzhj;
	/**种植项目情况*/
	@Excel(name = "种植项目情况", width = 15)
    @ApiModelProperty(value = "种植项目情况")
	private String zzxmqk;
	/**种植项目年收入*/
	@Excel(name = "种植项目年收入", width = 15)
    @ApiModelProperty(value = "种植项目年收入")
	private String zzxmsr;
	/**种植项目年支出*/
	@Excel(name = "种植项目年支出", width = 15)
    @ApiModelProperty(value = "种植项目年支出")
	private String zzxmzc;
	/**种植项目净收入*/
	@Excel(name = "种植项目净收入", width = 15)
    @ApiModelProperty(value = "种植项目净收入")
	private String zzxmjsr;
	/**养殖项目情况*/
	@Excel(name = "养殖项目情况", width = 15)
    @ApiModelProperty(value = "养殖项目情况")
	private String yzxmqk;
	/**养殖项目年收入*/
	@Excel(name = "养殖项目年收入", width = 15)
    @ApiModelProperty(value = "养殖项目年收入")
	private String yzxmsr;
	/**养殖项目年支出*/
	@Excel(name = "养殖项目年支出", width = 15)
    @ApiModelProperty(value = "养殖项目年支出")
	private String yzxmzc;
	/**养殖项目净收入*/
	@Excel(name = "养殖项目净收入", width = 15)
    @ApiModelProperty(value = "养殖项目净收入")
	private String yzxmjsr;
	/**商业项目情况*/
	@Excel(name = "商业项目情况", width = 15)
    @ApiModelProperty(value = "商业项目情况")
	private String syxmqk;
	/**商业项目年收入*/
	@Excel(name = "商业项目年收入", width = 15)
    @ApiModelProperty(value = "商业项目年收入")
	private String syxmsr;
	/**商业项目年支出*/
	@Excel(name = "商业项目年支出", width = 15)
    @ApiModelProperty(value = "商业项目年支出")
	private String syxmzc;
	/**商业项目净收入*/
	@Excel(name = "商业项目净收入", width = 15)
    @ApiModelProperty(value = "商业项目净收入")
	private String syxmjsr;
	/**劳务项目情况*/
	@Excel(name = "劳务项目情况", width = 15)
    @ApiModelProperty(value = "劳务项目情况")
	private String nwxmqk;
	/**劳务项目年收入*/
	@Excel(name = "劳务项目年收入", width = 15)
    @ApiModelProperty(value = "劳务项目年收入")
	private String nwxmsr;
	/**劳务项目年支出*/
	@Excel(name = "劳务项目年支出", width = 15)
    @ApiModelProperty(value = "劳务项目年支出")
	private String nwxmzc;
	/**劳务项目净收入*/
	@Excel(name = "劳务项目净收入", width = 15)
    @ApiModelProperty(value = "劳务项目净收入")
	private String nwxmjsr;
	/**其他项目情况*/
	@Excel(name = "其他项目情况", width = 15)
    @ApiModelProperty(value = "其他项目情况")
	private String qtxmqk;
	/**其他项目年收入*/
	@Excel(name = "其他项目年收入", width = 15)
    @ApiModelProperty(value = "其他项目年收入")
	private String qtxmsr;
	/**其他项目年支出*/
	@Excel(name = "其他项目年支出", width = 15)
    @ApiModelProperty(value = "其他项目年支出")
	private String qtxmzc;
	/**其他项目净收入*/
	@Excel(name = "其他项目净收入", width = 15)
    @ApiModelProperty(value = "其他项目净收入")
	private String qtxmjsr;
	/**所有经营情况合计*/
	@Excel(name = "所有经营情况合计", width = 15)
    @ApiModelProperty(value = "所有经营情况合计")
	private String ssjyqkhj;
	/**户主及家庭成员社会关系*/
	@Excel(name = "户主及家庭成员社会关系", width = 15)
    @ApiModelProperty(value = "户主及家庭成员社会关系")
	private String jtcyshgx;
	/**劳动力人数*/
	@Excel(name = "劳动力人数", width = 15,dicCode = "khgl_ldlrs")
    @ApiModelProperty(value = "劳动力人数")
	private String ndlrs;
	/**净资产*/
	@Excel(name = "净资产", width = 15)
    @ApiModelProperty(value = "净资产")
	private String nhjzc;
	/**纯收入*/
	@Excel(name = "纯收入", width = 15)
    @ApiModelProperty(value = "纯收入")
	private String nhcsr;
	/**社会评价*/
	@Excel(name = "社会评价", width = 15,dicCode="khgl_shpj" )
    @ApiModelProperty(value = "社会评价")
	private String nhshpj;
	/**当前经营项目从业时间*/
	@Excel(name = "当前经营项目从业时间", width = 15,dicCode="khgl_jyxmcysj" )
    @ApiModelProperty(value = "当前经营项目从业时间")
	private String jyxmcysj;
	/**与本行信贷往来时间*/
	@Excel(name = "与本行信贷往来时间", width = 15,dicCode="khgl_jyxmcysj" )
    @ApiModelProperty(value = "与本行信贷往来时间")
	private String ybhlwsj;
	/**综合竞争实力*/
	@Excel(name = "综合竞争实力", width = 15,dicCode="khgl_zhjzsl" )
    @ApiModelProperty(value = "综合竞争实力")
	private String zhjzsl;
	/**资信状况*/
	@Excel(name = "资信状况", width = 15)
	@ApiModelProperty(value = "资信状况")
	private String nhzxzk;
	/**贷记卡逾期情况*/
	@Excel(name = "贷记卡逾期情况", width = 15)
	@ApiModelProperty(value = "贷记卡逾期情况")
	private String djkyqqk;
	/**总评得分小计*/
	@Excel(name = "总评得分小计", width = 15)
    @ApiModelProperty(value = "总评得分小计")
	private String zpdfxj;
	/**测评等级*/
	@Excel(name = "测评等级", width = 15)
    @ApiModelProperty(value = "测评等级")
	private String nhcpdj;
	/**等级调整事项*/
	@Excel(name = "等级调整事项", width = 15)
    @ApiModelProperty(value = "等级调整事项")
	private String djtzsx;
	/**调整后等级*/
	@Excel(name = "调整后等级", width = 15)
    @ApiModelProperty(value = "调整后等级")
	private String tzhdj;
	/**村支两委评定等级*/
	@Excel(name = "村支两委评定等级", width = 15)
    @ApiModelProperty(value = "村支两委评定等级")
	private String czlwpddj;
	/**村支两委授信额度*/
	@Excel(name = "村支两委授信额度", width = 15)
    @ApiModelProperty(value = "村支两委授信额度")
	private java.math.BigDecimal czlwsxed;
	/**客户经理评定等级*/
	@Excel(name = "客户经理评定等级", width = 15)
    @ApiModelProperty(value = "客户经理评定等级")
	private String khjlpddj;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
    @ApiModelProperty(value = "客户经理授信额度")
	private java.math.BigDecimal khjlsxed;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
    @ApiModelProperty(value = "系统测算评定等级")
	private String xtcspddj;
	/**系统测算授信额度*/
	@Excel(name = "系统测算授信额度", width = 15)
    @ApiModelProperty(value = "系统测算授信额度")
	private java.math.BigDecimal xtcssxed;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
    @ApiModelProperty(value = "最终授信额度")
	private java.math.BigDecimal nhzzsxed;
	/**内部测试额度*/
	@Excel(name = "内部测试额度", width = 15)
    @ApiModelProperty(value = "内部测试额度")
	private java.math.BigDecimal nbcsed;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**时间戳*/
	@Excel(name = "时间戳", width = 15)
    @ApiModelProperty(value = "时间戳")
	private String tmSmp;
	/**qydm*/
	@Excel(name = "qydm", width = 15)
    @ApiModelProperty(value = "qydm")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**房地产资产合计*/
	@Excel(name = "房地产资产合计", width = 15)
    @ApiModelProperty(value = "房地产资产合计")
	private String fczchj;
	/**股权数量*/
	@Excel(name = "股权数量", width = 15)
    @ApiModelProperty(value = "股权数量")
	private String gqsl;
	/**股权价值*/
	@Excel(name = "股权价值", width = 15)
    @ApiModelProperty(value = "股权价值")
	private String gqjz;
	/**股权详情说明*/
	@Excel(name = "股权详情说明", width = 15)
    @ApiModelProperty(value = "股权详情说明")
	private String gqxqsm;
	/**农机具数量*/
	@Excel(name = "农机具数量", width = 15)
    @ApiModelProperty(value = "农机具数量")
	private String njjsl;
	/**农机具价值*/
	@Excel(name = "农机具价值", width = 15)
    @ApiModelProperty(value = "农机具价值")
	private String njjjz;
	/**农机具详情说明*/
	@Excel(name = "农机具详情说明", width = 15)
    @ApiModelProperty(value = "农机具详情说明")
	private String njjqxsm;
	/**家用电器数量*/
	@Excel(name = "家用电器数量", width = 15)
    @ApiModelProperty(value = "家用电器数量")
	private String jydqsl;
	/**家用电器价值*/
	@Excel(name = "家用电器价值", width = 15)
    @ApiModelProperty(value = "家用电器价值")
	private String jydqjz;
	/**家用电器详情说明*/
	@Excel(name = "家用电器详情说明", width = 15)
    @ApiModelProperty(value = "家用电器详情说明")
	private String jydqxqsm;
	/**交通工具数量*/
	@Excel(name = "交通工具数量", width = 15)
    @ApiModelProperty(value = "交通工具数量")
	private String jtgjsl;
	/**交通工具价值*/
	@Excel(name = "交通工具价值", width = 15)
    @ApiModelProperty(value = "交通工具价值")
	private String jtgjjz;
	/**交通工具详情说明*/
	@Excel(name = "交通工具详情说明", width = 15)
    @ApiModelProperty(value = "交通工具详情说明")
	private String jtgjxqsm;
	/**其他资产数量*/
	@Excel(name = "其他资产数量", width = 15)
    @ApiModelProperty(value = "其他资产数量")
	private String qtzcsl;
	/**其他资产价值*/
	@Excel(name = "其他资产价值", width = 15)
    @ApiModelProperty(value = "其他资产价值")
	private String qtzcjz;
	/**其他资产详情说明*/
	@Excel(name = "其他资产详情说明", width = 15)
    @ApiModelProperty(value = "其他资产详情说明")
	private String qtzcxqsm;
	/**所有资产合计*/
	@Excel(name = "所有资产合计", width = 15)
    @ApiModelProperty(value = "所有资产合计")
	private String sszchj;
	/**本系统借款债权人*/
	@Excel(name = "本系统借款债权人", width = 15)
    @ApiModelProperty(value = "本系统借款债权人")
	private String bxtjkzqr;
	/**本系统借款数量*/
	@Excel(name = "本系统借款数量", width = 15)
    @ApiModelProperty(value = "本系统借款数量")
	private String bxtjksl;
	/**本系统借款详情说明*/
	@Excel(name = "本系统借款详情说明", width = 15)
    @ApiModelProperty(value = "本系统借款详情说明")
	private String bxtjkxqsm;
	/**他行借款债权人*/
	@Excel(name = "他行借款债权人", width = 15)
    @ApiModelProperty(value = "他行借款债权人")
	private String thjkzqr;
	/**
	 * 农户基本信息：总评打分补充字段
	 * 资产负债率、存款贡献、手机银行
	 */
	/**资产负债率*/
	@Excel(name = "资产负债率", width = 15)
	@ApiModelProperty(value = "资产负债率")
	private String zcfzl;
	/**存款贡献*/
	@Excel(name = "存款贡献", width = 15)
	@ApiModelProperty(value = "存款贡献")
	private String ckgx;
	/**手机银行*/
	@Excel(name = "手机银行", width = 15)
	@ApiModelProperty(value = "手机银行")
	private String sjyh;

	/**
	 * 祁阳补充字段
	 */
	/**民间借款债权人*/
	@Excel(name = "民间借款债权人", width = 15)
	@ApiModelProperty(value = "民间借款债权人")
	private String mjjkzqr;
	/**民间借款金额*/
	@Excel(name = "民间借款金额", width = 15)
	@ApiModelProperty(value = "民间借款金额")
	private String mjjkje;
	/**民间借款说明*/
	@Excel(name = "民间借款说明", width = 15)
	@ApiModelProperty(value = "民间借款说明")
	private String mjjksm;
	/**生活消费支出债权人*/
	@Excel(name = "生活消费支出债权人", width = 15)
	@ApiModelProperty(value = "生活消费支出债权人")
	private String shxfzczqr;
	/**生活消费支出借款金额*/
	@Excel(name = "生活消费支出借款金额", width = 15)
	@ApiModelProperty(value = "生活消费支出借款金额")
	private String shxfzcjkje;
	/**民间借款说明*/
	@Excel(name = "生活消费支出说明", width = 15)
	@ApiModelProperty(value = "生活消费支出说明")
	private String shxfzcsm;
	/**子女教育支出债权人*/
	@Excel(name = "生活消费支出债权人", width = 15)
	@ApiModelProperty(value = "子女教育支出债权人")
	private String znjyzqr;
	/**子女教育支出借款金额*/
	@Excel(name = "子女教育支出借款金额", width = 15)
	@ApiModelProperty(value = "子女教育支出借款金额")
	private String znjyjkje;
	/**民间借款说明*/
	@Excel(name = "子女教育支出说明", width = 15)
	@ApiModelProperty(value = "子女教育支出说明")
	private String znjysm;
	/**家庭重大支出债权人*/
	@Excel(name = "家庭重大支出债权人", width = 15)
	@ApiModelProperty(value = "家庭重大支出债权人")
	private String jtzdzczqr;
	/**家庭重大支出借款金额*/
	@Excel(name = "家庭重大支出借款金额", width = 15)
	@ApiModelProperty(value = "家庭重大支出借款金额")
	private String jtzdzcjkje;
	/**家庭重大支出说明*/
	@Excel(name = "家庭重大支出说明", width = 15)
	@ApiModelProperty(value = "家庭重大支出说明")
	private String jtzdzcsm;
	/**工资性项目情况*/
	@Excel(name = "工资性项目情况", width = 15)
	@ApiModelProperty(value = "工资性项目情况")
	private String gzxxmqk;
	/**工资性项目年收入*/
	@Excel(name = "工资性项目年收入", width = 15)
	@ApiModelProperty(value = "工资性项目年收入")
	private String gzxxmsr;
	/**工资性项目年支出*/
	@Excel(name = "工资性项目年支出", width = 15)
	@ApiModelProperty(value = "工资性项目年支出")
	private String gzxxmzc;
	/**工资性项目净收入*/
	@Excel(name = "工资性项目净收入", width = 15)
	@ApiModelProperty(value = "工资性项目净收入")
	private String gzxxmjsr;
	/**"住房公积金年个人交纳额项目情况"*/
	@Excel(name = "住房公积金年个人交纳额项目情况", width = 15)
	@ApiModelProperty(value = "住房公积金年个人交纳额项目情况")
	private String zfgjjxmqk;
	/**住房公积金年个人交纳额项目年收入*/
	@Excel(name = "住房公积金年个人交纳额项目年收入", width = 15)
	@ApiModelProperty(value = "住房公积金年个人交纳额项目年收入")
	private String zfgjjxmsr;
	/**住房公积金年个人交纳额项目年支出*/
	@Excel(name = "住房公积金年个人交纳额项目年支出", width = 15)
	@ApiModelProperty(value = "住房公积金年个人交纳额项目年支出")
	private String zfgjjxmzc;
	/**住房公积金年个人交纳额项目净收入*/
	@Excel(name = "住房公积金年个人交纳额项目净收入", width = 15)
	@ApiModelProperty(value = "住房公积金年个人交纳额项目净收入")
	private String zfgjjxmjsr;
}
