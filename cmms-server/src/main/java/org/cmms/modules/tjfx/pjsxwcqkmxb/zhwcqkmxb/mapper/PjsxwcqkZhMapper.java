package org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.entity.PjsxwcqkZh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface PjsxwcqkZhMapper extends BaseMapper<PjsxwcqkZh> {

    void InitDataToZh(Map<String, String> sql);

}
