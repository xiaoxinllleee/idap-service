package org.cmms.modules.khgl.sjyh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.sjyh.entity.Ckglsjyh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;

/**
 * @Description: 客户管理_手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-16
 * @Version: V1.0
 */
public interface CkglsjyhMapper extends BaseMapper<Ckglsjyh> {

    List<KhzlbVo> getList(@Param("start") int start,
                          @Param("end") int end,
                          @Param("jx") int jx,
                          @Param("px") int px,
                          @Param("ssmc")String ssmc);

    IPage<KhzlbVo> getPageList(Page page,
                               @Param("jx") int jx,
                               @Param("px") int px,
                               @Param("ssmc")String ssmc);
}
