package org.cmms.modules.tjfx.khnlfctj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.khnlfctj.entity.Tjfxkhnlfctj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户年龄分层统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface TjfxkhnlfctjMapper extends BaseMapper<Tjfxkhnlfctj> {

    public void extract(String tjyf);


}
