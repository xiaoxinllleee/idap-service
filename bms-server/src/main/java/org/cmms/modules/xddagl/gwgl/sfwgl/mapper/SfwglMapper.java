package org.cmms.modules.xddagl.gwgl.sfwgl.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
public interface SfwglMapper extends BaseMapper<Sfwgl> {

    public IPage<Sfwgl> getQuery(Page<Sfwgl> page, @Param("sfwgl") Sfwgl sfwgl, @Param("zzbz") String zzbz);

}
