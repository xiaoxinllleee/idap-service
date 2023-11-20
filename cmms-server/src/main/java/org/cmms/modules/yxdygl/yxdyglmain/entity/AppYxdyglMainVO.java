package org.cmms.modules.yxdygl.yxdyglmain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 网格基本信息
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
@ToString
public class AppYxdyglMainVO {
	/**主键id*/
	private String id;
	/**网格名称*/
	private String wgmc;
	/**网格编号*/
	private String wgbh;
	/**单元性质(1-镇,2-村,3-组,4-城区街道，5社区，6商圈)*/
	private String wgxz;
	/**网格面积*/
	private Integer wgmj;
	/**网格情况*/
	private String wgqk;
	/**网格痛点分析*/
	private String wgtdfx;
	/**网格位置*/
	private String wgwz;
	/**网格关键人1*/
	private String wggjr1;
	/**网格关键人1联系方式*/
	private String gjrlxfs1;
	/**网格关键人2*/
	private String wggjr2;
	/**网格关键人2联系方式*/
	private String gjrlxfs2;
	/**网格关键人3*/
	private String wggjr3;
	/**网格关键人3联系方式*/
	private String gjrlxfs3;
	/**备注*/
	private String bz;
	/**父id*/
	private String parentId;
	private String zzbz;
	private String zrbs;
	private String zrld;
	private String fjid;
	/**所属支行*/
	private String sszh;
	/**所属单元编号*/
	private String menuId;
	/**客户经理*/
	/**是否主客户经理*/
	private String sfzkhjl;
	/**区分标识(1:农户，2：商户)*/
	private String sjqx;
	/**经度*/
	private String longitude;
	/**维度*/
	private String latitude;


	private List<WgxxFjVO> yxdyfjxxList;

}
