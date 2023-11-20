package org.cmms.modules.jx.common.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.common.entity.TbJjfxJgdksj;
import org.springframework.stereotype.Component;

/**
 * @Description: 机构贷款数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Component
public interface TbJjfxJgdksjMapper extends BaseMapper<TbJjfxJgdksj> {
    List<Map<String, Object>> getAllByMap(Page page,Map<String, Object> map);
}
