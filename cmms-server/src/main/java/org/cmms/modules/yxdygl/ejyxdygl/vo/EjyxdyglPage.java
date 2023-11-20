package org.cmms.modules.yxdygl.ejyxdygl.vo;

import java.util.List;
import org.cmms.modules.yxdygl.ejyxdygl.entity.VEjyxdygl;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 二级营销单元管理
 * @Author: jeecg-boot
 * @Date:   2020-07-30
 * @Version: V1.0
 */
@Data
public class EjyxdyglPage {
	
	/**主键id*/
	private String id;
	/**所属支行*/
  	@Excel(name = "所属支行", width = 15)
	private String sszh;
	/**一级营销单元编号*/
  	@Excel(name = "一级营销单元编号", width = 15)
	private String yjyxdybh;
	/**单元名称*/
  	@Excel(name = "单元名称", width = 15)
	private String dymc;
	/**单元编号*/
  	@Excel(name = "单元编号", width = 15)
	private String dybh;
	/**单元性质(1-行政区,2-社区,3-街道,4-商圈,5-园区)*/
  	@Excel(name = "单元性质(1-行政区,2-社区,3-街道,4-商圈,5-园区)", width = 15)
	private String dyxz;
	/**责任部室*/
  	@Excel(name = "责任部室", width = 15)
	private String zrbs;
	/**责任领导*/
  	@Excel(name = "责任领导", width = 15)
	private String zrld;
	/**农户数*/
  	@Excel(name = "农户数", width = 15)
	private Integer nhs;
	/**农户建档数*/
  	@Excel(name = "农户建档数", width = 15)
	private Integer nhjds;
	/**农户建档覆盖率*/
  	@Excel(name = "农户建档覆盖率", width = 15)
	private java.math.BigDecimal nhjdfgl;
	/**商户数*/
  	@Excel(name = "商户数", width = 15)
	private Integer shs;
	/**商户建档数*/
  	@Excel(name = "商户建档数", width = 15)
	private Integer shjds;
	/**商户建档覆盖率*/
  	@Excel(name = "商户建档覆盖率", width = 15)
	private java.math.BigDecimal shjdfgl;
	/**城区居民*/
  	@Excel(name = "城区居民", width = 15)
	private Integer cqjm;
	/**城区居民建档数*/
  	@Excel(name = "城区居民建档数", width = 15)
	private Integer cqjmjds;
	/**城区居民建档覆盖率*/
  	@Excel(name = "城区居民建档覆盖率", width = 15)
	private java.math.BigDecimal cqjmjdfgl;
	/**企业数*/
  	@Excel(name = "企业数", width = 15)
	private Integer qys;
	/**企业建档数*/
  	@Excel(name = "企业建档数", width = 15)
	private Integer qyjds;
	/**企业建档覆盖率*/
  	@Excel(name = "企业建档覆盖率", width = 15)
	private java.math.BigDecimal qyjdfgl;
	/**机构数*/
  	@Excel(name = "机构数", width = 15)
	private Integer jgs;
	/**机构建档数*/
  	@Excel(name = "机构建档数", width = 15)
	private Integer jgjds;
	/**机构建档覆盖率*/
  	@Excel(name = "机构建档覆盖率", width = 15)
	private java.math.BigDecimal jgjdfgl;
	/**备注*/
  	@Excel(name = "备注", width = 15)
	private String bz;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;

	/**网格面积*/
	@Excel(name = "网格面积", width = 15)
	private String wgmj;
	/**网格情况*/
	@Excel(name = "网格情况", width = 15)
	private String wgqk;
	/**网格痛点分析*/
	@Excel(name = "网格痛点分析", width = 15)
	private String wgtdfx;
	/**村委书记*/
	@Excel(name = "村委书记", width = 15)
	private String cwsj;
	/**村委主任*/
	@Excel(name = "村委主任", width = 15)
	private String cwzr;
	/**村委办公室主任*/
	@Excel(name = "村委办公室主任", width = 15)
	private String cwbgszr;
	/**网格位置*/
	@Excel(name = "网格位置", width = 15)
	private String wgwz;

	@ExcelCollection(name="营销单元附件信息")
	private List<Yxdyfjxx> yxdyfjxxList;

	private List<String> deleteIds;
}
