package org.cmms.modules.yxgl.khhhmx.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 客户回访明细
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@Data
@TableName("YXGL_KHHFMXB")
public class YxglKhhfmxb implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**组织标识*/
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**员工工号*/
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String yggh;
	/**客户经理编号*/
	private String khjlbh;
	/**营销单元*/
	@Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="organize")
	private String yxdy;
	/**客户名称*/
	private String khmc;
	/**证件号码*/
	private String zjhm;
	/**客户性质*/
	@Dict(dicCode = "khxz")
	private String khxz;
	/**客户等级*/
	@Dict(dicCode ="djbh",dictTable ="KHDJ_KHDJSZ",dicText = "djmc")
	private String khdj;
	/**回访日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hfrq;
	/**回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)*/
	@Dict(dicCode = "khhf_hffs")
	private String hffs;
	/**计划月份*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhyf;
	/**计划开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhjssj;
	/**回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)*/
	@Dict(dicCode = "khhf_hfmd")
	private String hfmd;
	/**本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/

	private String bcyxyw;
	/**营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)*/
	private String yxywcg;
	/**业务办理日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ywblrq;
	/**潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/
	private String qzywxq;
	/**礼品名称*/
	private String lpmc;
	/**礼品价值*/
	private java.math.BigDecimal lpjz;
	/**回访详情说明*/
	private String hfxqsm;
	/**下次回访日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xchfrq;
	/**下次回访注意事项*/
	private String xchfzysx;
	/**数据来源(1.PC端录入;2.平板端录入)*/
	@Dict(dicCode = "sjly")
	private String sjly;
	/**提交状态(1.未提交;2.已提交)*/
	@Dict(dicCode = "submit_state")
	private String tjzt;
	/**录入标识*/
	private String lrbz;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**是否完成回访*/
	@Dict(dicCode ="sfbz")
	private String sfwchf;
}
