package org.cmms.modules.pad.nhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;

/**
 * @Description: 客户信息管理-个人授信类型明细-农户
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
public interface KhxxglGrsxlxmxNhMapper extends BaseMapper<KhxxglGrsxlxmxNh> {
    int syncYesNHGrsxlxmx();
    int delNhgrsxlxmxByHhbm(String hhbm);
    int delNhgrsxlxmx();
}
