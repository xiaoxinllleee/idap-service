package org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Description: 网格信息统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Data
public class WgxxtjVo {
	@Dict(dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	private String yhmc;
	private String yhkh;
	private String kzt;
	private String ybykjl;
	private String ysfxk;
	private String lxdh;
	private String lxdz;
	private String dw;
	private String wdmc;

	private String jkzk;
	private String ldjn;
	private String zpyy;
	private String hlx;
	private String yfpzpflx;
	private String fxsfxc;

	/**客户名称*/
	private String khmc;
	/**证件号码*/

	private String zjhm;

	/**是否吸毒*/

	private String sfxdry;


	/**是否诉讼*/

	private String sfss;


	/**是否五保低保户*/

	private String sfdb;


	/**是否退岗教师*/

	private String sftgjs;


	/**是否党员*/

	private String sfdy;

	/**是否公职人员*/
	private String sfgzry;

	/**是否服刑*/

	private String sffx;



	/**是否诈骗人员*/

	private String sfzpry;


	/**是否重大疾病*/

	private String sfzdjb;




	/**是否贫困户*/
	//@Excel(name = "是否贫困户", width = 15, dicCode = "sfbz")

	private String sfpkh;

	/**是否非法集资*/

	private String sfffjz;

}
