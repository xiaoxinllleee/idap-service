package org.cmms.modules.yxdygl.yjyxdygl.mapper;

import java.util.List;
import java.util.Map;

import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 一级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
@Component
public interface YjyxdyglMapper extends BaseMapper<Yjyxdygl> {

    Yjyxdygl queryDataByDybh(Map<String,String> sql);

    List<Yjyxdygl> queryDataByUser(String username);
}
