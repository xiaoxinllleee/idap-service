package org.cmms.modules.word.service;

import org.cmms.modules.word.entity.GrdkGtDTO;
import org.cmms.modules.word.entity.GrdkMtDTO;
import org.cmms.modules.word.entity.GrdkdyDTO;
import org.cmms.modules.word.entity.ZxbgDTO;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;

import java.util.HashMap;

public interface IWordService {

    /**
     * 生成 <农户小额信用贷款借款申请书></>
     * 证件号码 存储文件地址
     * @param grkhpjsx
     * 成功值
     * @return int
     * @author liuxin
     */
    String smallLoanApplicaiton(Grpjsxspjl grkhpjsx);
    /**
     * 生成 <个人贷款></>
     * 证件号码
     * @param id
     * 成功值
     * @return String
     * @author liuxin
     */
    String grdkWord(String id);
    String grdkWordSx(String zjhm);

    /**
     * 小额农贷附件
     * */
    String xendFjWord(String zjhm,String hhbm);


    /**
     * 个人贷款附件
     * */
    String grdkFjWord(String zjhm,String hhbm);

    String grdkMtWord(GrdkMtDTO grdkMtDTO);
    String grdkGtWord(GrdkGtDTO grdkGtDTO);

    String grdkDyWord(GrdkdyDTO grdkdyDTO);
    String ZxbgWord(ZxbgDTO zxbgDTO);
    String jtspWord(String id);
}
