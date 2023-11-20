package org.cmms.modules.hr.yggl.ygjsxxgl.entity;

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
 * @Description: 员工家属信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YGJSXXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YGJSXXB对象", description="员工家属信息管理")
public class ErpBasYgjsxxb {
    
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**家属关系*/
	@Excel(name = "家属关系", width = 15, dicCode = "ygjsgx")
    @ApiModelProperty(value = "家属关系")
	@Dict(dicCode = "ygjsgx")
	private Integer jsgx;
	/**家属姓名*/
	@Excel(name = "家属姓名", width = 15)
    @ApiModelProperty(value = "家属姓名")
	private String jsxm;
	/**家属证件号码*/
	@Excel(name = "家属证件号码", width = 15)
    @ApiModelProperty(value = "家属证件号码")
	private String jszjhm;
	/**家属联系方式*/
	@Excel(name = "家属联系方式", width = 15)
    @ApiModelProperty(value = "家属联系方式")
	private String jslxfs;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
