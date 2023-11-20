package org.cmms.modules.dklldj.csszgl.khxmsz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 考核项目设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
public interface KhxmszMapper extends BaseMapper<Khxmsz> {

    Khxmsz queryByQydmAndZbid(Map<String, String> sql);

}
