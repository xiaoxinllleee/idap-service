package org.cmms.modules.gzap.jhxf_real.entity;

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
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@Data
@TableName("GZAP_JHXF_KHJL")
public class Gzap_jhxf_khjl_real implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**工作内容*/
    @Excel(name = "工作内容", width = 15)
	private String gznr;
	/**外键*/
	private String orderId;
	/**实际完成情况*/
    @Excel(name = "实际完成情况", width = 15)
	private String sjwcqk;
	/**客户经理*/
    @Excel(name = "客户经理", width = 15)
	private String khjl;
}
