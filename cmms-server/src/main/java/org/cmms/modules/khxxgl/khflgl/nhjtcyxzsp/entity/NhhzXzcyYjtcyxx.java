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
 * @Description: 新增成员信息（户主）原家庭成员信息
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("khgl_nhhzxzcy_yjtcyxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_nhhzxzcy_yjtcyxx对象", description="新增成员信息（户主）原家庭成员信息")
public class NhhzXzcyYjtcyxx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**待审核信息ID*/
	@Excel(name = "待审核信息ID", width = 15)
	@ApiModelProperty(value = "待审核信息ID")
	private String shid;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String sszh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "归属机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15, dicCode = "id", dictTable = "v_yxdygl_main", dicText = "wgmc_show")
	@ApiModelProperty(value = "归属网格")
	@Dict(dicCode = "id", dictTable = "v_yxdygl_main", dicText = "wgmc_show")
	private String wgbh;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "所属客户经理")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String sskhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "clkhlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "clkhlx")
	private String khlx;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**名族*/
	@Excel(name = "名族", width = 15, dicCode = "mz")
    @ApiModelProperty(value = "名族")
	@Dict(dicCode = "mz")
	private String mz;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**与新户主关系*/
	@Excel(name = "与新户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与新户主关系")
	@Dict(dicCode = "yhzgx")
	private String xyhzgx;
	/**与原户主关系*/
	@Excel(name = "与原户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与原户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**系统评定结果：1-灰名单，2-白名单，3-黑名单*/
	@Excel(name = "系统评定结果", width = 15, dicCode = "xtpdjg")
    @ApiModelProperty(value = "系统评定结果")
	@Dict(dicCode = "xtpdjg")
	private String xtpdjg;
	/**系统评定说明*/
	@Excel(name = "系统评定说明", width = 15)
	@ApiModelProperty(value = "系统评定说明")
	private String xtpdsm;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
