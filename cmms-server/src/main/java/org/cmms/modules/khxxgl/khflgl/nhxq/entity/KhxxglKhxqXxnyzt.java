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
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2022-12-06
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_XXNYZT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_XXNYZT", description="新型农业主体")
public class KhxxglKhxqXxnyzt {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**主体分类*/
	@Excel(name = "主体分类", width = 15)
    @ApiModelProperty(value = "主体分类")
	@Dict(dicCode = "xxnyzt-ztfl")
	private String ztfl;
	/**新型主体名称*/
	@Excel(name = "新型主体名称", width = 15)
    @ApiModelProperty(value = "新型主体名称")
	private String xxztmc;
	/**新型主体证件号码*/
	@Excel(name = "新型主体证件号码", width = 15)
    @ApiModelProperty(value = "新型主体证件号码")
	private String xxztzjhm;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
    @ApiModelProperty(value = "注册地址")
	private String zcdz;
	/**经营者*/
	@Excel(name = "经营者", width = 15)
    @ApiModelProperty(value = "经营者")
	private String jyz;
	/**经营者证件号码*/
	@Excel(name = "经营者证件号码", width = 15)
    @ApiModelProperty(value = "经营者证件号码")
	private String jyzzjhm;
	/**经营者电话*/
	@Excel(name = "经营者电话", width = 15)
    @ApiModelProperty(value = "经营者电话")
	private String jyzdh;
	/**经营项目*/
	@Excel(name = "经营项目", width = 15)
    @ApiModelProperty(value = "经营项目")
	private String jyxm;
	/**经营规模*/
	@Excel(name = "经营规模", width = 15)
    @ApiModelProperty(value = "经营规模")
	private String jygm;
	/**年收入*/
	@Excel(name = "年收入", width = 15)
    @ApiModelProperty(value = "年收入")
	private Integer nsr;
	/**是否有信贷需求*/
	@Excel(name = "是否有信贷需求", width = 15)
    @ApiModelProperty(value = "是否有信贷需求")
	@Dict(dicCode = "sfbz")
	private String sfyxdxq;
	/**管户责任人*/
	@Excel(name = "管户责任人", width = 15)
    @ApiModelProperty(value = "管户责任人")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String ghzrr;
	/**支行初筛*/
	@Excel(name = "支行初筛", width = 15)
    @ApiModelProperty(value = "支行初筛")
	private String zhcs;
	/**已授信对象*/
	@Excel(name = "已授信对象", width = 15)
    @ApiModelProperty(value = "已授信对象")
	private String ysxdx;
	/**已授信证件号码*/
	@Excel(name = "已授信证件号码", width = 15)
    @ApiModelProperty(value = "已授信证件号码")
	private String ysxzjhm;
	/**已授信额度*/
	@Excel(name = "已授信额度", width = 15)
    @ApiModelProperty(value = "已授信额度")
	private Integer ysxed;
	/**诚信评级*/
	@Excel(name = "诚信评级", width = 15)
    @ApiModelProperty(value = "诚信评级")
	@Dict(dicCode = "xxnyzt-cxpj")
	private String cxpj;
	/**管理水平*/
	@Excel(name = "管理水平", width = 15)
    @ApiModelProperty(value = "管理水平")
	@Dict(dicCode = "xxnyzt-cxpj")
	private String glsp;
	/**是否遵纪守法*/
	@Excel(name = "是否遵纪守法", width = 15)
    @ApiModelProperty(value = "是否遵纪守法")
	@Dict(dicCode = "sfbz")
	private String sfzjsf;
	/**评定分类*/
	@Excel(name = "评定分类", width = 15)
    @ApiModelProperty(value = "评定分类")
	@Dict(dicCode = "xxnyzt-cxpj")
	private String pdfl;
	/**资产*/
	@Excel(name = "资产", width = 15)
    @ApiModelProperty(value = "资产")
	private Integer zc;
	/**负债*/
	@Excel(name = "负债", width = 15)
    @ApiModelProperty(value = "负债")
	private Integer fz;
	/**年支出*/
	@Excel(name = "年支出", width = 15)
    @ApiModelProperty(value = "年支出")
	private Integer nzc;
	/**支行分类*/
	@Excel(name = "支行分类", width = 15)
    @ApiModelProperty(value = "支行分类")
	@Dict(dicCode = "xxnyzt-zhfl")
	private String zhfl;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private Integer sxed;
	/**授信对象证件号*/
	@Excel(name = "授信对象证件号", width = 15)
    @ApiModelProperty(value = "授信对象证件号")
	private String sxdxzjhm;
	/**授信对象电话*/
	@Excel(name = "授信对象电话", width = 15)
    @ApiModelProperty(value = "授信对象电话")
	private String sxdzdh;
	/**是否签约*/
	@Excel(name = "是否签约", width = 15)
    @ApiModelProperty(value = "是否签约")
	@Dict(dicCode = "sfbz")
	private String sfqy;
	/**导入惠农快贷系统情况*/
	@Excel(name = "导入惠农快贷系统情况", width = 15)
    @ApiModelProperty(value = "导入惠农快贷系统情况")
	private String drhnkdxtqk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**实际控制人*/
	@Excel(name = "实际控制人", width = 15)
    @ApiModelProperty(value = "实际控制人")
	private String sjkzr;
	/**实际控制人证件号码*/
	@Excel(name = "实际控制人证件号码", width = 15)
    @ApiModelProperty(value = "实际控制人证件号码")
	private String sjkzrzjhm;
	/**实际控制人电话*/
	@Excel(name = "实际控制人电话", width = 15)
    @ApiModelProperty(value = "实际控制人电话")
	private String sjkzrdh;
	/**时间控制人hhbm*/
	@Excel(name = "时间控制人hhbm", width = 15)
    @ApiModelProperty(value = "时间控制人hhbm")
	private String sjkzrhhbm;
	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**陪访人证件号码*/
	@Excel(name = "陪访人证件号码", width = 15)
    @ApiModelProperty(value = "陪访人证件号码")
	private String pfrzjhm;
	/**陪访人电话*/
	@Excel(name = "陪访人电话", width = 15)
    @ApiModelProperty(value = "陪访人电话")
	private String pfrdh;
	/**陪访人职务*/
	@Excel(name = "陪访人职务", width = 15)
    @ApiModelProperty(value = "陪访人职务")
	private String pfrzw;
	/**是否已授信*/
	@Excel(name = "是否已授信", width = 15)
    @ApiModelProperty(value = "是否已授信")
	@Dict(dicCode = "sfbz")
	private String sfysx;
	@Dict(dicCode = "sfbz")
	private String sfzcjy;
	private String wzcjyyzr;
	private String wzcjyyzrdh;
	@TableField(exist = false)
	private String pyyKhid;
	@TableField(exist = false)
	private String pyyid;
	@TableField(exist = false)
	private String pyyxm;
	@TableField(exist = false)
	private String pyyzjhm;


	@Excel(name = "已授信对象", width = 15)
	@ApiModelProperty(value = "已授信对象")
	private String ysxdxcj;
	/**已授信证件号码*/
	@Excel(name = "已授信证件号码", width = 15)
	@ApiModelProperty(value = "已授信证件号码")
	private String ysxzjhmcj;
	private String jcxxbz;
	@Dict(dicCode = "sfbz")
	private String sfhmdkh;
	@Dict(dicCode = "xxnyzt-sxqx")
	private String sxqx;
	/**已授信额度*/
	@Excel(name = "已授信额度", width = 15)
	@ApiModelProperty(value = "已授信额度")
	private BigDecimal ysxedcj;
	private BigDecimal jtgdzc;
	private BigDecimal jyxgzc;
	private BigDecimal qtzc;
	private BigDecimal yhfz;
	private BigDecimal qtfz;
	private BigDecimal nsxedck;
	@Dict(dicCode = "xxnyzt-hyzk")
	private String hyzk;
	private String poxm;
	private String pozjhm;
	private String huimdly;
	//用来做查选条件
	@TableField(exist = false)
	private String bmdysx;
	private String dczrr;

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	private Date czpywcsj;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	private Date rhhdwcsj;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	private Date yxzfwcsj;

	@ApiModelProperty(value = "是否新型农业主体有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	@ApiModelProperty(value = "是否操作存量客户筛选")
	private String sfczclkhsx;
	@ApiModelProperty(value = "经度")
	private String longitude;
	@ApiModelProperty(value = "纬度")
	private String latitude;

	@TableField(exist = false)
	private String qybm;

	@ApiModelProperty(value = "贷款需求金额")
	private Integer dkxqje;
	private String hmdly;

	//永兴-新型农业主体-村组评定
	/**健康状态情况(1.较差；2.一般；3.良好)*/
	@Excel(name = "健康状态情况", width = 15, dicCode = "bkbpy_qkms")
	@ApiModelProperty(value = "健康状态情况(1.较差；2.一般；3.良好)")
	@Dict(dicCode = "bkbpy_qkms")
	private String jkztqk;
	/**房屋价值情况*/
	@Excel(name = "房屋价值情况", width = 15, dicCode = "bkbpy_fwjzqk")
	@ApiModelProperty(value = "房屋价值情况")
	@Dict(dicCode = "bkbpy_fwjzqk")
	private String fwjzqk;
	/**家庭纯收入情况*/
	@Excel(name = "家庭纯收入情况", width = 15, dicCode = "bkbpy_jtcsrqk")
	@ApiModelProperty(value = "家庭纯收入情况")
	@Dict(dicCode = "bkbpy_jtcsrqk")
	private String jtcsrqk;
	/**信誉状况(1.较差；2.一般；3.良好)*/
	@Excel(name = "信誉状况", width = 15, dicCode = "bkbpy_qkms")
	@ApiModelProperty(value = "信誉状况(1.较差；2.一般；3.良好)")
	@Dict(dicCode = "bkbpy_qkms")
	private String xyzk;
	/**家庭负债情况(1.无负债；2.少量负债；3.较高负债)*/
	@Excel(name = "家庭负债情况", width = 15, dicCode = "py_jtfzqk")
	@ApiModelProperty(value = "家庭负债情况(1.无负债；2.少量负债；3.较高负债)")
	@Dict(dicCode = "py_jtfzqk")
	private String jtfzqk;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx")
	@ApiModelProperty(value = "不予授信情形")
	@Dict(dicCode = "py_bysxqx_ls", dicCodeQybm = "405=py_bysxqx_ny|310=py_bysxqx_sf|320=py_bysxqx_sf|095=py_bysxqx_ty")
	private String bysxqx;
	/**评议得分*/
	@Excel(name = "评议得分", width = 15)
	@ApiModelProperty(value = "评议得分")
	private String pydf;
	/**诚信度(1.很好；2.较好；3.一般；4.差)*/
	@Excel(name = "诚信度", width = 15, dicCode = "bkbpy_xydj")
	@ApiModelProperty(value = "诚信度(1.很好；2.较好；3.一般；4.差)")
	@Dict(dicCode = "bkbpy_xydj")
	private String cxd;
	/**是否注销*/
	@Excel(name = "是否注销", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否注销")
	@Dict(dicCode = "sfbz")
	private String sfzx;
}
