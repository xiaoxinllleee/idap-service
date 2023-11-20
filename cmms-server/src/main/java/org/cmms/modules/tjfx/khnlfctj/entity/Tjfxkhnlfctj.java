package org.cmms.modules.tjfx.khnlfctj.entity;

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
 * @Description: 客户年龄分层统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHNLFCTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHNLFCTJ对象", description="客户年龄分层统计")
public class Tjfxkhnlfctj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private java.util.Date tjyf;
	/** 所属支行*/
	@Excel(name ="所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zhmc;
	/**营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
    @ApiModelProperty(value = "营销单元")
	@Dict(dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
	private String yxdy;
	/**年龄不详客户数*/
	@Excel(name = "年龄不详客户数", width = 15)
    @ApiModelProperty(value = "年龄不详客户数")
	private String nlbxkhs;
	/**18岁以下客户数*/
	@Excel(name = "18岁以下客户数", width = 15)
    @ApiModelProperty(value = "18岁以下客户数")
	private String sbsyxkhs;
	/**19-26岁客户数*/
	@Excel(name = "19-26岁客户数", width = 15)
    @ApiModelProperty(value = "19-26岁客户数")
	private String sjzel;
	/**27-30岁客户数
*/
	@Excel(name = "27-30岁客户数 ", width = 15)
    @ApiModelProperty(value = "27-30岁客户数 ")
	private java.lang.String eqzsh;
	/**31-40岁客户数
*/
	@Excel(name = "31-40岁客户数 ", width = 15)
    @ApiModelProperty(value = "31-40岁客户数 ")
	private java.lang.String syzsh;
	/**41-55岁客户数
*/
	@Excel(name = "41-55岁客户数 ", width = 15)
    @ApiModelProperty(value = "41-55岁客户数 ")
	private java.lang.String syzww;
	/**56-65岁客户数
*/
	@Excel(name = "56-65岁客户数 ", width = 15)
    @ApiModelProperty(value = "56-65岁客户数 ")
	private java.lang.String wlzlw;
	/**66岁以上客户数*/
	@Excel(name = "66岁以上客户数", width = 15)
    @ApiModelProperty(value = "66岁以上客户数")
	private java.lang.String llsys;
}
