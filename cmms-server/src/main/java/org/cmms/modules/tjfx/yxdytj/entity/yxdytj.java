package org.cmms.modules.tjfx.yxdytj.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-05-27
 * @Version: V1.0
 */
@Data
@TableName("TJFX_YXDYTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_YXDYTJ对象", description="1")
public class yxdytj {
    
	/**存款余额  */
	@Excel(name = "存款余额  ", width = 15)
    @ApiModelProperty(value = "存款余额  ")
	private java.math.BigDecimal ckye;
	/**存款户数*/
	@Excel(name = "存款户数", width = 15)
    @ApiModelProperty(value = "存款户数")
	private Long ckhs;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal ckje;
	/**贷款户数 */
	@Excel(name = "贷款户数 ", width = 15)
    @ApiModelProperty(value = "贷款户数 ")
	private Long dkhs;
	/**不良贷款余额 */
	@Excel(name = "不良贷款余额 ", width = 15)
    @ApiModelProperty(value = "不良贷款余额 ")
	private java.math.BigDecimal bldkye;
	/**不良贷款户数*/
	@Excel(name = "不良贷款户数", width = 15)
    @ApiModelProperty(value = "不良贷款户数")
	private Long bldkhs;
	/**表外不良贷款户数*/
	@Excel(name = "表外不良贷款户数", width = 15)
    @ApiModelProperty(value = "表外不良贷款户数")
	private Long bwbldkhs;
	/**表外不良贷款余额 */
	@Excel(name = "表外不良贷款余额 ", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额 ")
	private java.math.BigDecimal bwbldkye;
	/**手机银行户数  */
	@Excel(name = "手机银行户数  ", width = 15)
    @ApiModelProperty(value = "手机银行户数  ")
	private Long sjyhhs;
	/**ETC户数 */
	@Excel(name = "ETC户数 ", width = 15)
    @ApiModelProperty(value = "ETC户数 ")
	private Long etchs;
	/**网上银行户数*/
	@Excel(name = "网上银行户数", width = 15)
    @ApiModelProperty(value = "网上银行户数")
	private Long wsyhhs;
	/**福民卡户数*/
	@Excel(name = "福民卡户数", width = 15)
    @ApiModelProperty(value = "福民卡户数")
	private Long fmkhs;
	/**社保卡户数*/
	@Excel(name = "社保卡户数", width = 15)
    @ApiModelProperty(value = "社保卡户数")
	private Long sbkhs;
	/**信用卡户数*/
	@Excel(name = "信用卡户数", width = 15)
    @ApiModelProperty(value = "信用卡户数")
	private Long xykhs;
	/**POS机户数*/
	@Excel(name = "POS机户数", width = 15)
    @ApiModelProperty(value = "POS机户数")
	private Long posjhs;
	/**扫码付户数*/
	@Excel(name = "扫码付户数", width = 15)
    @ApiModelProperty(value = "扫码付户数")
	private Long smfhs;
	/**潜在客户数*/
	@Excel(name = "潜在客户数", width = 15)
    @ApiModelProperty(value = "潜在客户数")
	private Long qzkhs;
	/**普通客户数*/
	@Excel(name = "普通客户数", width = 15)
	@ApiModelProperty(value = "普通客户数")
	private Long ptkhs;
	/**核心客户数*/
	@Excel(name = "核心客户数", width = 15)
	@ApiModelProperty(value = "核心客户数")
	private Long hxkhs;
	/**高端客户数*/
	@Excel(name = "高端客户数", width = 15)
	@ApiModelProperty(value = "高端客户数")
	private Long gdkhs;
	/**吸毒人员数*/
	@Excel(name = "吸毒人员数", width = 15)
    @ApiModelProperty(value = "吸毒人员数")
	private Long xdrys;
	/**贫困户户数*/
	@Excel(name = "贫困户户数", width = 15)
    @ApiModelProperty(value = "贫困户户数")
	private Long pkhhs;
	/**低保户户数*/
	@Excel(name = "低保户户数", width = 15)
    @ApiModelProperty(value = "低保户户数")
	private Long dbhhs;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**营销单元编号*/
	@Excel(name = "营销单元编号", width = 15)
    @ApiModelProperty(value = "营销单元编号")
	private String dybh;
	/**营销单元名称*/
	@Excel(name = "营销单元名称", width = 15)
    @ApiModelProperty(value = "营销单元名称")
	private String dymc;
	/**营销单元性质*/
	@Excel(name = "营销单元性质", width = 15)
    @ApiModelProperty(value = "营销单元性质")
	@Dict(dicCode = "yjyxdyxz")
	private String dyxz;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**归属客户经理*/
	@Excel(name = "归属客户经理", width = 15)
    @ApiModelProperty(value = "归属客户经理")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String khjlbz;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**本月走访户数*/
	@Excel(name = "本月走访户数", width = 15)
    @ApiModelProperty(value = "本月走访户数")
	private Long byzfhs;
	/**累计走访户数*/
	@Excel(name = "累计走访户数", width = 15)
    @ApiModelProperty(value = "累计走访户数")
	private Long ljzfhs;
	/**本月评级户数*/
	@Excel(name = "本月评级户数", width = 15)
    @ApiModelProperty(value = "本月评级户数")
	private Long bypjhs;
	/**累计评级户数*/
	@Excel(name = "累计评级户数", width = 15)
    @ApiModelProperty(value = "累计评级户数")
	private Long ljpjhs;
	/**本月建档人数*/
	@Excel(name = "本月建档人数", width = 15)
    @ApiModelProperty(value = "本月建档人数")
	private Long byjdrs;
	/**累计建档人数*/
	@Excel(name = "累计建档人数", width = 15)
    @ApiModelProperty(value = "累计建档人数")
	private Long ljjdrs;
	/**本月授信客户数*/
	@Excel(name = "本月授信客户数", width = 15)
    @ApiModelProperty(value = "本月授信客户数")
	private Long bysxkhs;
	/**累计授信客户数*/
	@Excel(name = "累计授信客户数", width = 15)
    @ApiModelProperty(value = "累计授信客户数")
	private Long ljsykhs;
	/**本月用信客户数*/
	@Excel(name = "本月用信客户数", width = 15)
    @ApiModelProperty(value = "本月用信客户数")
	private Long byyxkhs;
	/**累计用信客户数*/
	@Excel(name = "累计用信客户数", width = 15)
    @ApiModelProperty(value = "累计用信客户数")
	private Long ljyxkhs;
	/**本月授信金额*/
	@Excel(name = "本月授信金额", width = 15)
    @ApiModelProperty(value = "本月授信金额")
	private java.math.BigDecimal bysxje;
	/**累计授信金额*/
	@Excel(name = "累计授信金额", width = 15)
    @ApiModelProperty(value = "累计授信金额")
	private java.math.BigDecimal ljsxje;
	/**本月用信金额*/
	@Excel(name = "本月用信金额", width = 15)
    @ApiModelProperty(value = "本月用信金额")
	private java.math.BigDecimal byyxje;
	/**累计用信金额*/
	@Excel(name = "累计用信金额", width = 15)
    @ApiModelProperty(value = "累计用信金额")
	private java.math.BigDecimal ljyxje;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Long sxhs;
	/**预授信户数*/
	@Excel(name = "预授信户数", width = 15)
    @ApiModelProperty(value = "预授信户数")
	private Long ysxhs;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;

	@Excel(name = "非法集资人数", width = 15)
	@ApiModelProperty(value = "非法集资人数")
	private Long ffjzrs;
	@Excel(name = "重大疾病人数", width = 15)
	@ApiModelProperty(value = "重大疾病人数")
	private Long zdjbrs;
	@Excel(name = "存款余额占比", width = 15)
	@ApiModelProperty(value = "存款余额占比")
	private java.math.BigDecimal ckyezb;
	@Excel(name = "贷款余额占比", width = 15)
	@ApiModelProperty(value = "贷款余额占比")
	private java.math.BigDecimal dkyezb;
	@Excel(name = "表内不良余额占比", width = 15)
	@ApiModelProperty(value = "表内不良余额占比")
	private java.math.BigDecimal bnblyezb;
	@Excel(name = "表外不良余额占比", width = 15)
	@ApiModelProperty(value = "表外不良余额占比")
	private java.math.BigDecimal bwblyezb;

}
