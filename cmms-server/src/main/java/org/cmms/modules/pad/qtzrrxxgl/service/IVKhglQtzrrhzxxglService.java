package org.cmms.modules.pad.qtzrrxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxgl;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxglQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
public interface IVKhglQtzrrhzxxglService extends IService<VKhglQtzrrhzxxgl> {

    List<String> selectHzByCy(VKhglQtzrrhzxxglQuery hzxx, HttpServletRequest req);

    public void init(String hhbm);
}
