package org.cmms.modules.tjfx.birthdayreminder.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.birthdayreminder.entity.wdsrkhFileEntity;


import java.util.List;


/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
public interface srtxFileMapper extends BaseMapper<wdsrkhFileEntity> {

 void insertBatchs(@Param("list") List<wdsrkhFileEntity> list);


}
