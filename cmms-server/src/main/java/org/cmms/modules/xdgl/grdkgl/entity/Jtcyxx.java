package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 家庭成员
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("KHGL_KHHMCXX_GRXD")
public class Jtcyxx implements Serializable {
    private static final long serialVersionUID = 1L;
	/**从事职业*/
	@Excel(name = "从事行业", width = 15)
	private String cshy;

	/**从事职业*/
    @Excel(name = "从事职业", width = 15)
	private String cszy;
	/**户籍地址*/
    @Excel(name = "户籍地址", width = 15)
	private String hjdz;
	/**手机号码*/
    @Excel(name = "手机号码", width = 15)
	private String sjhm;
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**所属支行*/
    @Excel(name = "所属支行", width = 15)
	private String sszh;
	/**所属营销单元*/
    @Excel(name = "所属营销单元", width = 15)
	private String ssyxdy;
	/**户号编码*/
	private String hhbm;
	/**与户主关系*/
    @Excel(name = "与户主关系", width = 15)
	private String yhzgx;
	/**是否户主*/
    @Excel(name = "是否户主", width = 15)
	private String sfhz;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
    @Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**客户类型*/
    @Excel(name = "客户类型", width = 15)
	private String khlx;
	/**地址*/
    @Excel(name = "地址", width = 15)
	private String dz;
	/**性别*/
    @Excel(name = "性别", width = 15)
	private String xb;
	/**年龄*/
    @Excel(name = "年龄", width = 15)
	private String nl;
	/**民族*/
    @Excel(name = "民族", width = 15)
	private String mz;
	/**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15)
	private String hyzk;
	/**备注*/
    @Excel(name = "备注", width = 15)
	private String bz;
	/**录入标识*/
    @Excel(name = "录入标识", width = 15)
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**录入人*/
    @Excel(name = "录入人", width = 15)
	private String lrr;
	/**原所属乡镇*/
    @Excel(name = "原所属乡镇", width = 15)
	private String yssxz;
	/**机构代码*/
    @Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date xgsj;
	/**修改人*/
    @Excel(name = "修改人", width = 15)
	private String xgr;
	/**建档完整度*/
    @Excel(name = "建档完整度", width = 15)
	private String infoRate;
	/**出生年月*/
    @Excel(name = "出生年月", width = 15)
	private String csny;
}
