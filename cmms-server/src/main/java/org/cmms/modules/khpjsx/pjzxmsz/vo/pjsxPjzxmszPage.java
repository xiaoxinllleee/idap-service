package org.cmms.modules.khpjsx.pjzxmsz.vo;

import lombok.Data;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.List;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
public class pjsxPjzxmszPage {

	/**区域代码*/
  	@Excel(name = "区域代码", width = 15)
	private String qydm;
	/**父项目编号*/
  	@Excel(name = "父项目编号", width = 15)
	private String fxmbh;
	/**项目编号*/
  	@Excel(name = "项目编号", width = 15)
	private String xmbh;
	/**项目名称*/
  	@Excel(name = "项目名称", width = 15)
	private String xmmc;
	/**计算方式(1.下拉（单选）  2.区间  3.公式)*/
  	@Excel(name = "计算方式(1.下拉（单选）  2.区间  3.公式)", width = 15)
	private String jsfs;
	/**分值*/
  	@Excel(name = "分值", width = 15)
	private java.math.BigDecimal fz;
	/**客户类型*/
  	@Excel(name = "客户类型", width = 15)
	private String khlx;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String bz;
	/**排序序号*/
  	@Excel(name = "排序序号", width = 15)
	private Integer pxxh;
	/**是否启用 0 启用 1 停用*/
  	@Excel(name = "是否启用 0 启用 1 停用", width = 15)
	private String sfqy;

	@ExcelCollection(name="与我行往来情况")
	private List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList;
	@ExcelCollection(name="金融业务信息")
	private List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList;
	@ExcelCollection(name="金融业务信息")
	private List<PJSX_PJZXMGZSZ_GS> pjsxPjzxmgzszGsList;

}
