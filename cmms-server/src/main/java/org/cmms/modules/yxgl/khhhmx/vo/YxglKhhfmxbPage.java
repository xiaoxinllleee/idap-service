package org.cmms.modules.yxgl.khhhmx.vo;

import java.util.List;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhfmxb;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 客户回访明细
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@Data
public class YxglKhhfmxbPage {
	
	/**ID*/
	private String id;
	/**组织标识*/
  	@Excel(name = "组织标识", width = 15)
	private String zzbz;
	/**员工工号*/
  	@Excel(name = "员工工号", width = 15)
	private String yggh;
	/**客户经理编号*/
  	@Excel(name = "客户经理编号", width = 15)
	private String khjlbh;
	/**营销单元*/
  	@Excel(name = "营销单元", width = 15)
	private String yxdy;
	/**客户名称*/
  	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户性质*/
  	@Excel(name = "客户性质", width = 15)
	private String khxz;
	/**客户等级*/
  	@Excel(name = "客户等级", width = 15)
	private String khdj;
	/**回访日期*/
  	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hfrq;
	/**回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)*/
  	@Excel(name = "回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)", width = 15)
	private String hffs;
	/**计划月份*/
  	@Excel(name = "计划月份", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhyf;
	/**计划开始时间*/
  	@Excel(name = "计划开始时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划结束时间*/
  	@Excel(name = "计划结束时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhjssj;
	/**回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)*/
  	@Excel(name = "回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)", width = 15)
	private String hfmd;
	/**本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/
  	@Excel(name = "本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)", width = 15)
	private String bcyxyw;
	/**营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)*/
  	@Excel(name = "营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)", width = 15)
	private String yxywcg;
	/**业务办理日期*/
  	@Excel(name = "业务办理日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ywblrq;
	/**潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/
  	@Excel(name = "潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)", width = 15)
	private String qzywxq;
	/**礼品名称*/
  	@Excel(name = "礼品名称", width = 15)
	private String lpmc;
	/**礼品价值*/
  	@Excel(name = "礼品价值", width = 15)
	private java.math.BigDecimal lpjz;
	/**回访详情说明*/
  	@Excel(name = "回访详情说明", width = 15)
	private String hfxqsm;
	/**下次回访日期*/
  	@Excel(name = "下次回访日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xchfrq;
	/**下次回访注意事项*/
  	@Excel(name = "下次回访注意事项", width = 15)
	private String xchfzysx;
	/**数据来源(1.PC端录入;2.平板端录入)*/
  	@Excel(name = "数据来源(1.PC端录入;2.平板端录入)", width = 15)
	private String sjly;
	/**提交状态(1.未提交;2.已提交)*/
  	@Excel(name = "提交状态(1.未提交;2.已提交)", width = 15)
	private String tjzt;
	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
  	@Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
  	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**是否完成回访*/
  	@Excel(name = "是否完成回访", width = 15)
	private String sfwchf;
	
	@ExcelCollection(name="客户回访附件信息")
	private List<YxglKhhffjxxb> yxglKhhffjxxbList;
	
}
