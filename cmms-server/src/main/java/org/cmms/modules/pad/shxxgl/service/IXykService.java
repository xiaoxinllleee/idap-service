package org.cmms.modules.pad.shxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.XykVO;

import java.util.List;

/**
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
public interface IXykService extends IService<Xyk> {

    //IPage<Xyk> getByWgbh(Page page, String wgbh);
    IPage<XykVO> getByWgbh(Page page, List<String> wgbhList);
    IPage<Nhxq> getWktByWgbh(Page page, List<String> wgbhList);
    List<XykVO> getByWgbhList(List<String> wgbhList);
    List<Nhxq> getWktByWgbhList(List<String> wgbhList);


}
