package org.cmms.modules.pad.qtzrrxxgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.qtzrrxxgl.entity.YxglQtzrrhfxxb;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface YxglQtzrrhfxxbMapper extends BaseMapper<YxglQtzrrhfxxb> {

    List<YxglQtzrrhfxxb> queryHfxxByZjhm(String mainId);

}
