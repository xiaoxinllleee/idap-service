package org.cmms.modules.system.sjbd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.sjbd.entity.SjxfJgbd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 数据下发结果比对
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
public interface SjxfJgbdMapper extends BaseMapper<SjxfJgbd> {
    long getCount(@Param("tablename") String tablename);

    public double getSum(@Param("tablename") String tablename, @Param("fzzd") String fzzd, @Param("hzzd") String hzzd);

    public double getAvg(@Param("tablename") String tablename, @Param("fzzd") String fzzd, @Param("hzzd") String hzzd);



}
