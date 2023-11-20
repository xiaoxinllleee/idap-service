package org.cmms.modules.tjfx.pyqktj.entity;

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
 * @Description: 评议情况统计
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_pyqktj_wg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_pyqktj_wg对象", description="评议情况统计")
public class Pyqktj {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**tjrq*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**sszh*/
	@Excel(name = "所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**wgbh*/
	@Excel(name = "整村授信试点村", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "整村授信试点村")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**gdry*/
	@Excel(name = "挂点人员", width = 15)
    @ApiModelProperty(value = "挂点人员")
	private String gdry;
	/**zrs*/
	@Excel(name = "人口数", width = 15)
    @ApiModelProperty(value = "人口数")
	private Integer zrs;
	/**zhs*/
	@Excel(name = "户数", width = 15)
    @ApiModelProperty(value = "户数")
	private Integer zhs;
	/**kpyhs*/
	@Excel(name = "可评议户数", width = 15)
    @ApiModelProperty(value = "可评议户数")
	private Integer kpyhs;
	/**zhpyhs*/
	@Excel(name = "综合评议户数", width = 15)
    @ApiModelProperty(value = "综合评议户数")
	private Integer zhpyhs;
	/**pysyhs*/
	@Excel(name = "评议剩余户数", width = 15)
    @ApiModelProperty(value = "评议剩余户数")
	private Integer pysyhs;
	/**bmdl*/
	@Excel(name = "白名单率", width = 15)
    @ApiModelProperty(value = "白名单率")
	private java.math.BigDecimal bmdl;
	/**hnkddrhs*/
	@Excel(name = "惠农快贷导入户数", width = 15)
    @ApiModelProperty(value = "惠农快贷导入户数")
	private Integer hnkddrhs;
	/**hnkddred*/
	@Excel(name = "农户快贷导入授信金额", width = 15)
    @ApiModelProperty(value = "农户快贷导入授信金额")
	private java.math.BigDecimal hnkddred;
	/**hnkddrpyhs*/
	@Excel(name = "完成导入白名单以及完成2轮评议户数", width = 15)
    @ApiModelProperty(value = "完成导入白名单以及完成2轮评议户数")
	private Integer hnkddrpyhs;
	/**hnkddrhsjz*/
	@Excel(name = "惠农快贷导入户数净增", width = 15)
    @ApiModelProperty(value = "惠农快贷导入户数净增")
	private Integer hnkddrhsjz;
	/**hnkdbmdl*/
	@Excel(name = "惠农快贷白名单生成率", width = 15)
    @ApiModelProperty(value = "惠农快贷白名单生成率")
	private java.math.BigDecimal hnkdbmdl;
	/**clkhs*/
	@Excel(name = "存量客户数", width = 15)
    @ApiModelProperty(value = "存量客户数")
	private Integer clkhs;
	/**qmkhs*/
	@Excel(name = "期末客户数", width = 15)
    @ApiModelProperty(value = "期末客户数")
	private Integer qmkhs;
	/**xkhs1*/
	@Excel(name = "新签合同户数（含20万以上）", width = 15)
    @ApiModelProperty(value = "新签合同户数（含20万以上）")
	private Integer xkhs1;
	/**xkhs2*/
	@Excel(name = "新签合同户数(10-20万）", width = 15)
    @ApiModelProperty(value = "新签合同户数(10-20万）")
	private Integer xkhs2;
	/**xkhs3*/
	@Excel(name = "新签合同户数(10万以下）", width = 15)
	@ApiModelProperty(value = "新签合同户数(10万以下）")
	private Integer xkhs3;
	/**sxje*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**yxje*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**yxhs*/
	@Excel(name = "用信户数", width = 15)
	@ApiModelProperty(value = "用信户数")
	private java.math.BigDecimal yxhs;
	/**yxl*/
	@Excel(name = "用信率", width = 15)
	@ApiModelProperty(value = "用信率")
	private java.math.BigDecimal yxl;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
