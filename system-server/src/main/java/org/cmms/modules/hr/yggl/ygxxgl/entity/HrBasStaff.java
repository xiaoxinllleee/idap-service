package org.cmms.modules.hr.yggl.ygxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工信息
 * @Author: jeecg-boot
 * @Date:   2021-01-12
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_STAFF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Hr_bas_staff对象", description="员工信息")
public class HrBasStaff {
	/**员工工号*/
	@TableId(type = IdType.ASSIGN_ID)
	@Excel(name = "员工工号", width = 15)
	@ApiModelProperty(value = "员工工号")
	@ExcelVerify(notNull = true, interHandler = true)
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
	@ApiModelProperty(value = "员工姓名")
	@ExcelVerify(notNull = true)
	private String ygxm;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
	@ApiModelProperty(value = "柜员号")
	private String gyh;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
	@ApiModelProperty(value = "客户经理编号")
	private String khjlbh;
	/**员工类型*/
	@Excel(name = "员工类型", width = 15, dicCode = "yglx")
	@ApiModelProperty(value = "员工类型")
	@Dict(dicCode = "yglx")
	private Integer yglx;
	/**员工状态*/
	@Excel(name = "员工状态", width = 15, dicCode = "ygzt")
	@ApiModelProperty(value = "员工状态")
	@Dict(dicCode = "ygzt")
	private Integer ygzt;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private Integer xb;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
	@ApiModelProperty(value = "身份证号")
	private String sfzh;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**工资卡号*/
	@Excel(name = "工资卡号", width = 15)
	@ApiModelProperty(value = "工资卡号")
	private String gzkh;
	/**入职日期*/
	@Excel(name = "入职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "入职日期")
	private Date rzrq;
	/**转正日期*/
	@Excel(name = "转正日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "转正日期")
	private Date zzrq;
	/**离职日期*/
	@Excel(name = "离职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "离职日期")
	private Date lzrq;
	/**退休日期*/
	@Excel(name = "退休日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "退休日期")
	private Date txrq;
	/**删除标志*/
//	@Excel(name = "删除标志", width = 15)
	@ApiModelProperty(value = "删除标志")
	private Integer scbz;
	/**删除时间*/
//	@Excel(name = "删除时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "删除时间")
	private Date scsj;
	/**删除操作员*/
//	@Excel(name = "删除操作员", width = 15)
	@ApiModelProperty(value = "删除操作员")
	private String scczy;

}
