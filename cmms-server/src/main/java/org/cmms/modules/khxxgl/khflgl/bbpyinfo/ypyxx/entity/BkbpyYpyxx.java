package org.cmms.modules.khxxgl.khflgl.bbpyinfo.ypyxx.entity;

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
 * @Description: 背靠背评议-已评议信息
 * @Author: jeecg-boot
 * @Date:   2022-11-15
 * @Version: V1.0
 */
@Data
@TableName("V_KHXXGL_BKBPY_YPYXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHXXGL_BKBPY_YPYXX对象", description="背靠背评议-已评议信息")
public class BkbpyYpyxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**wgbh*/
	@Excel(name = "归属网格", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	/**hhbm*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**yhzgx*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**xb*/
	@Excel(name = "xb", width = 15)
    @ApiModelProperty(value = "xb")
	@Dict(dicCode = "sex")
	private String xb;
	/**nl*/
	@Excel(name = "nl", width = 15)
    @ApiModelProperty(value = "nl")
	private String nl;
	/**sjhm*/
	@Excel(name = "sjhm", width = 15)
    @ApiModelProperty(value = "sjhm")
	private String sjhm;
	/**zz*/
	@Excel(name = "zz", width = 15)
    @ApiModelProperty(value = "zz")
	private String zz;
	/**ckrpye*/
	@Excel(name = "ckrpye", width = 15)
    @ApiModelProperty(value = "ckrpye")
	private java.math.BigDecimal ckrpye;
	/**sfktsjyhyw*/
	@Excel(name = "sfktsjyhyw", width = 15)
    @ApiModelProperty(value = "sfktsjyhyw")
	@Dict(dicCode = "sfbz")
	private String sfktsjyhyw;
	/**pyls*/
	@Excel(name = "pyls", width = 15)
    @ApiModelProperty(value = "pyls")
	private Integer pyls;
	/**sxdxzjhm*/
	@Excel(name = "sxdxzjhm", width = 15)
    @ApiModelProperty(value = "sxdxzjhm")
	private String sxdxzjhm;
	/**sxdxmc*/
	@Excel(name = "sxdxmc", width = 15)
    @ApiModelProperty(value = "sxdxmc")
	private String sxdxmc;
	/**jysxed*/
	@Excel(name = "jysxed", width = 15)
    @ApiModelProperty(value = "jysxed")
	private java.math.BigDecimal jysxed;
	/**pyyjyed*/
	@Excel(name = "pyyjyed", width = 15)
    @ApiModelProperty(value = "pyyjyed")
	private Integer pyyjyed;
	/**ncfcqk*/
	@Excel(name = "ncfcqk", width = 15)
    @ApiModelProperty(value = "ncfcqk")
	@Dict(dicCode = "ncfcqk")
	private String ncfcqk;
	/**ncfcqkBz*/
	@Excel(name = "ncfcqkBz", width = 15)
    @ApiModelProperty(value = "ncfcqkBz")
	private String ncfcqkBz;
	/**cqywfc*/
	@Excel(name = "cqywfc", width = 15)
    @ApiModelProperty(value = "cqywfc")
	@Dict(dicCode = "ywbz")
	private String cqywfc;
	/**cqywfcBz*/
	@Excel(name = "cqywfcBz", width = 15)
    @ApiModelProperty(value = "cqywfcBz")
	private String cqywfcBz;
	/**ywcl*/
	@Excel(name = "ywcl", width = 15)
    @ApiModelProperty(value = "ywcl")
	@Dict(dicCode = "ywbz")
	private String ywcl;
	/**ywclBz*/
	@Excel(name = "ywclBz", width = 15)
    @ApiModelProperty(value = "ywclBz")
	private String ywclBz;
	/**sr*/
	@Excel(name = "sr", width = 15)
    @ApiModelProperty(value = "sr")
	@Dict(dicCode = "bkbpy_sr")
	private Integer sr;
	/**gzlx*/
	@Excel(name = "gzlx", width = 15)
    @ApiModelProperty(value = "gzlx")
	@Dict(dicCode = "gzlx")
	private String gzlx;
	/**cqjzd*/
	@Excel(name = "cqjzd", width = 15)
    @ApiModelProperty(value = "cqjzd")
	@Dict(dicCode = "sfzbd")
	private String cqjzd;
	/**cqjzdbz*/
	@Excel(name = "cqjzdbz", width = 15)
    @ApiModelProperty(value = "cqjzdbz")
	private String cqjzdbz;
	/**zyxm*/
	@Excel(name = "zyxm", width = 15)
    @ApiModelProperty(value = "zyxm")
	private String zyxm;
	/**sfzbd*/
	@Excel(name = "sfzbd", width = 15)
    @ApiModelProperty(value = "sfzbd")
	@Dict(dicCode = "sfzbd")
	private String sfzbd;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
}
