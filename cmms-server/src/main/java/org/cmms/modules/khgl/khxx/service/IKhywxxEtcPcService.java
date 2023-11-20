package org.cmms.modules.khgl.khxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khxx.entity.KhywxxEtcPc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;

import java.util.List;

/**
 * @Description: ETC
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
public interface IKhywxxEtcPcService extends IService<KhywxxEtcPc> {

    IPage<KhywxxEtcPc> getByWgbh(Page page, List<String> wgbhList);
    List<KhywxxEtcPc> getByWgbh(List<String> wgbhList);

}
