package org.cmms.modules.khgl.dkkh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.AppDkkhGzList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface AppDkkhGzListMapper extends BaseMapper<AppDkkhGzList> {

    IPage<String> getZjhms(Page page, @Param("yggh") String yggh, @Param("custType") String custType, @Param("wjfl") String wjfl,@Param("zjhm") String zjhm);

}
