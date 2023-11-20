package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxZhsj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 评级授信支行数据
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
public interface IPjsxZhsjService extends IService<PjsxZhsj> {

    void gxsj(String zzbz);

}
