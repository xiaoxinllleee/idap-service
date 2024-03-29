package org.cmms.modules.activiti.entity;

import org.cmms.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Exrick
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@TableName("t_act_starter")
@ApiModel(value = "流程可发起人")
@Accessors(chain = true)
public class ActStarter extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "关联用户ID")
    private String userId;
}