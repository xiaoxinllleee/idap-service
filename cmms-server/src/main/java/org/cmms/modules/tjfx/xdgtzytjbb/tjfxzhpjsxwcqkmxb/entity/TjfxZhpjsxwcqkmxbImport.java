package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity;

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
 * @Description: 支行评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZHPJSXWCQKMXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZHPJSXWCQKMXB对象", description="支行评级授信完成情况明细表")
public class TjfxZhpjsxwcqkmxbImport {
    
	/**农商行*/
	@Excel(name = "农商行", width = 15)
    @ApiModelProperty(value = "农商行")
	private String nsh;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**实际走访数*/
	@Excel(name = "实际走访数", width = 15)
    @ApiModelProperty(value = "实际走访数")
	private Long sjzfhs;
	/**全村（社区）户数*/
	@Excel(name = "全村（社区）户数", width = 15)
    @ApiModelProperty(value = "全村（社区）户数")
	private Long chs;
	/**采集率*/
	@Excel(name = "采集率", width = 15)
    @ApiModelProperty(value = "采集率")
	private Long cjl;
	/**实际评级户数*/
	@Excel(name = "实际评级户数", width = 15)
    @ApiModelProperty(value = "实际评级户数")
	private Long sjpjhs;
	/**评级率*/
	@Excel(name = "评级率", width = 15)
    @ApiModelProperty(value = "评级率")
	private Long pjl;
	/**实际授信户数*/
	@Excel(name = "实际授信户数", width = 15)
    @ApiModelProperty(value = "实际授信户数")
	private Long sjsxhs;
	/**授信率*/
	@Excel(name = "授信率", width = 15)
    @ApiModelProperty(value = "授信率")
	private Long sxl;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Long yxhs;
	/**用信率*/
	@Excel(name = "用信率", width = 15)
    @ApiModelProperty(value = "用信率")
	private Long yxl;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private String ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private String jsrq;
}
