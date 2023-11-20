package org.cmms.modules.tjfx.shpjsx.wg.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.shpjsx.wg.entity.ShPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.wg.entity.TjfxPjsxtjbbSh;

import java.util.List;

/**
 * @Description: 评级授信统计报表-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
public interface TjfxPjsxtjbbShMapper extends BaseMapper<TjfxPjsxtjbbSh> {
    void initData(String sjrq,String yggh);
    IPage<ShPjsxMxVo> queryPageListMx(Page page, String sjrq, String wgbh,String type);
    List<ShPjsxMxVo> queryListMx( String sjrq, String wgbh,String type);
}
