package org.cmms.modules.jx.wdyy.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.wdyy.entity.HistoryPageSearchDTO;
import org.cmms.modules.jx.wdyy.entity.KhgxglCkkhyxdjb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款客户营销登记簿表
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@DS("eweb")
public interface IKhgxglCkkhyxdjbService extends IService<KhgxglCkkhyxdjb> {
    IPage getByPage(Page page, HistoryPageSearchDTO historyPageSearchDTO);
}
