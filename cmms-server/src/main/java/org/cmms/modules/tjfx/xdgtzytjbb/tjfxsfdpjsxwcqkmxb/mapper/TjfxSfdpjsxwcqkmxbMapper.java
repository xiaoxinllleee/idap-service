package org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxsfdpjsxwcqkmxb.entity.TjfxSfdpjsxwcqkmxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 示范点评级授信完成情况明细表
 * @Author: cmms
 * @Date:   2019-12-11
 * @Version: V1.0
 */
public interface TjfxSfdpjsxwcqkmxbMapper extends BaseMapper<TjfxSfdpjsxwcqkmxb> {

    public  void extract(Map<String,String> sql);

}
