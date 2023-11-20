package org.cmms.modules.tjfx.pjsxtjbb.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 农户评级授信信息
 * @Author: cmms
 * @Date:   2019-12-03
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHPJSXXX对象", description="农户评级授信信息")
public class NhPjsxxxMx {
	/**所属支行*/
	@Excel(name = "归属机构", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "归属机构")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "归属网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String ssyxdy;
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
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
	@ApiModelProperty(value = "最终授信额度")
	private java.math.BigDecimal nhzzsxed;
	/**总评得分小计*/
	@Excel(name = "总评得分小计", width = 15)
	@ApiModelProperty(value = "总评得分小计")
	private String zpdfxj;
	/**录入人*/
	@Excel(name = "评级人", width = 15)
	@ApiModelProperty(value = "评级人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "评级时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "评级时间")
	private Date lrsj;



}
