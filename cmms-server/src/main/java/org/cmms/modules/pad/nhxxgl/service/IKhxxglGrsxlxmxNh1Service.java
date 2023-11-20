package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;

/**
 * @author 龚辉
 * @date 2023/6/17 17:06 周六
 */
@DS("idapimpala")
public interface IKhxxglGrsxlxmxNh1Service extends IService<KhxxglGrsxlxmxNh> {
    int delNhgrsxlxmx();
}
