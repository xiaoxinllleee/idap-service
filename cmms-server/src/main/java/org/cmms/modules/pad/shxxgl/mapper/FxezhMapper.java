package org.cmms.modules.pad.shxxgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.shxxgl.entity.Fxezh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.shxxgl.entity.Xyk;

/**
 * @Description: 福祥E支付
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
public interface FxezhMapper extends BaseMapper<Fxezh> {
    public IPage<Fxezh> getByWgbh(Page page, String wgbh);
    public List<Fxezh> getByWgbhList(String wgbh);
    public IPage<Fxezh> getByWgbhTy(Page page, List<String> wgbhList);
}
