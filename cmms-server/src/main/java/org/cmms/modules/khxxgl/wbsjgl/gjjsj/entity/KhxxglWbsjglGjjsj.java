package org.cmms.modules.khxxgl.wbsjgl.gjjsj.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 公积金数据
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_WBSJGL_GJJSJGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_WBSJGL_GJJSJGL对象", description="公积金数据")
public class KhxxglWbsjglGjjsj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    @ExcelVerify(notNull = true)
	private String zjhm;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String age;
	/**个人账户状态*/
	@Excel(name = "个人账户状态", width = 15)
    @ApiModelProperty(value = "个人账户状态")
	private String grzhzt;
	/**公积金起缴年月*/
	@Excel(name = "公积金起缴年月", width = 15)
    @ApiModelProperty(value = "公积金起缴年月")
	private String gjjqjny;
	/**公积金月缴额*/
	@Excel(name = "公积金月缴额", width = 15)
    @ApiModelProperty(value = "公积金月缴额")
	private String gjjyje;
	/**个人账户余额*/
	@Excel(name = "个人账户余额", width = 15)
    @ApiModelProperty(value = "个人账户余额")
	private String grzhye;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String dwmc;
	/**录入人*/
	@ApiModelProperty(value = "录入人")
	@ExcelVerify(interHandler = true)
	private String lrr;
	/**录入时间*/
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识*/
	@ApiModelProperty(value = "录入标识")
	private String lrbz;

}
