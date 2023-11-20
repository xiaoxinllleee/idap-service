package org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.entity.YxzfgzszSh;

/**
 * @Description: 有效走访规则设置_商户
 * @Author: jeecg-boot
 * @Date:   2022-05-11
 * @Version: V1.0
 */
public interface YxzfgzszShMapper extends BaseMapper<YxzfgzszSh> {
    List<Yxzfmx> getZfzbxxByShid(String shid,String tjrq);
}
