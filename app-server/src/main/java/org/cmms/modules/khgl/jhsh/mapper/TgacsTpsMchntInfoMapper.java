package org.cmms.modules.khgl.jhsh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 聚合商户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
public interface TgacsTpsMchntInfoMapper extends BaseMapper<TgacsTpsMchntInfo> {
    IPage<TgacsTpsMchntInfo> getxj(Page page, @Param("ks") String ks, @Param("js") String js);
}
