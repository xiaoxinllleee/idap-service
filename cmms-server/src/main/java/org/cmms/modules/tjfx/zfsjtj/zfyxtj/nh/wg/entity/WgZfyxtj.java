package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.wg.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 网格走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zfyxtj_wg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zfyxtj_wg对象", description="网格走访营销统计")
public class WgZfyxtj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计维度*/
    @ApiModelProperty(value = "统计维度")
	private String tjwd;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**网格编号*/
	@Excel(name = "所属网格", width = 15, dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
	private String wgbh;
	/**客户类型*/
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**总客户数*/
	@Excel(name = "总客户数", width = 15)
	@ApiModelProperty(value = "总客户数")
	private Integer zkhs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
	@ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**走访户数*/
	@Excel(name = "走访户数", width = 15)
    @ApiModelProperty(value = "走访户数")
	private Integer zfhs;
	/**走访率*/
	@Excel(name = "走访率", width = 15)
	@ApiModelProperty(value = "走访率")
	private BigDecimal zfl;
	/**有效走访户数*/
	@Excel(name = "有效走访户数", width = 15)
    @ApiModelProperty(value = "有效走访户数")
	private Integer yxzfhs;
	/**有效走访率*/
	@Excel(name = "有效走访率", width = 15)
	@ApiModelProperty(value = "有效走访率")
	private BigDecimal yxzfl;
	/**走访排名*/
	@Excel(name = "走访排名", width = 15)
    @ApiModelProperty(value = "走访排名")
	private Integer zfpm;
	/**有效走访排名*/
	@Excel(name = "有效走访排名", width = 15)
    @ApiModelProperty(value = "有效走访排名")
	private Integer yxzfpm;
	/**走访户数比上期*/
	@Excel(name = "走访户数比上期", width = 15)
    @ApiModelProperty(value = "走访户数比上期")
	private Integer zfhsBsq;
	/**有效走访户数比上期*/
	@Excel(name = "有效走访户数比上期", width = 15)
    @ApiModelProperty(value = "有效走访户数比上期")
	private Integer yxzfhsBsq;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Integer sxhs;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**授信户数排名*/
	@Excel(name = "授信户数排名", width = 15)
    @ApiModelProperty(value = "授信户数排名")
	private Integer sxhspm;
	/**授信金额排名*/
	@Excel(name = "授信金额排名", width = 15)
    @ApiModelProperty(value = "授信金额排名")
	private Integer sxjepm;
	/**授信户数比上期*/
	@Excel(name = "授信户数比上期", width = 15)
    @ApiModelProperty(value = "授信户数比上期")
	private Integer sxhsBsq;
	/**授信金额比上期*/
	@Excel(name = "授信金额比上期", width = 15)
    @ApiModelProperty(value = "授信金额比上期")
	private java.math.BigDecimal sxjeBsq;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Integer yxhs;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**用信户数排名*/
	@Excel(name = "用信户数排名", width = 15)
    @ApiModelProperty(value = "用信户数排名")
	private Integer yxhspm;
	/**用信金额排名*/
	@Excel(name = "用信金额排名", width = 15)
    @ApiModelProperty(value = "用信金额排名")
	private Integer yxjepm;
	/**用信户数比上期*/
	@Excel(name = "用信户数比上期", width = 15)
    @ApiModelProperty(value = "用信户数比上期")
	private Integer yxhsBsq;
	/**用信金额比上期*/
	@Excel(name = "用信金额比上期", width = 15)
    @ApiModelProperty(value = "用信金额比上期")
	private java.math.BigDecimal yxjeBsq;
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
