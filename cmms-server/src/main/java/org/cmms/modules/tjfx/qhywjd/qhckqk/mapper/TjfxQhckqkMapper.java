package org.cmms.modules.tjfx.qhywjd.qhckqk.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.qhywjd.qhckqk.entity.TjfxQhckqk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行存款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface TjfxQhckqkMapper extends BaseMapper<TjfxQhckqk> {
    void initData(String sjrq,String yggh);
    Date getMaxDate();
    TjfxQhckqk getHjDate(String sjrq,String sszh);
}
