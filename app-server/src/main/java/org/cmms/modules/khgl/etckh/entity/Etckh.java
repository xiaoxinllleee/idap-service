package org.cmms.modules.khgl.etckh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

/**
 * @Description: ETC绑定信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ibus_etc_bdxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_etc_bdxxb对象", description="ETC绑定信息表")
public class Etckh {

    /**绑定时间*/
    @Excel(name = "绑定时间", width = 15)
    @ApiModelProperty(value = "绑定时间")
    private String workdate;
    /**操作网点*/
    @Excel(name = "操作网点", width = 15)
    @ApiModelProperty(value = "操作网点")
    private String operbankno;
    /**操作人员*/
    @Excel(name = "操作人员", width = 15)
    @ApiModelProperty(value = "操作人员")
    private String operno;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**证件类型*/
    @Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
    private String zjlx;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**湘通卡号*/
    @Excel(name = "湘通卡号", width = 15)
    @ApiModelProperty(value = "湘通卡号")
    private String xtkh;
    /**账户类型*/
    @Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
    private String zhlx;
    /**账号*/
    @Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
    private String zh;
    /**户名*/
    @Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
    private String hm;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
    /**车牌号码*/
    @Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
    private String cphm;
    /**车牌颜色*/
    @Excel(name = "车牌颜色", width = 15)
    @ApiModelProperty(value = "车牌颜色")
    private String cpys;
    /**车辆类型*/
    @Excel(name = "车辆类型", width = 15)
    @ApiModelProperty(value = "车辆类型")
    private String cllx;
    /**发动机号*/
    @Excel(name = "发动机号", width = 15)
    @ApiModelProperty(value = "发动机号")
    private String fdjh;
    /**车辆特征*/
    @Excel(name = "车辆特征", width = 15)
    @ApiModelProperty(value = "车辆特征")
    private String cltz;
    /**OBU序号*/
    @Excel(name = "OBU序号", width = 15)
    @ApiModelProperty(value = "OBU序号")
    private String obuid;
    /**开户机构*/
    @Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String khjg;
    /**账户序号*/
    @Excel(name = "账户序号", width = 15)
    @ApiModelProperty(value = "账户序号")
    private String zhxh;
    /**贷记卡银行代号*/
    @Excel(name = "贷记卡银行代号", width = 15)
    @ApiModelProperty(value = "贷记卡银行代号")
    private String djkyhdh;
    /**贷记卡分行代号*/
    @Excel(name = "贷记卡分行代号", width = 15)
    @ApiModelProperty(value = "贷记卡分行代号")
    private String djkfhdh;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;
    /**预解绑日期*/
    @Excel(name = "预解绑日期", width = 15)
    @ApiModelProperty(value = "预解绑日期")
    private String yjbrq;
    /**数据开始日期*/
    @Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
    private String sDate;
    /**数据结束日期*/
    @Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
    private String eDate;
    /**加载时间*/
    @ApiModelProperty(value = "加载时间")
    private Date loadDate;
    /**法人标识*/
    @Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
    private String legalNo;
    /**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
    /**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
    /**zhOld*/
    @Excel(name = "zhOld", width = 15)
    @ApiModelProperty(value = "zhOld")
    private String zhOld;
    /**acctno*/
    @Excel(name = "acctno", width = 15)
    @ApiModelProperty(value = "acctno")
    private String acctno;
    /**ggjzjlx*/
    @Excel(name = "ggjzjlx", width = 15)
    @ApiModelProperty(value = "ggjzjlx")
    private String ggjzjlx;
    /**etcstate*/
    @Excel(name = "etcstate", width = 15)
    @ApiModelProperty(value = "etcstate")
    private String etcstate;
    /**ggjcardno*/
    @Excel(name = "ggjcardno", width = 15)
    @ApiModelProperty(value = "ggjcardno")
    private String ggjcardno;
    /**channelno*/
    @Excel(name = "channelno", width = 15)
    @ApiModelProperty(value = "channelno")
    private String channelno;
}
