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
 * @Description: 惠农快贷
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_HNKD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_HNKD对象", description="惠农快贷")
public class KhxxglHnkd {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**额度(单位：元)*/
	@Excel(name = "额度(单位：元)", width = 15)
    @ApiModelProperty(value = "额度(单位：元)")
	private java.math.BigDecimal sxed;
	/**基点*/
	@Excel(name = "基点", width = 15)
    @ApiModelProperty(value = "基点")
	private java.math.BigDecimal jd;
	/**授信期限（月）*/
	@Excel(name = "授信期限（月）", width = 15)
    @ApiModelProperty(value = "授信期限（月）")
	private Integer sxqx;
	/**客户经理工号*/
	@Excel(name = "客户经理工号", width = 15)
    @ApiModelProperty(value = "客户经理工号")
	//@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String khjl;
	/**调查责任人1工号*/
	@Excel(name = "调查责任人1工号", width = 15)
    @ApiModelProperty(value = "调查责任人1工号")
	private String dczrr1;
	/**调查责任人1比例*/
	@Excel(name = "调查责任人1比例", width = 15)
    @ApiModelProperty(value = "调查责任人1比例")
	private java.math.BigDecimal dczrr1bl;
	/**调查责任人2工号*/
	@Excel(name = "调查责任人2工号", width = 15)
    @ApiModelProperty(value = "调查责任人2工号")
	private String dczrr2;
	/**调查责任人2比例*/
	@Excel(name = "调查责任人2比例", width = 15)
    @ApiModelProperty(value = "调查责任人2比例")
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
	/**是否发送短信提醒*/
	@Excel(name = "是否发送短信提醒", width = 15)
    @ApiModelProperty(value = "是否发送短信提醒")
	private String sffsdx;
	/**经营情况是否正常*/
	@Excel(name = "经营情况是否正常", width = 15)
    @ApiModelProperty(value = "经营情况是否正常")
	private String jyqk;
	/**产品市场需求情况*/
	@Excel(name = "产品市场需求情况", width = 15)
    @ApiModelProperty(value = "产品市场需求情况")
	private String scxu;
	/**借款人还款意愿*/
	@Excel(name = "借款人还款意愿", width = 15)
    @ApiModelProperty(value = "借款人还款意愿")
	private String hkyy;
	/**综合评价标志*/
	@Excel(name = "综合评价标志", width = 15)
    @ApiModelProperty(value = "综合评价标志")
	private String pjbz;
	/**循环标志*/
	@Excel(name = "循环标志", width = 15)
    @ApiModelProperty(value = "循环标志")
	private String xhbz;
	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
    @ApiModelProperty(value = "信用等级")
	private String xydj;
	/**个体工商户名称*/
	@Excel(name = "个体工商户名称", width = 15)
    @ApiModelProperty(value = "个体工商户名称")
	private String gtgshmc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String xydm;
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
	/**配偶证件号码*/
	@Excel(name = "配偶电话", width = 15)
	@ApiModelProperty(value = "配偶电话")
	private String podh;
	/**家庭人口*/
	@Excel(name = "家庭人口", width = 15)
    @ApiModelProperty(value = "家庭人口")
	private Integer jtrk;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String txdz;
	/**个人年收入*/
	@Excel(name = "个人年收入", width = 15)
    @ApiModelProperty(value = "个人年收入")
	private Integer grnsr;
	/**家庭年收入*/
	@Excel(name = "家庭年收入", width = 15)
    @ApiModelProperty(value = "家庭年收入")
	private Integer jtnsr;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String zc;
	/**1白名单 2红名单 3黑名单 4外出务工名称*/
	@Excel(name = "1白名单 2红名单 3黑名单 4外出务工名称", width = 15)
    @ApiModelProperty(value = "1白名单 2红名单 3黑名单 4外出务工名称")
	@Dict(dicCode = "hnkd_khlxmd")
	private String khlx;
	/**是否外出打工*/
	@Excel(name = "是否外出打工", width = 15)
    @ApiModelProperty(value = "是否外出打工")
	private String sfycdg;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")
	private String ssxz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户籍所在地行政区划*/
	@Excel(name = "户籍所在地行政区划", width = 15)
    @ApiModelProperty(value = "户籍所在地行政区划")
	private String hjszdxzqh;
	/**通讯地址邮编*/
	@Excel(name = "通讯地址邮编", width = 15)
    @ApiModelProperty(value = "通讯地址邮编")
	private String txdzyb;
	/**就业状况*/
	@Excel(name = "就业状况", width = 15)
    @ApiModelProperty(value = "就业状况")
	private String jyzk;
	/**单位性质*/
	@Excel(name = "单位性质", width = 15)
    @ApiModelProperty(value = "单位性质")
	private String dwxz;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String zgxw;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String khlx2;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
    @ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hnkd_hyzk",dicCodeQybm = "095=hyzk")
	private String hyzk;
	/**劳动能力*/
	@Excel(name = "劳动能力", width = 15)
    @ApiModelProperty(value = "劳动能力")
	private String ldnl;
	/**居住年限*/
	@Excel(name = "居住年限", width = 15)
    @ApiModelProperty(value = "居住年限")
	private String jznx;
	/**居住状况*/
	@Excel(name = "居住状况", width = 15)
    @ApiModelProperty(value = "居住状况")
	private String jzzk;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String zgxl;
	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
	private String czdz;
	/**驻地邮政编码*/
	@Excel(name = "驻地邮政编码", width = 15)
    @ApiModelProperty(value = "驻地邮政编码")
	private String zdyzbm;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**现工作单位*/
	@Excel(name = "现工作单位", width = 15)
    @ApiModelProperty(value = "现工作单位")
	private String xgzdw;
	/**现担任职务*/
	@Excel(name = "现担任职务", width = 15)
    @ApiModelProperty(value = "现担任职务")
	private String xdrzw;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String zy;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
    @ApiModelProperty(value = "从业年限")
	private String cynx;
	/**综合评价*/
	@Excel(name = "综合评价", width = 15)
    @ApiModelProperty(value = "综合评价")
	private String zhpj;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**户籍所在*/
	@Excel(name = "户籍所在", width = 15)
    @ApiModelProperty(value = "户籍所在")
	private String hjsz;
	private String poid;
	private String impResult;
	private String khfq;
	private String xzqhdm;
	private String hy;
	private String hjyzbm;
	private String txdjyb;
	private String sfgxxfb;
	private String xsm;

	@TableField(exist = false)
	@Dict(dicCode = "sfbz")
	private String sfdrhnkd;
	@TableField(exist = false)
	private BigDecimal dred;



	
}
