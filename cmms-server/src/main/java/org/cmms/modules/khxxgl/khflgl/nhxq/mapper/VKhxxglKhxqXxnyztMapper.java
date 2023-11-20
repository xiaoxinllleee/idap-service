package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VKhxxglKhxqXxnyzt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2023-10-13
 * @Version: V1.0
 */
public interface VKhxxglKhxqXxnyztMapper extends BaseMapper<VKhxxglKhxqXxnyzt> {
    void initData(String sjrq);
    Date getMaxDate();
}
