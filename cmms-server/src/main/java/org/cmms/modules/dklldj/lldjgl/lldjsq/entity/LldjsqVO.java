package org.cmms.modules.dklldj.lldjgl.lldjsq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.constant.RateConstant;
import org.cmms.modules.xdgl.grdkgl.entity.RateDbxxgl;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Data
@ToString
public class LldjsqVO {
	/**定价年份*/
	private String djnf;
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**组织标识*/
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**证件号码*/
	private String zjhm;
	/**客户名称*/
	private String khmc;
	/**法人代表*/
	private String frdb;
	/**上年授信额度*/
	private java.math.BigDecimal snsxed;
	/**上年贷款利率上浮幅度*/
	private java.math.BigDecimal sndkllsffd;
	/**综合授信额度*/
	private java.math.BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口*/
	private java.math.BigDecimal cdck;
	/**贷款期限*/
	private Integer dkqx;
	/**客户类型*/
	private Integer khlx;
	/**是否便民卡*/
	private Integer sfbmk;
	/**是否保证保险贷款*/
	private Integer sfbzbxdk;
	/**是否享受`小微客户定价普惠措施`*/
	private Integer sfjzxkh;
	/**是否高危行业*/
	private Integer sfgwhy;

	/**农村三权抵（质）押贷款*/
	private java.lang.Integer ncsqdzydk;
	/**是否为花炮企业*/
	private java.lang.Integer sfhpqy;

	/**还款方式(1.其他；2.等额本金；3.等额本息)*/
	private Integer hkfs;
	/**上年贷款基点(加/减)BP*/
	private java.math.BigDecimal sndkjdbp;
	/**信贷贷款品种*/
	private String xddkpz;

	/**录入时间*/
	private Date lrsj;
	/**录入标志*/
	private Integer lrbz;
	/**录入人*/
	private String lrr;
	/**修改人*/
	private String xgr;
	/**修改时间*/
	private Date xgsj;
	/**授信额度*/
	private String sxed;

	//借款人（含法人代表、主要股东及配偶）征信有不良记录
	private String xydj;
	private String jyqx;
	//借款人上年在本行贷款（含贷记卡）未按期清息、还款次数
	private String GZ00009 = RateConstant.INIT_NUMBER;
	private String GZ00010 = RateConstant.INIT_NUMBER;
	private String GZ00013 = RateConstant.INIT_NUMBER;
	private String GZ00014 = RateConstant.INIT_NUMBER;
	private String GZ00015;
	private String GZ00021 = RateConstant.INIT_NUMBER;
	private String GZ00022 = RateConstant.INIT_NUMBER;
	private String GZ00023;
	private String GZ00031;
	private String GZ00032;
	private String GZ00033;
	private String GZ00034;
	private String GZ00035;
	private String GZ00036;
	private String GZ00037;
	private String GZ00038;
	private String GZ00039;
	private String GZ00040;
	private String GZ00041;
	private String GZ00042;
	private String GZ00043;
	private String GZ00044;
	private String GZ00045;
	private String GZ00046;
	private String GZ00047;
	private String GZ00048;
	private String GZ00049;
	private String GZ00050;
	private String GZ00051;
	private String GZ00052;
	private String GZ00053;
	private String GZ00057;
	/*//资产总额
	private String zcze;
	//负债总额
	private String zcfz;
	//资产负债率(%)
	private String zcfzl;
	//经营期限
	private String jyqx;
	//销售收入
	private String xssr;
	//流动负债
	private String ldfz;
	//倍数
	private String bs;
	//开户基本账户往来年限
	private String wlnx;
	//日平存款占贷款比例(%)
	private String rpckzdkbl;
	//前三年第一个年度存款日平
	private String ynrp;
	//前三年第二个年度存款日平
	private String enrp;
	//前三年第二个年度存款日平
	private String snrp;
	//客户上一个年度在其他银行存款日平
	private String qtyhckrp;
	//客户上一个年度在其他银行定期存款日平
	private String qtyhdqckrp;
	//定价存款日平合计
	private String djckrphj;
	//上年执行利率（‰）
	private String snzxll;
	//本行资金成本率(%)
	private String bhzjcbl;
	//上年贷款日平
	private String snckrp;
	//收益贡献
	private String sygx;
	//客户本年度代发工资业务不在本行代发
	private String gzsfdf;
	//高危行业财产保险本年应保或第一受益人不是本行
	private String sysfbh;
	//高危行业财产保险本年应保不全
	private String ybbq;
	//保险到期未能如期续保且第一受益人不是本行、不能覆盖定价周期
	private String fgdjzq;
	//其他银行存款日平占本行存款日平比例(%)
	private String thckrpbl;
	//基本账户未在我行或在我行但资金归行不走基本账户
	private String bzjbzh;
	//客户能够开立我行手机银行、口袋零钱及其他第三方支付绑定我行卡但未开通的，或开通未使用的，每项扣2分
	private String wktyw;
	//客户实际控制企业有国际贸易业务的，上年在本行往来
	private String bhwl;
	//贷款一年（含）以内基准利率(%)
	private String nll;
	//贷款一至五年（含）基准利率(%)
	private String hll;
	//贷款五年以上基准利率(%)
	private String sll;
	//本行资金成本率(%)
	private String zjcbl;*/
	//抵押信息
	@TableField(exist = false)
	private List<RateDbxxgl> rateDbxxgls;
}
