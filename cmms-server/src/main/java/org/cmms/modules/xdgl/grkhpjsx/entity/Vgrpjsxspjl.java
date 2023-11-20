package org.cmms.modules.xdgl.grkhpjsx.entity;

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
 * @Description: 评级授信审批记录视图
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Data
@TableName("v_cams_zcsx_grpjsxxx_spjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_cams_zcsx_grpjsxxx_spjl对象", description="评级授信审批记录视图")
public class Vgrpjsxspjl {
    
	/**sfjdlkpkh*/
	@Excel(name = "sfjdlkpkh", width = 15)
    @ApiModelProperty(value = "sfjdlkpkh")
	private String sfjdlkpkh;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
	/**yj*/
	@Excel(name = "yj", width = 15)
    @ApiModelProperty(value = "yj")
	private String yj;
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
	@Dict(dicCode = "pjsx_pddj")
	private String zzpddj;
	/**zzsxed*/
	@Excel(name = "zzsxed", width = 15)
    @ApiModelProperty(value = "zzsxed")
	private java.math.BigDecimal zzsxed;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
	private Integer status;
	/**procDefId*/
	@Excel(name = "procDefId", width = 15)
    @ApiModelProperty(value = "procDefId")
	private String procDefId;
	/**procInstId*/
	@Excel(name = "procInstId", width = 15)
    @ApiModelProperty(value = "procInstId")
	private String procInstId;
	/**bussinessId*/
	@Excel(name = "bussinessId", width = 15)
    @ApiModelProperty(value = "bussinessId")
	private String bussinessId;
	/**title*/
	@Excel(name = "title", width = 15)
    @ApiModelProperty(value = "title")
	private String title;
	/**dhqye*/
	@Excel(name = "dhqye", width = 15)
    @ApiModelProperty(value = "dhqye")
	private java.math.BigDecimal dhqye;
	/**crjye*/
	@Excel(name = "crjye", width = 15)
    @ApiModelProperty(value = "crjye")
	private java.math.BigDecimal crjye;
	/**zcjye*/
	@Excel(name = "zcjye", width = 15)
    @ApiModelProperty(value = "zcjye")
	private java.math.BigDecimal zcjye;
	/**ckzhs*/
	@Excel(name = "ckzhs", width = 15)
    @ApiModelProperty(value = "ckzhs")
	private Integer ckzhs;
	/**kdlqjls*/
	@Excel(name = "kdlqjls", width = 15)
    @ApiModelProperty(value = "kdlqjls")
	private Integer kdlqjls;
	/**wyjls*/
	@Excel(name = "wyjls", width = 15)
    @ApiModelProperty(value = "wyjls")
	private Integer wyjls;
	/**sjyhjls*/
	@Excel(name = "sjyhjls", width = 15)
    @ApiModelProperty(value = "sjyhjls")
	private Integer sjyhjls;
	/**etcjls*/
	@Excel(name = "etcjls", width = 15)
    @ApiModelProperty(value = "etcjls")
	private Integer etcjls;
	/**nxyjls*/
	@Excel(name = "nxyjls", width = 15)
    @ApiModelProperty(value = "nxyjls")
	private Integer nxyjls;
	/**wjfl*/
	@Excel(name = "wjfl", width = 15)
    @ApiModelProperty(value = "wjfl")
	private String wjfl;
	/**bwdk*/
	@Excel(name = "bwdk", width = 15)
    @ApiModelProperty(value = "bwdk")
	private java.math.BigDecimal bwdk;
	/**yqcs*/
	@Excel(name = "yqcs", width = 15)
    @ApiModelProperty(value = "yqcs")
	private Integer yqcs;
	/**sxed*/
	@Excel(name = "sxed", width = 15)
    @ApiModelProperty(value = "sxed")
	private java.math.BigDecimal sxed;
	/**zqje*/
	@Excel(name = "zqje", width = 15)
    @ApiModelProperty(value = "zqje")
	private java.math.BigDecimal zqje;
	/**sfxd*/
	@Excel(name = "sfxd", width = 15)
    @ApiModelProperty(value = "sfxd")
	private String sfxd;
	/**khsj*/
	@Excel(name = "khsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "khsj")
	private Date khsj;
	/**ckrp*/
	@Excel(name = "ckrp", width = 15)
    @ApiModelProperty(value = "ckrp")
	private java.math.BigDecimal ckrp;
	/**crjycs*/
	@Excel(name = "crjycs", width = 15)
    @ApiModelProperty(value = "crjycs")
	private Integer crjycs;
	/**zcjycs*/
	@Excel(name = "zcjycs", width = 15)
    @ApiModelProperty(value = "zcjycs")
	private Integer zcjycs;
	/**sfds*/
	@Excel(name = "sfds", width = 15)
    @ApiModelProperty(value = "sfds")
	private Integer sfds;
	/**spid*/
	@Excel(name = "spid", width = 15)
    @ApiModelProperty(value = "spid")
	private String spid;
	/**sqrq*/
	@Excel(name = "sqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sqrq")
	private Date sqrq;
	/**sqr*/
	@Excel(name = "sqr", width = 15)
    @ApiModelProperty(value = "sqr")
	private String sqr;
	/**userId*/
	@Excel(name = "userId", width = 15)
    @ApiModelProperty(value = "userId")
	private String userId;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**qydm*/
	@Excel(name = "qydm", width = 15)
    @ApiModelProperty(value = "qydm")
	@Dict(dicCode="wgbh", dictTable="V_YXDYGL_MAIN", dicText="wgmc_show")
	private String qydm;
	/**yjyxdybh*/
	@Excel(name = "yjyxdybh", width = 15)
    @ApiModelProperty(value = "yjyxdybh")
	private String yjyxdybh;
	/**ejyxdybh*/
	@Excel(name = "ejyxdybh", width = 15)
    @ApiModelProperty(value = "ejyxdybh")
	private String ejyxdybh;
	/**sjyxdybh*/
	@Excel(name = "sjyxdybh", width = 15)
    @ApiModelProperty(value = "sjyxdybh")
	private String sjyxdybh;
	/**sskhjl*/
	@Excel(name = "sskhjl", width = 15)
    @ApiModelProperty(value = "sskhjl")
	private String sskhjl;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**yhzgx*/
	@Excel(name = "yhzgx", width = 15)
    @ApiModelProperty(value = "yhzgx")
	private String yhzgx;
	/**khlx1*/
	@Excel(name = "khlx1", width = 15)
    @ApiModelProperty(value = "khlx1")
	private String khlx1;
	/**sjhm*/
	@Excel(name = "sjhm", width = 15)
    @ApiModelProperty(value = "sjhm")
	private String sjhm;
	/**xb*/
	@Excel(name = "xb", width = 15)
    @ApiModelProperty(value = "xb")
	private String xb;
	/**zz*/
	@Excel(name = "zz", width = 15)
    @ApiModelProperty(value = "zz")
	private String zz;
	/**hyzk*/
	@Excel(name = "hyzk", width = 15)
    @ApiModelProperty(value = "hyzk")
	private String hyzk;
	/**cszy*/
	@Excel(name = "cszy", width = 15)
    @ApiModelProperty(value = "cszy")
	private String cszy;
	/**khlx*/
	@Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
	private String khlx;
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
	/**zzchj*/
	@Excel(name = "zzchj", width = 15)
    @ApiModelProperty(value = "zzchj")
	private java.math.BigDecimal zzchj;
	/**jtjzc*/
	@Excel(name = "jtjzc", width = 15)
    @ApiModelProperty(value = "jtjzc")
	private java.math.BigDecimal jtjzc;
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
	/**qtbzqk*/
	@Excel(name = "qtbzqk", width = 15)
    @ApiModelProperty(value = "qtbzqk")
	private String qtbzqk;
	/**fzlx*/
	@Excel(name = "fzlx", width = 15)
    @ApiModelProperty(value = "fzlx")
	private String fzlx;


	@Excel(name = "是否开通存款", width = 15)
	@ApiModelProperty(value = "是否开通存款")
	@Dict(dicCode = "sfbz")
	private String sfktckyw;

	@Excel(name = "是否开通贷款", width = 15)
	@ApiModelProperty(value = "是否开通贷款")
	@Dict(dicCode = "sfbz")
	private String sfktdkyw;


	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;

	@Excel(name = "存款月日平", width = 15)
	@ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckrpye;

	@Excel(name = "存款年日平", width = 15)
	@ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal cknrpye;


	@Excel(name = "贷款金额", width = 15)
	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;

	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;

	@Excel(name = "不良贷款余额", width = 15)
	@ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;

	@Excel(name = "表外不良贷款", width = 15)
	@ApiModelProperty(value = "表外不良贷款")
	private java.math.BigDecimal bwbldkye;

	@Excel(name = "是否开通手机银行", width = 15)
	@ApiModelProperty(value = "是否开通手机银行")
	@Dict(dicCode = "sfbz")
	private String sfktsjyhyw;

	@Excel(name = "是否开通网上银行", width = 15)
	@ApiModelProperty(value = "是否开通网上银行")
	@Dict(dicCode = "sfbz")
	private String sfktwsyhyw;

	@Excel(name = "是否开通社保卡", width = 15)
	@ApiModelProperty(value = "是否开通社保卡")
	@Dict(dicCode = "sfbz")
	private String sfktsbk;

	@Excel(name = "是否办理ETC", width = 15)
	@ApiModelProperty(value = "是否办理ETC")
	@Dict(dicCode = "sfbz")
	private String sfbletcyw;

	private String riskLevel;
	
	private java.math.BigDecimal xdxtsxje;

}
