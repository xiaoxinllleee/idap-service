package org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 走访统计
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_tjfx_zftjysb_town")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_tjfx_zftjysb_town对象", description="走访统计")
public class WgxxtjZftjysbTown {
    
	/**tjrq*/
	@Excel(name = "tjrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjrq")
	private java.util.Date tjrq;
	/**ssxz*/
	@Excel(name = "ssxz", width = 15)
    @ApiModelProperty(value = "ssxz")
	private java.lang.String ssxz;
	/**zhs*/
	@Excel(name = "zhs", width = 15)
    @ApiModelProperty(value = "zhs")
	private java.lang.Integer zhs;
	/**heimdhs*/
	@Excel(name = "heimdhs", width = 15)
    @ApiModelProperty(value = "heimdhs")
	private java.lang.Integer heimdhs;
	/**huimdhs*/
	@Excel(name = "huimdhs", width = 15)
    @ApiModelProperty(value = "huimdhs")
	private java.lang.Integer huimdhs;


	private java.lang.Integer bmdlrhs;

	/**bkbpyhs*/
	@Excel(name = "bkbpyhs", width = 15)
    @ApiModelProperty(value = "bkbpyhs")
	private java.lang.Integer bkbpyhs;
	/**zfhs*/
	@Excel(name = "zfhs", width = 15)
    @ApiModelProperty(value = "zfhs")
	private java.lang.Integer zfhs;
	/**sxhs*/
	@Excel(name = "sxhs", width = 15)
    @ApiModelProperty(value = "sxhs")
	private java.lang.Integer sxhs;
	/**yxhs*/
	@Excel(name = "yxhs", width = 15)
    @ApiModelProperty(value = "yxhs")
	private java.lang.Integer yxhs;
	/**clhs*/
	@Excel(name = "clhs", width = 15)
    @ApiModelProperty(value = "clhs")
	private java.lang.Integer clhs;
	/**zlhs*/
	@Excel(name = "zlhs", width = 15)
    @ApiModelProperty(value = "zlhs")
	private java.lang.Integer zlhs;
	/**yxje*/
	@Excel(name = "yxje", width = 15)
    @ApiModelProperty(value = "yxje")
	private java.math.BigDecimal yxje;
	/**clyxje*/
	@Excel(name = "clyxje", width = 15)
    @ApiModelProperty(value = "clyxje")
	private java.math.BigDecimal clyxje;
	/**zlyxje*/
	@Excel(name = "zlyxje", width = 15)
    @ApiModelProperty(value = "zlyxje")
	private java.math.BigDecimal zlyxje;

	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "增量授信")
	private java.math.BigDecimal nhzzsxed;

	/**增量授信金额*/
	@Excel(name = "增量授信金额", width = 15)
	@ApiModelProperty(value = "增量授信金额")
	private java.math.BigDecimal zlsxje;

	/**增量授信户数*/
	@Excel(name = "增量授信户数", width = 15)
	@ApiModelProperty(value = "增量授信户数")
	private Integer zlsxhs;

}
