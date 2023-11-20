package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSbk;

import java.util.List;

/**
 * @Description: 社保卡业务信息
 * @Author: jeecg-boot
 * @Date:   2021-07-23
 * @Version: V1.0
 */
public interface IKhywxxSbkService extends IService<KhywxxSbk> {

    IPage<KhywxxSbk> getByWgbh(Page page, List<String> wgbhList);
}
