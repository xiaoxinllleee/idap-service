package org.cmms.modules.dklldj.lldjgl.lldjjs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.xdgl.grdkgl.entity.RateLldjZhckrp;
import org.cmms.modules.xdgl.grdkgl.entity.RateZxlldjb;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class RateLldjjsVO {

    /**gz00009*/
    @Excel(name = "gz00009", width = 15)
    @ApiModelProperty(value = "gz00009")
    private String gz00009;
    /**gz00010*/
    @Excel(name = "gz00010", width = 15)
    @ApiModelProperty(value = "gz00010")
    private String gz00010;
    /**gz00013*/
    @Excel(name = "gz00013", width = 15)
    @ApiModelProperty(value = "gz00013")
    private String gz00013;
    /**gz00014*/
    @Excel(name = "gz00014", width = 15)
    @ApiModelProperty(value = "gz00014")
    private String gz00014;
    /**gz00015*/
    @Excel(name = "gz00015", width = 15)
    @ApiModelProperty(value = "gz00015")
    private String gz00015;
    /**gz00021*/
    @Excel(name = "gz00021", width = 15)
    @ApiModelProperty(value = "gz00021")
    private String gz00021;
    /**gz00022*/
    @Excel(name = "gz00022", width = 15)
    @ApiModelProperty(value = "gz00022")
    private String gz00022;
    /**gz00023*/
    @Excel(name = "gz00023", width = 15)
    @ApiModelProperty(value = "gz00023")
    private String gz00023;
    /**gz00031*/
    @Excel(name = "gz00031", width = 15)
    @ApiModelProperty(value = "gz00031")
    private String gz00031;
    private String gz00031Val;
    /**gz00032*/
    @Excel(name = "gz00032", width = 15)
    @ApiModelProperty(value = "gz00032")
    private String gz00032;
    /**gz00033*/
    @Excel(name = "gz00033", width = 15)
    @ApiModelProperty(value = "gz00033")
    private String gz00033;
    /**gz00034*/
    @Excel(name = "gz00034", width = 15)
    @ApiModelProperty(value = "gz00034")
    private String gz00034;
    /**gz00035*/
    @Excel(name = "gz00035", width = 15)
    @ApiModelProperty(value = "gz00035")
    private String gz00035;
    /**gz00036*/
    @Excel(name = "gz00036", width = 15)
    @ApiModelProperty(value = "gz00036")
    private String gz00036;
    /**gz00037*/
    @Excel(name = "gz00037", width = 15)
    @ApiModelProperty(value = "gz00037")
    private String gz00037;
    /**gz00038*/
    @Excel(name = "gz00038", width = 15)
    @ApiModelProperty(value = "gz00038")
    private String gz00038;
    /**gz00039*/
    @Excel(name = "gz00039", width = 15)
    @ApiModelProperty(value = "gz00039")
    private String gz00039;
    /**gz00040*/
    @Excel(name = "gz00040", width = 15)
    @ApiModelProperty(value = "gz00040")
    private String gz00040;
    /**gz00041*/
    @Excel(name = "gz00041", width = 15)
    @ApiModelProperty(value = "gz00041")
    private String gz00041;
    /**gz00042*/
    @Excel(name = "gz00042", width = 15)
    @ApiModelProperty(value = "gz00042")
    private String gz00042;
    /**gz00043*/
    @Excel(name = "gz00043", width = 15)
    @ApiModelProperty(value = "gz00043")
    private String gz00043;
    /**gz00044*/
    @Excel(name = "gz00044", width = 15)
    @ApiModelProperty(value = "gz00044")
    private String gz00044;
    /**gz00045*/
    @Excel(name = "gz00045", width = 15)
    @ApiModelProperty(value = "gz00045")
    private String gz00045;
    /**gz00046*/
    @Excel(name = "gz00046", width = 15)
    @ApiModelProperty(value = "gz00046")
    private String gz00046;
    /**gz00047*/
    @Excel(name = "gz00047", width = 15)
    @ApiModelProperty(value = "gz00047")
    private String gz00047;
    private String gz00047Val;
    /**gz00048*/
    @Excel(name = "gz00048", width = 15)
    @ApiModelProperty(value = "gz00048")
    private String gz00048;
    /**gz00049*/
    @Excel(name = "gz00049", width = 15)
    @ApiModelProperty(value = "gz00049")
    private String gz00049;
    /**gz00050*/
    @Excel(name = "gz00050", width = 15)
    @ApiModelProperty(value = "gz00050")
    private String gz00050;
    /**gz00051*/
    @Excel(name = "gz00051", width = 15)
    @ApiModelProperty(value = "gz00051")
    private String gz00051;
    /**gz00052*/
    @Excel(name = "gz00052", width = 15)
    @ApiModelProperty(value = "gz00052")
    private String gz00052;
    /**gz00053*/
    @Excel(name = "gz00053", width = 15)
    @ApiModelProperty(value = "gz00053")
    private String gz00053;
    /**gz00057*/
    @Excel(name = "gz00057", width = 15)
    @ApiModelProperty(value = "gz00057")
    private String gz00057;
    /**jxqx*/
    @Excel(name = "jxqx", width = 15)
    @ApiModelProperty(value = "jxqx")
    private String jyqx;
    private String jyqxVal;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**lrr*/
    @Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
    private String lrr;
    /**lrsj*/
    @Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
    private Date lrsj;
    /**xgr*/
    @Excel(name = "xgr", width = 15)
    @ApiModelProperty(value = "xgr")
    private String xgr;
    /**xgsj*/
    @Excel(name = "xgsj", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "xgsj")
    private Date xgsj;
    /**djnf*/
    @Excel(name = "djnf", width = 15)
    @ApiModelProperty(value = "djnf")
    private String djnf;
    /**zzbz*/
    @Excel(name = "zzbz", width = 15)
    @ApiModelProperty(value = "zzbz")
    @Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    private String zzbz;
    /**khlx*/
    @Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
    @Dict(dicCode = "lldj_khlx")
    private String khlx;
    private String khlxVal;
    /**zjhm*/
    @Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
    private String zjhm;
    private String khmc;
    /**frdb*/
    @Excel(name = "frdb", width = 15)
    @ApiModelProperty(value = "frdb")
    private String frdb;
    /**snsxed*/
    @Excel(name = "snsxed", width = 15)
    @ApiModelProperty(value = "snsxed")
    private java.math.BigDecimal snsxed = new BigDecimal(0.00);
    /**sndkllsffd*/
    @Excel(name = "sndkllsffd", width = 15)
    @ApiModelProperty(value = "sndkllsffd")
    private java.math.BigDecimal sndkllsffd = new BigDecimal(0.00);
    /**dkqx*/
    @Excel(name = "dkqx", width = 15)
    @ApiModelProperty(value = "dkqx")
    @Dict(dicCode = "dkqx")
    private Integer dkqx;
    private String dkqxVal;
    /**sfbmk*/
    @Excel(name = "sfbmk", width = 15)
    @ApiModelProperty(value = "sfbmk")
    @Dict(dicCode = "sfbz")
    private Integer sfbmk;
    /**lrbz*/
    @Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
    private Integer lrbz;
    /**zhsxed*/
    @Excel(name = "zhsxed", width = 15)
    @ApiModelProperty(value = "zhsxed")
    private java.math.BigDecimal zhsxed;
    /**cdck*/
    @Excel(name = "cdck", width = 15)
    @ApiModelProperty(value = "cdck")
    private java.math.BigDecimal cdck;
    /**sfbzbxdk*/
    @Excel(name = "sfbzbxdk", width = 15)
    @ApiModelProperty(value = "sfbzbxdk")
    @Dict(dicCode = "sfbz")
    private Integer sfbzbxdk;
    /**hkfs*/
    @Excel(name = "hkfs", width = 15)
    @ApiModelProperty(value = "hkfs")
    @Dict(dicCode = "hkfs")
    private Integer hkfs;
    /**sndkjdbp*/
    @Excel(name = "sndkjdbp", width = 15)
    @ApiModelProperty(value = "sndkjdbp")
    private java.math.BigDecimal sndkjdbp;
    /**xddkpz*/
    @Excel(name = "xddkpz", width = 15)
    @ApiModelProperty(value = "xddkpz")
    private String xddkpz;
    /**sxed*/
    @Excel(name = "sxed", width = 15)
    @ApiModelProperty(value = "sxed")
    private java.math.BigDecimal sxed;
    /**xydj*/
    @Excel(name = "xydj", width = 15)
    @ApiModelProperty(value = "xydj")
    private String xydj;
    private String xydjVal;

    private String xydjdf;
    private String dfkh00002;
    private String dfkh00003;
    private String dfkh00004;
    private String dfkh00005;
    private String dfkh00007;
    private String dfkh00008;
    private String dfkh00009;
    private String dfkh00010;
    private String dbzdf;
    private java.math.BigDecimal dfhj;
    private java.math.BigDecimal cbfd;
    private java.math.BigDecimal jyhfd;
    private java.math.BigDecimal sffd;
    private java.math.BigDecimal dyjzlv;
    private java.math.BigDecimal zxll;
    private String khgxdf;
    private java.math.BigDecimal jdbp;
    private java.math.BigDecimal yhhjdbp;
    private java.math.BigDecimal lprll;
    private java.math.BigDecimal yhhzxll;
    private java.math.BigDecimal jjll;
    private String reCalc;

    private RateZxlldjb rateZxlldjb;

}
