package org.cmms.modules.tjfx.wgywsjtj.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 网格业务数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_tjfx_wgywsjtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_tjfx_wgywsjtj对象", description="网格业务数据统计")
public class VKhxxglTjfxWgywsjtj {
    
	/**tjrq*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjrq")
	private Date tjrq;
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**wgbh*/
	@Excel(name = "网格名称", width = 15,dicCode ="wgbh",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode ="wgbh",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private String wgbh;

	@Excel(name = "主客户经理", width = 15,dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	@Dict(dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	private String khjl;
	@Excel(name = "总户数", width = 15)
	private Integer zhs;
	/**zkhs*/
	@Excel(name = "总客户数", width = 15)
    @ApiModelProperty(value = "zkhs")
	private Integer zkhs;
	/**clkhs*/
	@Excel(name = "我行客户数", width = 15)
    @ApiModelProperty(value = "clkhs")
	private Integer clkhs;
	/**clkhszb*/
	@Excel(name = "我行客户数占比", width = 15)
    @ApiModelProperty(value = "clkhszb")
	private BigDecimal clkhszb;
	/**qzkhs*/
	@Excel(name = "潜在客户数", width = 15)
    @ApiModelProperty(value = "qzkhs")
	private Integer qzkhs;
	/**qzkhszb*/
	@Excel(name = "潜在客户数占比", width = 15)
    @ApiModelProperty(value = "qzkhszb")
	private BigDecimal qzkhszb;
	/**ckkhs*/
	@Excel(name = "存款客户数", width = 15)
    @ApiModelProperty(value = "ckkhs")
	private Integer ckkhs;
	/**ckye*/
	@Excel(name = "存款余额（万元）", width = 15)
    @ApiModelProperty(value = "ckye")
	private java.math.BigDecimal ckye;
	/**dkkhs*/
	@Excel(name = "贷款客户数", width = 15)
    @ApiModelProperty(value = "dkkhs")
	private Integer dkkhs;
	/**dkye*/
	@Excel(name = "贷款余额（万元）", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**bldkkhs*/
	@Excel(name = "表内不良贷款客户数", width = 15)
    @ApiModelProperty(value = "bldkkhs")
	private Integer bldkkhs;
	/**bldkye*/
	@Excel(name = "表内不良贷款余额（万元）", width = 15)
    @ApiModelProperty(value = "bldkye")
	private java.math.BigDecimal bldkye;
	/**bwbldkkhs*/
	@Excel(name = "表外不良贷款客户数", width = 15)
    @ApiModelProperty(value = "bwbldkkhs")
	private Integer bwbldkkhs;
	/**bwbldkye*/
	@Excel(name = "表外不良贷款余额（万元）", width = 15)
    @ApiModelProperty(value = "bwbldkye")
	private java.math.BigDecimal bwbldkye;
	/**sjyhkhs*/
	@Excel(name = "手机银行", width = 15)
    @ApiModelProperty(value = "sjyhkhs")
	private Integer sjyhkhs;
	/**wsyhkhs*/
	@Excel(name = "网上银行", width = 15)
    @ApiModelProperty(value = "wsyhkhs")
	private Integer wsyhkhs;
	/**etckhs*/
	@Excel(name = "ETC", width = 15)
    @ApiModelProperty(value = "etckhs")
	private Integer etckhs;
	/**xykkhs*/
	@Excel(name = "信用卡", width = 15)
    @ApiModelProperty(value = "xykkhs")
	private Integer xykkhs;
	/**ezfkhs*/
	@Excel(name = "福祥E支付", width = 15)
	@ApiModelProperty(value = "ezfkhs")
	private Integer ezfkhs;
	/**fxezkhs*/
	@Excel(name = "福祥e站", width = 15)
    @ApiModelProperty(value = "fxezkhs")
	private Integer fxezkhs;
	/**ssrs*/
	@Excel(name = "我行诉讼", width = 15)
    @ApiModelProperty(value = "ssrs")
	private Integer ssrs;
	/**sbkkhs*/
	@Excel(name = "我行社保卡", width = 15)
    @ApiModelProperty(value = "sbkkhs")
	private Integer sbkkhs;
	/**wbsbk*/
	@Excel(name = "外部社保卡", width = 15)
    @ApiModelProperty(value = "wbsbk")
	private Integer wbsbk;
	/**dyrs*/
	@Excel(name = "共产党员", width = 15)
    @ApiModelProperty(value = "dyrs")
	private Integer dyrs;
	/**gzryrs*/
	@Excel(name = "公职人员", width = 15)
    @ApiModelProperty(value = "gzryrs")
	private Integer gzryrs;
	/**tgjsrs*/
	@Excel(name = "特岗教师", width = 15)
    @ApiModelProperty(value = "tgjsrs")
	private Integer tgjsrs;
	/**tpjch*/
	@Excel(name = "脱贫及监测户", width = 15)
    @ApiModelProperty(value = "tpjch")
	private Integer tpjch;
	/**wbdbh*/
	@Excel(name = "五保、低保户", width = 15)
    @ApiModelProperty(value = "wbdbh")
	private Integer wbdbh;
	/**zdjbrs*/
	@Excel(name = "重大疾病", width = 15)
    @ApiModelProperty(value = "zdjbrs")
	private Integer zdjbrs;
	/**zpry*/
	@Excel(name = "诈骗人员", width = 15)
    @ApiModelProperty(value = "zpry")
	private Integer zpry;
	/**ffjzrs*/
	@Excel(name = "非法集资", width = 15)
    @ApiModelProperty(value = "ffjzrs")
	private Integer ffjzrs;
	/**xdry*/
	@Excel(name = "吸毒人员", width = 15)
    @ApiModelProperty(value = "xdry")
	private Integer xdry;
	/**fxry*/
	@Excel(name = "服刑人员", width = 15)
	@ApiModelProperty(value = "fxry")
	private Integer fxry;

	@Excel(name = "涉案人员", width = 15)
	private Integer sars;

	/**网格性质*/
	private String wgxz;

}
