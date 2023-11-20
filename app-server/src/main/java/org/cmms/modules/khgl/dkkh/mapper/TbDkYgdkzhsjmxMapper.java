package org.cmms.modules.khgl.dkkh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgdkzhsjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款员工管理综合数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface TbDkYgdkzhsjmxMapper extends BaseMapper<TbDkYgdkzhsjmx> {

    IPage<TbDkYgdkzhsjmx> getAppList(Page page,@Param("dao") TbDkYgdkzhsjmx tbDkYgdkzhsjmx,@Param("yggh")String yggh);

    String dbfs(String hth);
}
