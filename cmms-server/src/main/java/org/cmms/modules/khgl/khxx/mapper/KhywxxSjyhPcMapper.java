package org.cmms.modules.khgl.khxx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
public interface KhywxxSjyhPcMapper extends BaseMapper<KhywxxSjyhPc> {

    public IPage<KhywxxSjyhPc> getByWgbh(Page page, List<String> wgbhList);
    public IPage<Nhxq> getWktByWgbhList(Page page,List<String> wgbhList);
    public List<KhywxxSjyhPc> getByWgbhList(List<String> wgbhList);
    public List<Nhxq> getWktByWgbhList(List<String> wgbhList);
}
