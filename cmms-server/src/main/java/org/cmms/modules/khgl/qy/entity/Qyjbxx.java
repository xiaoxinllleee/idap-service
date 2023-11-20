package org.cmms.modules.khgl.qy.entity;

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
 * @Description: 企业客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-02-15
 * @Version: V1.0
 */
@Data
@TableName("KHGL_QYJBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_QYJBXX对象", description="企业客户信息管理")
public class Qyjbxx {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="QYBM",dictTable="V_CZXXGL",dicText="VILLAGE")
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode="QYBM",dictTable="V_CZXXGL",dicText="VILLAGE")
	private String ssyxdy;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**企业法人*/
	@Excel(name = "企业法人", width = 15)
    @ApiModelProperty(value = "企业法人")
	private String qyfr;
	/**法人证件号码*/
	@Excel(name = "法人证件号码", width = 15)
    @ApiModelProperty(value = "法人证件号码")
	private String frzjhm;
	/**法人联系方式*/
	@Excel(name = "法人联系方式", width = 15)
    @ApiModelProperty(value = "法人联系方式")
	private String frlxfs;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "成立日期")
	private Date clrq;
	/**成立年限*/
	@Excel(name = "成立年限", width = 15)
    @ApiModelProperty(value = "成立年限")
	private String clnx;
	/**营业执照名称*/
	@Excel(name = "营业执照名称", width = 15)
    @ApiModelProperty(value = "营业执照名称")
	private String yyzzmc;
	/**营业执照编号*/
	@Excel(name = "营业执照编号", width = 15)
    @ApiModelProperty(value = "营业执照编号")
	private String yyzzbh;
	/**税务登记编号*/
	@Excel(name = "税务登记编号", width = 15)
    @ApiModelProperty(value = "税务登记编号")
	private String swdjbh;
	/**企业注册资本*/
	@Excel(name = "企业注册资本(万元)", width = 15)
    @ApiModelProperty(value = "企业注册资本(万元)")
	private java.math.BigDecimal qyzcje;
	/**企业职工人数*/
	@Excel(name = "企业职工人数", width = 15)
    @ApiModelProperty(value = "企业职工人数")
	private Integer qyzgrs;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String txdz;
	/**传真号*/
	@Excel(name = "传真号", width = 15)
    @ApiModelProperty(value = "传真号")
	private String czh;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String yzbm;
	/**行业分类*/
	@Excel(name = "行业分类", width = 15)
    @ApiModelProperty(value = "行业分类")
	private String hyfl;
	/**经营项目*/
	@Excel(name = "经营项目", width = 15)
    @ApiModelProperty(value = "经营项目")
	private String jyxm;
	/**经营状况*/
	@Excel(name = "经营状况", width = 15)
    @ApiModelProperty(value = "经营状况")
	private String jyzk;
	/**资产负债*/
	@Excel(name = "资产负债", width = 15)
    @ApiModelProperty(value = "资产负债")
	private java.math.BigDecimal zcfz;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15, dicCode = "khgl_khzycd")
    @ApiModelProperty(value = "客户重要程度")
    @Dict(dicCode = "khgl_khzycd")
	private String khzycd;
	/**归属客户经理*/
	@Excel(name = "归属客户经理", width = 15, dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "归属客户经理")
    @Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String gskhjl;
	/**潜在业务*/
	@Excel(name = "潜在业务", width = 15, dicCode = "khqzyw")
    @ApiModelProperty(value = "潜在业务")
	@Dict(dicCode = "khqzyw")
	private String qzyw;
	/**年营业收入(万元)*/
	@Excel(name = "年营业收入(万元)", width = 15)
    @ApiModelProperty(value = "年营业收入(万元)")
	private java.math.BigDecimal nyysr;
	/**年经营业务成本(万元)*/
	@Excel(name = "年经营业务成本(万元)", width = 15)
    @ApiModelProperty(value = "年经营业务成本(万元)")
	private java.math.BigDecimal njyywcb;
	/**年工资额(万元)*/
	@Excel(name = "年工资额(万元)", width = 15)
    @ApiModelProperty(value = "年工资额(万元)")
	private java.math.BigDecimal ngze;
	/**年缴纳所得税(万元)*/
	@Excel(name = "年缴纳所得税(万元)", width = 15)
    @ApiModelProperty(value = "年缴纳所得税(万元)")
	private java.math.BigDecimal njnsds;
	/**年缴纳增值税(万元)*/
	@Excel(name = "年缴纳增值税(万元)", width = 15)
    @ApiModelProperty(value = "年缴纳增值税(万元)")
	private java.math.BigDecimal njnzzs;
	/**年经营净利润(万元)*/
	@Excel(name = "年经营净利润(万元)", width = 15)
    @ApiModelProperty(value = "年经营净利润(万元)")
	private java.math.BigDecimal njyjlr;
	/**其它收入(万元)*/
	@Excel(name = "其它收入(万元)", width = 15)
    @ApiModelProperty(value = "其它收入(万元)")
	private java.math.BigDecimal qtsr;
	/**其它支出(万元)*/
	@Excel(name = "其它支出(万元)", width = 15)
    @ApiModelProperty(value = "其它支出(万元)")
	private java.math.BigDecimal qtzc;
	/**总收入(万元)*/
	@Excel(name = "总收入(万元)", width = 15)
    @ApiModelProperty(value = "总收入(万元)")
	private java.math.BigDecimal zsr;
	/**总支出(万元)*/
	@Excel(name = "总支出(万元)", width = 15)
    @ApiModelProperty(value = "总支出(万元)")
	private java.math.BigDecimal zzc;
	/**净利润(万元)*/
	@Excel(name = "净利润(万元)", width = 15)
    @ApiModelProperty(value = "净利润(万元)")
	private java.math.BigDecimal jlr;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
