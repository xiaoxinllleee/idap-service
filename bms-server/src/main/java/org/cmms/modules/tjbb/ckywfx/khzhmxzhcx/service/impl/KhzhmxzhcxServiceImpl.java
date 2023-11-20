package org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.service.impl;

import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.entity.Khzhmxzhcx;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.mapper.KhzhmxzhcxMapper;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.service.IKhzhmxzhcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户账户明细综合查询
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@Service
public class KhzhmxzhcxServiceImpl extends ServiceImpl<KhzhmxzhcxMapper, Khzhmxzhcx> implements IKhzhmxzhcxService {
    @Override
    public String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo) {
        return baseMapper.getAccNoByMastAcctAndSubAcctNo(paramOne,paramTwo);
    }
}
