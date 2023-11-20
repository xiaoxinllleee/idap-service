package org.cmms.modules.khgl.nh.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.khgl.nh.entity.CamsPlpyYsxx;
import org.cmms.modules.khgl.nh.mapper.CamsPlpyYsxxMapper;
import org.cmms.modules.khgl.nh.service.ICamsPlpyYsxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 批量评议验收信息
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Service
public class CamsPlpyYsxxServiceImpl extends ServiceImpl<CamsPlpyYsxxMapper, CamsPlpyYsxx> implements ICamsPlpyYsxxService {

    @Override
    public Map<String, String> getWgbhYsqkAndPyls(Integer pyls) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pyls",pyls);
        List<CamsPlpyYsxx> list = baseMapper.selectList(queryWrapper);
        Map<String, String> map = new HashMap<>();
        if (CollUtil.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                CamsPlpyYsxx camsPlpyYsxx = list.get(i);
                map.put(camsPlpyYsxx.getWgbh(),camsPlpyYsxx.getBz());
            }
        }
        return map;
    }
}
