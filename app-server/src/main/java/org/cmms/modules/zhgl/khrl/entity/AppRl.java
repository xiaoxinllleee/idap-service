package org.cmms.modules.zhgl.khrl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户认领
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
@Data
@TableName("app_rl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="app_rl对象", description="客户认领")
public class AppRl {
    
	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
	private String id;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**认领种类 1手机银行 2ETC 3 信用卡*/
	@Excel(name = "认领种类 1手机银行 2ETC 3 信用卡", width = 15)
    @ApiModelProperty(value = "认领种类 1手机银行 2ETC 3 信用卡")
	private String type;
}
