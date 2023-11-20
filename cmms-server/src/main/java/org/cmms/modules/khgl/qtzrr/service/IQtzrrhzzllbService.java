package org.cmms.modules.khgl.qtzrr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.qtzrr.entity.KhglQtzrrhzzllb;

import java.util.List;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
public interface IQtzrrhzzllbService extends IService<KhglQtzrrhzzllb> {

    public List<KhglQtzrrhzzllb> selectByMainId(String hhbm);

}
