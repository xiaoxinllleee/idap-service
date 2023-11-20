package org.cmms.modules.hr.yggl.ygrggl.service;

import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;

import java.util.Date;
import java.util.List;

/**
 * @Description: 员工入岗管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface IHrBasStaffPostService extends IService<HrBasStaffPost> {

    boolean ifExistByYgghAndRgrqAndLgrq(HrBasStaffPost hrBasStaffPost);

    Long getId();

    public HrBasStaffPost getByNowDate(String yggh);

    public List<HrBasStaffPostZhzgry> getZhzgry(String fpyf, String zzbz);

    public HrBasStaffPost getStaffPostInfoBySprq(String yggh,String sprq);
}
