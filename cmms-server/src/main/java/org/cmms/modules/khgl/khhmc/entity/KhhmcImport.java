package org.cmms.modules.khgl.khhmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Data
@TableName("KHGL_KHHMCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_KHHMCXX对象", description="客户花名册")
public class KhhmcImport {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**所属营销单元*/
	@Excel(name = "区域编码", width = 15,dicCode="DYBH",dictTable="YXDYGL_EJYXDYGL",dicText="DYMC")
	private String ssyxdy;
	/**所属支行*/
	@Excel(name = "所属网点", width = 15,dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
	private String sszh;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
	private String yhzgx;
	/**是否户主*/
//	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
//	private String sfhz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	private String khlx;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**民族*/
	@Excel(name = "民族", width = 15, dicCode = "mz")
	private String mz;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15, dicCode = "hyzk")
	private String hyzk;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private String bz;
	/**原所属乡镇*/
	@Excel(name = "原所属乡镇", width = 15)
	private String yssxz;
	/**原所属乡镇*/
	@Excel(name = "原行政村", width = 15)
	private String yxzc;
}
