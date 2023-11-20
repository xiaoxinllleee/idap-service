package org.cmms.modules.report.sgtzgl.byyzprkmd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 边缘易致贫人口名单
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_byyzprkmd对象", description="边缘易致贫人口名单")
public class SgtzglByyzprkmdVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private Integer xh;
	/**县(市、区、旗)*/
	@Excel(name = "县(市、区、旗)", width = 15)
    @ApiModelProperty(value = "县(市、区、旗)")
	private String cs;
	/**乡(镇)*/
	@Excel(name = "乡(镇)", width = 15)
    @ApiModelProperty(value = "乡(镇)")
	private String xz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**自然村*/
	@Excel(name = "自然村", width = 15)
    @ApiModelProperty(value = "自然村")
	private String zrc;
	/**户编号*/
	@Excel(name = "户编号", width = 15)
    @ApiModelProperty(value = "户编号")
	private String hbh;
	/**人编号*/
	@Excel(name = "人编号", width = 15)
    @ApiModelProperty(value = "人编号")
	private String rgh;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**人数*/
	@Excel(name = "人数", width = 15)
    @ApiModelProperty(value = "人数")
	private String rs;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	private String yhzgx;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
    @ApiModelProperty(value = "文化程度")
	private String whcd;
	/**在校生状况*/
	@Excel(name = "在校生状况", width = 15)
    @ApiModelProperty(value = "在校生状况")
	private String zxszk;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**劳动技能*/
	@Excel(name = "劳动技能", width = 15)
    @ApiModelProperty(value = "劳动技能")
	private String ldjn;
	/**务工时间（月）*/
	@Excel(name = "务工时间（月）", width = 15)
    @ApiModelProperty(value = "务工时间（月）")
	private String wgsj;
	/**是否参加大病保险*/
	@Excel(name = "是否参加大病保险", width = 15)
    @ApiModelProperty(value = "是否参加大病保险")
	private String sfcjdbbx;
	/**致贫原因1*/
	@Excel(name = "致贫原因1", width = 15)
    @ApiModelProperty(value = "致贫原因1")
	private String zpyy;
	/**危房户*/
	@Excel(name = "危房户", width = 15)
    @ApiModelProperty(value = "危房户")
	private String wfh;
	/**是否解决安全饮用水*/
	@Excel(name = "是否解决安全饮用水", width = 15)
    @ApiModelProperty(value = "是否解决安全饮用水")
	private String sfjjaqyys;
	/**人均纯收入*/
	@Excel(name = "人均纯收入", width = 15)
    @ApiModelProperty(value = "人均纯收入")
	private java.math.BigDecimal rjcsr;
	/**户联系电话*/
	@Excel(name = "户联系电话", width = 15)
    @ApiModelProperty(value = "户联系电话")
	private String hlxdh;
	/**人联系电话*/
	@Excel(name = "人联系电话", width = 15)
    @ApiModelProperty(value = "人联系电话")
	private String rlxdh;
	/**首次识别时间*/
	@Excel(name = "首次识别时间", width = 15)
    @ApiModelProperty(value = "首次识别时间")
	private String scsbsj;
	/**人员识别时间*/
	@Excel(name = "人员识别时间", width = 15)
    @ApiModelProperty(value = "人员识别时间")
	private String rysbsj;
	/**户类型*/
	@Excel(name = "户类型", width = 15)
    @ApiModelProperty(value = "户类型")
	private String hlx;
	/**易返贫致贫户(监测对象)类型*/
	@Excel(name = "易返贫致贫户(监测对象)类型", width = 15)
    @ApiModelProperty(value = "易返贫致贫户(监测对象)类型")
	private String yfpzph;
	/**风险是否消除*/
	@Excel(name = "风险是否消除", width = 15)
    @ApiModelProperty(value = "风险是否消除")
	private String fxsfxc;
	/**风险识别时间*/
	@Excel(name = "风险识别时间", width = 15)
    @ApiModelProperty(value = "风险识别时间")
	private String fxsbsj;
	/**风险消除时间*/
	@Excel(name = "风险消除时间", width = 15)
    @ApiModelProperty(value = "风险消除时间")
	private String fxxcsj;

}
