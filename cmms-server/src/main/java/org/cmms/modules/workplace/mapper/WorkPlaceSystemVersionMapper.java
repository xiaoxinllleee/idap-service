package org.cmms.modules.workplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.workplace.entity.WorkPlaceSystemVersion;

/**
 * @Description: 系统版本号
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface WorkPlaceSystemVersionMapper extends BaseMapper<WorkPlaceSystemVersion> {

    /**
     * 获取测试环境最新版本号
     * @return
     */
    String getLatestTestVersion(String qydm);

    /**
     * 获取生产环境最新版本号
     * @return
     */
    String getLatestProdVersion(String qydm);

}
