package org.cmms.modules.khpjsx.pjzxmsz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmsz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface pjsxPjzxmszMapper extends BaseMapper<pjsxPjzxmsz> {
    public boolean insertXLInfoBatch(List<pjsxPjzxmgzszXl> pjsxPjzxmszs);
}
