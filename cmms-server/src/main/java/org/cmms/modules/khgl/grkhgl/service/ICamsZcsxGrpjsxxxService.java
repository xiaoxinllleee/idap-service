package org.cmms.modules.khgl.grkhgl.service;

import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.grkhgl.entity.ModelCalculationVO;

/**
 * @Description: 个人客户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
public interface ICamsZcsxGrpjsxxxService extends IService<CamsZcsxGrpjsxxx> {

    public CamsZcsxGrpjsxxx selectByMainId(String hhbm);
    public  void  getYwgywxx(String  hhbm);

    public String checkFile(String zjhm,String hhbm,String name);
    public String checkHtFile(String zjhm,String hhbm,String name);

    //进行模型计算
    public ModelCalculationVO calModel(CamsZcsxGrpjsxxx camsZcsxGrpjsxxx);

    CamsZcsxGrpjsxxx getByZjhm(String zjhm);
}
