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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: dwd
 * @Author: jeecg-boot
 * @Date:   2022-07-19
 * @Version: V1.0
 */
@Data
@TableName("wdsrkh_file_entity")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wdsrkh_file_entity对象", description="dwd")
public class wdsrkhFileEntity {

    /**附件ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "附件ID")
    private String id;
    /**单元编号*/
    @Excel(name = "单元编号", width = 15)
    @ApiModelProperty(value = "单元编号")
    private String srtxId;
    /**附件类型(1-图片/文件/2-视频/3-音频)*/
    @Excel(name = "附件类型(1-图片/文件/2-视频/3-音频)", width = 15)
    @ApiModelProperty(value = "附件类型(1-图片/文件/2-视频/3-音频)")
    private String fjlx;
    /**附件名称*/
    @Excel(name = "附件名称", width = 15)
    @ApiModelProperty(value = "附件名称")
    private String name;
    /**上传时间*/
    @Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
    private Date scsj;
    /**上传人*/
    @Excel(name = "上传人", width = 15)
    @ApiModelProperty(value = "上传人")
    private  String scr;
/*    *//**附件大小(单位KB)*//*
    @Excel(name = "附件大小(单位KB)", width = 15)
    @ApiModelProperty(value = "附件大小(单位KB)")
    private BigDecimal size;*/
    /**附件路径*/
    @Excel(name = "附件路径", width = 15)
    @ApiModelProperty(value = "附件路径")
    private String fjlj;
    /**访问路径*/
    @Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
    private String fwlj;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
    /**创建人*/
    @Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**创建时间*/
    @Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private  Date createTime;
    /**修改人*/
    @Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    /**修改时间*/
    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
