package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXNYZT_TJFX", description="新型农业主体-统计分析")
public class XxnyztTjfxZhaztfl {

	/**所属支行*/
	@Excel(name = "组织机构", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织机构")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**主体分类*/
	@Excel(name = "主体分类", width = 15,dicCode = "xxnyzt-ztfl")
	@ApiModelProperty(value = "主体分类")
	@Dict(dicCode = "xxnyzt-ztfl")
	private String ztfl;
	/**管户数  或 户数*/
	@Excel(name = "户数", width = 15)
	@ApiModelProperty(value = "户数")
	private Integer ghs;
	/**存量客户数*/
	@Excel(name = "其中存量客户数", width = 15)
	@ApiModelProperty(value = "其中存量客户数")
	private Integer clkhs;
	/**存量金额*/
	@Excel(name = "存量额度", width = 15)
	@ApiModelProperty(value = "存量额度")
	private Integer clje;
	/**黑名单户数*/
	@Excel(name = "黑名单户", width = 15)
	@ApiModelProperty(value = "黑名单户")
	private Integer hmdhs;
	/**无效户*/
	@Excel(name = "无效户", width = 15)
	@ApiModelProperty(value = "无效户")
	private Integer wxh;
	/**待村组评定户数*/
	@Excel(name = "待村组评定", width = 15)
	@ApiModelProperty(value = "待村组评定")
	private Integer dczpdhs;
	/**待入户核定户数*/
	@Excel(name = "待入户核定户数", width = 15)
	@ApiModelProperty(value = "待入户核定户数")
	private Integer drhhdhs;
	/**灰名单户数*/
	@Excel(name = "灰名单户数", width = 15)
	@ApiModelProperty(value = "灰名单户数")
	private Integer huimdhs;
	/**白名单户数*/
	@Excel(name = "白名单户数", width = 15)
	@ApiModelProperty(value = "白名单户数")
	private Integer bmdhs;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
	@ApiModelProperty(value = "授信额度")
	private Integer sxed;


}
