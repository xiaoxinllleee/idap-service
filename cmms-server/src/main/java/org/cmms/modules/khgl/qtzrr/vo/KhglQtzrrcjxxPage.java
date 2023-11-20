package org.cmms.modules.khgl.qtzrr.vo;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
public class KhglQtzrrcjxxPage {


	/**id*/
	private String id;

	/**所属支行*/
	@Excel(name = "所属支行", width = 15 ,dicCode="zzbz", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属乡镇*/
	@Excel(name = "镇", width = 15, dicCode="dybh", dictTable="YXDYGL_YJYXDYGL", dicText="dymc")
	private String ssxz;
	/**行政村*/
	@Excel(name = "村", width = 15, dicCode="dybh", dictTable="YXDYGL_EJYXDYGL", dicText="dymc")
	private String xzc;
	/**行政村*/
	@Excel(name = "组", width = 15, dicCode="dybh", dictTable="YXDYGL_SJYXDYGL", dicText="dymc")
	private String xzz;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
	private String yhzgx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
	private BigDecimal khjlsxed;
	/**最终授信额度*/
  	@Excel(name = "最终授信额度", width = 15)
	private BigDecimal zzsxed;
	
}
