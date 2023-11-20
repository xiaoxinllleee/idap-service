package org.cmms.modules.khgl.nh.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.cmms.modules.khgl.nh.entity.CamsPlpyPytj;
import org.cmms.modules.khgl.nh.mapper.CamsPlpyPytjMapper;
import org.cmms.modules.khgl.nh.service.ICamsPlpyPytjService;
import org.cmms.modules.khgl.nh.vo.HzKhlxVO;
import org.cmms.modules.khgl.nh.vo.KpyhsVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 批量评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-29
 * @Version: V1.0
 */
@Service
public class CamsPlpyPytjServiceImpl extends ServiceImpl<CamsPlpyPytjMapper, CamsPlpyPytj> implements ICamsPlpyPytjService {

    @Override
    public Map<String, Integer> getList() {
        Map<String, Integer> map = new HashMap<>();
        List<KpyhsVO> list = baseMapper.getList();
        if (CollUtil.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                KpyhsVO kpyhsVO = list.get(i);
                map.put(kpyhsVO.getWgbh(),kpyhsVO.getHs() != null?kpyhsVO.getHs():0);
            }
        }
        return map;
    }

    @Override
    public Map<String, Integer> getListByPyls(Integer pyls) {
        Map<String, Integer> map = new HashMap<>();
        List<KpyhsVO> list = baseMapper.getListByPyls(pyls);
        if (CollUtil.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                KpyhsVO kpyhsVO = list.get(i);
                map.put(kpyhsVO.getWgbh(),kpyhsVO.getHs() != null?kpyhsVO.getHs():0);
            }
        }
        return map;
    }

    @Override
    public Map<String, HzKhlxVO> getHzkhlx() {
        Map<String, HzKhlxVO> map = new HashMap<>();
        List<HzKhlxVO> hzkhlx = baseMapper.getHzkhlx();
        if (CollUtil.isNotEmpty(hzkhlx)){
            for (int i = 0; i < hzkhlx.size(); i++) {
                HzKhlxVO hzKhlxVO = hzkhlx.get(i);
                map.put(hzKhlxVO.getWgbh(), hzKhlxVO);
            }
        }
        return map;
    }
}
