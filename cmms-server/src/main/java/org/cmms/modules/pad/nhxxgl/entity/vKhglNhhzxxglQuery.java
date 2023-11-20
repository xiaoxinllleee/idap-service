package org.cmms.modules.pad.nhxxgl.entity;

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

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-01
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_nhhzxxgl_query")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_nhhzxxgl_query对象", description="1")
public class vKhglNhhzxxglQuery {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;

	/**ssyxdy*/
	@Excel(name = "ssyxdy", width = 15)
    @ApiModelProperty(value = "ssyxdy")
	@Dict( dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	private String ssyxdy;
	/**zkhjl*/
	@Excel(name = "zkhjl", width = 15)
    @ApiModelProperty(value = "zkhjl")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**hzxm*/
	@Excel(name = "hzxm", width = 15)
    @ApiModelProperty(value = "hzxm")
	private String hzxm;
	/**sxdx*/
	@Excel(name = "sxdx", width = 15)
    @ApiModelProperty(value = "sxdx")
	private String sxdx;
	/**khlx*/
	@Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
	@Dict(dicCode = "xtpdjg")
	private String khlx;

	/**xxws*/
	@Excel(name = "xxws", width = 15)
    @ApiModelProperty(value = "xxws")
	private String xxws;
	/**pjsx*/
	@Excel(name = "pjsx", width = 15)
    @ApiModelProperty(value = "pjsx")
	private String pjsx;
	/**yxzt*/
	@Excel(name = "yxzt", width = 15)
    @ApiModelProperty(value = "yxzt")
	private String yxzt;
	/**sxdxzjh*/
	@Excel(name = "sxdxzjh", width = 15)
    @ApiModelProperty(value = "sxdxzjh")
	private String sxdxzjh;
	/**hzzjhm*/
	@Excel(name = "hzzjhm", width = 15)
    @ApiModelProperty(value = "hzzjhm")
	private String hzzjhm;
	/**hkxz*/
	@Excel(name = "hkxz", width = 15)
    @ApiModelProperty(value = "hkxz")
	private String hkxz;

	/**pfr*/
	@Excel(name = "pfr", width = 15)
    @ApiModelProperty(value = "pfr")
	private String pfr;
	/**sfbldkh*/
	@Excel(name = "sfbldkh", width = 15)
    @ApiModelProperty(value = "sfbldkh")
	private String sfbldkh;
	/**sfpkh*/
	@Excel(name = "sfpkh", width = 15)
    @ApiModelProperty(value = "sfpkh")
	private String sfpkh;
	/**sfdbh*/
	@Excel(name = "sfdbh", width = 15)
    @ApiModelProperty(value = "sfdbh")
	private String sfdbh;
	/**khzycd*/
	@Excel(name = "khzycd", width = 15)
    @ApiModelProperty(value = "khzycd")
	private String khzycd;
	/**khqzyw*/
	@Excel(name = "khqzyw", width = 15)
    @ApiModelProperty(value = "khqzyw")
	private String khqzyw;
	/**khsxqk*/
	@Excel(name = "khsxqk", width = 15)
    @ApiModelProperty(value = "khsxqk")
	private String khsxqk;
	/**khdj*/
	@Excel(name = "khdj", width = 15)
    @ApiModelProperty(value = "khdj")
	private String khdj;
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
	private String longitude;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
	private String latitude;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
	/**sfsx*/
	@Excel(name = "sfsx", width = 15)
	@ApiModelProperty(value = "sfsx")
	@Dict(dicCode = "sfbz")
	private Integer sfsx;
	/**nhzzsxed*/
	@Excel(name = "nhzzsxed", width = 15)
	@ApiModelProperty(value = "nhzzsxed")
	private String nhzzsxed;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String lrr;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
	@ApiModelProperty(value = "修改日期")
	private String upDt;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15)
	@ApiModelProperty(value = "修改时间")
	private String upTm;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15)
	@ApiModelProperty(value = "是否采集")
	private String sfcj;

	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
}
