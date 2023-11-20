package org.cmms.modules.khgl.wcwgkhmx.entity;

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
 * @Description: 外出务工客户明细
 * @Author: penghr
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Data
@TableName("KHGL_WCWGKHMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_WCWGKHMX对象", description="外出务工客户明细")
public class Wcwgkhmx {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**区域代码*/
	//@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**所属村编码*/
	@Excel(name = "所属村编码", width = 15)
    @ApiModelProperty(value = "所属村编码")
	private String sscbm;
	/**所属村名称*/
	@Excel(name = "所属村名称", width = 15)
    @ApiModelProperty(value = "所属村名称")
	private String sscmc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
    @Dict(dicCode = "sex")
    private String xb;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15, dicCode = "khgl_hkxz")
    @ApiModelProperty(value = "户口性质")
    @Dict(dicCode = "khgl_hkxz")
	private String hkxz;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**从事行业*/
	@Excel(name = "从事行业", width = 15, dicCode = "cshyfl")
    @ApiModelProperty(value = "从事行业")
    @Dict(dicCode = "cshyfl")
	private String cshy;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
    @ApiModelProperty(value = "工作单位")
	private String gzdw;
    /**工作单位地址*/
    @Excel(name = "工作单位地址", width = 15)
    @ApiModelProperty(value = "工作单位地址")
    private String gzdwdz;
	/**预计返乡时间*/
	@Excel(name = "预计返乡时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预计返乡时间")
	private Date yjfxsj;
}
