package org.cmms.modules.base.entity;

import lombok.EqualsAndHashCode;
import org.cmms.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Exrick
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@TableName("t_dict")
@ApiModel(value = "字典")
@EqualsAndHashCode(callSuper = false)
public class Dict extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典名称")
    private String title;

    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;
}