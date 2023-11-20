package org.cmms.modules.tjfx.birthdayreminder.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;


import java.util.List;


/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
public interface srtxmyBirthdayCustomerMapper extends BaseMapper<myBirthdayCustomerEntity> {


    String  selectUserIdByUserName(@Param("userName") String UserName);
    List<String> selectRoleId(@Param("userId") String userId);

    List<String>  selectCreditDepartCode(@Param("userName") String UserName);

    List<String> selectRoleCode(@Param("roleId") List<String> RoleId);

}
