package org.cmms.modules.pad.pyxx.service;

import org.cmms.modules.pad.nhxxgl.entity.KhglYwhywwlxxPad;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 评议员信息
 * @Author: jeecg-boot
 * @Date:   2020-07-24
 * @Version: V1.0
 */
public interface IPyyxxService extends IService<Pyyxx> {

    List<String> getByPyls(Integer pyls);

    void updateSxxx(String id);

    List<KhglYwhywwlxxPad> getbldkInfo(String hhbm);
}
