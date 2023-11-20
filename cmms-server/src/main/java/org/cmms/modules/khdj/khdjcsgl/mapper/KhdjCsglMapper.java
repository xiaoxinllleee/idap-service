package org.cmms.modules.khdj.khdjcsgl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khdj.khdjcsgl.entity.KhdjCsgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户等级评定参数管理
 * @Author: cmms
 * @Date:   2019-10-28
 * @Version: V1.0
 */
public interface KhdjCsglMapper extends BaseMapper<KhdjCsgl> {

    KhdjCsgl queryByPid(Map<String, String> sql);

}
