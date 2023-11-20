package org.cmms.modules.yxgl.khhhmx.entity;

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
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
@Data
@TableName("YXGL_KHHF_FJXXB")
public class YxglKhhffjxxb implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**回访ID（关联客户回访信息表）*/
    @Excel(name = "回访ID（关联客户回访信息表）", width = 15)
	private String hfId;
	/**附件类型（1：图片/文件，2：视频 3：音频）*/
    @Excel(name = "附件类型（1：图片/文件，2：视频 3：音频）", width = 15)
	private String fjlx;
	/**附件名称*/
    @Excel(name = "附件名称", width = 15)
	private String fjmc;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date scsj;
	/**上传人*/
    @Excel(name = "上传人", width = 15)
	private String scr;
	/**附件大小（单位KB）*/
    @Excel(name = "附件大小（单位KB）", width = 15)
	private java.math.BigDecimal fjdx;
	/**附件路径*/
    @Excel(name = "附件路径", width = 15)
	private String fjlj;
	/**访问路径*/
    @Excel(name = "访问路径", width = 15)
	private String fwlj;
	/**备注*/
    @Excel(name = "备注", width = 15)
	private String bz;
	/**录入标识*/
    @Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**录入人*/
    @Excel(name = "录入人", width = 15)
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
}
