package org.cmms.modules.hr.xsgl.gwxssz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

/**
 * @Description: 岗位系数管理
 * @Author: jeecg-boot
 * @Date:   2021-10-25
 * @Version: V1.0
 */
@Data
public class ErpPostKhxsDTO {
    
	/**组织标识*/
	private List<String> zzbz;
	/**岗位标识*/
	private List<Integer> gwbz;
	/**考核系数*/
	private java.math.BigDecimal khxs;
	/**不参与考核系数*/
	private java.math.BigDecimal bcykhxs;
	/**总系数*/
	private java.math.BigDecimal zxs;
}
