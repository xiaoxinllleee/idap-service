package org.cmms.modules.khgl.jzyx.yxzfqd.entity;

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
 * @Description: 优先走访清单视图
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_jzyx_yxzfqd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_jzyx_yxzfqd对象", description="优先走访清单视图")
public class VYxzfqd {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**一卡通账号*/
	@Excel(name = "一卡通账号", width = 15)
    @ApiModelProperty(value = "一卡通账号")
	private String yktzh;
	/**种植地址*/
	@Excel(name = "种植地址", width = 15)
    @ApiModelProperty(value = "种植地址")
	private String zzdd;
	/**租赁稻田面积*/
	@Excel(name = "租赁稻田面积", width = 15)
    @ApiModelProperty(value = "租赁稻田面积")
	private String zldtmj;
	/**补贴金额*/
	@Excel(name = "补贴金额", width = 15)
    @ApiModelProperty(value = "补贴金额")
	private String btje;
	/**镇办核实双季稻种植*/
	@Excel(name = "镇办核实双季稻种植", width = 15)
    @ApiModelProperty(value = "镇办核实双季稻种植")
	private String zbhssjdzz;
	/**优先类型*/
	@Excel(name = "优先类型", width = 15, dicCode = "jzyx_yxlx")
    @ApiModelProperty(value = "优先类型")
	@Dict(dicCode = "jzyx_yxlx")
	private String yxlx;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
	private String qt;
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
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15, dicCode = "whcd")
    @ApiModelProperty(value = "文化程度")
	@Dict(dicCode = "whcd")
	private String whcd;
	/**名单情况*/
	@Excel(name = "名单情况", width = 15, dicCode = "xtpdjg")
    @ApiModelProperty(value = "名单情况")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
	/**是否走访*/
	@Excel(name = "是否走访", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否走访")
	@Dict(dicCode = "sfbz")
	private String sfzf;
	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	/**走访人*/
	@Excel(name = "走访人", width = 15, dicCode ="username",dictTable="sys_user",dicText="realname")
    @ApiModelProperty(value = "走访人")
	@Dict(dicCode ="username",dictTable="sys_user",dicText="realname")
	private String lrr;
	/**户籍所在网格*/
	@Excel(name = "户籍所在网格", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "户籍所在网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String hjszwg;
	/**户籍所属支行*/
	@Excel(name = "户籍所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "户籍所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String hjsszh;

	@Excel(name = "走访支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "走访支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zfzh;
}
