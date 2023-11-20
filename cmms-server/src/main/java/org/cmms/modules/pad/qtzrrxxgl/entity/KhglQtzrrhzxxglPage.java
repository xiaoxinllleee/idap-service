package org.cmms.modules.pad.qtzrrxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-30
 * @Version: V1.0
 */
@Data
@TableName("khgl_qtzrrhzxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_qtzrrhzxxgl对象", description="1")
public class KhglQtzrrhzxxglPage {
    
	/**是否不良贷款客户*/
	@Excel(name = "是否不良贷款客户", width = 15)
    @ApiModelProperty(value = "是否不良贷款客户")
	private String sfbldkh;
	/**是否贫困户*/
	@Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
	private String sfpkh;
	/**是否低保户*/
	@Excel(name = "是否低保户", width = 15)
    @ApiModelProperty(value = "是否低保户")
	private String sfdbh;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15)
    @ApiModelProperty(value = "客户重要程度")
	private String khzycd;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private String khqzyw;
	/**客户授信情况*/
	@Excel(name = "客户授信情况", width = 15)
    @ApiModelProperty(value = "客户授信情况")
	private String khsxqk;
	/**客户等级*/
	@Excel(name = "客户等级", width = 15)
    @ApiModelProperty(value = "客户等级")
	private String khdj;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
	private String latitude;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**户口性质*/
	@Excel(name = "户口性质", width = 15)
    @ApiModelProperty(value = "户口性质")
	private String hkxz;

	/**陪访人*/
	@Excel(name = "陪访人", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**授信对象证件号*/
	@Excel(name = "授信对象证件号", width = 15)
    @ApiModelProperty(value = "授信对象证件号")
	private String sxdxzjh;
	/**户主证件号码*/
	@Excel(name = "户主证件号码", width = 15)
    @ApiModelProperty(value = "户主证件号码")
	private String hzzjhm;
	/** ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = " ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")

	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	@Dict( dicCode="QYBM", dictTable="YXDYGL_CZXXGL", dicText="town||VILLAGE || ORGANIZE")
	private String ssyxdy;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户主姓名*/
	@Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "户主姓名")
	private String hzxm;
	/**授信对象*/
	@Excel(name = "授信对象", width = 15)
    @ApiModelProperty(value = "授信对象")
	private String sxdx;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
	/**信息完善*/
	@Excel(name = "信息完善", width = 15)
    @ApiModelProperty(value = "信息完善")
	private String xxws;
	/**评级授信*/
	@Excel(name = "评级授信", width = 15)
    @ApiModelProperty(value = "评级授信")
	private String pjsx;
	/**用信状态*/
	@Excel(name = "用信状态", width = 15)
    @ApiModelProperty(value = "用信状态")
	private String yxzt;

	@ExcelCollection(name="农户附件信息")
	private List<KhglQtzrrhzzllb> hkbuploadfiles;

	private List<String> hkbdeletefiles;
}
