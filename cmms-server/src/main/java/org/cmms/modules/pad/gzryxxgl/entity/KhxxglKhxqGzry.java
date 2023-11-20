package org.cmms.modules.pad.gzryxxgl.entity;

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
 * @Description: 公职人员信息管理
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_GZRY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_GZRY对象", description="公职人员信息管理")
public class KhxxglKhxqGzry {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")
	private String ssxz;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15)
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "归属网格")
	private String wgbh;
	/**所属支行*/
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**公职人员姓名*/
	@Excel(name = "公职人员姓名", width = 15)
    @ApiModelProperty(value = "公职人员姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private String hkxz;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15)
    @ApiModelProperty(value = "客户重要程度")
	private String khzycd;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15)
    @ApiModelProperty(value = "客户授信情况")
	private String khsxqk;
	/**客户等级*/
	@Excel(name = "客户等级", width = 15)
    @ApiModelProperty(value = "客户等级")
	private String khdj;
	/**客户风险度*/
	@Excel(name = "客户风险度", width = 15)
    @ApiModelProperty(value = "客户风险度")
	private String khfxd;
	/**是否有不良贷款*/
	@Excel(name = "是否有不良贷款", width = 15)
    @ApiModelProperty(value = "是否有不良贷款")
	private String sfybldk;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15)
    @ApiModelProperty(value = "是否低保户")
	private String sfdbh;
	/**是否惠农补贴户*/
	@Excel(name = "是否惠农补贴户", width = 15)
    @ApiModelProperty(value = "是否惠农补贴户")
	private String sfhnbth;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private String khqzyw;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**他行业务情况*/
	@Excel(name = "他行业务情况", width = 15)
    @ApiModelProperty(value = "他行业务情况")
	private String thywqk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**录入位置*/
	@Excel(name = "录入位置", width = 15)
    @ApiModelProperty(value = "录入位置")
	private String lrwz;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**预留号码*/
	@Excel(name = "预留号码", width = 15)
    @ApiModelProperty(value = "预留号码")
	private String ylhm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**是否发送短信*/
	@Excel(name = "是否发送短信", width = 15)
    @ApiModelProperty(value = "是否发送短信")
	private String sffsdx;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**有无子女*/
	@Excel(name = "有无子女", width = 15)
    @ApiModelProperty(value = "有无子女")
	private String ywzn;
	/**配偶姓名*/
	@Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
	private String poxm;
	/**配偶证件号码*/
	@Excel(name = "配偶证件号码", width = 15)
    @ApiModelProperty(value = "配偶证件号码")
	private String pozjhm;
	/**家庭人口*/
	@Excel(name = "家庭人口", width = 15)
    @ApiModelProperty(value = "家庭人口")
	private Integer jtrk;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String zkzt;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15)
    @ApiModelProperty(value = "劳动能力")
	private String ldnl;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String zgxl;
	/**居住状态*/
	@Excel(name = "居住状态", width = 15)
    @ApiModelProperty(value = "居住状态")
	private String jzzt;
	/**居住年限*/
	@Excel(name = "居住年限", width = 15)
    @ApiModelProperty(value = "居住年限")
	private String jznx;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String zgxw;
	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
	private String czdz;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**住地邮政编码*/
	@Excel(name = "住地邮政编码", width = 15)
    @ApiModelProperty(value = "住地邮政编码")
	private String zdyzbm;
	/**通讯地址邮编*/
	@Excel(name = "通讯地址邮编", width = 15)
    @ApiModelProperty(value = "通讯地址邮编")
	private String txdzbm;
	/**个人年收入*/
	@Excel(name = "个人年收入", width = 15)
    @ApiModelProperty(value = "个人年收入")
	private Integer grnsr;
	/**家庭年收入*/
	@Excel(name = "家庭年收入", width = 15)
    @ApiModelProperty(value = "家庭年收入")
	private Integer jtnsr;
	/**借款人还款意愿*/
	@Excel(name = "借款人还款意愿", width = 15)
    @ApiModelProperty(value = "借款人还款意愿")
	private String jkrhkyy;
	/**综合评价标志*/
	@Excel(name = "综合评价标志", width = 15)
    @ApiModelProperty(value = "综合评价标志")
	private String zhpjbz;
	/**循环标志*/
	@Excel(name = "循环标志", width = 15)
    @ApiModelProperty(value = "循环标志")
	private String xhbz;
	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
    @ApiModelProperty(value = "信用等级")
	private String xydj;
	/**客户分群*/
	@Excel(name = "客户分群", width = 15)
    @ApiModelProperty(value = "客户分群")
	private String khfq;
	/**客户类型Ⅰ*/
	@Excel(name = "客户类型Ⅰ", width = 15)
    @ApiModelProperty(value = "客户类型Ⅰ")
	private String khlx1;
	/**客户类型Ⅱ*/
	@Excel(name = "客户类型Ⅱ", width = 15)
    @ApiModelProperty(value = "客户类型Ⅱ")
	private String khlx2;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业")
	private String hy;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
    @ApiModelProperty(value = "从业年限")
	private String chnx;
	/**现工作单位*/
	@Excel(name = "现工作单位", width = 15)
    @ApiModelProperty(value = "现工作单位")
	private String xgzdw;
	/**单位性质*/
	@Excel(name = "单位性质", width = 15)
    @ApiModelProperty(value = "单位性质")
	private String dwxz;
	/**就业状况*/
	@Excel(name = "就业状况", width = 15)
    @ApiModelProperty(value = "就业状况")
	private String jyzk;
	/**现担任职务*/
	@Excel(name = "现担任职务", width = 15)
    @ApiModelProperty(value = "现担任职务")
	private String xdrzw;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String zy;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String zc;
	/**经营情况是否正常*/
	@Excel(name = "经营情况是否正常", width = 15)
    @ApiModelProperty(value = "经营情况是否正常")
	private String jyqksfzc;
	/**产品市场需求情况*/
	@Excel(name = "产品市场需求情况", width = 15)
    @ApiModelProperty(value = "产品市场需求情况")
	private String cpscxqqk;
	/**额度*/
	@Excel(name = "额度", width = 15)
    @ApiModelProperty(value = "额度")
	private java.math.BigDecimal ed;
	/**基点*/
	@Excel(name = "基点", width = 15)
    @ApiModelProperty(value = "基点")
	private java.math.BigDecimal jd;
	/**授信期限（月）*/
	@Excel(name = "授信期限（月）", width = 15)
    @ApiModelProperty(value = "授信期限（月）")
	private Integer sxqxy;
	/**客户经理工号*/
	@Excel(name = "客户经理工号", width = 15)
    @ApiModelProperty(value = "客户经理工号")
	private String khjlgh;
	/**调查责任人一工号*/
	@Excel(name = "调查责任人一工号", width = 15)
    @ApiModelProperty(value = "调查责任人一工号")
	private String dczrr1;
	/**调查责任人一比例*/
	@Excel(name = "调查责任人一比例", width = 15)
    @ApiModelProperty(value = "调查责任人一比例")
	private java.math.BigDecimal dczrr1bl;
	/**调查责任人二工号*/
	@Excel(name = "调查责任人二工号", width = 15)
    @ApiModelProperty(value = "调查责任人二工号")
	private String dczrr2;
	/**调查责任人二比例*/
	@Excel(name = "调查责任人二比例", width = 15)
    @ApiModelProperty(value = "调查责任人二比例")
	private java.math.BigDecimal dczrr2bl;
	/**管理责任人工号*/
	@Excel(name = "管理责任人工号", width = 15)
    @ApiModelProperty(value = "管理责任人工号")
	private String glzrr;
	/**管理责任人比例*/
	@Excel(name = "管理责任人比例", width = 15)
    @ApiModelProperty(value = "管理责任人比例")
	private java.math.BigDecimal glzrrbl;
	/**审查责任人工号*/
	@Excel(name = "审查责任人工号", width = 15)
    @ApiModelProperty(value = "审查责任人工号")
	private String sczrr;
	/**审查责任人比例*/
	@Excel(name = "审查责任人比例", width = 15)
    @ApiModelProperty(value = "审查责任人比例")
	private java.math.BigDecimal sczrrbl;
	/**最终审批责任人工号*/
	@Excel(name = "最终审批责任人工号", width = 15)
    @ApiModelProperty(value = "最终审批责任人工号")
	private String zzspzrr;
	/**最终审批责任人比例*/
	@Excel(name = "最终审批责任人比例", width = 15)
    @ApiModelProperty(value = "最终审批责任人比例")
	private java.math.BigDecimal zzspzrrbl;
	/**第一责任人工号*/
	@Excel(name = "第一责任人工号", width = 15)
    @ApiModelProperty(value = "第一责任人工号")
	private String dyzrr;
	/**第一责任人比例*/
	@Excel(name = "第一责任人比例", width = 15)
    @ApiModelProperty(value = "第一责任人比例")
	private java.math.BigDecimal dyzrrbl;
	/**我行诉讼*/
	@Excel(name = "我行诉讼", width = 15)
    @ApiModelProperty(value = "我行诉讼")
	private String whss;
	/**重大疾病*/
	@Excel(name = "重大疾病", width = 15)
    @ApiModelProperty(value = "重大疾病")
	private String zdjb;
	/**诈骗人员*/
	@Excel(name = "诈骗人员", width = 15)
    @ApiModelProperty(value = "诈骗人员")
	private String zpry;
	/**非法集资*/
	@Excel(name = "非法集资", width = 15)
    @ApiModelProperty(value = "非法集资")
	private String ffjz;
	/**是否吸毒*/
	@Excel(name = "是否吸毒", width = 15)
    @ApiModelProperty(value = "是否吸毒")
	private String sfxd;
	/**是否服刑*/
	@Excel(name = "是否服刑", width = 15)
    @ApiModelProperty(value = "是否服刑")
	private String sffx;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15)
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	/**评级授信额度*/
	@Excel(name = "评级授信额度", width = 15)
	@ApiModelProperty(value = "评级授信额度")
	private java.math.BigDecimal pjsxed;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
	@ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15)
	@ApiModelProperty(value = "是否授信")
	@Dict(dicCode = "sfbz")
	private String sfsx;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15)
	@ApiModelProperty(value = "是否采集")
	private String sfcj;
	/**采集人*/
	@Excel(name = "采集人", width = 15)
	@ApiModelProperty(value = "采集人")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String cjr;
	/**是否走访*/
	@Excel(name = "是否走访", width = 15)
	@ApiModelProperty(value = "是否走访")
	@Dict(dicCode = "sfbz")
	private String sfzf;
}
