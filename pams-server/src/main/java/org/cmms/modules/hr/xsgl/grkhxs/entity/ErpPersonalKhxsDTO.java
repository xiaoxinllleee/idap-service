package org.cmms.modules.hr.xsgl.grkhxs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ErpPersonalKhxsDTO {
    
	private List<String> staffs;
	/**考核系数*/
	private java.math.BigDecimal khxs;
	/**不参与考核系数*/
	private java.math.BigDecimal bcykhxs;
	/**总系数*/
	private java.math.BigDecimal zxs;
	/**有效开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date kssj;
	/**有效结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jssj;
}
