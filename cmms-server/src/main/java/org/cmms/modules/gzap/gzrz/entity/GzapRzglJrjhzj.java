package org.cmms.modules.gzap.gzrz.entity;

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
 * @Description: 今天计划总结
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
@Data
@TableName("GZAP_RZGL_JRJHZJ")
public class GzapRzglJrjhzj implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**备忘录*/
    @Excel(name = "备忘录", width = 15)
	private String jrjh;
	/**客户姓名*/
    @Excel(name = "客户姓名", width = 15)
	private String wcqk;
	/**主要内容*/
    @Excel(name = "主要内容", width = 15)
	private String wwcyy;
	/**外键*/
	private String orderId;
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
