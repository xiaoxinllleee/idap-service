package org.cmms.modules.qxfk.service;

import org.cmms.common.api.vo.Result;
import org.cmms.modules.qxfk.entity.QxfkPdfImg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 七星风控
 * @Author: jeecg-boot
 * @Date:   2022-07-28
 * @Version: V1.0
 */
public interface IQxfkPdfImgService extends IService<QxfkPdfImg> {
    public Result<?> QxJsonData(String data,String zjhm,String repCode,String qxfk,String yggh,String type,String sjhm,String khmc);
    public void queryQxfk(String zjhm,String khmc,String sjhm,String yggh,String qxfk);
}
