package org.cmms.modules.tjfx.bkbpysjtj.bkbpyqktj_zh.entity;

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
 * @Description: 评议情况统计(支行)
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_BKBPY_zh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_BKBPY_zh对象", description="评议情况统计(支行)")
public class Zhpyqktj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**已评议村组*/
	@Excel(name = "已评议村组", width = 15)
    @ApiModelProperty(value = "已评议村组")
	private Long ypycz;

	/**总人数*/
	@Excel(name = "总人数", width = 15)
	@ApiModelProperty(value = "总人数")
	private Long zrs;

	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**可评议户数*/
	@Excel(name = "可评议户数", width = 15)
    @ApiModelProperty(value = "可评议户数")
	private Long kpyhs;
	/**第一轮评议户数*/
	@Excel(name = "第一轮评议户数", width = 15)
    @ApiModelProperty(value = "第一轮评议户数")
	private Long pyhs1;
	/**第二轮评议户数*/
	@Excel(name = "第二轮评议户数", width = 15)
    @ApiModelProperty(value = "第二轮评议户数")
	private Long pyhs2;
	/**第三轮评议户数*/
	@Excel(name = "第三轮评议户数", width = 15)
    @ApiModelProperty(value = "第三轮评议户数")
	private Long pyhs3;
	/**第四轮评议户数*/
	@Excel(name = "第四轮评议户数", width = 15)
    @ApiModelProperty(value = "第四轮评议户数")
	private Long pyhs4;
	/**综合评议户数*/
	@Excel(name = "综合评议户数", width = 15)
    @ApiModelProperty(value = "综合评议户数")
	private Long zhpyhs;
	/**白名单户数*/
	@Excel(name = "白名单户数", width = 15)
    @ApiModelProperty(value = "白名单户数")
	private Long bmdhs;
	/**可评率*/
	@Excel(name = "可评率", width = 15)
    @ApiModelProperty(value = "可评率")
	private java.math.BigDecimal kpl;
	/**评议率*/
	@Excel(name = "评议率", width = 15)
    @ApiModelProperty(value = "评议率")
	private java.math.BigDecimal pyl;
	/**白名单率*/
	@Excel(name = "白名单率", width = 15)
    @ApiModelProperty(value = "白名单率")
	private java.math.BigDecimal bmdl;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
