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
@TableName("sys_loan_info")
public class SysLoanInfo extends Model<SysLoanInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;

    @TableField("`name`")
    private String name;

    @TableId(value = "idcard",type = IdType.INPUT)
    private String idcard;

    @TableField("`rank`")
    private String rank;

    @TableField("quota")
    private String quota;

    @TableField("organ")
    private String organ;

    @TableField("manager")
    private String manager;

    @TableField("tel")
    private String tel;

    @TableField("openid")
    private String openid;

    /**
     * 贷款类型：0-居民，1-农户
     */
    @TableField("loanType")
    private String loanType;

    /**
     * 更新时间
     */
    @TableField("upTime")
    private String upTime;

    /**
     * 时间戳
     */
    @TableField("tmSmp")
    private String tmSmp;

    @Override
    public Serializable pkVal() {
        return this.idcard;
    }
}
