package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxZhpjsxwcqkmxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
public interface TjfxZhpjsxwcqkmxbMapper extends BaseMapper<TjfxZhpjsxwcqkmxb> {

    public  void extract(Map<String,String> sql);
}
