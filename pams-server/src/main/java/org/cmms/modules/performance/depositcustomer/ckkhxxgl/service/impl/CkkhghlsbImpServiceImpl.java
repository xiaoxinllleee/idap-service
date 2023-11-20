package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.CkkhghlsbImp;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper.CkkhghlsbImpMapper;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.ICkkhghlsbImpService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款客户管户历史表（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
@Service
public class CkkhghlsbImpServiceImpl extends ServiceImpl<CkkhghlsbImpMapper, CkkhghlsbImp> implements ICkkhghlsbImpService {

    @Override
    public void pCkkhGhxxImp(String username) {
        baseMapper.pCkkhGhxxImp(username);
    }
}
