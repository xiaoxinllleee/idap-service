package org.cmms.modules.khjg.zbmx.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khjg.zbmx.entity.ZbmxSet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 指标明细设置
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
public interface IZbmxSetService extends IService<ZbmxSet> {
    List<LinkedHashMap> execZbmx(@Param("sqlStr") String sqlStr);

    Long execCount(@Param("sqlStr") String sqlStr);
}
