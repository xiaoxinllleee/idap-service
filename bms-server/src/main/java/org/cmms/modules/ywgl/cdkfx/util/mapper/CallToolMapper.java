package org.cmms.modules.ywgl.cdkfx.util.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface CallToolMapper {
    /**
     * --贷款考核梳计-贷款客户存款回报率
     */
   void pDkfxDkkhckhbl(String tjyf);
    /**
     *   --机构存贷款统计
     */
   void pWdcdktj(@Param("jgdm") String jgdm, @Param("tjyf") String tjyf, @Param("username") String username);

    /**
     * 客户经理存贷款提取
     */
    void pAutoExec(@Param("jgdm") String jgdm, @Param("tjyf") String tjyf, @Param("username") String username);

    /**
     * 客户经理新增不良扣款
     */
    void  pJxkhKhjlxzblkk(@Param("tjyf") String tjyf, @Param("username") String username);

    /**
     * 支行新增不良贷款汇总
     */
    void pJxkhZhxzbldkhz(String tjyf);
    /**
     * 客户经理新增不良扣款
     */
    void pJxkhKhjlxzblkkTwo(String tjyf);
}
