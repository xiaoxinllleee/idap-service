package org.cmms.modules.activiti.serviceimpl.mybatis;

import org.cmms.common.exception.XbootException;
import org.cmms.modules.activiti.dao.mapper.ActMapper;
import org.cmms.modules.activiti.service.mybatis.IActService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Exrickx
 */
@Service
public class IActServiceImpl implements IActService {

    @Autowired
    private ActMapper actMapper;

    @Override
    public Integer deleteBusiness(String table, String id) {

        if(StrUtil.isBlank(table)|| StrUtil.isBlank(id)){
            throw new XbootException("关联业务表名或id为空");
        }
        return actMapper.deleteBusiness(table, id);
    }
}
