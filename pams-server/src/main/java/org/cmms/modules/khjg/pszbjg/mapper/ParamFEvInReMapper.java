package org.cmms.modules.khjg.pszbjg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khjg.pszbjg.entity.GzQueryVo;
import org.cmms.modules.khjg.pszbjg.entity.ParamFEvInRe;
import org.springframework.stereotype.Component;

/**
 * @Description: 派生指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-11
 * @Version: V1.0
 */
@Component
public interface ParamFEvInReMapper extends BaseMapper<ParamFEvInRe> {

    IPage<ParamFEvInRe> getgz(Page page, @Param("dao") GzQueryVo gzQueryVo);
    IPage<ParamFEvInRe> getgzbyfa(Page page, @Param("dao") GzQueryVo gzQueryVo);
    IPage<ParamFEvInRe> getgzbyjg(Page page, @Param("dao") GzQueryVo gzQueryVo);
}
