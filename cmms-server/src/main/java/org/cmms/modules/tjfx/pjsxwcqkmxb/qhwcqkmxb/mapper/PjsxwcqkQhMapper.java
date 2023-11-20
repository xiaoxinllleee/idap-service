package org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.entity.PjsxwcqkQh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
public interface PjsxwcqkQhMapper extends BaseMapper<PjsxwcqkQh> {

    void InitDataToQh(Map<String, String> sql);

}
