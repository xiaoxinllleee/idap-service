package org.cmms.modules.pad.shxxgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.shxxgl.entity.XykVO;

import java.util.List;

/**
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
public interface XykMapper extends BaseMapper<Xyk> {

    public IPage<XykVO> getByWgbh(Page page, List<String> wgbhList);
    public IPage<Nhxq> getWktByWgbhList(Page page, List<String> wgbhList);
    public List<XykVO> getByWgbhList(List<String> wgbhList);
    public List<Nhxq> getWktByWgbhList(List<String> wgbhList);
}
