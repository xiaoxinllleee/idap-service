package org.cmms.modules.performance.depositcustomer.ckzhtzyj.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.entity.Ckzhtzyjxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.mapper.CkzhtzyjxxMapper;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.service.ICkzhtzyjxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款账号拓展移交信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Service
public class CkzhtzyjxxServiceImpl extends ServiceImpl<CkzhtzyjxxMapper, Ckzhtzyjxx> implements ICkzhtzyjxxService {
    @Override
    public String getProcessIdByProcessKey(String processKey) {
        return baseMapper.getProcessIdByProcessKey(processKey);
    }

    @Override
    public List<Ckzhtzyjxx> getListByIds(List<String> ids) {
        return baseMapper.getListByIds(ids);
    }
}
