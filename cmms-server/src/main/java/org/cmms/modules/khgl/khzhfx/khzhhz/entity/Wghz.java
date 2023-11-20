package org.cmms.modules.khgl.khzhfx.khzhhz.entity;

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
 * @Description: 客户转化网格汇总
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_khzhfx_wghz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_khzhfx_wghz对象", description="客户转化网格汇总")
public class Wghz {
    
	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
	private java.util.Date kssj;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
	private java.util.Date jssj;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private java.lang.String sszh;
	/**归属网格*/
	@Excel(name = "归属网格编号", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	@ApiModelProperty(value = "归属网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	private java.lang.String wgbh;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private java.lang.Integer zfhs;
	/**有效走访户数*/
	@Excel(name = "有效走访户数", width = 15)
    @ApiModelProperty(value = "有效走访户数")
	private java.lang.Integer yxzfhs;
	/**评议户数*/
	@Excel(name = "评议户数", width = 15)
    @ApiModelProperty(value = "评议户数")
	private java.lang.Integer pyhs;
	/**评议金额*/
	@Excel(name = "评议金额", width = 15)
    @ApiModelProperty(value = "评议金额")
	private java.math.BigDecimal pyje;
	/**评级授信户数*/
	@Excel(name = "评级授信户数", width = 15)
    @ApiModelProperty(value = "评级授信户数")
	private java.lang.Integer pjsxhs;
	/**预授信金额*/
	@Excel(name = "预授信金额", width = 15)
    @ApiModelProperty(value = "预授信金额")
	private java.math.BigDecimal ysxje;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private java.lang.Integer dkhs;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款转化户数*/
	@Excel(name = "贷款转化户数", width = 15)
    @ApiModelProperty(value = "贷款转化户数")
	private java.lang.Integer dkzhhs;
	/**贷款转化率*/
	@Excel(name = "贷款转化率", width = 15)
    @ApiModelProperty(value = "贷款转化率")
	private java.math.BigDecimal dkzhl;
	/**存款户数*/
	@Excel(name = "存款户数", width = 15)
    @ApiModelProperty(value = "存款户数")
	private java.lang.Integer ljhs;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ljye;
	/**活期户数*/
	@Excel(name = "活期户数", width = 15)
    @ApiModelProperty(value = "活期户数")
	private java.lang.Integer hqhs;
	/**活期余额*/
	@Excel(name = "活期余额", width = 15)
    @ApiModelProperty(value = "活期余额")
	private java.math.BigDecimal hqye;
	/**定期户数*/
	@Excel(name = "定期户数", width = 15)
    @ApiModelProperty(value = "定期户数")
	private java.lang.Integer dqhs;
	/**定期余额*/
	@Excel(name = "定期余额", width = 15)
    @ApiModelProperty(value = "定期余额")
	private java.math.BigDecimal dqye;
	/**存款转化户数*/
	@Excel(name = "存款转化户数", width = 15)
    @ApiModelProperty(value = "存款转化户数")
	private java.lang.Integer ckzhhs;
	/**存款转化率*/
	@Excel(name = "存款转化率", width = 15)
    @ApiModelProperty(value = "存款转化率")
	private java.math.BigDecimal ckzhl;
	/**手机银行户数*/
	@Excel(name = "手机银行户数", width = 15)
    @ApiModelProperty(value = "手机银行户数")
	private java.lang.Integer sjyhhs;
	/**手机银行转化户数*/
	@Excel(name = "手机银行转化户数", width = 15)
    @ApiModelProperty(value = "手机银行转化户数")
	private java.lang.Integer sjyhzhhs;
	/**手机银行转化率*/
	@Excel(name = "手机银行转化率", width = 15)
    @ApiModelProperty(value = "手机银行转化率")
	private java.math.BigDecimal sjyhzhl;
	/**网上银行户数*/
	@Excel(name = "网上银行户数", width = 15)
    @ApiModelProperty(value = "网上银行户数")
	private java.lang.Integer wsyhhs;
	/**网上银行转化户数*/
	@Excel(name = "网上银行转化户数", width = 15)
    @ApiModelProperty(value = "网上银行转化户数")
	private java.lang.Integer wsyhzhhs;
	/**网上银行转化率*/
	@Excel(name = "网上银行转化率", width = 15)
    @ApiModelProperty(value = "网上银行转化率")
	private java.math.BigDecimal wsyhzhl;
	/**ETC户数*/
	@Excel(name = "ETC户数", width = 15)
    @ApiModelProperty(value = "ETC户数")
	private java.lang.Integer etchs;
	/**ETC转化户数*/
	@Excel(name = "ETC转化户数", width = 15)
    @ApiModelProperty(value = "ETC转化户数")
	private java.lang.Integer etzhhs;
	/**ETC转化率*/
	@Excel(name = "ETC转化率", width = 15)
    @ApiModelProperty(value = "ETC转化率")
	private java.math.BigDecimal etczhl;
	/**信用卡户数*/
	@Excel(name = "信用卡户数", width = 15)
    @ApiModelProperty(value = "信用卡户数")
	private java.lang.Integer xykhs;
	/**信用卡转化户数*/
	@Excel(name = "信用卡转化户数", width = 15)
    @ApiModelProperty(value = "信用卡转化户数")
	private java.lang.Integer xykzhhs;
	/**信用卡转化率*/
	@Excel(name = "信用卡转化率", width = 15)
    @ApiModelProperty(value = "信用卡转化率")
	private java.math.BigDecimal xykzhl;
}
