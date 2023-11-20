package org.cmms.modules.zhgl.wdckls.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 网点存款流失
 * @Author: jeecg-boot
 * @Date:   2022-03-12
 * @Version: V1.0
 */
@Data
@TableName("app_zhgl_wdckls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="app_zhgl_wdckls对象", description="网点存款流失")
public class Wdckls {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**期末余额*/
	@Excel(name = "期末余额", width = 15)
    @ApiModelProperty(value = "期末余额")
	private Integer qmye;
	/**期末余额较上日*/
	@Excel(name = "期末余额较上日", width = 15)
    @ApiModelProperty(value = "期末余额较上日")
	private Integer qmyejsr;
	/**期末余额较月初*/
	@Excel(name = "期末余额较月初", width = 15)
    @ApiModelProperty(value = "期末余额较月初")
	private Integer qmyejyc;
	/**期末余额较年初*/
	@Excel(name = "期末余额较年初", width = 15)
    @ApiModelProperty(value = "期末余额较年初")
	private Integer qmyejnc;
	/**期末余额较季初*/
	@Excel(name = "期末余额较季初", width = 15)
    @ApiModelProperty(value = "期末余额较季初")
	private Integer qmyejjc;
}
