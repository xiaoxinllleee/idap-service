package org.cmms.modules.khgl.khjjrzgl.entity;

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
 * @Description: 客户交接日志管理
 * @Author: jeecg-boot
 * @Date:   2020-02-14
 * @Version: V1.0
 */
@Data
@TableName("KHGL_KHJJRZGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_KHJJRZGL对象", description="客户交接日志管理")
public class Khjjrzgl {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="qybm",dictTable="YXDYGL_CZXXGL",dicText="village || organize")
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode="qybm",dictTable="YXDYGL_CZXXGL",dicText="village || organize")
	private String ssyxdy;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**交接前组织标识*/
	@Excel(name = "交接前组织标识", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "交接前组织标识")
    @Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String yzzbz;
	/**交接后组织标识*/
	@Excel(name = "交接后组织标识", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "交接后组织标识")
    @Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String xzzbz;
	/**交接前客户经理*/
	@Excel(name = "交接前客户经理", width = 15, dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "交接前客户经理")
    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String ykhjl;
	/**交接后客户经理*/
	@Excel(name = "交接后客户经理", width = 15, dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "交接后客户经理")
    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String xkhjl;
	/**交接人*/
	@Excel(name = "交接人", width = 15)
    @ApiModelProperty(value = "交接人")
	private String createBy;
	/**交接时间*/
	@Excel(name = "交接时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "交接时间")
	private Date createTime;
}
