package org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.entity.PjsxwcqkZmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 组完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface PjsxwcqkZmxMapper extends BaseMapper<PjsxwcqkZmx> {

    void InitDataToXzz(Map<String, String> sql);

}
