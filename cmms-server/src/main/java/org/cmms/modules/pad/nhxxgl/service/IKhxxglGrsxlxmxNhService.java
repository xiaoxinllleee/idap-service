package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;

/**
 * @Description: 客户信息管理-个人授信类型明细-农户
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
public interface IKhxxglGrsxlxmxNhService extends IService<KhxxglGrsxlxmxNh> {
    int syncYesNHGrsxlxmx();

    /**
     * 大数据版本 删除KHXXGL_GRSXLXMX_NH对应户号编码数据
     * @param hhbm 户号编码
     * @return
     */
    int delNhgrsxlxmxByHhbm(String hhbm);

    /**
     * 大数据版本 清空impala中该表数据
     * @return
     */
    @DS("idapimpala")
    int delNhgrsxlxmx();
}
