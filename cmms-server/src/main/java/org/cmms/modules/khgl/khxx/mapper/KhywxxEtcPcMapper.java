package org.cmms.modules.khgl.khxx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khxx.entity.KhywxxEtcPc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.khxx.entity.KhywxxWsyhPc;

/**
 * @Description: ETC
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
public interface KhywxxEtcPcMapper extends BaseMapper<KhywxxEtcPc> {

    public IPage<KhywxxEtcPc> getByWgbh(Page page, List<String> wgbhList);
    public List<KhywxxEtcPc> getByWgbhList(List<String> wgbhList);

}
