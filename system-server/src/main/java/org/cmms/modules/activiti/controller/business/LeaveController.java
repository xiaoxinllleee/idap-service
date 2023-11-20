package org.cmms.modules.activiti.controller.business;

import org.apache.shiro.SecurityUtils;
import org.cmms.base.XbootBaseController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.ResultUtil;
import org.cmms.common.vo.Result;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.business.Leave;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.business.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(tags = "请假申请接口")
@Transactional
@RequestMapping(value = "/leave")
public class LeaveController extends XbootBaseController<Leave, String> {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public LeaveService getService() {
        return leaveService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加申请草稿状态")
    public Result<Object> add(Leave leave,
                              @RequestParam String procDefId){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        leave.setCreateBy(sysUser.getRealname());
        leave.setCreateTime(new Date());
        Leave le = leaveService.save(leave);
        // 保存至我的申请业务
        SysUser user =sysUserService.getUserByName(sysUser.getUsername());
        String userId = user.getId();
        ActBusiness actBusiness = new ActBusiness();
        actBusiness.setUserId(userId);
        actBusiness.setTableId(le.getId());
        actBusiness.setProcDefId(procDefId);
        actBusiness.setTitle(leave.getTitle());
        actBusiness.setCreateBy(sysUser.getRealname());
        actBusiness.setCreateTime(new Date());
        actBusiness.setApplyTime(new Date());
        actBusinessService.save(actBusiness);
        return ResultUtil.data(null);
    }
}
