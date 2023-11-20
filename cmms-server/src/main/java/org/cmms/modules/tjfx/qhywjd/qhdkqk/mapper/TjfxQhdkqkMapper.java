package org.cmms.modules.tjfx.qhywjd.qhdkqk.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.qhywjd.qhdkqk.entity.TjfxQhdkqk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行贷款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface TjfxQhdkqkMapper extends BaseMapper<TjfxQhdkqk> {
    void initData(String sjrq,String yggh);
    Date getMaxDate();
    TjfxQhdkqk getHjDate(String sjrq,String sszh);
}
