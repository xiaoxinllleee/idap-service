package org.cmms.modules.xdgl.nsb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;

import java.util.List;

/**
 * @Description: 浏阳农户评级授信农户年审表
 * @Author: jeecg-boot
 * @Date:   2022-10-12
 * @Version: V1.0
 */
public interface ICamsNhpjsxNsbService extends IService<CamsNhpjsxNsb> {

    public List<String> getAllByErrorType(String sszh,String errorType);

    public void updateByDao(CamsNhpjsxNsb camsNhpjsxNsb);

    void tq();
}
