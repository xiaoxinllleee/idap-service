package org.cmms.modules.system.mapper;

import java.util.List;

import org.cmms.modules.system.entity.HrBasOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.SysDepart;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * @Description: 组织机构管理
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Component
public interface HrBasOrganizationMapper extends BaseMapper<HrBasOrganization> {
    public List<HrBasOrganization> queryUserDeparts(@Param("userId") String userId);

    public HrBasOrganization queryByYwjgdm(String ywjgdm);

    public HrBasOrganization queryByZzbz(String zzbz);

    public List<HrBasOrganization> queryAuthOrgList(String zzbz);

    public List<HrBasOrganization> queryZzxxTreeByZzbz(String zzbz);

    public HrBasOrganization queryZhbyZzbz(String zzbz);

    public List<HrBasOrganization> getTreeData(@Param("userId") String userId);

    public List<HrBasOrganization> queryZzbzZh();

    String queryYwjgdmByZzjcLike(@Param("text") String text,
                                 @Param("code") String code,
                                 @Param("branchName") String branchName);
}
