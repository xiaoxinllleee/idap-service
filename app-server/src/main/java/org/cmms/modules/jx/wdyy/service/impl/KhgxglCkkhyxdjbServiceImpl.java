package org.cmms.modules.jx.wdyy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.wdyy.entity.HistoryPageSearchDTO;
import org.cmms.modules.jx.wdyy.entity.KhgxglCkkhyxdjb;
import org.cmms.modules.jx.wdyy.mapper.KhgxglCkkhyxdjbMapper;
import org.cmms.modules.jx.wdyy.service.IKhgxglCkkhyxdjbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款客户营销登记簿表
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Service
public class KhgxglCkkhyxdjbServiceImpl extends ServiceImpl<KhgxglCkkhyxdjbMapper, KhgxglCkkhyxdjb> implements IKhgxglCkkhyxdjbService {

    @Override
    public IPage getByPage(Page page, HistoryPageSearchDTO historyPageSearchDTO) {
        return baseMapper.getTheYyHistoryMap(page,historyPageSearchDTO);
    }
}
