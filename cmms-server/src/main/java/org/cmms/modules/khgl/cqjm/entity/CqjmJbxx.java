package org.cmms.modules.khgl.cqjm.entity;

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
 * @Description: 城区居民功能包
 * @Author: jeecg-boot
 * @Date:   2020-02-20
 * @Version: V1.0
 */
@Data
@TableName("KHGL_CQJMJBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_CQJMJBXX对象", description="城区居民功能包")
public class CqjmJbxx {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
    /**档案编号*/
    @Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
    private String dabh;
    /**客户重要程度*/
    @Excel(name = "客户重要程度", width = 15, dicCode = "khzycd")
    @ApiModelProperty(value = "客户重要程度")
    @Dict(dicCode = "khzycd")
    private String khzycd;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**所属营销单元*/
	//@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode = "QYBM", dictTable = "YXDYGL_CZXXGL", dicText = "VILLAGE || ORGANIZE")
	private String ssyxdy;
	/**所属社区编码*/
	//@Excel(name = "所属社区", width = 15, dicCode = "QYBM", dictTable = "V_CZXXGL", dicText = "VILLAGE")
    @ApiModelProperty(value = "所属社区编码")
    @Dict(dicCode = "QYBM", dictTable = "V_CZXXGL", dicText = "VILLAGE")
	private String sscbm;
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
    private Integer age;
    /**性别*/
    @Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
    @Dict(dicCode = "sex")
    private String sex;
    /**民族*/
    @Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String nation;
    /**文化程度*/
    @Excel(name = "文化程度", width = 15, dicCode = "whcd")
    @ApiModelProperty(value = "文化程度")
    @Dict(dicCode = "whcd")
    private String whcd;
    /**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15, dicCode = "hyzk")
    @ApiModelProperty(value = "婚姻状况")
    @Dict(dicCode = "hyzk")
    private String hyzk;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String address;
    /**户口性质*/
    @Excel(name = "户口性质", width = 15, dicCode = "khgl_hkxz")
    @ApiModelProperty(value = "户口性质")
    @Dict(dicCode = "khgl_hkxz")
    private String hkxz;
    /**户号编码*/
    @Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
    private String hhbm;
    /**是否户主*/
    @Excel(name = "是否户主", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否户主")
    @Dict(dicCode = "sfbz")
    private String sfhz;
    /**与户主关系*/
    @Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
    @Dict(dicCode = "yhzgx")
    private String yhzgx;
    /**户籍地址*/
    @Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
    private String hjdz;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否贫困户")
    @Dict(dicCode = "sfbz")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否低保户")
    @Dict(dicCode = "sfbz")
	private String sfdbh;
	/**从事行业*/
	@Excel(name = "从事行业", width = 15)
    @ApiModelProperty(value = "从事行业")
	private String cshy;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
    @ApiModelProperty(value = "工作单位")
	private String gzdw;
	/**单位地址*/
	@Excel(name = "单位地址", width = 15)
    @ApiModelProperty(value = "单位地址")
	private String dwdz;
	/**是否外出务工*/
	@Excel(name = "是否外出务工", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否外出务工")
    @Dict(dicCode = "sfbz")
	private String sfwcwg;
	/**最早返乡时间*/
	@Excel(name = "最早返乡时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早返乡时间")
	private Date zzfxsj;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15, dicCode = "khqzyw")
    @ApiModelProperty(value = "客户潜在业务")
    @Dict(dicCode = "khqzyw")
	private String khqzyw;
    /**所属客户经理*/
    @Excel(name = "所属客户经理", width = 15, dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "所属客户经理")
    @Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    private String sskhjl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
    /**建档完整度*/
    @Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
    private java.math.BigDecimal jdwzd;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
}
