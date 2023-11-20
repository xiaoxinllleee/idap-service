package org.cmms.modules.ywgl.dkyw.dkzhzy.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 卡基本信息表VO
 */
@Data
@ToString
public class DzyzhsVO {

    /**账号数*/
    private Integer zhs;
    /**客户经理标识*/
    private String khjlbz;

    private String yggh;

    private String jgdm;

    private String ygxm;

    private String dkzh;



    private String zyhyggh;


    private String zyhzzbz;


    private String zyhgyh;

    private String zyhgwbz;


    private String zyhkhjlbz;


}
