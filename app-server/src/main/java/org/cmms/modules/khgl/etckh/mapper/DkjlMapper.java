package org.cmms.modules.khgl.etckh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlVO;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlbVO;
import org.cmms.modules.khgl.etckh.entity.Dkjl;

import java.util.List;

/**
 * @Description: ETC贷记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
public interface DkjlMapper extends BaseMapper<Dkjl> {
    List<DjkdkjlbVO> getDkjlList(@Param("start") int start, @Param("end") int end, @Param("namecn") String namecn);

    List<DjkdkjlbVO> getSfcs(@Param("start") int start, @Param("end") int end, @Param("namecn") String namecn);

    List<DjkdkjlbVO> getAll(@Param("start") int start, @Param("end") int end, @Param("namecn") String namecn);

    IPage<DjkdkjlVO> getDjkDkjlList(Page page, @Param("zh") String zh);
}
