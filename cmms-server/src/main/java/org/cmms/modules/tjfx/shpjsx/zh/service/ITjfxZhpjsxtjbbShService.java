package org.cmms.modules.tjfx.shpjsx.zh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.shpjsx.zh.entity.ShZhPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.TjfxZhpjsxtjbbSh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 支行评级授信统计-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
public interface ITjfxZhpjsxtjbbShService extends IService<TjfxZhpjsxtjbbSh> {
    IPage<ShZhPjsxMxVo> queryPageListMx(Page page, String sjrq, String sszh,String type);
    List<ShZhPjsxMxVo> queryListMx(String sjrq, String sszh,String type);
}
