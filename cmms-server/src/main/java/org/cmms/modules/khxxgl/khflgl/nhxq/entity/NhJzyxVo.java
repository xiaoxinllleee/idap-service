package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 农户精准营销试图
 * @Author: jeecg-boot
 * @Date:   2023-05-19
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_khxq_nh_jzyx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_khxq_nh_jzyx对象", description="农户精准营销试图")
public class NhJzyxVo {

	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC")
	private String xzc;
	/**村组*/
	@Excel(name = "村组", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC")
	private String cz;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**姓名*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**评议金额*/
	@Excel(name = "评议金额", width = 15)
	private java.math.BigDecimal jcmxcs;
	/**评议员建议额度*/
	@Excel(name = "评议员建议额度", width = 15)
	private java.math.BigDecimal pyyjyed;
	/**支行审定额度*/
	@Excel(name = "支行审定额度", width = 15)
	private java.math.BigDecimal zhsded;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	private String sjhm;
	/**配偶姓名*/
	@Excel(name = "配偶姓名", width = 15)
	private String poxm;
	/**配偶证件号码*/
	@Excel(name = "配偶证件号码", width = 15)
	private String pozjhm;
	/**有无车辆*/
	@Excel(name = "有无车辆", width = 15,dicCode = "ywbz")
	private String ywcl;
	/**有无车辆备注*/
	@Excel(name = "有无车辆备注", width = 15)
	private String ywclBz;
	/**个人年收入*/
	@Excel(name = "个人年收入", width = 15,dicCode = "bkbpy_sr")
	private String grnsr;
	/**长期居住地*/
	@Excel(name = "长期居住地", width = 15)
	private String cqjzdxq;
	/**长期居住地备注*/
	@Excel(name = "长期居住地备注", width = 15)
	private String cqjzdbz;
	/**农村房产情况*/
	@Excel(name = "农村房产情况", width = 15,dicCode = "ncfcqk")
	private String ncfcqk;
	/**农村房产情况备注*/
	@Excel(name = "农村房产情况备注", width = 15)
	private String ncfcqkBz;
	/**城区有无房产*/
	@Excel(name = "城区有无房产", width = 15,dicCode = "ywbz")
	private String cqywfc;
	/**城区房产位置*/
	@Excel(name = "城区房产位置", width = 15,dicCode = "ty_cqfcwz")
	private String cqfcwz;
	/**客户分群*/
	@Excel(name = "客户分类", width = 15,dicCode = "ty_khfl")
	private String khfl;
	/**行业分类*/
	@Excel(name = "行业分类", width = 15,dicCode = "ty_hyfl")
	private String hyfl;
	/**就业分类*/
	@Excel(name = "就业分类", width = 15,dicCode = "ty_jyfl")
	private String jyfl;
	/**就业地点*/
	@Excel(name = "就业地点", width = 15,dicCode = "ty_jydd")
	private String jydd;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15,dicCode = "hyzk")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
	/**营销方式*/
	@Excel(name = "走访营销方式", width = 15,dicCode = "zfyxfs")
	private String zfyxfs;
	/**是否完成精准营销*/
	@Excel(name = "是否完成精准营销", width = 15,dicCode = "sfbz")
	private String sfwcjzyx;
	/**完成精准营销时间*/
	@Excel(name = "完成精准营销时间", width = 15,format = "yyyy-MM-dd")
	private Date wcjzyxsj;
}
