package org.cmms.modules.gzap.rwxf.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Data
@TableName("gzap_rwxf_jxz")
public class GzapRwxfJxz implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**任务名称*/
	private String rwmc;
	/**开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date kssj;
	/**结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jssj;
	/**任务类型(1.贷款/2.贷款笔数/3.拓展/4.回访/5.定期/6.活期)*/
	private String rwlx;
	/**重要级别(1.普通/2.重要/3.非常重要)*/
	private String zyjb;
	/**任务值*/
	private Long rwz;
	/**任务属性(1.自建/2.下发)*/
	private String rwsx;
	/**状态(1.进行中/2.已结束)*/
	private String zt;
}
