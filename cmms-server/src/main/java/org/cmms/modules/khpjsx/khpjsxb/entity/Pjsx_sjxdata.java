package org.cmms.modules.khpjsx.khpjsxb.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-02-22
 * @Version: V1.0
 */
@Data
@TableName("PJSX_SJXDATA")
public class Pjsx_sjxdata implements Serializable {
    private static final long serialVersionUID = 1L;

	/**组织标志*/
	private String zzbz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户类型*/
	private String khlx;
	/**数据项id*/
	@Excel(name = "项目ID", width = 15)
	private String sjxid;
	/**数据项名称*/
	@Excel(name = "项目名称", width = 15)
	private String sjxmc;
	/**数据项结果*/
	@Excel(name = "项目结果", width = 15)
	private java.math.BigDecimal sjxjg;
	/**得分*/
	@Excel(name = "项目分值", width = 15)
	private java.math.BigDecimal df;
	/**数据来源*/
	@Excel(name = "数据来源", width = 15)
	private String sjly;
	/**统计日期*/
	@Excel(name = "评议日期", width = 15, format = "yyyy-MM-dd")
	@ApiModelProperty(value = "评议日期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tjrq;
}
