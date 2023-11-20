package org.cmms.modules.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.system.entity.DpJdrwgl;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.mapper.DpJdrwglMapper;
import org.cmms.modules.system.service.IDpJdrwglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 节点任务管理
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
@Service

public class DpJdrwglServiceImpl extends ServiceImpl<DpJdrwglMapper, DpJdrwgl> implements IDpJdrwglService {
    @Autowired
    private DpJdrwglMapper dpJdrwglMapper;

    @Override
    public IPage<DpJdrwgl> getJdrwByJdId(Page<DpJdrwgl> page, String jdid, String rwmc) {
        return dpJdrwglMapper.getJdrwByJdId(page, jdid,rwmc);
    }

    @Override
    @Transactional
    public void extract(String spname,String ksrq,String jsrq,String rwid) {
        dpJdrwglMapper.extract(spname,ksrq,jsrq,rwid);
    }
    @Override
    @Transactional
    public void updatezt(String tjrq,String rwid){
        dpJdrwglMapper.updatezt(tjrq,rwid);
    }
    @Override
    @Transactional
    public void updateBatchzt(String ksrq,String jsrq,String rwid){
        dpJdrwglMapper.updateBatchzt(ksrq,jsrq,rwid);

    }



}
