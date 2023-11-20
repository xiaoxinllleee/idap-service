package org.cmms.modules.pad.nhxxgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSbk;

/**
 * @Description: 社保卡业务信息
 * @Author: jeecg-boot
 * @Date:   2021-07-23
 * @Version: V1.0
 */
public interface KhywxxSbkMapper extends BaseMapper<KhywxxSbk> {

    IPage<KhywxxSbk> getByWgbh(Page page,@Param("wgbhList") List<String> wgbhList);
}
