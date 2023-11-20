package org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Data
@TableName("V_Dkjkpt_Dhjcbbg")
public class VDkjkptDhjcbbg implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**jgdm*/
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**khmc*/
	private String khmc;
	/**zjhmId*/
	private String zjhm;
	/**dhdkje*/
	private Integer dhdkje;
	/**dhdkye*/
	private Integer dhdkye;
	/**zxjkrq*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date zxjkrq;
	/**wjid*/
	private String wjid;
	/**fjlx*/
	@Dict(dicCode="dhjcfjlx")
	private String fjlx;
	/**fjnf*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fjnf;
	/**fjsjjd*/
	@Dict(dicCode="dhjcfjsjjd")
	private String fjsjjd;
	/**wjm*/
	private String wjm;
	private String bz;
	private String wjlj;
	private String fwlj;


}
