package org.cmms.modules.sbxj.fxzdfjxx.entity;

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
 * @Description: 福祥站点附件表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("khywxx_fjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khywxx_fjxx对象", description="福祥站点附件表")
public class KhywxxFjxx {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**站点编号*/
	@Excel(name = "站点编号", width = 15)
    @ApiModelProperty(value = "站点编号")
	private String zdbh;
	/**附件类型(1：店铺门头合影 2：维护人员与商户合影 3：店内设备合影 4：维护人员福祥E站设备合影 5：测试交易照测试交易证据照片)*/
	@Excel(name = "附件类型(1：店铺门头合影 2：维护人员与商户合影 3：店内设备合影 4：维护人员福祥E站设备合影 5：测试交易照测试交易证据照片)", width = 15)
    @ApiModelProperty(value = "附件类型(1：店铺门头合影 2：维护人员与商户合影 3：店内设备合影 4：维护人员福祥E站设备合影 5：测试交易照测试交易证据照片)")
	private String fjlx;
	/**上传人*/
	@Excel(name = "上传人", width = 15)
    @ApiModelProperty(value = "上传人")
	private String scr;
	/**附件大小*/
	@Excel(name = "附件大小", width = 15)
    @ApiModelProperty(value = "附件大小")
	private java.math.BigDecimal fjdx;
	/**附件路径*/
	@Excel(name = "附件路径", width = 15)
    @ApiModelProperty(value = "附件路径")
	private String fjlj;
	/**附件访问路径*/
	@Excel(name = "附件访问路径", width = 15)
    @ApiModelProperty(value = "附件访问路径")
	private String fjfwlj;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
	private Date scsj;
}
