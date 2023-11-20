package org.cmms.modules.khgl.grkhgl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 视图
 * @Author: jeecg-boot
 * @Date:   2020-08-12
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_GRKHGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGL_GRKHGL对象", description="视图")
public class VKhglGrkhgl {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
	private String yhzgx;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String zz;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String xb;
	/**年龄*/
	//@Excel(name = "年龄", width = 15)
    //@ApiModelProperty(value = "年龄")
	//private Integer nl;
	/**民族*/
	//@Excel(name = "民族", width = 15)
    //@ApiModelProperty(value = "民族")
	//private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
    @ApiModelProperty(value = "原所属乡镇")
	private String yssxz;
	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
    @ApiModelProperty(value = "原行政村")
	private String yxzc;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**出生年月*/
	@Excel(name = "出生年月", width = 15)
    @ApiModelProperty(value = "出生年月")
	private String csny;
	/**从事职业*/
	@Excel(name = "从事职业", width = 15)
    @ApiModelProperty(value = "从事职业")
	private String cszy;
	/**是否主客户经理*/
	@Excel(name = "是否主客户经理", width = 15)
    @ApiModelProperty(value = "是否主客户经理")
	private String sfzkhjl;
	/**一级营销单元编号*/
	@Excel(name = "一级营销单元编号", width = 15)
    @ApiModelProperty(value = "一级营销单元编号")
	private String yjyxdybh;
	/**二级营销单元编号*/
	@Excel(name = "二级营销单元编号", width = 15)
    @ApiModelProperty(value = "二级营销单元编号")
	private String ejyxdybh;
	/**三级营销单元编号*/
	@Excel(name = "三级营销单元编号", width = 15)
    @ApiModelProperty(value = "三级营销单元编号")
	private String sjyxdybh;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**家庭人数*/
	//@Excel(name = "家庭人数", width = 15)
    //@ApiModelProperty(value = "家庭人数")
	//private Integer jtrs;
	/**健康状况*/
	//@Excel(name = "健康状况", width = 15)
    //@ApiModelProperty(value = "健康状况")
	//private String jkzk;
	/**最高学历*/
	//@Excel(name = "最高学历", width = 15)
    //@ApiModelProperty(value = "最高学历")
	//private String zgxl;
	/**职称*/
	//@Excel(name = "职称", width = 15)
    //@ApiModelProperty(value = "职称")
	//private String zc;
	/**职业*/
	//@Excel(name = "职业", width = 15)
    //@ApiModelProperty(value = "职业")
	//private String zy;
	/**采集时间*/
	@Excel(name = "采集时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "采集时间")
	private Date cjsj;
	/**采集人*/
	@Excel(name = "采集人", width = 15)
    @ApiModelProperty(value = "采集人")
	private String cjr;
	/**是否采集*/
	@Excel(name = "是否采集", width = 15)
    @ApiModelProperty(value = "是否采集")
	private Integer sfcj;
	/**sfsxdx*/
	@Excel(name = "sfsxdx", width = 15)
    @ApiModelProperty(value = "sfsxdx")
	private String sfsxdx;
}
