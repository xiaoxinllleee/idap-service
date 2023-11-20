package org.cmms.modules.pad.nhxxgl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-01
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_nhhzxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_nhhzxxgl对象", description="1")
public class vKhglNhhzxxgl {

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
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String ssyxdy;
	/**sszh*/
	@Excel(name = "ysswg", width = 15)
	@ApiModelProperty(value = "ysswg")
	private String ysswg;
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
	/**lrry*/
	@Excel(name = "lrry", width = 15)
	@ApiModelProperty(value = "lrry")
	private String lrry;
	/**pfr*/
	@Excel(name = "pfr", width = 15)
    @ApiModelProperty(value = "pfr")
	private String pfr;
	/**zhpfr*/
	@Excel(name = "zhpfr", width = 15)
	@ApiModelProperty(value = "zhpfr")
	private String zhpfr;
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
	/**sfhnbth*/
	@Excel(name = "sfhnbth", width = 15)
	@ApiModelProperty(value = "sfhnbth")
	private String sfhnbth;
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
	/**wbpysl*/
	@Excel(name = "wbpysl", width = 15)
    @ApiModelProperty(value = "wbpysl")
	private Integer wbpysl;
	/**sfsx*/
	@Excel(name = "sfsx", width = 15)
	@ApiModelProperty(value = "sfsx")
	@Dict(dicCode = "sfbz")
	private Integer sfsx;
	/**nhzzsxed*/
	@Excel(name = "nhzzsxed", width = 15)
	@ApiModelProperty(value = "nhzzsxed")
	private String nhzzsxed;
	/**pyxydj*/
	@Excel(name = "pyxydj", width = 15)
	@ApiModelProperty(value = "pyxydj")
	@Dict(dicCode = "bkbpy_xydj")
	private String pyxydj;
	/**pysxed*/
	@Excel(name = "pysxed", width = 15)
	@ApiModelProperty(value = "pysxed")
	private BigDecimal pysxed;
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
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String lrr;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
	@ApiModelProperty(value = "修改日期")
	private String upDt;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15)
	@ApiModelProperty(value = "修改时间")
	private String upTm;

	/**sfyxzf*/
	@Excel(name = "sfyxzf", width = 15)
	@ApiModelProperty(value = "sfyxzf")
	@Dict(dicCode = "sfbz")
	private Integer sfyxzf;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	@ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15)
	@ApiModelProperty(value = "是否采集")
	private String sfcj;
	/**是否存在业务往来*/
	@Excel(name = "是否存在业务往来", width = 15)
	@ApiModelProperty(value = "是否存在业务往来")
	private String sfczywwl;
	/**评议平均得分*/
	@Excel(name = "评议平均得分", width = 15)
	@ApiModelProperty(value = "评议平均得分")
	private String pypjdf;
	private String khfxd;
	private String thywqk;
	/**服务跟踪人*/
	@Excel(name = "服务跟踪人", width = 15)
	@ApiModelProperty(value = "服务跟踪人")
	private String fwgzr;

	/**客户类型描述*/
	@Excel(name = "客户类型描述", width = 15)
	@ApiModelProperty(value = "客户类型描述")
	private String khlxms;

	/**是否散户*/
	@Excel(name = "是否散户", width = 15)
	@ApiModelProperty(value = "是否散户")
	@Dict(dicCode = "sfbz")
	private String sfsh;

	/** 是否导入惠农快贷 */
	@Excel(name = "是否导入惠农快贷", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否导入惠农快贷")
	@Dict(dicCode = "sfbz")
	private String sfdrhnkd;

	/**支行审定额度*/
	@Excel(name = "支行审定额度", width = 15)
	@ApiModelProperty(value = "支行审定额度")
	private java.math.BigDecimal zhsded;
}
