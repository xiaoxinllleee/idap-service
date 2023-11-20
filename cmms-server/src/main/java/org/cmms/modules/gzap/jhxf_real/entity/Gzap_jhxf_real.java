package org.cmms.modules.gzap.jhxf_real.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
@TableName("GZAP_JHXF")
public class Gzap_jhxf_real implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**支行名称*/
	private String zhmc;
	/**客户经理*/
	private String khmc;
	/**工作类型*/
	private String gzlx;
	/**计划开始日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhkssj;
	/**计划完成日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jhwcsj;
	/**实际完成日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sjwcrq;
	/**概述*/
	private String gs;
	/**内容*/
	private String nr;
	/**状态*/
	private String zt;
	/**新增人*/
	private String xzr;
	/**新增时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xzsj;
	/**备注*/
	private String bz;
}
