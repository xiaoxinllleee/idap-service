package org.cmms.modules.khgl.ckkh.entity;

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
 * @Description: 存款账号管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_CKZHGHLSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_CKZHGHLSB对象", description="存款账号管户历史表")
public class KhgxglCkzhghlsb {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**账号类型*/
	@Excel(name = "账号类型", width = 15)
    @ApiModelProperty(value = "账号类型")
	private String zhlx;
	/**管户类型*/
	@Excel(name = "管户类型", width = 15)
    @ApiModelProperty(value = "管户类型")
	private Integer ghlx;
	/**管户人*/
	@Excel(name = "管户人", width = 15)
    @ApiModelProperty(value = "管户人")
	private String ghr;
	/**管户比例*/
	@Excel(name = "管户比例", width = 15)
    @ApiModelProperty(value = "管户比例")
	private java.math.BigDecimal ghbl;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**认领金额*/
	@Excel(name = "认领金额", width = 15)
    @ApiModelProperty(value = "认领金额")
	private java.math.BigDecimal rlje;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
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
	/**数据来源（1：预约报备 2：片区责任人 3：推荐人 4：同主账号 5：同证件号码 6：同管户人 7：虚拟柜员 8：办理柜员 9：对公默认综合户）*/
	@Excel(name = "数据来源（1：预约报备 2：片区责任人 3：推荐人 4：同主账号 5：同证件号码 6：同管户人 7：虚拟柜员 8：办理柜员 9：对公默认综合户）", width = 15)
    @ApiModelProperty(value = "数据来源（1：预约报备 2：片区责任人 3：推荐人 4：同主账号 5：同证件号码 6：同管户人 7：虚拟柜员 8：办理柜员 9：对公默认综合户）")
	private Integer sjly;


	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
}
