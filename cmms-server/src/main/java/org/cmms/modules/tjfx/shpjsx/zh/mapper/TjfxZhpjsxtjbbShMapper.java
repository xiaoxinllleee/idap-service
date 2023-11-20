package org.cmms.modules.tjfx.shpjsx.zh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.shpjsx.wg.entity.ShPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.ShZhPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.TjfxZhpjsxtjbbSh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行评级授信统计-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
public interface TjfxZhpjsxtjbbShMapper extends BaseMapper<TjfxZhpjsxtjbbSh> {
    IPage<ShZhPjsxMxVo> queryPageListMx(Page page, String sjrq, String sszh,String type);
    List<ShZhPjsxMxVo> queryListMx( String sjrq, String sszh,String type);
}
