package org.cmms.modules.jx.ckyj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 员工关注客户关系表 app_ckkh_watch
 */
@Data
@TableName("app_ckkh_watch")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="员工关注客户关系表", description="员工关注客户关系表")
public class AppCkkhWatch {
    /** 客户编号*/
    private String khbh;
    /** 员工工号 */
    private String yggh;
}
