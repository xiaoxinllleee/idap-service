package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 个人客户家庭成员信息
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
@Data
@TableName("KHGL_GRKHJTCY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_GRKHJTCY对象", description="个人客户家庭成员信息")
public class Clgrkhjtcy {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**客户ID*/
	@Excel(name = "客户ID", width = 15)
    @ApiModelProperty(value = "客户ID")
	private String khId;
	/**与客户关系*/
	@Excel(name = "与客户关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与客户关系")
    @Dict(dicCode = "yhzgx")
	private String ykhgx;
	/**成员姓名*/
	@Excel(name = "成员姓名", width = 15)
    @ApiModelProperty(value = "成员姓名")
	private String cyxm;
	/**成员证件类型*/
	@Excel(name = "成员证件类型", width = 15, dicCode = "dkjkpt_zjlx")
    @ApiModelProperty(value = "成员证件类型")
    @Dict(dicCode = "dkjkpt_zjlx")
	private String cyzjlx;
	/**成员证件号码*/
	@Excel(name = "成员证件号码", width = 15)
    @ApiModelProperty(value = "成员证件号码")
	private String cyzjhm;
	/**成员联系电话*/
	@Excel(name = "成员联系电话", width = 15)
    @ApiModelProperty(value = "成员联系电话")
	private String cylxdh;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
    /**录入标识*/
    @Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
    private String lrbz;
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
}
