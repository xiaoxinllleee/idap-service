package org.cmms.modules.xdgl.grdkgl.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 抵押担保
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_DYDB")
@ToString
public class Dydb implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.NONE)
	private String id;
	/**客户名称*/
    @Excel(name = "客户名称", width = 15)
	private String khmc;
	/**证件号码*/
	private String zjhm;
	/**创建人*/
    @Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**更新人*/
    @Excel(name = "更新人", width = 15)
	private String updateBy;
	/**更新时间
*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**抵押物类型*/
    @Excel(name = "抵押物类型", width = 15)
	private String zywlx;
	/**所有权人*/
    @Excel(name = "所有权人", width = 15)
	private String syqr;
	/**与被调查人关系*/
    @Excel(name = "与被调查人关系", width = 15)
	private String ybdcrgx;
	/**评估值*/
    @Excel(name = "评估值", width = 15)
	private java.math.BigDecimal pgz;
	/**使用权面积*/
    @Excel(name = "使用权面积", width = 15)
	private java.math.BigDecimal syqmj;
	/**具体位置*/
    @Excel(name = "具体位置", width = 15)
	private String jtwz;
	/**不动产登记证号*/
    @Excel(name = "不动产登记证号", width = 15)
	private String bdcdjzh;
	/**不动产用途*/
    @Excel(name = "不动产用途", width = 15)
	private String bdcyt;
	/**不动产建成时间*/
    @Excel(name = "不动产建成时间", width = 15)
	private String bdcjcsj;
	/**不动产每平方单价*/
    @Excel(name = "不动产每平方单价", width = 15)
	private java.math.BigDecimal bdcmpfdj;
	/**所有权人电话*/
    @Excel(name = "所有权人电话", width = 15)
	private Long syqrdh;
	/**抵押贷款金额*/
    @Excel(name = "抵押贷款金额", width = 15)
	private Long dydkje;
	/**土地证号*/
    @Excel(name = "土地证号", width = 15)
	private String tdzh;
	/**土地类型*/
    @Excel(name = "土地类型", width = 15)
	private String tdlx;
	/**土地用途*/
    @Excel(name = "土地用途", width = 15)
	private String tdyt;
	/**土地使用面积*/
    @Excel(name = "土地使用面积", width = 15)
	private java.math.BigDecimal tdsymj;
	/**土地终止日期*/
    @Excel(name = "土地终止日期", width = 15)
	private String tdzzrq;
	/**房产证号*/
    @Excel(name = "房产证号", width = 15)
	private String fczh;
	/**总层数*/
    @Excel(name = "总层数", width = 15)
	private String zcs;
	/**整栋用途*/
    @Excel(name = "整栋用途", width = 15)
	private String zdyt;
	/**房产建成时间*/
    @Excel(name = "房产建成时间", width = 15)
	private String fcjcsj;
	/**房屋楼层*/
    @Excel(name = "房屋楼层", width = 15)
	private java.math.BigDecimal fclc;
	/**房屋用途*/
    @Excel(name = "房屋用途", width = 15)
	private String fcyt;
	/**房屋建筑面积*/
    @Excel(name = "房屋建筑面积", width = 15)
	private java.math.BigDecimal fcjzmj;
	/**整栋建筑面积*/
    @Excel(name = "整栋建筑面积", width = 15)
	private java.math.BigDecimal zdljzmj;
}
