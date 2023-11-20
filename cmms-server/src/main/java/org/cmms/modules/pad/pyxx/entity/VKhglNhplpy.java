package org.cmms.modules.pad.pyxx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 农户批量评议客户视图
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_nhplpy")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_nhplpy对象", description="农户批量评议客户视图")
public class VKhglNhplpy {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	private String sszh;
	/**ssxz*/
	@Excel(name = "ssxz", width = 15)
    @ApiModelProperty(value = "ssxz")
	private String ssxz;
	/**xzc*/
	@Excel(name = "xzc", width = 15)
    @ApiModelProperty(value = "xzc")
	private String xzc;
	/**ssyxdy*/
	@Excel(name = "ssyxdy", width = 15)
    @ApiModelProperty(value = "ssyxdy")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String ssyxdy;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**hzxm*/
	@Excel(name = "hzxm", width = 15)
    @ApiModelProperty(value = "hzxm")
	private String hzxm;
	/**hzzjhm*/
	@Excel(name = "hzzjhm", width = 15)
    @ApiModelProperty(value = "hzzjhm")
	private String hzzjhm;
	/**sxdx*/
	@Excel(name = "sxdx", width = 15)
    @ApiModelProperty(value = "sxdx")
	private String sxdx;
	/**sxdxzjh*/
	@Excel(name = "sxdxzjh", width = 15)
    @ApiModelProperty(value = "sxdxzjh")
	private String sxdxzjh;
	/**khlx*/
	@Excel(name = "khlx", width = 15)
    @ApiModelProperty(value = "khlx")
	private String khlx;
	/**sfplpy*/
	@Excel(name = "sfplpy", width = 15)
    @ApiModelProperty(value = "sfplpy")
	private String sfplpy;
	/**pyyzjhm*/
	@Excel(name = "pyyzjhm", width = 15)
	@ApiModelProperty(value = "pyyzjhm")
	private String pyyzjhm;
	/**jysxed*/
	@Excel(name = "jysxed", width = 15)
	@ApiModelProperty(value = "jysxed")
	private BigDecimal jysxed;
	/**pyyjyed*/
	@Excel(name = "pyyjyed", width = 15)
	@ApiModelProperty(value = "pyyjyed")
	private BigDecimal pyyjyed;
	/**sfsd*/
	@Excel(name = "sfsd", width = 15)
	@ApiModelProperty(value = "sfsd")
	private String sfsd;
	/**sfwbdbh*/
	@Excel(name = "sfwbdbh", width = 15)
	@ApiModelProperty(value = "sfwbdbh")
	private String sfwbdbh;
	/**sfzpry*/
	@Excel(name = "sfzpry", width = 15)
	@ApiModelProperty(value = "sfzpry")
	private String sfzpry;
	/**sfbysx*/
	@Excel(name = "sfbysx", width = 15)
	@ApiModelProperty(value = "sfbysx")
	private String sfbysx;
	/**sffhnl*/
	@Excel(name = "sffhnl", width = 15)
	@ApiModelProperty(value = "sffhnl")
	private String sffhnl;
	/**ckye*/
	@Excel(name = "ckye", width = 15)
	@ApiModelProperty(value = "ckye")
	private BigDecimal ckye;
	/**ckrpye*/
	@Excel(name = "ckrpye", width = 15)
	@ApiModelProperty(value = "ckrpye")
	private BigDecimal ckrpye;
	/**cknrpye*/
	@Excel(name = "cknrpye", width = 15)
	@ApiModelProperty(value = "cknrpye")
	private BigDecimal cknrpye;
	/**zcdkye*/
	@Excel(name = "zcdkye", width = 15)
	@ApiModelProperty(value = "zcdkye")
	private BigDecimal zcdkye;
	/**bnbldkye*/
	@Excel(name = "bnbldkye", width = 15)
	@ApiModelProperty(value = "bnbldkye")
	private BigDecimal bnbldkye;
	/**bwbldkye*/
	@Excel(name = "bwbldkye", width = 15)
	@ApiModelProperty(value = "bwbldkye")
	private BigDecimal bwbldkye;
	/**sfyzcdkye*/
	@Excel(name = "sfyzcdkye", width = 15)
	@ApiModelProperty(value = "sfyzcdkye")
	private String sfyzcdkye;
	/**sfybnbldkye*/
	@Excel(name = "sfybnbldkye", width = 15)
	@ApiModelProperty(value = "sfybnbldkye")
	private String sfybnbldkye;
	/**sfybwbldkye*/
	@Excel(name = "sfybwbldkye", width = 15)
	@ApiModelProperty(value = "sfybwbldkye")
	private String sfybwbldkye;
	/**sfss*/
	@Excel(name = "sfss", width = 15)
	@ApiModelProperty(value = "sfss")
	private String sfss;
	/**sffx*/
	@Excel(name = "sffx", width = 15)
	@ApiModelProperty(value = "sffx")
	private String sffx;
	/**sfsx*/
	@Excel(name = "sfsx", width = 15)
	@ApiModelProperty(value = "sfsx")
	private String sfsx;
	/**bysxqx*/
	@Excel(name = "bysxqx", width = 15)
	@ApiModelProperty(value = "bysxqx")
	private String bysxqx;
	/**pycs*/
	@Excel(name = "pycs", width = 15)
	@ApiModelProperty(value = "pycs")
	private Integer pycs;

	private String hjdz;
	private String yxzc;
	private String ysswg;
	private String zfsfbysx;
	private String sfhhmd;
	/** 是否被走访（包括本人和他人） */
	private String sfbpy;
}
