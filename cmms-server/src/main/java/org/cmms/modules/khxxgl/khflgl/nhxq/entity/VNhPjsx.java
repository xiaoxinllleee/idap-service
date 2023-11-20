package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

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
 * @Description: 农户评级授信视图
 * @Author: jeecg-boot
 * @Date:   2023-02-05
 * @Version: V1.0
 */
@Data
@TableName("v_nh_pjsx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_nh_pjsx对象", description="农户评级授信视图")
public class VNhPjsx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private String wgbh;
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**khlx*/
	@Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
	@Dict(dicCode = "clkhlx")
	private String khlx;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**mz*/
	@Excel(name = "mz", width = 15)
    @ApiModelProperty(value = "mz")
	@Dict(dicCode = "mz")
	private String mz;
	/**hyzk*/
	@Excel(name = "hyzk", width = 15)
    @ApiModelProperty(value = "hyzk")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**hkxz*/
	@Excel(name = "hkxz", width = 15)
    @ApiModelProperty(value = "hkxz")
	@Dict(dicCode = "khgl_hkxz")
	private String hkxz;
	/**yhzgx*/
	@Excel(name = "yhzgx", width = 15)
    @ApiModelProperty(value = "yhzgx")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**whcd*/
	@Excel(name = "whcd", width = 15)
    @ApiModelProperty(value = "whcd")
	@Dict(dicCode = "whcd")
	private String whcd;
	/**cshygz*/
	@Excel(name = "cshygz", width = 15)
    @ApiModelProperty(value = "cshygz")
	private String cshygz;
	/**sjhm*/
	@Excel(name = "sjhm", width = 15)
    @ApiModelProperty(value = "sjhm")
	private String sjhm;
	/**hjdz*/
	@Excel(name = "hjdz", width = 15)
    @ApiModelProperty(value = "hjdz")
	private String hjdz;
	/**zz*/
	@Excel(name = "zz", width = 15)
    @ApiModelProperty(value = "zz")
	private String zz;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
	/**yssxz*/
	@Excel(name = "yssxz", width = 15)
    @ApiModelProperty(value = "yssxz")
	private String yssxz;
	/**yxzc*/
	@Excel(name = "yxzc", width = 15)
    @ApiModelProperty(value = "yxzc")
	private String yxzc;
	/**zz1*/
	@Excel(name = "zz1", width = 15)
    @ApiModelProperty(value = "zz1")
	private String zz1;
	/**zz2*/
	@Excel(name = "zz2", width = 15)
    @ApiModelProperty(value = "zz2")
	private String zz2;
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
	/**xtpdjg*/
	@Excel(name = "xtpdjg", width = 15)
    @ApiModelProperty(value = "xtpdjg")
	private String xtpdjg;
	/**xtpdsm*/
	@Excel(name = "xtpdsm", width = 15)
    @ApiModelProperty(value = "xtpdsm")
	private String xtpdsm;
	/**gjrpdjg*/
	@Excel(name = "gjrpdjg", width = 15)
    @ApiModelProperty(value = "gjrpdjg")
	private String gjrpdjg;
	/**gjrpdsm*/
	@Excel(name = "gjrpdsm", width = 15)
    @ApiModelProperty(value = "gjrpdsm")
	private String gjrpdsm;
	/**zzpdjg*/
	@Excel(name = "zzpdjg", width = 15)
    @ApiModelProperty(value = "zzpdjg")
	private String zzpdjg;
	/**zzpdsm*/
	@Excel(name = "zzpdsm", width = 15)
    @ApiModelProperty(value = "zzpdsm")
	private String zzpdsm;
	/**sfycdg*/
	@Excel(name = "sfycdg", width = 15)
    @ApiModelProperty(value = "sfycdg")
	private String sfycdg;
	/**kfyyqk*/
	@Excel(name = "kfyyqk", width = 15)
    @ApiModelProperty(value = "kfyyqk")
	private String kfyyqk;
	/**kcqzyw*/
	@Excel(name = "kcqzyw", width = 15)
    @ApiModelProperty(value = "kcqzyw")
	private String kcqzyw;
	/**khsxqk*/
	@Excel(name = "khsxqk", width = 15)
    @ApiModelProperty(value = "khsxqk")
	private String khsxqk;
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
	private String longitude;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
	private String latitude;
	/**sign1*/
	@Excel(name = "sign1", width = 15)
    @ApiModelProperty(value = "sign1")
	private String sign1;
	/**sign2*/
	@Excel(name = "sign2", width = 15)
    @ApiModelProperty(value = "sign2")
	private String sign2;
	/**byhm*/
	@Excel(name = "byhm", width = 15)
    @ApiModelProperty(value = "byhm")
	private String byhm;
	/**pfr*/
	@Excel(name = "pfr", width = 15)
    @ApiModelProperty(value = "pfr")
	private String pfr;
	/**khlx1*/
	@Excel(name = "khlx1", width = 15)
    @ApiModelProperty(value = "khlx1")
	private String khlx1;
	/**khlx2*/
	@Excel(name = "khlx2", width = 15)
    @ApiModelProperty(value = "khlx2")
	private String khlx2;
	/**ywzn*/
	@Excel(name = "ywzn", width = 15)
    @ApiModelProperty(value = "ywzn")
	private String ywzn;
	/**jkzk*/
	@Excel(name = "jkzk", width = 15)
    @ApiModelProperty(value = "jkzk")
	private String jkzk;
	/**ldnl*/
	@Excel(name = "ldnl", width = 15)
    @ApiModelProperty(value = "ldnl")
	private String ldnl;
	/**jznx*/
	@Excel(name = "jznx", width = 15)
    @ApiModelProperty(value = "jznx")
	private String jznx;
	/**zgxl*/
	@Excel(name = "zgxl", width = 15)
    @ApiModelProperty(value = "zgxl")
	private String zgxl;
	/**zgxw*/
	@Excel(name = "zgxw", width = 15)
    @ApiModelProperty(value = "zgxw")
	private String zgxw;
	/**ddpzzhpj*/
	@Excel(name = "ddpzzhpj", width = 15)
    @ApiModelProperty(value = "ddpzzhpj")
	private String ddpzzhpj;
	/**jzzt*/
	@Excel(name = "jzzt", width = 15)
    @ApiModelProperty(value = "jzzt")
	private String jzzt;
	/**zjqfrq*/
	@Excel(name = "zjqfrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "zjqfrq")
	private Date zjqfrq;
	/**zjdqrq*/
	@Excel(name = "zjdqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "zjdqrq")
	private Date zjdqrq;
	/**fxsj*/
	@Excel(name = "fxsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "fxsj")
	private Date fxsj;
	/**sfsx*/
	@Excel(name = "sfsx", width = 15)
    @ApiModelProperty(value = "sfsx")
	private String sfsx;
	/**pyxxFlag*/
	@Excel(name = "pyxxFlag", width = 15)
    @ApiModelProperty(value = "pyxxFlag")
	private String pyxxFlag;
	/**khxxFlag*/
	@Excel(name = "khxxFlag", width = 15)
    @ApiModelProperty(value = "khxxFlag")
	private String khxxFlag;
	/**khglFlag*/
	@Excel(name = "khglFlag", width = 15)
    @ApiModelProperty(value = "khglFlag")
	private String khglFlag;
	/**signFlag*/
	@Excel(name = "signFlag", width = 15)
    @ApiModelProperty(value = "signFlag")
	private String signFlag;
	/**sskhjl*/
	@Excel(name = "sskhjl", width = 15)
    @ApiModelProperty(value = "sskhjl")
	private String sskhjl;
	/**gzdw*/
	@Excel(name = "gzdw", width = 15)
    @ApiModelProperty(value = "gzdw")
	private String gzdw;
	/**gzdwdz*/
	@Excel(name = "gzdwdz", width = 15)
    @ApiModelProperty(value = "gzdwdz")
	private String gzdwdz;
	/**qq*/
	@Excel(name = "qq", width = 15)
    @ApiModelProperty(value = "qq")
	private String qq;
	/**wgcs*/
	@Excel(name = "wgcs", width = 15)
    @ApiModelProperty(value = "wgcs")
	private String wgcs;
	/**sfxdry*/
	@Excel(name = "sfxdry", width = 15)
    @ApiModelProperty(value = "sfxdry")
	private String sfxdry;
	/**sign3*/
	@Excel(name = "sign3", width = 15)
    @ApiModelProperty(value = "sign3")
	private String sign3;
	/**sfsxdx*/
	@Excel(name = "sfsxdx", width = 15)
    @ApiModelProperty(value = "sfsxdx")
	private String sfsxdx;
	/**gddz*/
	@Excel(name = "gddz", width = 15)
    @ApiModelProperty(value = "gddz")
	private String gddz;
	/**zdyb*/
	@Excel(name = "zdyb", width = 15)
    @ApiModelProperty(value = "zdyb")
	private String zdyb;
	/**bysxqx*/
	@Excel(name = "bysxqx", width = 15)
    @ApiModelProperty(value = "bysxqx")
	private String bysxqx;
	/**cjzt*/
	@Excel(name = "cjzt", width = 15)
    @ApiModelProperty(value = "cjzt")
	private String cjzt;
	/**sjhyy*/
	@Excel(name = "sjhyy", width = 15)
    @ApiModelProperty(value = "sjhyy")
	private String sjhyy;
	/**cjwczt*/
	@Excel(name = "cjwczt", width = 15)
    @ApiModelProperty(value = "cjwczt")
	private String cjwczt;
	/**jtcsxm*/
	@Excel(name = "jtcsxm", width = 15)
    @ApiModelProperty(value = "jtcsxm")
	private String jtcsxm;
	/**wgdq*/
	@Excel(name = "wgdq", width = 15)
    @ApiModelProperty(value = "wgdq")
	private String wgdq;
	/**wglx*/
	@Excel(name = "wglx", width = 15)
    @ApiModelProperty(value = "wglx")
	private String wglx;
	/**khlx3*/
	@Excel(name = "khlx3", width = 15)
    @ApiModelProperty(value = "khlx3")
	private String khlx3;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private String lrbz;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**upDt*/
	@Excel(name = "upDt", width = 15)
    @ApiModelProperty(value = "upDt")
	private String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
    @ApiModelProperty(value = "upTm")
	private String upTm;
	/**zdyzbm*/
	@Excel(name = "zdyzbm", width = 15)
    @ApiModelProperty(value = "zdyzbm")
	private String zdyzbm;
	/**khblsh*/
	@Excel(name = "khblsh", width = 15)
    @ApiModelProperty(value = "khblsh")
	private String khblsh;
	/**ddpzzhpj1*/
	@Excel(name = "ddpzzhpj1", width = 15)
    @ApiModelProperty(value = "ddpzzhpj1")
	private String ddpzzhpj1;
	/**ddpzzhpj2*/
	@Excel(name = "ddpzzhpj2", width = 15)
    @ApiModelProperty(value = "ddpzzhpj2")
	private String ddpzzhpj2;
	/**qzywyysj*/
	@Excel(name = "qzywyysj", width = 15)
    @ApiModelProperty(value = "qzywyysj")
	private String qzywyysj;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**xb*/
	@Excel(name = "xb", width = 15)
    @ApiModelProperty(value = "xb")
	private String xb;
	/**nl*/
	@Excel(name = "nl", width = 15)
    @ApiModelProperty(value = "nl")
	private String nl;
	/**sfhz*/
	@Excel(name = "sfhz", width = 15)
    @ApiModelProperty(value = "sfhz")
	private String sfhz;
	/**sflqsbk*/
	@Excel(name = "sflqsbk", width = 15)
    @ApiModelProperty(value = "sflqsbk")
	private String sflqsbk;
	/**sfktsbk*/
	@Excel(name = "sfktsbk", width = 15)
    @ApiModelProperty(value = "sfktsbk")
	private String sfktsbk;
	/**hbjl*/
	@Excel(name = "hbjl", width = 15)
    @ApiModelProperty(value = "hbjl")
	private String hbjl;
	/**sfffjz*/
	@Excel(name = "sfffjz", width = 15)
    @ApiModelProperty(value = "sfffjz")
	private String sfffjz;
	/**sfgzry*/
	@Excel(name = "sfgzry", width = 15)
    @ApiModelProperty(value = "sfgzry")
	private String sfgzry;
	/**xdrzw*/
	@Excel(name = "xdrzw", width = 15)
    @ApiModelProperty(value = "xdrzw")
	private String xdrzw;
	/**hylb*/
	@Excel(name = "hylb", width = 15)
    @ApiModelProperty(value = "hylb")
	private String hylb;
	/**khfq*/
	@Excel(name = "khfq", width = 15)
    @ApiModelProperty(value = "khfq")
	private String khfq;
	/**cszy*/
	@Excel(name = "cszy", width = 15)
    @ApiModelProperty(value = "cszy")
	private String cszy;
	/**cynx*/
	@Excel(name = "cynx", width = 15)
    @ApiModelProperty(value = "cynx")
	private String cynx;
	/**qtwbsj1*/
	@Excel(name = "qtwbsj1", width = 15)
    @ApiModelProperty(value = "qtwbsj1")
	private String qtwbsj1;
	/**qtwbsj2*/
	@Excel(name = "qtwbsj2", width = 15)
    @ApiModelProperty(value = "qtwbsj2")
	private String qtwbsj2;
	/**qtwbsj3*/
	@Excel(name = "qtwbsj3", width = 15)
    @ApiModelProperty(value = "qtwbsj3")
	private String qtwbsj3;
	/**qtwbsj4*/
	@Excel(name = "qtwbsj4", width = 15)
    @ApiModelProperty(value = "qtwbsj4")
	private String qtwbsj4;
	/**qtwbsj5*/
	@Excel(name = "qtwbsj5", width = 15)
    @ApiModelProperty(value = "qtwbsj5")
	private String qtwbsj5;
	/**sjhmImport*/
	@Excel(name = "sjhmImport", width = 15)
    @ApiModelProperty(value = "sjhmImport")
	private String sjhmImport;
	/**ylhm*/
	@Excel(name = "ylhm", width = 15)
    @ApiModelProperty(value = "ylhm")
	private String ylhm;
	/**whss*/
	@Excel(name = "whss", width = 15)
    @ApiModelProperty(value = "whss")
	private String whss;
	/**zpry*/
	@Excel(name = "zpry", width = 15)
    @ApiModelProperty(value = "zpry")
	private String zpry;
	/**ffjz*/
	@Excel(name = "ffjz", width = 15)
    @ApiModelProperty(value = "ffjz")
	private String ffjz;
	/**sffx*/
	@Excel(name = "sffx", width = 15)
    @ApiModelProperty(value = "sffx")
	private String sffx;
	/**dkzsx*/
	@Excel(name = "dkzsx", width = 15)
    @ApiModelProperty(value = "dkzsx")
	private String dkzsx;
	/**bz1*/
	@Excel(name = "bz1", width = 15)
    @ApiModelProperty(value = "bz1")
	private String bz1;
	/**bz2*/
	@Excel(name = "bz2", width = 15)
    @ApiModelProperty(value = "bz2")
	private String bz2;
	/**sfsw*/
	@Excel(name = "sfsw", width = 15)
    @ApiModelProperty(value = "sfsw")
	private String sfsw;
	/**tlsj*/
	@Excel(name = "tlsj", width = 15)
    @ApiModelProperty(value = "tlsj")
	private Long tlsj;
	/**gdzcZfts*/
	@Excel(name = "gdzcZfts", width = 15)
    @ApiModelProperty(value = "gdzcZfts")
	private Integer gdzcZfts;
	/**gdzcZfmj*/
	@Excel(name = "gdzcZfmj", width = 15)
    @ApiModelProperty(value = "gdzcZfmj")
	private java.math.BigDecimal gdzcZfmj;
	/**gdzcZfjz*/
	@Excel(name = "gdzcZfjz", width = 15)
    @ApiModelProperty(value = "gdzcZfjz")
	private java.math.BigDecimal gdzcZfjz;
	/**gdzcQt*/
	@Excel(name = "gdzcQt", width = 15)
    @ApiModelProperty(value = "gdzcQt")
	private java.math.BigDecimal gdzcQt;
	/**gdzcHj*/
	@Excel(name = "gdzcHj", width = 15)
    @ApiModelProperty(value = "gdzcHj")
	private java.math.BigDecimal gdzcHj;
	/**ldzcXjjwhck*/
	@Excel(name = "ldzcXjjwhck", width = 15)
    @ApiModelProperty(value = "ldzcXjjwhck")
	private java.math.BigDecimal ldzcXjjwhck;
	/**ldzcQt*/
	@Excel(name = "ldzcQt", width = 15)
    @ApiModelProperty(value = "ldzcQt")
	private java.math.BigDecimal ldzcQt;
	/**ldzcYsk*/
	@Excel(name = "ldzcYsk", width = 15)
    @ApiModelProperty(value = "ldzcYsk")
	private java.math.BigDecimal ldzcYsk;
	/**ldzcSfthkh*/
	@Excel(name = "ldzcSfthkh", width = 15)
    @ApiModelProperty(value = "ldzcSfthkh")
	private String ldzcSfthkh;
	/**ldzcSfthyck*/
	@Excel(name = "ldzcSfthyck", width = 15)
    @ApiModelProperty(value = "ldzcSfthyck")
	private String ldzcSfthyck;
	/**ldzcThckje*/
	@Excel(name = "ldzcThckje", width = 15)
    @ApiModelProperty(value = "ldzcThckje")
	private java.math.BigDecimal ldzcThckje;
	/**ldzcHj*/
	@Excel(name = "ldzcHj", width = 15)
    @ApiModelProperty(value = "ldzcHj")
	private java.math.BigDecimal ldzcHj;
	/**zzchj*/
	@Excel(name = "zzchj", width = 15)
    @ApiModelProperty(value = "zzchj")
	private java.math.BigDecimal zzchj;
	/**fzWhjk*/
	@Excel(name = "fzWhjk", width = 15)
    @ApiModelProperty(value = "fzWhjk")
	private java.math.BigDecimal fzWhjk;
	/**fzQtyhjk*/
	@Excel(name = "fzQtyhjk", width = 15)
    @ApiModelProperty(value = "fzQtyhjk")
	private java.math.BigDecimal fzQtyhjk;
	/**fzSrjkhqtjk*/
	@Excel(name = "fzSrjkhqtjk", width = 15)
    @ApiModelProperty(value = "fzSrjkhqtjk")
	private java.math.BigDecimal fzSrjkhqtjk;
	/**fzYfk*/
	@Excel(name = "fzYfk", width = 15)
    @ApiModelProperty(value = "fzYfk")
	private java.math.BigDecimal fzYfk;
	/**fzQtfz*/
	@Excel(name = "fzQtfz", width = 15)
    @ApiModelProperty(value = "fzQtfz")
	private java.math.BigDecimal fzQtfz;
	/**fzWtrdb*/
	@Excel(name = "fzWtrdb", width = 15)
    @ApiModelProperty(value = "fzWtrdb")
	private java.math.BigDecimal fzWtrdb;
	/**fzHj*/
	@Excel(name = "fzHj", width = 15)
    @ApiModelProperty(value = "fzHj")
	private java.math.BigDecimal fzHj;
	/**jtjzc*/
	@Excel(name = "jtjzc", width = 15)
    @ApiModelProperty(value = "jtjzc")
	private java.math.BigDecimal jtjzc;
	/**srZz*/
	@Excel(name = "srZz", width = 15)
    @ApiModelProperty(value = "srZz")
	private java.math.BigDecimal srZz;
	/**srYz*/
	@Excel(name = "srYz", width = 15)
    @ApiModelProperty(value = "srYz")
	private java.math.BigDecimal srYz;
	/**srLw*/
	@Excel(name = "srLw", width = 15)
    @ApiModelProperty(value = "srLw")
	private java.math.BigDecimal srLw;
	/**srGsy*/
	@Excel(name = "srGsy", width = 15)
    @ApiModelProperty(value = "srGsy")
	private java.math.BigDecimal srGsy;
	/**srQtsr*/
	@Excel(name = "srQtsr", width = 15)
    @ApiModelProperty(value = "srQtsr")
	private java.math.BigDecimal srQtsr;
	/**srHj*/
	@Excel(name = "srHj", width = 15)
    @ApiModelProperty(value = "srHj")
	private java.math.BigDecimal srHj;
	/**zcJy*/
	@Excel(name = "zcJy", width = 15)
    @ApiModelProperty(value = "zcJy")
	private java.math.BigDecimal zcJy;
	/**zcYl*/
	@Excel(name = "zcYl", width = 15)
    @ApiModelProperty(value = "zcYl")
	private java.math.BigDecimal zcYl;
	/**zcYlao*/
	@Excel(name = "zcYlao", width = 15)
    @ApiModelProperty(value = "zcYlao")
	private java.math.BigDecimal zcYlao;
	/**zcSccb*/
	@Excel(name = "zcSccb", width = 15)
    @ApiModelProperty(value = "zcSccb")
	private java.math.BigDecimal zcSccb;
	/**zcRcsh*/
	@Excel(name = "zcRcsh", width = 15)
    @ApiModelProperty(value = "zcRcsh")
	private java.math.BigDecimal zcRcsh;
	/**zcQtzc*/
	@Excel(name = "zcQtzc", width = 15)
    @ApiModelProperty(value = "zcQtzc")
	private java.math.BigDecimal zcQtzc;
	/**zcHj*/
	@Excel(name = "zcHj", width = 15)
    @ApiModelProperty(value = "zcHj")
	private java.math.BigDecimal zcHj;
	/**jtjsr*/
	@Excel(name = "jtjsr", width = 15)
    @ApiModelProperty(value = "jtjsr")
	private java.math.BigDecimal jtjsr;
	/**shsyBlsh*/
	@Excel(name = "shsyBlsh", width = 15)
    @ApiModelProperty(value = "shsyBlsh")
	private String shsyBlsh;
	/**shsySfqk*/
	@Excel(name = "shsySfqk", width = 15)
    @ApiModelProperty(value = "shsySfqk")
	private String shsySfqk;
	/**shsySfygld*/
	@Excel(name = "shsySfygld", width = 15)
    @ApiModelProperty(value = "shsySfygld")
	private String shsySfygld;
	/**shsySfdjns*/
	@Excel(name = "shsySfdjns", width = 15)
    @ApiModelProperty(value = "shsySfdjns")
	private String shsySfdjns;
	/**shsySflqbz*/
	@Excel(name = "shsySflqbz", width = 15)
    @ApiModelProperty(value = "shsySflqbz")
	private String shsySflqbz;
	/**shsySfxsfz*/
	@Excel(name = "shsySfxsfz", width = 15)
    @ApiModelProperty(value = "shsySfxsfz")
	private String shsySfxsfz;
	/**shsySfss*/
	@Excel(name = "shsySfss", width = 15)
    @ApiModelProperty(value = "shsySfss")
	private String shsySfss;
	/**dhzpjPxpj*/
	@Excel(name = "dhzpjPxpj", width = 15)
    @ApiModelProperty(value = "dhzpjPxpj")
	private String dhzpjPxpj;
	/**dhzpjXypj*/
	@Excel(name = "dhzpjXypj", width = 15)
    @ApiModelProperty(value = "dhzpjXypj")
	private String dhzpjXypj;
	/**cpdj*/
	@Excel(name = "cpdj", width = 15)
    @ApiModelProperty(value = "cpdj")
	private String cpdj;
	/**xtpddj*/
	@Excel(name = "xtpddj", width = 15)
    @ApiModelProperty(value = "xtpddj")
	private String xtpddj;
	/**xtpddf*/
	@Excel(name = "xtpddf", width = 15)
    @ApiModelProperty(value = "xtpddf")
	private java.math.BigDecimal xtpddf;
	/**xtsxed*/
	@Excel(name = "xtsxed", width = 15)
    @ApiModelProperty(value = "xtsxed")
	private java.math.BigDecimal xtsxed;
	/**zzpddj*/
	@Excel(name = "zzpddj", width = 15)
    @ApiModelProperty(value = "zzpddj")
	private String zzpddj;
	/**zzpddf*/
	@Excel(name = "zzpddf", width = 15)
    @ApiModelProperty(value = "zzpddf")
	private java.math.BigDecimal zzpddf;
	/**zzsxed*/
	@Excel(name = "zzsxed", width = 15)
    @ApiModelProperty(value = "zzsxed")
	private java.math.BigDecimal zzsxed;
	/**bzt2*/
	@Excel(name = "bzt2", width = 15)
    @ApiModelProperty(value = "bzt2")
	private String bzt2;
	/**yj*/
	@Excel(name = "yj", width = 15)
    @ApiModelProperty(value = "yj")
	private String yj;
	/**qtbzqk*/
	@Excel(name = "qtbzqk", width = 15)
    @ApiModelProperty(value = "qtbzqk")
	private String qtbzqk;
	/**fzlx*/
	@Excel(name = "fzlx", width = 15)
    @ApiModelProperty(value = "fzlx")
	private String fzlx;
	/**sfjdlkpkh*/
	@Excel(name = "sfjdlkpkh", width = 15)
    @ApiModelProperty(value = "sfjdlkpkh")
	private String sfjdlkpkh;
	/**sxrq*/
	@Excel(name = "sxrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sxrq")
	private Date sxrq;
	/**spid*/
	@Excel(name = "spid", width = 15)
    @ApiModelProperty(value = "spid")
	private String spid;
	/**bysxqxt2*/
	@Excel(name = "bysxqxt2", width = 15)
    @ApiModelProperty(value = "bysxqxt2")
	private String bysxqxt2;
	/**xtpdyy*/
	@Excel(name = "xtpdyy", width = 15)
    @ApiModelProperty(value = "xtpdyy")
	private String xtpdyy;
//	private String procDefId;
//	private String procInstId;
	private Integer status;

	/**sxrq*/
	@Excel(name = "sxsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "sxsj")
	private Date sxsj;
	private BigDecimal dkje;
	private BigDecimal ckje;

	private BigDecimal cknrpye;
	private BigDecimal bldkye;
	private BigDecimal bwbldkye;

	private String snzzpddj;
	@Dict(dicCode = "ly_xend_cpzl")
	private String cpzl;
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String xdjgdm;
	private BigDecimal cpzlll;
	private BigDecimal snzzsxed;


	private String khjlqz;
	private String fxjlqz;
	private String zhhzqz;

	//年审分类
	@Dict(dicCode = "ly_xend_nsfl")
	private String nsfl;
	//年审分类原因
	private String nsflyy;

	//信贷系统授信金额
	private BigDecimal xdje;
	//信贷系统冻结授信金额
	private BigDecimal xddjje;
	//近三年存款日平
	private BigDecimal sanckrp;
	//逾期次数
	private BigDecimal yqcs;
}
