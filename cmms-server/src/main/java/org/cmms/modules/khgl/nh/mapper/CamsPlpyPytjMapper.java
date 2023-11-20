package org.cmms.modules.khgl.nh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.cmms.modules.khgl.nh.entity.CamsPlpyPytj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.nh.vo.HzKhlxVO;
import org.cmms.modules.khgl.nh.vo.KpyhsVO;

/**
 * @Description: 批量评议统计
 * @Author: jeecg-boot
 * @Date:   2022-04-29
 * @Version: V1.0
 */
public interface CamsPlpyPytjMapper extends BaseMapper<CamsPlpyPytj> {

    List<KpyhsVO> getList();
    //获取已评议户数 通过 评议轮数
    List<KpyhsVO> getListByPyls(Integer pyls);

    List<HzKhlxVO> getHzkhlx();
}
