package org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.entity.PjsxwcqkCmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 行政村完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
public interface PjsxwcqkCmxMapper extends BaseMapper<PjsxwcqkCmx> {

    void InitDataToXzc(Map<String, String> sql);

}
