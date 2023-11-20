package org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.entity.Jcdkmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 交叉贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
public interface JcdkmxMapper extends BaseMapper<Jcdkmx> {
    void pJcdkmx();
}
