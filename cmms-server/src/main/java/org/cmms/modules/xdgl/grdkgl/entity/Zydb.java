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
 * @Description: 质押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_ZYDB")
public class Zydb implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	private String zjhm;
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
	/**更新时间
*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**质押物类型*/
    @Excel(name = "质押物类型", width = 15)
	private String zywlx;
	/**所有权人*/
    @Excel(name = "所有权人", width = 15)
	private String syqr;
	/**与被调查人关系*/
    @Excel(name = "与被调查人关系", width = 15)
	private String ybdcrgx;
	/**评估值*/
    @Excel(name = "评估值", width = 15)
	private java.math.BigDecimal pgz;
	/**使用权面积*/
    @Excel(name = "使用权面积", width = 15)
	private java.math.BigDecimal syqmj;
	/**具体位置*/
    @Excel(name = "具体位置", width = 15)
	private String jtwz;
}
