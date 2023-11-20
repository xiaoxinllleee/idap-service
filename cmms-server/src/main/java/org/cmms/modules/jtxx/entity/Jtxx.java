package org.cmms.modules.jtxx.entity;

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
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
@Data
@TableName("Jtxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Jtxx对象", description="家庭信息")
public class Jtxx {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
	private String name;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String sex;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**1-正常,2-禁用*/
	@Excel(name = "1-正常,2-禁用", width = 15)
    @ApiModelProperty(value = "1-正常,2-禁用")
	@Dict(dicCode = "bwtzgl_status")
	private String zt;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
	private Date rq;
	/**家庭地址*/
	@Excel(name = "家庭地址", width = 15)
	@ApiModelProperty(value = "家庭地址")
	private String address;
	/**婚姻状态*/
	@Excel(name = "婚姻状态", width = 15)
	@ApiModelProperty(value = "婚姻状态")
	@Dict(dicCode = "hyzk")
	private String hyzt;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**婚姻状态*/
	@Excel(name = "与户主关系", width = 15)
	@ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	@Dict(dicCode = "khsf")
	private String khsf;

}
