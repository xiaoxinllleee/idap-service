package org.cmms.modules.jx.wdyy.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.wdyy.entity.HistoryPageSearchDTO;
import org.cmms.modules.jx.wdyy.entity.KhgxglCkkhyxdjb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款客户营销登记簿表
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
public interface KhgxglCkkhyxdjbMapper extends BaseMapper<KhgxglCkkhyxdjb> {

    IPage<List<Map<String, Object>>> getTheYyHistoryMap(Page page, HistoryPageSearchDTO historyPageSearchDTO);
}
