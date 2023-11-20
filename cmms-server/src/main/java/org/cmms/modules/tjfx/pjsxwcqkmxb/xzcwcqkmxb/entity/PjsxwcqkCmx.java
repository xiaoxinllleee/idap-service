package org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.entity;

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
 * @Description: 行政村完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Data
@TableName("TJFX_PJSXWCQK_CMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_PJSXWCQK_CMX对象", description="行政村完成情况明细表")
public class PjsxwcqkCmx {
    
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
    /**行政村*/
    @Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
    private String xzc;
	/**班子成员名称*/
	@Excel(name = "班子成员名称", width = 15, dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "班子成员名称")
    @Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String bzcymc;
    /**村户数*/
    @Excel(name = "村户数", width = 15)
    @ApiModelProperty(value = "村户数")
    private Long chs;
	/**实际走访数*/
	@Excel(name = "实际走访数", width = 15)
    @ApiModelProperty(value = "实际走访数")
	private Long sjzfhs;
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
}
