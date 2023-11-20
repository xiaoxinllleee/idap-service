package org.cmms.modules.tjfx.birthdayreminder.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class srtxDto {
    private  String id;

    /**姓名*/
    private String name;
    /**日期一*/
/*    @Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date days;


    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date daysBegin;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date  daysEnd;



    /**户号编码*/
    private String doornumbercode;

    /**是否达标*/
    private String gzrwSfdb;

    /**原所属村组*/
    private String yssxz;

    /**所属支行
     *
     */
    private String sszh;
    /**
     * 是否重要
     */
    @Dict(dicCode = "if_master")
    private String ifMaster;


}
