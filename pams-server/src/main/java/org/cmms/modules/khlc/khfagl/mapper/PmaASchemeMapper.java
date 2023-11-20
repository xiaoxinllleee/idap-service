package org.cmms.modules.khlc.khfagl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.DpJdrwgl;

/**
 * @Description: 考核方案基础信息表
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
public interface PmaASchemeMapper extends BaseMapper<PmaAScheme> {
    IPage<PmaAScheme> getSchenmeByJdId(Page page, @Param("jdid") String jdid, @Param("famc") String famc);
}
