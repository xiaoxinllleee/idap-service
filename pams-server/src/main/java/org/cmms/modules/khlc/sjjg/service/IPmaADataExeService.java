package org.cmms.modules.khlc.sjjg.service;

import org.cmms.modules.khlc.sjjg.entity.PmaADataExe;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 数据加工功能
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
public interface IPmaADataExeService extends IService<PmaADataExe> {
    public void extract(String spname,String ksrq,String rwid);

}
