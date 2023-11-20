package org.cmms.modules.dzdkz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sequence")
public class Sequence extends Model<Sequence> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "name", type = IdType.ASSIGN_ID)
    private String name;

    @TableField("current_value")
    private Long currentValue;

    @TableField("increment")
    private Integer increment;

    @Override
    public Serializable pkVal() {
        return this.name;
    }
}
