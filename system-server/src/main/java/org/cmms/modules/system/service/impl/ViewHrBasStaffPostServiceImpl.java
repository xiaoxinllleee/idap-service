package org.cmms.modules.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.system.entity.ViewHrBasStaffPost;
import org.cmms.modules.system.mapper.ViewHrBasStaffPostMapper;
import org.cmms.modules.system.service.IViewHrBasStaffPostService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工在岗视图
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class ViewHrBasStaffPostServiceImpl extends ServiceImpl<ViewHrBasStaffPostMapper, ViewHrBasStaffPost> implements IViewHrBasStaffPostService {

}
