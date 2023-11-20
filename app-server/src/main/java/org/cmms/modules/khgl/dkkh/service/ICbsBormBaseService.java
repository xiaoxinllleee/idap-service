package org.cmms.modules.khgl.dkkh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@DS("sjxf")
public interface ICbsBormBaseService extends IService<CbsBormBase> {

    /**
     * 根据证件号码去查 便民卡号不为空的数据
     * */
    List<CbsBormBase> getCardNoIsNotNull(String zjhm);

}
