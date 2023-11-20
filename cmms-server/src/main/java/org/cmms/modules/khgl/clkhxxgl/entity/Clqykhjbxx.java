package org.cmms.modules.khgl.clkhxxgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 存量企业客户基本信息
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
@TableName("Khgl_qykhjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Clqykhjbxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**组织标识*/
	private String zzbz;
	/**机构代码*/
	private String jgdm;
	/**所属营销单元*/
	private String ssyxdy;
	/**客户编号*/
	private String khbh;
	/**企业名称*/
	private String khmc;
	/**证件类型*/
	private String zjlx;
	/**证件号码*/
	private String zjhm;
	/**组织机构代码*/
	private String zzjgdm;
	/**客户类型*/
	private String custType;
	/**客户类型1*/
	private String custType1;
	/**客户类型2*/
	private String custType2;
	/**客户类型3*/
	private String custType3;
	/**账户类型*/
	private String zhlx;
	/**法人代表*/
	private String frdb;
	/**法人联系方式*/
	private String frlxfs;
	/**法人证件类型*/
	private String frzjlx;
	/**法人证件号码*/
	private String frzjhm;
	/**邮政编码*/
	private String yzbm;
	/**通讯地址*/
	private String txdz;
	/**联系人*/
	private String lxr;
	/**联系电话*/
	private String lxdh;
	/**行业分类*/
	private String hyfl;
	/**行业分类（副营）*/
	private String hyflfy;
	/**主营业务*/
	private String zyyw;
	/**国籍*/
	private String gj;
	/**企业规模*/
	private String qygm;
	/**营业执照号*/
	private String yyzzh;
	/**注册资本金额*/
	private java.math.BigDecimal zczbje;
	/**成立日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date clrq;
	/**税务登记编号*/
	private String swdjbh;
	/**经营范围*/
	private String jyfw;
	/**经营状况*/
	private String jyzk;
	/**资产负债*/
	private java.math.BigDecimal zcfz;
	/**传真*/
	private String cz;
	/**ECIF 客户编号*/
	private String custId;
	/**客户重要程度*/
	private String khzycd;
	/**客户潜在业务*/
	private String khqzyw;
	/**年营业收入（万元）*/
	private java.math.BigDecimal nyysr;
	/**年主营业务成本（万元）*/
	private java.math.BigDecimal nzyywcb;
	/**年工资（万元）*/
	private java.math.BigDecimal ngz;
	/**年缴纳所得税（万元）*/
	private java.math.BigDecimal njnsds;
	/**年缴纳增值税（万元）*/
	private java.math.BigDecimal njnzzs;
	/**年主营净利润（万元）*/
	private java.math.BigDecimal nzyjlr;
	/**其他收入（万元）*/
	private java.math.BigDecimal qtsr;
	/**其他支出（万元）*/
	private java.math.BigDecimal qtzc;
	/**总收入（万元）*/
	private java.math.BigDecimal zsr;
	/**总支出（万元）*/
	private java.math.BigDecimal zzc;
	/**净利润（万元）*/
	private java.math.BigDecimal jlr;
	/**录入人*/
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入标识*/
	private String lrbz;
}
