package org.cmms.modules.khxxgl.wbsjgl.xyksj.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 信用卡数据
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_WBSJGL_XYKSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_WBSJGL_XYKSJ对象", description="信用卡数据")
public class KhxxglWbsjglXyksj {
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "唯一标识")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**身份证号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	@ExcelVerify(notNull = true)
	private String zjhm;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**贷款额度*/
	@Excel(name = "贷款额度", width = 15)
    @ApiModelProperty(value = "贷款额度")
	private String dked;
	/**信用卡额度*/
	@Excel(name = "信用卡额度", width = 15)
    @ApiModelProperty(value = "信用卡额度")
	private String xyked;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**卡状态*/
	@Excel(name = "卡状态", width = 15)
    @ApiModelProperty(value = "卡状态")
	private String kzt;
	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
    @ApiModelProperty(value = "逾期期数")
	private String yqqs;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String zhzt;
	/**账单地址*/
	@Excel(name = "账单地址", width = 15)
    @ApiModelProperty(value = "账单地址")
	private String zddz;
	/**发卡日期*/
	@Excel(name = "发卡日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发卡日期")
	private Date fkrq;
	/**是否激活*/
	@Excel(name = "是否激活", width = 15)
    @ApiModelProperty(value = "是否激活")
	private String sfjh;
	/**是否绑定微信*/
	@Excel(name = "是否绑定微信", width = 15)
    @ApiModelProperty(value = "是否绑定微信")
	private String sfbdwx;
	/**推广人*/
	@Excel(name = "推广人", width = 15)
    @ApiModelProperty(value = "推广人")
	private String tgr;
	/**录入人*/
	@ExcelVerify(interHandler = true)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
    @ApiModelProperty(value = "录入标志")
	private String lrbz;

}
