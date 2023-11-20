package org.cmms.modules.yxdygl.yjyxdygl.vo;

import java.util.List;

import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 一级营销单元管理
 * @Author: jeecg-boot
 * @Date:   2020-07-28
 * @Version: V1.0
 */
@Data
public class YjyxdyglPage {
	
	/**主键id*/
	private String id;
	/**单元名称*/
  	@Excel(name = "单元名称", width = 15)
	private String dymc;
	/**单元编号*/
  	@Excel(name = "单元编号", width = 15)
	private String dybh;
	/**单元性质(1-农区乡镇,2-工业园区,3-城区街道)*/
  	@Excel(name = "单元性质(1-农区乡镇,2-工业园区,3-城区街道)", width = 15)
	private String dyxz;
	/**农户数*/
  	@Excel(name = "农户数", width = 15)
	private Long nhs;
	/**农户建档数*/
  	@Excel(name = "农户建档数", width = 15)
	private Long nhjds;
	/**农户建档覆盖率*/
  	@Excel(name = "农户建档覆盖率", width = 15)
	private Long nhjdfgl;
	/**商户数*/
  	@Excel(name = "商户数", width = 15)
	private Long shs;
	/**商户建档数*/
  	@Excel(name = "商户建档数", width = 15)
	private Long shjds;
	/**商户建档覆盖率*/
  	@Excel(name = "商户建档覆盖率", width = 15)
	private Long shjdfgl;
	/**城区居民*/
  	@Excel(name = "城区居民", width = 15)
	private Long cqjm;
	/**城区居民建档数*/
  	@Excel(name = "城区居民建档数", width = 15)
	private Long cqjmjds;
	/**城区居民建档覆盖率*/
  	@Excel(name = "城区居民建档覆盖率", width = 15)
	private Long cqjmjdfgl;
	/**企业数*/
  	@Excel(name = "企业数", width = 15)
	private Long qys;
	/**企业建档数*/
  	@Excel(name = "企业建档数", width = 15)
	private Long qyjds;
	/**企业建档覆盖率*/
  	@Excel(name = "企业建档覆盖率", width = 15)
	private Long qyjdfgl;
	/**机构数*/
  	@Excel(name = "机构数", width = 15)
	private Long jgs;
	/**机构建档数*/
  	@Excel(name = "机构建档数", width = 15)
	private Long jgjds;
	/**机构建档覆盖率*/
  	@Excel(name = "机构建档覆盖率", width = 15)
	private Long jgjdfgl;
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
	
	@ExcelCollection(name="营销单元附件信息")
	private List<Yxdyfjxx> yxdyfjxxList;

	private List<String> deleteIds;
	
}
