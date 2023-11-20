package org.cmms.modules.dklldj.lldjgl.glzhgl.service.impl;

import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.dklldj.lldjgl.glzhgl.mapper.CbsInvmBaseMapper;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.ICbsInvmBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class CbsInvmBaseServiceImpl extends ServiceImpl<CbsInvmBaseMapper, CbsInvmBase> implements ICbsInvmBaseService {

    @Autowired
    private CbsInvmBaseMapper mapper;

    @Override
    public String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo) {
        return mapper.getAccNoByMastAcctAndSubAcctNo(paramOne,paramTwo);
    }
}
