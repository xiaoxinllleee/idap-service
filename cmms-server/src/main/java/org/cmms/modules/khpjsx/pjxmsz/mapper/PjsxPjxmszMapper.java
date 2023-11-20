package org.cmms.modules.khpjsx.pjxmsz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.pjxmsz.entity.PjsxPjxmsz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface PjsxPjxmszMapper extends BaseMapper<PjsxPjxmsz> {
    public PjsxPjxmsz queryxmbh (Map<String,String> sql);


}
