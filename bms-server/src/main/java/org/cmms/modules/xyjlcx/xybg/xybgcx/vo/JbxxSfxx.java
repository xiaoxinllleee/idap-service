package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

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
 * @Description: 一键准入报告 / 一 基本信息 / 身份信息
 * @Author: penghr
 * @Date:   2023.04.16
 * @Version: V1.0
 */
@Data
public class JbxxSfxx {

    /**姓名*/
    private String khmc;
    /**性别*/
    @Dict(dicCode = "sex")
    private String xb;
    /**出生日期：substr(zjhm,7,8)*/
    private String csrq;
    /**婚姻状况*/
    @Dict(dicCode = "hyzk")
    private String hyzk;
    /**手机号码*/
    private String sjhm;
    /**住宅电话*/
    // TODO 农户信息内未包含有`住宅电话`
    private String zzdh;
    /**学历：whcd*/
    @Dict(dicCode = "whcd")
    private String xl;
    /**通讯地址：zz*/
    private String txdz;

}
