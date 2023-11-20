package org.cmms.modules.hr.yggl.ygxxgl.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.demo.test.entity.JeecgDemo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;

/**
 * @Description: 岗位表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface VhrbasstaffpostMapper extends BaseMapper<Vhrbasstaffpost> {
    public Vhrbasstaffpost selectByYggh(String yggh);

    public  Vhrbasstaffpost selectYgList(String yggh,String zzbz);

    public  Vhrbasstaffpost selectYgByLrsj(String yggh,String lrsj);

    public  List<Vhrbasstaffpost> geYgxxByZzbz(@Param("zzbz") String zzbz);

    public  List<Vhrbasstaffpost> getZhYgxxByZzbz(@Param("zzbz") String zzbz);

}
