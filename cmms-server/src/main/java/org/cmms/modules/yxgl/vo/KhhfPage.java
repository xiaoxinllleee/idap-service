package org.cmms.modules.yxgl.vo;

import java.util.List;
import org.cmms.modules.yxgl.entity.Khhf;
import org.cmms.modules.yxgl.entity.Khhffjxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Data
public class KhhfPage {

	/**ID*/
	private java.lang.String id;
	/**组织标识*/
  	@Excel(name = "组织标识", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private java.lang.String zzbz;
	/**员工工号*/
  	@Excel(name = "员工工号", width = 15)
	private java.lang.String yggh;
	/**客户经理编号*/
  	@Excel(name = "客户经理编号", width = 15)
	private java.lang.String khjlbh;
	/**营销单元*/
  	@Excel(name = "营销单元", width = 15)
	private java.lang.String yxdy;
	/**客户名称*/
  	@Excel(name = "客户名称", width = 15)
	private java.lang.String khmc;
	/**证件号码*/
  	@Excel(name = "证件号码", width = 15)
	private java.lang.String zjhm;
	/**客户性质*/
  	@Excel(name = "客户性质", width = 15)
	private java.lang.String khxz;
	/**回访日期*/
  	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date hfrq;
	/**回访方式（1：上门回访 2：电话联系 3：约谈 4：其他）*/
  	@Excel(name = "回访方式", width = 15)
	private java.lang.String hffs;
	/**回访目的（1：初次拜访 2：完善客户资料 3：营销产品 4：解决客户疑问 5：维系客户关系 6：其他）*/
  	@Excel(name = "回访目的", width = 15)
	private java.lang.String hfmd;
	/**本次营销业务（1：存款 2：贷款 3：便民卡 4：开户 5：手机银行 6：网上银行 7：理财产品 8：ETC 9：其他）*/
  	@Excel(name = "本次营销业务", width = 15)
	private java.lang.String bcyxyw;
	/**营销业务成果（1：现场办理 2：预约办理 3：继续跟进 4：不感兴趣 5：其他）*/
  	@Excel(name = "营销业务成果", width = 15)
	private java.lang.String yxywcg;
	/**业务办理日期*/
  	@Excel(name = "业务办理日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date ywblrq;
	/**潜在业务需求（可多选。1：存款 2：贷款 3：便民卡 4：开户 5：手机银行 6：网上银行 7：理财产品 8：ETC 9：其他）*/
  	@Excel(name = "潜在业务需求", width = 15)
	private java.lang.String qzywxq;
	/**礼品名称*/
  	@Excel(name = "礼品名称", width = 15)
	private java.lang.String lpmc;
	/**礼品价值*/
  	@Excel(name = "礼品价值", width = 15)
	private java.math.BigDecimal lpjz;
	/**回访详情说明*/
  	@Excel(name = "回访详情说明", width = 15)
	private java.lang.String hfxqsm;
	/**下次回访日期*/
  	@Excel(name = "下次回访日期", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date xchfrq;
	/**下次回访注意事项*/
  	@Excel(name = "下次回访注意事项", width = 15)
	private java.lang.String xchfzysx;
	/**数据来源（1：PC端录入 2：平板端录入）*/
	@Excel(name = "数据来源", width = 15)
	private java.lang.String sjly;
	/**提交状态（1：未提交 2：已提交）*/
	@Excel(name = "提交状态", width = 15)
	private java.lang.String tjzt;
	/**录入标识*/
  	@Excel(name = "录入标识", width = 15)
	private java.lang.String lrbz;
	/**录入人*/
  	@Excel(name = "录入人", width = 15)
	private java.lang.String lrr;
	/**录入时间*/
  	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date lrsj;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private java.lang.String xgr;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date xgsj;

	@ExcelCollection(name="客户回访附件信息")
	private List<Khhffjxx> khhffjxxList;

}
