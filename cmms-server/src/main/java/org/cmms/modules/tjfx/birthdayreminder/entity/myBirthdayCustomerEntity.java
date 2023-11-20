package org.cmms.modules.tjfx.birthdayreminder.entity;


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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("TJFX_SCTPKHXX_HISTORY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_SCTPKHXX_HISTORY对象", description="dw")

public class myBirthdayCustomerEntity {

    /**
     * @Description: dw
     * @Author: jeecg-boot
     * @Date:   2022-07-19
     * @Version: V1.0
     *
    /**姓名*/
    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
    /**日期*/
    @Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date days;
    /**性别*/
    @Excel(name = "性别", width = 15,dicCode = "sex")
    @Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;
    /**户号编码*/
    @Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
    private String doornumbercode;
    /**年龄*/
    @Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String age;
    /**身份证*/
    @Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
    private String idnumber;
    /**家庭地址*/
    @Excel(name = "家庭地址", width = 15)
    @ApiModelProperty(value = "家庭地址")
    private String homeaddress;
    /**是否达标*/
    @Excel(name = "是否达标", width = 15,dicCode = "gzrw_sfdb")
    @Dict(dicCode = "gzrw_sfdb")
    @ApiModelProperty(value = "是否达标")
    private String 	gzrwSfdb;
    /**达标原因*/
    @Excel(name = "达标原因", width = 15)
    @ApiModelProperty(value = "达标原因")
    private String standardcause;
    /**操作*/
    private String operate;
    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    private String qsId;
    /**操作人*/
    @Excel(name = "操作人", width = 15)
    private String operatePeople;

    /**备注*/
    @Excel(name = "备注", width = 15)
    private String bz;

    /**上传文件 id**
     *
     */
    private String id;


    /**所属支行 id**
     *
     */
    private String sszh;

    /**原所属乡镇**
     *
     */
    private String yssxz;
    /**
     * 是否重要
     */
    @Dict(dicCode = "if_master")
    private String ifMaster;

    /**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date createTime;

    private String   csrqstr;

}
