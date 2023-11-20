package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

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
 * @Description: 新型农业主体-统计分析
 * @Author: jeecg-boot
 * @Date:   2022-12-25
 * @Version: V1.0
 */
@Data
@TableName("XXNYZT_TJFX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXNYZT_TJFX", description="新型农业主体-统计分析")
public class XxnyztTjfx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**管护责任人*/
	@Excel(name = "管护责任人", width = 15)
    @ApiModelProperty(value = "管护责任人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String ghzrr;
	/**管户数  或 户数*/
	@Excel(name = "管户数  或 户数", width = 15)
    @ApiModelProperty(value = "管户数  或 户数")
	private Integer ghs;
	/**存量客户数*/
	@Excel(name = "存量客户数", width = 15)
    @ApiModelProperty(value = "存量客户数")
	private Integer clkhs;
	/**存量金额*/
	@Excel(name = "存量金额", width = 15)
    @ApiModelProperty(value = "存量金额")
	private Integer clje;
	/**黑名单户数*/
	@Excel(name = "黑名单户数", width = 15)
    @ApiModelProperty(value = "黑名单户数")
	private Integer hmdhs;
	/**无效户*/
	@Excel(name = "无效户", width = 15)
    @ApiModelProperty(value = "无效户")
	private Integer wxh;
	/**待村组评定户数*/
	@Excel(name = "待村组评定户数", width = 15)
    @ApiModelProperty(value = "待村组评定户数")
	private Integer dczpdhs;
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
	/**名单分类  */
	@Excel(name = "名单分类  ", width = 15)
    @ApiModelProperty(value = "名单分类  ")
	private String mdfl;
	/**待入户核定户数*/
	@Excel(name = "待入户核定户数", width = 15)
    @ApiModelProperty(value = "待入户核定户数")
	private Integer drhhdhs;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**主体分类*/
	@Excel(name = "主体分类", width = 15)
    @ApiModelProperty(value = "主体分类")
	@Dict(dicCode = "xxnyzt-ztfl")
	private String ztfl;
	/**存量不良金额*/
	@Excel(name = "存量不良金额", width = 15)
    @ApiModelProperty(value = "存量不良金额")
	private Integer clblje;

	@Excel(name = "统计类型", width = 15)
	@ApiModelProperty(value = "统计类型")
	private String tjlx;
}
