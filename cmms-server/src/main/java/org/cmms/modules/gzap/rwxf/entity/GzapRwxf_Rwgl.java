package org.cmms.modules.gzap.rwxf.entity;

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
 * @Description: 任务对象管理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Data
@TableName("GZAP_RWXF_RWGL")
public class GzapRwxf_Rwgl implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**任务对象*/
    @Excel(name = "任务对象", width = 15)
	private String rwdx;
	/**任务值*/
    @Excel(name = "任务值", width = 15)
	private Long rwz;
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
	/**是否达标(1.已达标/2.未达标)*/
    @Excel(name = "是否达标(1.已达标/2.未达标)", width = 15)
	private String sfdb;
	/**完成状态(1.已完成/2.未完成)*/
    @Excel(name = "完成状态(1.已完成/2.未完成)", width = 15)
	private String wczt;
	/**完成值*/
    @Excel(name = "完成值", width = 15)
	private Long wcz;
	/**完成时间*/
	@Excel(name = "完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date wcsj;
	/**完成情况*/
    @Excel(name = "完成情况", width = 15)
	private String wcqk;
}
