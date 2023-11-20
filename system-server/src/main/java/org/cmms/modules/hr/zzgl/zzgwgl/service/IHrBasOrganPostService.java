package org.cmms.modules.hr.zzgl.zzgwgl.service;

import org.cmms.modules.hr.zzgl.zzgwgl.entity.HrBasOrganPost;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.RelationDTO;

/**
 * @Description: 组织岗位管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface IHrBasOrganPostService extends IService<HrBasOrganPost> {

    int relationByRolesAndBank(RelationDTO relationDTO);

    Boolean ifExistByGwbzAndZzbz(Integer gwbz,String zzbz);
}
