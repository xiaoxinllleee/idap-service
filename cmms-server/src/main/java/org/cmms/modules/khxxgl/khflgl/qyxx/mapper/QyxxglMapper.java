package org.cmms.modules.khxxgl.khflgl.qyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.Qyxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;

/**
 * @Description: 企业信息
 * @Author: jeecg-boot
 * @Date:   2022-11-02
 * @Version: V1.0
 */
public interface QyxxglMapper extends BaseMapper<Qyxxgl> {
    public void init();

    public List<Qyxxgl> selectByQyxx (String hhbm);
}
