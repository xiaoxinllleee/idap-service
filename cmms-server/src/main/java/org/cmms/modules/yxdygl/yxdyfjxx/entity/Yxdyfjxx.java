package org.cmms.modules.yxdygl.yxdyfjxx.entity;

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
 * @Description: 营销单元附件信息
 * @Author: jeecg-boot
 * @Date:   2020-07-28
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_YXDYFJXX")
public class Yxdyfjxx implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**附件ID*/
    @Excel(name = "附件ID", width = 15)
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**单元编号*/
	private String dybh;
	/**附件类型(1-图片/文件/2-视频/3-音频)*/
    @Excel(name = "附件类型(1-图片/文件/2-视频/3-音频)", width = 15)
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
	/**附件大小(单位KB)*/
    @Excel(name = "附件大小(单位KB)", width = 15)
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
}
