package org.cmms.modules.gzap.jhxf.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Data
@TableName("GZAP_JHXF")
public class jhxf implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**更新人*/
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**计划类型(1.月计划/2.周计划)*/
    @Dict(dicCode = "gzap_gzlx")
	private String gzlx;
	/**计划开始日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划结束日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhjssj;
	/**计划概述*/
	private String gs;
	/**计划内容*/
	private String nr;
	/**状态(1.待审核/2.已审核)*/
    @Dict(dicCode = "gzap_rwxfzt")
	private String zt;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**备注*/
	private String bz;
}
