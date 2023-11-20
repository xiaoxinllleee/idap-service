package org.cmms.modules.hr.yggl.ygxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;

import java.util.Date;
import java.util.List;

/**
 * @Description: 岗位表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface IVhrbasstaffpostService extends IService<Vhrbasstaffpost> {
    public Vhrbasstaffpost selectByYggh(String yggh);

    public Vhrbasstaffpost  selectYgList(String yggh, String zzbz);

    public Vhrbasstaffpost  selectYgByLrsj(String yggh, String lrsj);



    public  List<Vhrbasstaffpost> geYgxxByZzbz(String zzbz);

    public  List<Vhrbasstaffpost> getZhYgxxByZzbz(String zzbz);

}
