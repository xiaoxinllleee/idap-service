package org.cmms.modules.dklldj.jbxxgl.glrxxgl.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 关联人信息管理
 * @Author: jeecg-boot
 * @Date: 2020-03-06
 * @Version: V1.0
 */
@Data
@TableName("rate_khglrxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "rate_khglrxxb对象", description = "关联人信息管理")
public class Rate_khglrxxb {

    /**
     * 证件号码
     */
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**
     * 客户名称
     */
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**
     * 关联人证件号码
     */
    @Excel(name = "关联人证件号码", width = 15)
    @ApiModelProperty(value = "关联人证件号码")
    private String glrzjhm;
    /**
     * 关联人名称
     */
    @Excel(name = "关联人名称", width = 15)
    @ApiModelProperty(value = "关联人名称")
    private String glrmc;
    /**
     * 关联人类型
     */
    @Excel(name = "关联人类型", width = 15, dicCode = "dklldj_glrlx")
    @ApiModelProperty(value = "关联人类型")
    @Dict(dicCode = "dklldj_glrlx")
    private Integer glrlx;
    /**
     * 删除标志
     */
    @ApiModelProperty(value = "删除标志")
    @Dict(dicCode = "del_flag")
    private Integer scbz;
    /**
     * 录入时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;
    /**
     * 录入操作员
     */
    @ApiModelProperty(value = "录入操作员")
    private String lrczy;
    /**
     * 录入标志
     */
    @ApiModelProperty(value = "录入标志")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;

}
