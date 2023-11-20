package org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Data
@TableName("Dkjkpt_Dhjcbgfjxx")
public class DkjkptDhjcbgfjxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**文件id*/
	@TableId(type = IdType.ASSIGN_ID)
	@Excel(name = "文件id", width = 15)
	private String wjid;
	/**证件号码*/
	private String zjhm;
	/**附件类型*/
    @Excel(name = "附件类型", width = 15)
	private String fjlx;
	/**文件路径*/
    @Excel(name = "文件路径", width = 15)
	private String wjlj;
	/**文件映射路径*/
    @Excel(name = "文件映射路径", width = 15)
	private String fwlj;
	/**文件大小*/
    @Excel(name = "文件大小", width = 15)
	private java.math.BigDecimal wjdx;
	/**浏览测试*/
    @Excel(name = "浏览测试", width = 15)
	private Long llcs;
	/**下载次数*/
    @Excel(name = "下载次数", width = 15)
	private Long xzcs;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入标志*/
    @Excel(name = "录入标志", width = 15)
	private Integer lrbz;
	/**录入人*/
    @Excel(name = "录入人", width = 15)
	private String lrr;
	/**附件年份*/
	@Excel(name = "附件年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fjnf;
	/**1：第一季度报告，2：第二季度报表，3：第三季度报告，4：第四季度报表，5：上半年报表，6下半年报告 7:年度报告，8首发跟踪*/
    @Excel(name = "1：第一季度报告，2：第二季度报表，3：第三季度报告，4：第四季度报表，5：上半年报表，6下半年报告 7:年度报告，8首发跟踪", width = 15)
	private String fjsjjd;
	/**备注*/
    @Excel(name = "备注", width = 15)
	private String bz;
}
