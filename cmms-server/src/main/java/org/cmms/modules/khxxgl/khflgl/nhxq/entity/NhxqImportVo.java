package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * @Description: 农户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_NH对象", description="农户采集信息")
public class NhxqImportVo {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格编号", width = 15,dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
    @ApiModelProperty(value = "归属网格编号")
	@Dict(dicCode="ID",dictTable="YXDYGL_MAIN",dicText="WGBH")
	private String wgbh;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "clkhlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "clkhlx")
	private String khlx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	@ExcelVerify(notNull = true)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;
	/**民族*/
	@Excel(name = "民族", width = 15,dicCode = "mz")
    @ApiModelProperty(value = "民族")
	@Dict(dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk")
    @ApiModelProperty(value = "婚姻状况")
	@Dict(dicCode = "hyzk")
	private String hyzk;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15,dicCode = "khgl_hkxz")
    @ApiModelProperty(value = "户口性质")
	@Dict(dicCode = "khgl_hkxz")
	private String hkxz;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15,dicCode = "whcd")
    @ApiModelProperty(value = "文化程度")
	@Dict(dicCode = "whcd")
	private String whcd;
	/**职业*/
	@Excel(name = "职业", width = 15,dicCode = "cszy")
    @ApiModelProperty(value = "职业")
	@Dict(dicCode = "cszy")
	private String cshygz;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;

	/**常住地址*/
	@Excel(name = "常住地址", width = 15)
    @ApiModelProperty(value = "常住地址")
	private String zz;

	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
//	@ExcelVerify(interHandler = true)
	private String bz;

	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	@ApiModelProperty(value = "原所属乡镇")
	private String yssxz;

	/**原行政村*/
	@Excel(name = "原行政村", width = 15)
	@ApiModelProperty(value = "原行政村")
	private String yxzc;

	/**是否死亡*/
	@Excel(name = "是否死亡", width = 15,dicCode="sfbz")
	@ApiModelProperty(value = "是否死亡")
	@Dict(dicCode="sfbz")
	private String sfsw;

}
