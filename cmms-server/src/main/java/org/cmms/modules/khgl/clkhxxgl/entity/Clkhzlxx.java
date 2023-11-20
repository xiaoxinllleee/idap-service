package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 存量个人客户资料信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Data
@TableName("Khgl_zlxx")
public class Clkhzlxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户ID*/
	private String khId;
	/**资料类型（1：图片/文件，2：视频 3：音频）*/
    @Excel(name = "资料类型（1：图片/文件，2：视频 3：音频）", width = 15)
	private String zllx;
	/**资料名称*/
    @Excel(name = "资料名称", width = 15)
	private String zlmc;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date scsj;
	/**上传人*/
    @Excel(name = "上传人", width = 15)
	private String scr;
	/**资料大小（单位KB）*/
    @Excel(name = "资料大小（单位KB）", width = 15)
	private java.math.BigDecimal zldx;
	/**资料路径*/
    @Excel(name = "资料路径", width = 15)
	private String zllj;
	/**访问路径*/
    @Excel(name = "访问路径", width = 15)
	private String fwlj;
	/**备注*/
    @Excel(name = "备注", width = 15)
	private String bz;
	/**录入人*/
    @Excel(name = "录入人", width = 15)
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入标识*/
    @Excel(name = "录入标识", width = 15)
	private String lrbz;
}
