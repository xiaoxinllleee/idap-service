package org.cmms.modules.pad.qtzrrxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhzxxgl;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhzzllb;

import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-15
 * @Version: V1.0
 */
public interface IKhglQtzrrhzxxglService extends IService<KhglQtzrrhzxxgl> {
    public void updateMain(KhglQtzrrhzxxgl khglQtzrrhzxxgl, List<KhglQtzrrhzzllb> khglQtzrrhzzllbs);
}
