package org.cmms.modules.yxgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Data
@TableName("yxgl_khhfxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Khhf implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private java.lang.String id;
	/**组织标识*/
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String zzbz;
	/**员工工号*/
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private java.lang.String yggh;
	/**客户经理编号*/
	private java.lang.String khjlbh;
	/**营销单元*/
	@Dict(dicCode="dybh",dictTable="YXDYGL_EJYXDYGL",dicText="dymc")
	private java.lang.String yxdy;
	/**客户名称*/
	private java.lang.String khmc;
	/**证件号码*/
	private java.lang.String zjhm;
	/**客户性质*/
	private java.lang.String khxz;
	/**回访日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date hfrq;
	/**回访方式（1：上门回访 2：电话联系 3：约谈 4：其他）*/
	@Dict(dicCode = "khhf_hffs")
	private java.lang.String hffs;
	/**回访目的（1：初次拜访 2：完善客户资料 3：营销产品 4：解决客户疑问 5：维系客户关系 6：其他）*/
	@Dict(dicCode = "khhf_hfmd")
	private java.lang.String hfmd;
	/**本次营销业务（1：存款 2：贷款 3：便民卡 4：开户 5：手机银行 6：网上银行 7：理财产品 8：ETC 9：其他）*/
	private java.lang.String bcyxyw;
	/**营销业务成果（1：现场办理 2：预约办理 3：继续跟进 4：不感兴趣 5：其他）*/
	private java.lang.String yxywcg;
	/**业务办理日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date ywblrq;
	/**潜在业务需求（可多选。1：存款 2：贷款 3：便民卡 4：开户 5：手机银行 6：网上银行 7：理财产品 8：ETC 9：其他）*/
	private java.lang.String qzywxq;
	/**礼品名称*/
	private java.lang.String lpmc;
	/**礼品价值*/
	private java.math.BigDecimal lpjz;
	/**回访详情说明*/
	private java.lang.String hfxqsm;
	/**下次回访日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date xchfrq;
	/**下次回访注意事项*/
	private java.lang.String xchfzysx;
	/**数据来源（1：PC端录入 2：平板端录入）*/
	@Dict(dicCode = "sjly")
	private java.lang.String sjly;
	/**提交状态（1：未提交 2：已提交）*/
	@Dict(dicCode = "submit_state")
	private java.lang.String tjzt;
	/**录入标识*/
	private java.lang.String lrbz;
	/**录入人*/
	private java.lang.String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date lrsj;
	/**修改人*/
	private java.lang.String xgr;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date xgsj;
}
