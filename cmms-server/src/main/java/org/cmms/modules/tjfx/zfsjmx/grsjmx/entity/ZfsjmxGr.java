package org.cmms.modules.tjfx.zfsjmx.grsjmx.entity;

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
 * @Description: 个人走访数据统计
 * @Author: jeecg-boot
 * @Date:   2020-03-20
 * @Version: V1.0
 */
@Data
@TableName("TJFX_ZFSJMX_GR")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_ZFSJMX_GR对象", description="个人走访数据统计")
public class ZfsjmxGr {

	/**走访日期*/
	@Excel(name = "走访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "走访日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15, dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	@ApiModelProperty(value = "行政村")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	private String xzc;
	/**行政组*/
	@ApiModelProperty(value = "行政组")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_SJYXDYGL", dicText = "DYMC")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15, dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "责任人")
    @Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否户主")
    @Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "khlx")
	private String khlx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
    /**是否有效走访*/
    @Excel(name = "是否有效走访", width = 15, dicCode = "sfbz_ex")
    @ApiModelProperty(value = "是否有效走访")
    @Dict(dicCode = "sfbz_ex")
    private String sfyxzf;
	/**字段采集完整度*/
	@Excel(name = "字段采集完整度", width = 15)
	@ApiModelProperty(value = "字段采集完整度")
	private String zdcjwzd;
	/**是否上传附件*/
	@Excel(name = "是否上传附件", width = 15, dicCode = "sfbz_ex")
	@ApiModelProperty(value = "是否上传附件")
	@Dict(dicCode = "sfbz_ex")
	private String sfscfj;
	/**符合采集标准家庭人数*/
	@Excel(name = "符合采集标准家庭人数", width = 15)
	@ApiModelProperty(value = "符合采集标准家庭人数")
	private Long jtrs;
	/**实际采集人数*/
	@Excel(name = "实际采集人数", width = 15)
	@ApiModelProperty(value = "实际采集人数")
	private Long cjrs;
	/**采集字段达标家庭人数*/
	@Excel(name = "采集字段达标家庭人数", width = 15)
	@ApiModelProperty(value = "采集字段达标家庭人数")
	private Long zddbjtrs;
	/**单户附件数量*/
	@Excel(name = "单户附件数量", width = 15)
	@ApiModelProperty(value = "单户附件数量")
	private Long dhfjsl;
	/**采集人*/
	@Excel(name = "采集人", width = 15, dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	@ApiModelProperty(value = "采集人")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String cjr;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**是否外出打工*/
	@Excel(name = "是否外出打工", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否外出打工")
	@Dict(dicCode = "sfbz")
	private String sfycdg;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15,dicCode = "khgl_khzycd")
	@ApiModelProperty(value = "客户重要程度")
	@Dict(dicCode = "khgl_khzycd")
	private String kfyyqk;
	/**备用号码*/
	@Excel(name = "备用号码", width = 15)
	@ApiModelProperty(value = "备用号码")
	private String byhm;
	/**住址*/
	@Excel(name = "住址", width = 15)
	@ApiModelProperty(value = "住址")
	private String zz;

}
