package org.cmms.modules.khxxgl.wbsjgl.sjxfsj.entity;

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
 * @Description: 市局下发数据
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_wbsjgl_sjxf")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_wbsjgl_sjxf对象", description="市局下发数据")
public class Sjxfsj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**客户名称*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
	private String zjhm;
	/**银行名称*/
	@Excel(name = "银行名称", width = 15)
    @ApiModelProperty(value = "银行名称")
	private String yhmc;
	/**银行卡号*/
	@Excel(name = "银行卡号", width = 15)
    @ApiModelProperty(value = "银行卡号")
	private String yhkh;
	/**卡类别*/
	@Excel(name = "卡类别", width = 15)
    @ApiModelProperty(value = "卡类别")
	private String klb;
	/**是否本人持卡*/
	@Excel(name = "是否本人持卡", width = 15)
    @ApiModelProperty(value = "是否本人持卡")
	private String sfbrck;
	/**本人或亲属的签名*/
	@Excel(name = "本人或亲属的签名", width = 15)
    @ApiModelProperty(value = "本人或亲属的签名")
	private String brhqsqm;
	/**本人或亲属的联系方式*/
	@Excel(name = "本人或亲属的联系方式", width = 15)
    @ApiModelProperty(value = "本人或亲属的联系方式")
	private String brhqslxfs;
	/**是否发放过待遇*/
	@Excel(name = "是否发放过待遇", width = 15)
    @ApiModelProperty(value = "是否发放过待遇")
	private String sfffgdy;
	/**是否服刑人员*/
	@Excel(name = "是否服刑人员", width = 15)
    @ApiModelProperty(value = "是否服刑人员")
	private String sffxry;
	/**服刑开始时间*/
	@Excel(name = "服刑开始时间", width = 15)
    @ApiModelProperty(value = "服刑开始时间")
	private String fxkssj;
	/**服刑结束时间*/
	@Excel(name = "服刑结束时间", width = 15)
    @ApiModelProperty(value = "服刑结束时间")
	private String fxjssj;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
