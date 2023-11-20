package org.cmms.modules.khgl.clkhxxgl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Data
@TableName("Khgl_grkhjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Clgrkhjbxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**手机号码*/
	private String sjhm;
	/**固定电话*/
	private String gddh;
	/**电子邮箱*/
	private String dzyx;
	/**住址*/
	private String zz;
	/**最高学历*/
	private String zgxl;
	/**最高学位*/
	private String zgxw;
	/**行业分类*/
	private String hyfl;
	/**职业*/
	private String zy;
	/**邮编*/
	private String yb;
	/**个人性格*/
	private String grxg;
	/**兴趣爱好*/
	private String xqah;
	/**客户重要程度*/
	private String khzycd;
	/**客户潜在业务*/
	private String khqzyw;
	/**录入人*/
	private String lrr;
	/**录入标识*/
	private String lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lrsj;
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**ECIF 客户编号*/
	private String custId;
	/**组织标识*/
	private String zzbz;
	/**机构代码*/
	private String jgdm;
	/**客户编号*/
	private String khbh;
	/**所属营销单元*/
	private String ssyxdy;
	/**客户姓名*/
	private String khmc;
	/**证件类型*/
	private String zjlx;
	/**证件号码*/
	private String zjhm;
	/**客户类型*/
	private String custType;
	/**客户类型1*/
	private String custType1;
	/**客户类型2*/
	private String custType2;
	/**客户类型3*/
	private String custType3;
	/**性别*/
	private String xb;
	/**民族*/
	private String mz;
	/**出生日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date csrq;
	/**工作单位名称*/
	private String gzdwmc;
	/**单位地址*/
	private String dwdz;
	/**单位电话*/
	private String dwdh;
	/**婚姻状况*/
	private String hyzk;
}
