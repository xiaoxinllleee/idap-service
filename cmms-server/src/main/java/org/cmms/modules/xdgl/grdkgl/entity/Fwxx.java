package org.cmms.modules.xdgl.grdkgl.entity;

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
 * @Description: 房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_FW")
public class Fwxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	private String zjhm;
	/**占地面积*/
    @Excel(name = "占地面积", width = 15)
	private java.math.BigDecimal zdmj;
	/**房屋面积*/
    @Excel(name = "房屋面积", width = 15)
	private java.math.BigDecimal fwmj;
	/**具体位置*/
    @Excel(name = "具体位置", width = 15)
	private String jtwz;
	/**评价估值*/
	@Excel(name = "评价估值", width = 15)
	private java.math.BigDecimal pjgz;
	/**土地性质*/
	@Excel(name = "土地性质", width = 15)
	private String tdxz;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
}
