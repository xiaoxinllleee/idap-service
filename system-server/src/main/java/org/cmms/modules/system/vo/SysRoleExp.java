package org.cmms.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
@Data
@ToString
public class SysRoleExp{

    /**
     * 角色名称
     */
    @Excel(name="角色名",width=15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String roleName;
    
    /**
     * 角色编码
     */
    @Excel(name="角色编码",width=15)
    @ExcelVerify(notNull = true,interHandler = true)
    private String roleCode;
    
    /**
          * 描述
     */
    @Excel(name="描述(非必填)",width=60)
    private String description;



}
