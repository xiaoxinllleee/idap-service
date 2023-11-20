package org.cmms.modules.xdgl.nhxe.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.khgl.grkhgl.entity.VKhglGrkhgl;
import org.cmms.modules.khgl.grkhgl.mapper.VKhglGrkhglMapper;
import org.cmms.modules.khgl.grkhgl.service.ICamsZcsxGrpjsxxxService;
import org.cmms.modules.khgl.grkhgl.service.IVKhglGrkhglService;
import org.cmms.modules.word.entity.CamsZcsxWordinfo;
import org.cmms.modules.word.entity.SmallLoanVO;
import org.cmms.modules.word.mapper.CamsZcsxWordinfoMapper;
import org.cmms.modules.word.service.IWordService;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grkhpjsx;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/xdgl/nhxe/nhxe")
public class NhxeController {
    @Autowired
    IWordService iWordService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    CamsZcsxWordinfoMapper camsZcsxWordinfoMapper;
    @Autowired
    ICamsZcsxGrpjsxxxService camsZcsxGrpjsxxxService;
    HashMap<String,Object> map = new HashMap<>();




    @RequestMapping("/generate")
    public Result<?> generate(@RequestParam(name="zjhm",required=true) String zjhm,
                              @RequestParam(name="hhbm") String hhbm){
        Grpjsxspjl grkhpjsx = new Grpjsxspjl();
        grkhpjsx.setZjhm(zjhm);
        grkhpjsx.setHhbm(hhbm);
        String result = iWordService.smallLoanApplicaiton(grkhpjsx);

        if (result.equals("2"))
            return Result.error(2,"不满足生成条件,请补充完整信息");

        return Result.ok(result);
    }

    @RequestMapping("/supplement")
    public Result<?> supplement(@RequestBody SmallLoanVO smallLoanVO){
        map.put(smallLoanVO.getZjhm(),smallLoanVO);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            String mapKey = entry.getKey();
            SmallLoanVO mapValue = (SmallLoanVO)entry.getValue();
            System.out.println(mapKey+":"+mapValue.toString());
        }
        return Result.ok("生成农户小额申请书成功");
    }

    @Autowired
    VKhglGrkhglMapper vKhglGrkhglMapper;
    @RequestMapping("/existence")
    public Result<?> existence(@RequestParam(name="zjhm",required=true) String zjhm,
    @RequestParam(name="hhbm") String hhbm){
        String result = "0";
        QueryWrapper nhxe = new QueryWrapper();
        nhxe.eq("ZJHM",zjhm);
        nhxe.eq("HHBM",hhbm);
        nhxe.orderByDesc("UPDATE_TIME");
        CamsZcsxWordinfo camsZcsxWordinfo = null;
        List<CamsZcsxWordinfo> list = camsZcsxWordinfoMapper.selectList(nhxe);
        if (list != null && list.size() > 0){
            camsZcsxWordinfo = list.get(0);
        }


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM",zjhm);
        queryWrapper.eq("HHBM",hhbm);
        List<VKhglGrkhgl> vKhglGrkhgls = vKhglGrkhglMapper.selectList(queryWrapper);
        VKhglGrkhgl vKhglGrkhgl = null;
        if (vKhglGrkhgls != null && vKhglGrkhgls.size() >0){
            vKhglGrkhgl = vKhglGrkhgls.get(0);
        }
        if (camsZcsxWordinfo != null && StringUtils.isNoneBlank(camsZcsxWordinfo.getWordType())){
            if (camsZcsxWordinfo.getWordType().equals("nhxe")){
                if (vKhglGrkhgl != null){
                File file = new File(uploadpath+File.separator+"dkzl"+File.separator+vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"nhxe.doc");
                log.info("检测文件位置|{}|",uploadpath+File.separator+"dkzl"+File.separator+vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"nhxe.doc");
                if (file.exists())
                    result = vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"nhxe.doc";
                }
            }
            if (camsZcsxWordinfo.getWordType().equals("pmk")){
                if (vKhglGrkhgl != null){
                File pmk = new File(uploadpath+File.separator+"dkzl"+File.separator+vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"pmk.doc");
                log.info("检测文件位置|{}|",uploadpath+File.separator+"dkzl"+File.separator+vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"pmk.doc");
                if (pmk.exists())
                    result = vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"pmk.doc";
                }
            }

        }
        return Result.ok(result);
    }

    @Autowired
    IVKhglGrkhglService ivKhglGrkhglService;

    @RequestMapping("/modelResult")
    public Result<?> getModelResult(@RequestParam(name="zjhm",required=true) String zjhm,
                                    @RequestParam(name="hhbm") String hhbm){
        if (StringUtils.isBlank(zjhm) || StringUtils.isBlank(hhbm)){
            log.info("第三方计算模型接口参数为空 提前返回");
            return Result.ok("证件号码或者户名编码为空");
        }
        ivKhglGrkhglService.calculateModel(hhbm,zjhm);
        return Result.ok();
    }


    @RequestMapping("/getYwhywxxResult")
    public Result<?> getYwhywxxResult(@RequestParam(name="hhbm") String hhbm){
        log.info("---getYwhywxxResult---");
        log.info("---param={}---",hhbm);
        try {
            camsZcsxGrpjsxxxService.getYwgywxx(hhbm);
            return Result.ok();
        }catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/checkFile")
    public Result checkFile(@RequestParam(name="zjhm",required=true) String zjhm,
                            @RequestParam(name="hhbm") String hhbm,
                            @RequestParam(name="name") String name){
        String s = camsZcsxGrpjsxxxService.checkFile(zjhm, hhbm, name);
        if (s.contains("docx"))
            return Result.ok(s);
        return Result.error(s);
    }

    @RequestMapping("/checkHtFile")
    public Result checkHtFile(@RequestParam(name="zjhm",required=true) String zjhm,
                            @RequestParam(name="hhbm") String hhbm,
                            @RequestParam(name="name") String name){
        String s = camsZcsxGrpjsxxxService.checkHtFile(zjhm, hhbm, name);
        if (s.contains("docx"))
            return Result.ok(s);
        return Result.error(s);
    }
}
