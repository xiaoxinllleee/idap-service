package org.cmms.modules.activiti.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.activiti.entity.ActXendSpls;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 小额农贷审批历史
 * @Author: jeecg-boot
 * @Date:   2020-09-06
 * @Version: V1.0
 */
@Component
public interface ActXendSplsMapper extends BaseMapper<ActXendSpls> {

}
