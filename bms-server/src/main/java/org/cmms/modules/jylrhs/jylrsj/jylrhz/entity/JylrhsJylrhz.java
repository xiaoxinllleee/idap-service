package org.cmms.modules.jylrhs.jylrsj.jylrhz.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 经营利润汇总
 * @Author: jeecg-boot
 * @Date:   2023-11-02
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_jylrhz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_jylrhz对象", description="经营利润汇总")
public class JylrhsJylrhz {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15)
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**上年末存款日平余额（本外币）*/
	@Excel(name = "上年末存款日平余额（本外币）", width = 15, groupName = "资金来源")
    @ApiModelProperty(value = "上年末存款日平余额（本外币）")
	private java.math.BigDecimal snmCkrpyeBwb;
	/**期末存款日平余额（含本外币）*/
	@Excel(name = "期末存款日平余额（含本外币）", width = 15, groupName = "资金来源")
    @ApiModelProperty(value = "期末存款日平余额（含本外币）")
	private java.math.BigDecimal qmCkrpHbwb;
	/**上年末贷款日平余额（不含卡）*/
	@Excel(name = "上年末贷款日平余额（不含卡）", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "上年末贷款日平余额（不含卡）")
	private java.math.BigDecimal snmDkrpye;
	/**上年末信用卡月均余额*/
	@Excel(name = "上年末信用卡月均余额", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "上年末信用卡月均余额")
	private java.math.BigDecimal snmXykYjye;
	/**上年末贷款余额（含贷记卡）*/
	@Excel(name = "上年末贷款余额（含贷记卡）", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "上年末贷款余额（含贷记卡）")
	private java.math.BigDecimal snmDkye;
	/**期末贷款余额（含贷记卡）*/
	@Excel(name = "期末贷款余额（含贷记卡）", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "期末贷款余额（含贷记卡）")
	private java.math.BigDecimal qmDkye;
	/**期末信用卡月均余额*/
	@Excel(name = "期末信用卡月均余额", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "期末信用卡月均余额")
	private java.math.BigDecimal qmXykYjye;
	/**期末贷款日平余额（不含贷记卡、转贴）*/
	@Excel(name = "期末贷款日平余额（不含贷记卡、转贴）", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "期末贷款日平余额（不含贷记卡、转贴）")
	private java.math.BigDecimal qmDkrpye;
	/**期末日平现金余额*/
	@Excel(name = "期末日平现金余额", width = 15, groupName = "资金运用")
    @ApiModelProperty(value = "期末日平现金余额")
	private java.math.BigDecimal qmRpXjye;
	/**存款准备金*/
	@Excel(name = "存款准备金", width = 15)
    @ApiModelProperty(value = "存款准备金")
	private java.math.BigDecimal ckzbj;
	/**日均富余或占用（+，-）*/
	@Excel(name = "日均富余或占用（+，-）", width = 15)
    @ApiModelProperty(value = "日均富余或占用（+，-）")
	private java.math.BigDecimal rjFyhzy;
	/**存贷款日均比例(%)*/
	@Excel(name = "存贷款日均比例(%)", width = 15)
    @ApiModelProperty(value = "存贷款日均比例(%)")
	private java.math.BigDecimal cdkrjbl;
	/**期末6011科目贷款利息收入（不含税）*/
	@Excel(name = "期末6011科目贷款利息收入（不含税）", width = 15, groupName = "利息收入")
    @ApiModelProperty(value = "期末6011科目贷款利息收入（不含税）")
	private java.math.BigDecimal qmKm6011Dklxsr;
	/**期末税后日平贷款收息率(%)*/
	@Excel(name = "期末税后日平贷款收息率(%)", width = 15, groupName = "利息收入")
    @ApiModelProperty(value = "期末税后日平贷款收息率(%)")
	private java.math.BigDecimal qmShrpDksxl;
	/**其中：期末6011科目免增值税贷款利息收入（分摊）*/
	@Excel(name = "其中：期末6011科目免增值税贷款利息收入（分摊）", width = 15, groupName = "利息收入")
    @ApiModelProperty(value = "其中：期末6011科目免增值税贷款利息收入（分摊）")
	private java.math.BigDecimal qmMzzsDklxsr;
	/**6012科目往来收入*/
	@Excel(name = "6012科目往来收入", width = 15, groupName = "往来收入")
    @ApiModelProperty(value = "6012科目往来收入")
	private java.math.BigDecimal km6012Wlsr;
	/**6111科目富余资金计收*/
	@Excel(name = "6111科目富余资金计收", width = 15, groupName = "内部资金计价")
    @ApiModelProperty(value = "6111科目富余资金计收")
	private java.math.BigDecimal km6111Fyzjjs;
	/**6111科目占用资金计付*/
	@Excel(name = "6111科目占用资金计付", width = 15, groupName = "内部资金计价")
    @ApiModelProperty(value = "6111科目占用资金计付")
	private java.math.BigDecimal km6111Zyzjjf;
	/**6021科目支行报表手续费收入(不含税）*/
	@Excel(name = "6021科目支行报表手续费收入(不含税）", width = 15, groupName = "手续费收入")
    @ApiModelProperty(value = "6021科目支行报表手续费收入(不含税）")
	private java.math.BigDecimal km6021Zhbbsxfsr;
	/**6021科目总部分摊手续费收入(不含税）*/
	@Excel(name = "6021科目总部分摊手续费收入(不含税）", width = 15, groupName = "手续费收入")
    @ApiModelProperty(value = "6021科目总部分摊手续费收入(不含税）")
	private java.math.BigDecimal km6021Zbftsxfsr;
	/**6051其他业务收入科目*/
	@Excel(name = "6051其他业务收入科目", width = 15)
    @ApiModelProperty(value = "6051其他业务收入科目")
	private java.math.BigDecimal km6501Qtywsr;
	/**6061汇兑损益*/
	@Excel(name = "6061汇兑损益", width = 15)
    @ApiModelProperty(value = "6061汇兑损益")
	private java.math.BigDecimal km6061Hdsy;
	/**6101公允价值变动损益*/
	@Excel(name = "6101公允价值变动损益", width = 15)
    @ApiModelProperty(value = "6101公允价值变动损益")
	private java.math.BigDecimal km6101Gyjzbdsy;
	/**6301营业外收入科目*/
	@Excel(name = "6301营业外收入科目", width = 15)
    @ApiModelProperty(value = "6301营业外收入科目")
	private java.math.BigDecimal km6301Yywsr;
	/**各项收入合计*/
	@Excel(name = "各项收入合计", width = 15)
    @ApiModelProperty(value = "各项收入合计")
	private java.math.BigDecimal gxsrhj;
	/**增值税金*/
	@Excel(name = "增值税金", width = 15)
    @ApiModelProperty(value = "增值税金")
	private java.math.BigDecimal zzsj;
	/**6403城建税及附加*/
	@Excel(name = "6403城建税及附加", width = 15, groupName = "营业税金及附加")
    @ApiModelProperty(value = "6403城建税及附加")
	private java.math.BigDecimal km6403Cjsjfj;
	/**6403其他税金*/
	@Excel(name = "6403其他税金", width = 15, groupName = "营业税金及附加")
    @ApiModelProperty(value = "6403其他税金")
	private java.math.BigDecimal km6403Qtsj;
	/**6411科目期末利息支出*/
	@Excel(name = "6411科目期末利息支出", width = 15, groupName = "利息支出")
    @ApiModelProperty(value = "6411科目期末利息支出")
	private java.math.BigDecimal qmKm6411Lxzc;
	/**期末日平存款付息率(%)*/
	@Excel(name = "期末日平存款付息率(%)", width = 15, groupName = "利息支出")
    @ApiModelProperty(value = "期末日平存款付息率(%)")
	private java.math.BigDecimal qmRpckFxl;
	/**6412科目金融机构往来支出*/
	@Excel(name = "6412科目金融机构往来支出", width = 15)
    @ApiModelProperty(value = "6412科目金融机构往来支出")
	private java.math.BigDecimal km6412JrjgWlsr;
	/**6421科目支行报表手续费支出*/
	@Excel(name = "6421科目支行报表手续费支出", width = 15, groupName = "手续费支出")
    @ApiModelProperty(value = "6421科目支行报表手续费支出")
	private java.math.BigDecimal km6421ZhbbSxfzc;
	/**6421科目总部分摊手续费支出*/
	@Excel(name = "6421科目总部分摊手续费支出", width = 15, groupName = "手续费支出")
    @ApiModelProperty(value = "6421科目总部分摊手续费支出")
	private java.math.BigDecimal km6421ZbftSxfzc;
	/**6601科目支行费用台账计算*/
	@Excel(name = "6601科目支行费用台账计算", width = 15, groupName = "业务及管理费")
    @ApiModelProperty(value = "6601科目支行费用台账计算")
	private java.math.BigDecimal km6601Zhfytzjs;
	/**6602科目支行其他业务分摊支出*/
	@Excel(name = "6602科目支行其他业务分摊支出", width = 15, groupName = "其他业务支出")
    @ApiModelProperty(value = "6602科目支行其他业务分摊支出")
	private java.math.BigDecimal km6602Zhqtywftzc;
	/**6711科目营业外支出*/
	@Excel(name = "6711科目营业外支出", width = 15, groupName = "营业外支出")
    @ApiModelProperty(value = "6711科目营业外支出")
	private java.math.BigDecimal km6711Yywzc;
	/**资产减值损失(6701及6702科目不计算分摊到支行)*/
	@Excel(name = "资产减值损失(6701及6702科目不计算分摊到支行)", width = 15, groupName = "资产减值损失")
    @ApiModelProperty(value = "资产减值损失(6701及6702科目不计算分摊到支行)")
	private java.math.BigDecimal zcjzss;
	/**各项支出合计*/
	@Excel(name = "各项支出合计", width = 15)
    @ApiModelProperty(value = "各项支出合计")
	private java.math.BigDecimal gxzchj;
	/**上年末贷款应收利息（含贷记卡）*/
	@Excel(name = "上年末贷款应收利息（含贷记卡）", width = 15, groupName = "应收利息(万元)")
    @ApiModelProperty(value = "上年末贷款应收利息（含贷记卡）")
	private java.math.BigDecimal snmDkyslx;
	/**期末贷款应收利息（含贷记卡）*/
	@Excel(name = "期末贷款应收利息（含贷记卡）", width = 15, groupName = "应收利息(万元)")
    @ApiModelProperty(value = "期末贷款应收利息（含贷记卡）")
	private java.math.BigDecimal qmDkyslx;
	/**报批费用、装饰、维修和购置(20万元以上部分）*/
	@Excel(name = "报批费用、装饰、维修和购置(20万元以上部分）", width = 15)
    @ApiModelProperty(value = "报批费用、装饰、维修和购置(20万元以上部分）")
	private java.math.BigDecimal dxbbfy;
	/**经营利润*/
	@Excel(name = "各项收入减各项支出减应收利息增加（加应收利息减少）计算经营利润任务（万元）", width = 15, groupName = "经营利润")
    @ApiModelProperty(value = "经营利润")
	private java.math.BigDecimal jylr;
	/**当年经营利润任务*/
	@Excel(name = "当年经营利润任务", width = 15)
    @ApiModelProperty(value = "当年经营利润任务")
	private java.math.BigDecimal dnJylrrw;
	/**任务完成率(%)*/
	@Excel(name = "任务完成率(%)", width = 15)
    @ApiModelProperty(value = "任务完成率(%)")
	private java.math.BigDecimal rwwcl;
	/**统计时间*/
	//@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计时间")
	private Date tjsj;
	/**操作类型-支行可见范围2-修改，等同于已审核*/
	//@Excel(name = "操作类型", width = 15)
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	//@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	//@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}
