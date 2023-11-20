package org.cmms.modules.performance.loancustomer.dkkhyj.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.loancustomer.dkkhyj.entity.Dkkhyjxx;
import org.cmms.modules.performance.loancustomer.dkkhyj.mapper.DkkhyjxxMapper;
import org.cmms.modules.performance.loancustomer.dkkhyj.service.IDkkhyjxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款客户移交
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
@Service
public class DkkhyjxxServiceImpl extends ServiceImpl<DkkhyjxxMapper, Dkkhyjxx> implements IDkkhyjxxService {
    @Override
    public String getProcessIdByProcessKey(String processKey) {
        return baseMapper.getProcessIdByProcessKey(processKey);
    }

    @Override
    public List<Dkkhyjxx> getListByIds(List<String> ids) {
        return baseMapper.getListByIds(ids);
    }
}
