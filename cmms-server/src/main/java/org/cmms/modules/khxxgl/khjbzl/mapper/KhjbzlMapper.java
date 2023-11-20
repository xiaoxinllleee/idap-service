package org.cmms.modules.khxxgl.khjbzl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户画像
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface KhjbzlMapper extends BaseMapper<Khjbzl> {
    public void extract(@Param("tjrq") String tjrq);
    public Integer getTodayBirthDayMans(@Param("wgbh") String wgbh);

    int syncYesKhjbzl();

}
