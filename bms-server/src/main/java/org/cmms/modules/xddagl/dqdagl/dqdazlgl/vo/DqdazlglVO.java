package org.cmms.modules.xddagl.dqdagl.dqdazlgl.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.entity.DqdazlglFjxx;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷前档案资料管理
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_DQZLGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_DQZLGL对象", description="贷前档案资料管理")
public class DqdazlglVO {

    /**客户姓名*/
    @Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
    private String khxm;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**手机号码*/
    @Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    private String sjhm;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;
    /**档案编号*/
    @Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
    private String dabh;
    /**合同号*/
    @Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
    private String hth;
    /**签约日期*/
    @Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期")
    private Date htrq;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String ywjg;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入标志*/
    @Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;
    /**确认状态*/
    @Excel(name = "确认状态", width = 15)
    @ApiModelProperty(value = "确认状态")
    private String qrzt;
    /**匹配状态*/
    @Excel(name = "匹配状态", width = 15,dicCode = "ppzt")
    @ApiModelProperty(value = "匹配状态")
    @Dict(dicCode = "ppzt")
    private String ppzt;

    /**ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private String id;

    @ExcelCollection(name="附件信息")
    private List<DqdazlglFjxx> dqdazlglFjxxList;

}
