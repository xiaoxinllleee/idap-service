package org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.entity;

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
 * @Description: 家庭成员信息（新增待审批）
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
@Data
@TableName("khgl_nhjtcy_xz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_nhjtcy_xz对象", description="家庭成员信息（新增待审批）")
public class NhjtcyXzSP {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**原户号编码*/
	@Excel(name = "原户号编码", width = 15)
    @ApiModelProperty(value = "原户号编码")
	private String oldHhbm;
	/**原所属网格*/
	@Excel(name = "原所属网格", width = 15, dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
    @ApiModelProperty(value = "原所属网格")
	@Dict(dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
	private String oldSswg;
	/**原户主姓名*/
	@Excel(name = "原户主姓名", width = 15)
    @ApiModelProperty(value = "原户主姓名")
	private String oldHzxm;
	/**与原户主关系*/
	@Excel(name = "与原户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与原户主关系")
	@Dict(dicCode = "yhzgx")
	private String oldYhzgx;
	/**新户号编码*/
	@Excel(name = "新户号编码", width = 15)
    @ApiModelProperty(value = "新户号编码")
	private String hhbm;
	/**新所属网格*/
	@Excel(name = "新所属网格", width = 15, dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
	@ApiModelProperty(value = "新所属网格")
	@Dict(dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
	private String sswg;
	/**新户主姓名*/
	@Excel(name = "新户主姓名", width = 15)
    @ApiModelProperty(value = "新户主姓名")
	private String hzxm;
	/**与新户主关系*/
	@Excel(name = "与新户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与新户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**审核状态(0 未审核 1 审核通过 2 驳回)*/
	@Excel(name = "审核状态", width = 15, dicCode = "approval_status")
    @ApiModelProperty(value = "审核状态(0 未审核 1 审核通过 2 驳回)")
	@Dict(dicCode = "approval_status")
	private String shzt;
	/**审核/驳回批注*/
	@Excel(name = "审核/驳回批注", width = 15)
    @ApiModelProperty(value = "审核/驳回批注")
	private String shpz;
	/**录入标识*/
	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入人*/
	@Excel(name = "操作人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入操作时间")
	private Date lrczsj;
}
