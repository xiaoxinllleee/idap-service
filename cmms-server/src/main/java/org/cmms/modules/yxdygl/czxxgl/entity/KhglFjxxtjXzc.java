package org.cmms.modules.yxdygl.czxxgl.entity;

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
 * @Description: 村社附加信息统计
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("KHGL_FJXXTJ_XZC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_FJXXTJ_XZC对象", description="村社附加信息统计")
public class KhglFjxxtjXzc {

	/**任务日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计日期")
	private Date tjrq;


	/**区域编码*/
	@Excel(name = "区域编码", width = 15)
	@ApiModelProperty(value = "区域编码")
	private String qybm;

	/**所属营销单元*//*
	@Excel(name = "区域编码", width = 15, dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="VILLAGE || ORGANIZE")
	@ApiModelProperty(value = "区域编码")
	@Dict( dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="town||VILLAGE || ORGANIZE")
	private String qybm;*/

	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**人口总数*/
	@Excel(name = "人口总数", width = 15)
    @ApiModelProperty(value = "人口总数")
	private Long rkzs;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Long zhs;
	/**贫困户数*/
	@Excel(name = "贫困户数", width = 15)
    @ApiModelProperty(value = "贫困户数")
	private Long pkhs;
	/**低保户数*/
	@Excel(name = "低保户数", width = 15)
    @ApiModelProperty(value = "低保户数")
	private Long dbhs;
	/**吸毒人数*/
	@Excel(name = "吸毒人数", width = 15)
    @ApiModelProperty(value = "吸毒人数")
	private Long xdrs;
	/**非法集资人数*/
	@Excel(name = "非法集资人数", width = 15)
    @ApiModelProperty(value = "非法集资人数")
	private Long ffjzrs;
	/**重大疾病人数*/
	@Excel(name = "重大疾病人数", width = 15)
    @ApiModelProperty(value = "重大疾病人数")
	private Long zdjbrs;
	/**开通信用卡人数*/
	@Excel(name = "开通信用卡人数", width = 15)
    @ApiModelProperty(value = "开通信用卡人数")
	private Long ktxykrs;
	/**开通福民卡人数*/
	@Excel(name = "开通福民卡人数", width = 15)
    @ApiModelProperty(value = "开通福民卡人数")
	private Long ktfmkrs;
	/**开通扫码付人数*/
	@Excel(name = "开通扫码付人数", width = 15)
    @ApiModelProperty(value = "开通扫码付人数")
	private Long ktsmfrs;
}
