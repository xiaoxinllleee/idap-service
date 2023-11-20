package org.cmms;

import cn.hutool.core.util.IdUtil;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.lldjgl.glzhgl.mapper.CbsInvmBaseMapper;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.VKhglGrkhglMapper;
import org.cmms.modules.khgl.grkhgl.service.IVKhglGrkhglService;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.word.service.IWordService;
import org.cmms.modules.xdgl.grkhpjsx.mapper.GrkhpjsxMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MyTest {
    @Autowired
   private CbsInvmBaseMapper cbsInvmBaseMapper;
    @Autowired
    IvKhglKrkhglService vKhglKrkhglService;
    @Autowired
    VKhglGrkhglMapper vKhglGrkhglMapper;

    @Autowired
    IVKhglGrkhglService ivKhglGrkhglService;

    @Autowired
    IWordService iWordService;
    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;
    @Autowired
    GrkhpjsxMapper grkhpjsxMapper;
    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    @Test
    public void mothod()throws Exception{
        Khhmcxx khhmcxx = new Khhmcxx();
        khhmcxx.setId(IdUtil.simpleUUID());
        khhmcxx.setHhbm("1083");
    }

    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired

    /*@Test
    public void word(){
        iWordService.grdkWord("43070319941009685X","1223");
    }*/

    @Test
    public void method(){
        ivKhglGrkhglService.calculateModel("1223","43070319941009685X");
    }

    @Test
    public void test(){
        List<String> aList=new ArrayList<>();
        aList.add("a");
        aList.add("b");
        List<String> bList=new ArrayList<>();
        bList.add("a");
        bList.add("c");
        bList.add("d");
        bList.add("a");

        List<String> aAndB=new ArrayList<>();
        for(String aa: aList){
            for(String bb: bList){
                if (aa!=bb) aAndB.add(bb);
            }
        }
        System.out.println(aAndB);
    }

}
