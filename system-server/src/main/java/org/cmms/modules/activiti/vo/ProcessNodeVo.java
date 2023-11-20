package org.cmms.modules.activiti.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;

import java.util.List;

/**
 * @author Exrickx
 */
@Data
public class ProcessNodeVo {

    @ApiModelProperty(value = "节点id")
    private String id;

    @ApiModelProperty(value = "节点名")
    private String title;

    @ApiModelProperty(value = "节点类型 0开始 1用户任务 2结束 3排他网关")
    private Integer type;

    @ApiModelProperty(value = "关联角色")
    private List<SysRole> roles;

    @ApiModelProperty(value = "关联岗位")
    private List<HrBasPost> posts;

    @ApiModelProperty(value = "关联用户")
    private List<SysUser> users;

    @ApiModelProperty(value = "关联部门")
    private List<HrBasOrganization> departments;

    @ApiModelProperty(value = "多级连续部门负责人")
    private Boolean chooseDepHeader = false;

    @ApiModelProperty(value = "自选用户")
    private Boolean customUser = false;

    @ApiModelProperty(value = "节点展开 前端所需")
    private Boolean expand = true;

    @ApiModelProperty(value = "动态设置类名")
    private String dynamicClass;
}
