package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.CamsPlpyPytj;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.nh.vo.HzKhlxVO;

import java.util.Map;

/**
 * @Description: 批量评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-29
 * @Version: V1.0
 */
public interface ICamsPlpyPytjService extends IService<CamsPlpyPytj> {

    Map<String,Integer> getList();
    //获取已评议户数 通过 评议轮数
    Map<String,Integer> getListByPyls(Integer pyls);
    Map<String, HzKhlxVO> getHzkhlx();
}
