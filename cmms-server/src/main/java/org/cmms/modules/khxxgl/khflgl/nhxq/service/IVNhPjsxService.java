package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NsImportVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;

/**
 * @Description: 农户评级授信视图
 * @Author: jeecg-boot
 * @Date:   2023-02-05
 * @Version: V1.0
 */
public interface IVNhPjsxService extends IService<VNhPjsx> {

    void updatePjsx(NsImportVO nsImportVO);
    void updateCpzlll();
    void updateNh(NsImportVO nsImportVO);

    String getWorkNoByRole(String role,String sszh);
}
