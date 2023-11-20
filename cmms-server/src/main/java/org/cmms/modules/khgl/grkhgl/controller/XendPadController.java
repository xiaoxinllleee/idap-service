package org.cmms.modules.khgl.grkhgl.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.grkhgl.entity.*;
import org.cmms.modules.khgl.grkhgl.service.*;
import org.cmms.modules.khgl.grkhgl.vo.KhglKrkhglPage;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.FjglVo;
import org.cmms.modules.khgl.nh.service.IFjglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
* @Description: 小额农贷Pad
* @Author: jeecg-boot
* @Date:   2020-07-03
* @Version: V1.0
*/
@Slf4j
@Api(tags="小额农贷Pad")
@RestController
@RequestMapping("/xend/pad")
public class XendPadController extends JeecgController<vKhglKrkhgl, IvKhglKrkhglService> {
    @Autowired
    private IvKhglKrkhglService vKhglKrkhglService;
    @Autowired
    private IVKhglGrkhglService ivKhglGrkhglService;
    @Autowired
    private IZcsxNhcjxxService zcsxNhcjxxService;
    @Autowired
    private IKhhmcxxService khhmcxxService;
    @Autowired
    private ICamsZcsxGrpjsxxxService camsZcsxGrpjsxxxService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IFjglService fjglService;

    @Value(value = "${staticURL}")
    private String staticUrl;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    INhxqService nhxqService;

    /**
     * 分页列表查询
     *
     * @param vKhglKrkhgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "小额农贷Pad-分页列表查询")
    @ApiOperation(value="小额农贷Pad-分页列表查询", notes="小额农贷Pad-分页列表查询")
    @GetMapping(value = "/xendList")
    @PermissionData
    public Result<?> queryXendList(vKhglKrkhgl vKhglKrkhgl,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<vKhglKrkhgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglKrkhgl, req.getParameterMap());
        queryWrapper.orderByDesc("xgsj");
        if((vKhglKrkhgl.getZjhm()==null||vKhglKrkhgl.getZjhm().trim().length()==0)&&(vKhglKrkhgl.getKhmc()==null||vKhglKrkhgl.getKhmc().trim().length()==0)){
            queryWrapper.eq("cjwczt",1);
            queryWrapper.eq("sfsxdx",1);
        }
        Page<vKhglKrkhgl> page = new Page<vKhglKrkhgl>(pageNo, pageSize);
        IPage<vKhglKrkhgl> pageList = vKhglKrkhglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/queryMsgById")
    public Result<?> queryHzxxByHhbm(@Param("id") String id) {
        try {
            if (id != null) {
                QueryWrapper<vKhglKrkhgl> queryWrapper = new QueryWrapper<>();
                queryWrapper.orderByDesc("cjsj");
                queryWrapper.eq("cjwczt",1);
                queryWrapper.eq("id", id);
                List<vKhglKrkhgl> list = vKhglKrkhglService.list(queryWrapper);
                if (list != null && list.size() > 0) {
                    return Result.ok(list.get(0));
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

   /**
    * 添加
    * @param khglKrkhglPage
    * @return
    */
   @AutoLog(value = "小额农贷Pad-添加")
   @ApiOperation(value="小额农贷Pad-添加", notes="小额农贷Pad-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody KhglKrkhglPage khglKrkhglPage) {
       ZcsxNhcjxx zcsxNhcjxx = new ZcsxNhcjxx();
       KrkhglReceive krkhglReceive = new KrkhglReceive();
       Khhmcxx khhmcxx =new Khhmcxx();
       BeanUtils.copyProperties(khglKrkhglPage,zcsxNhcjxx);
       BeanUtils.copyProperties(khglKrkhglPage,krkhglReceive);
       BeanUtils.copyProperties(khglKrkhglPage,khhmcxx);
       vKhglKrkhglService.saveMain(zcsxNhcjxx,krkhglReceive,khglKrkhglPage.getKhhmcxxList(),khglKrkhglPage.getCamsZcsxGrpjsxxxList());
       return Result.ok("添加成功！");
   }

    /**
     * Pad添加
     * @return
     */
    @AutoLog(value = "小额农贷Pad-编辑")
    @ApiOperation(value="小额农贷Pad-编辑", notes="小额农贷Pad-编辑")
    @PutMapping(value = "/addXend")
    public Result<?> addXend(@RequestBody KhglKrkhglPage khglKrkhglPage) {
        ZcsxNhcjxx zcsxNhcjxx = new ZcsxNhcjxx();
        KrkhglReceive krkhglReceive = new KrkhglReceive();
        BeanUtils.copyProperties(khglKrkhglPage,zcsxNhcjxx);
        BeanUtils.copyProperties(khglKrkhglPage,krkhglReceive);
        vKhglKrkhglService.saveMainPad(zcsxNhcjxx,krkhglReceive,khglKrkhglPage.getKhhmcxxList(),khglKrkhglPage.getCamsZcsxGrpjsxxxList());
        return Result.ok("添加成功！");
    }

    /**
     * 查询小额农贷附件信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryXendFjxx",method = RequestMethod.GET)
    public Result<?> queryNhGrFjxx(@RequestParam(name = "id") String id) {
        try {
            /*QueryWrapper<vKhglKrkhgl> khxxQueryWrapper = new QueryWrapper<>();
            khxxQueryWrapper.eq("id", id);
            vKhglKrkhgl xendkhxx = vKhglKrkhglService.getOne(khxxQueryWrapper);
            QueryWrapper<Fjgl> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("zjhm", xendkhxx.getZjhm());
            List<Fjgl> list = fjglService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }*/
            QueryWrapper<Fjgl> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm", id);
            List<Fjgl> list = fjglService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }

        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }

    @RequestMapping(value = "/queryXendFjxx020",method = RequestMethod.GET)
    public Result<?> queryXendFjxx020(@RequestParam(name = "id") String id) {
        try {
            /*QueryWrapper<vKhglKrkhgl> khxxQueryWrapper = new QueryWrapper<>();
            khxxQueryWrapper.eq("id", id);
            vKhglKrkhgl xendkhxx = vKhglKrkhglService.getOne(khxxQueryWrapper);
            QueryWrapper<Fjgl> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("zjhm", xendkhxx.getZjhm());
            List<Fjgl> list = fjglService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }*/
            QueryWrapper<Fjgl> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("zjhm", id);
            List<Fjgl> list = fjglService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }

        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }

    /**
     * 小额农贷附件信息保存
     * @param fjglVoList
     * @return
     */
    @RequestMapping(value = "/saveXendFjxx",method = RequestMethod.POST)
    public Result<?> saveFjImage(@RequestBody List<FjglVo> fjglVoList) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        /*String ZJHM = "";
        boolean flag = true;
        if (StringUtils.isEmpty(fjglVoList.get(0).getId())) {
            flag = false;
        } else {
            ZcsxNhcjxx form = zcsxNhcjxxService.getById(fjglVoList.get(0).getId());
            ZJHM = form.getZjhm();
        }*/
        try {
            if (fjglVoList != null && fjglVoList.size() > 0) {
                log.info("===CAMS_JBXX_NHZLLB附件{}张===",fjglVoList.size());
                int count =  0;
                for (int i = 0; i < fjglVoList.size(); i++) {
                    if (StringUtils.isEmpty(fjglVoList.get(i).getZlbh())) {
                        Fjgl fjgl = new Fjgl();
                        fjgl.setQydm(fjglVoList.get(i).getQydm());
                        fjgl.setHhbm(fjglVoList.get(i).getHhbm());
                        /*if (flag) {
                            fjgl.setZjhm(ZJHM);
                        } else {
                            fjgl.setZjhm(fjglVoList.get(i).getZjhm());
                        }*/
                        fjgl.setZjhm(fjglVoList.get(i).getZjhm());
                        fjgl.setZlbh(UUIDGenerator.generate());
                        fjgl.setZllx(fjglVoList.get(i).getZllx());
                        fjgl.setZldx(fjglVoList.get(i).getZldx());
                        fjgl.setFwlj(fjglVoList.get(i).getFwlj());
                        fjgl.setZlmc(fjglVoList.get(i).getZlmc());
                        fjgl.setZllj(uploadpath + "/" + fjglVoList.get(i).getFwlj());
                        fjgl.setScsj(new Date());
                        fjgl.setScr(sysUser.getUsername());
                        fjgl.setLrsj(new Date());
                        fjgl.setLrr(sysUser.getUsername());
                        boolean save = fjglService.save(fjgl);
                        if (save)
                            count++;
                    }
                }
                log.info("===成功增加{}张附件===",count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    /**
     * 小额农贷附件信息删除
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/deleteXendFjxx",method = RequestMethod.POST)
    public Result<?> deleteNhGrfjImage(@RequestBody  List<Fjgl> jsonObject) {
        try {
            if (jsonObject!=null  && jsonObject.size()>0){
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (!StringUtils.isEmpty(jsonObject.get(i).getZlbh())){
                        UpdateWrapper<Fjgl> fjxxWrapper=new UpdateWrapper<>();
                        fjxxWrapper.eq("zlbh",jsonObject.get(i).getZlbh());
                        fjglService.remove(fjxxWrapper);
                    }
                }
            }

        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }


    /**
     * 添加家庭成员信息
     * @param
     * @return
     */
    @RequestMapping(value = "/addJtcyxx", method = RequestMethod.POST)
    public Result<?> addJtcyxx(@RequestBody VKhglGrkhgl vKhglGrkhgl,@Param("hhbm") String hhbm) {
        System.out.println("vKhglGrkhgl--------------"+vKhglGrkhgl+hhbm+"---hhbm");
        int count = ivKhglGrkhglService.addJtcyxx(vKhglGrkhgl);
        if (count>0){
           return Result.ok("添加成功！");
       }else {
           return Result.error("添加失败！");
       }
    }

   /**
    * 编辑
    * @param khglKrkhglPage
    * @return
    */
   @AutoLog(value = "小额农贷Pad-编辑")
   @ApiOperation(value="小额农贷Pad-编辑", notes="小额农贷Pad-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody KhglKrkhglPage khglKrkhglPage) {
       ZcsxNhcjxx zcsxNhcjxx = new ZcsxNhcjxx();
       KrkhglReceive krkhglReceive = new KrkhglReceive();
       Khhmcxx khhmcxx =new Khhmcxx();
       BeanUtils.copyProperties(khglKrkhglPage,zcsxNhcjxx);
       BeanUtils.copyProperties(khglKrkhglPage,krkhglReceive);
       BeanUtils.copyProperties(khglKrkhglPage,khhmcxx);
       vKhglKrkhglService.updateMain(zcsxNhcjxx,krkhglReceive,khglKrkhglPage.getKhhmcxxList(),khglKrkhglPage.getCamsZcsxGrpjsxxxList());
       return Result.ok("编辑成功!");
   }

    /**
     * Pad编辑
     * @param khglKrkhglPage
     * @return
     */
    @AutoLog(value = "小额农贷Pad-编辑")
    @ApiOperation(value="小额农贷Pad-编辑", notes="小额农贷Pad-编辑")
    @PutMapping(value = "/editPad")
    public Result<?> editPad(@RequestBody KhglKrkhglPage khglKrkhglPage) {
        ZcsxNhcjxx zcsxNhcjxx = new ZcsxNhcjxx();
        KrkhglReceive krkhglReceive = new KrkhglReceive();
        BeanUtils.copyProperties(khglKrkhglPage,zcsxNhcjxx);
        BeanUtils.copyProperties(khglKrkhglPage,krkhglReceive);
        vKhglKrkhglService.updateMainPad(zcsxNhcjxx,krkhglReceive,khglKrkhglPage.getKhhmcxxList(),khglKrkhglPage.getCamsZcsxGrpjsxxxList());
        return Result.ok("编辑成功!");
    }

    /**
    * 通过id删除
    * @param id
    * @return
    */
    @AutoLog(value = "小额农贷Pad-通过id删除")
    @ApiOperation(value="小额农贷Pad-通过id删除", notes="小额农贷Pad-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       vKhglKrkhglService.delMain(id);
       return Result.ok("删除成功!");
    }

    /**
    * 批量删除
    * @param ids
    * @return
    */
    @AutoLog(value = "小额农贷Pad-批量删除")
    @ApiOperation(value="小额农贷Pad-批量删除", notes="小额农贷Pad-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.vKhglKrkhglService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
    }

    /**
    * 通过id查询
    * @param id
    * @return
    */
    @AutoLog(value = "小额农贷Pad-通过id查询")
    @ApiOperation(value="小额农贷Pad-通过id查询", notes="小额农贷Pad-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       vKhglKrkhgl vKhglKrkhgl = vKhglKrkhglService.getById(id);
       return Result.ok(vKhglKrkhgl);
    }

    /**
    * 导出excel
    * @param request
    * @param vKhglKrkhgl
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, vKhglKrkhgl vKhglKrkhgl) {
     return super.exportXls(request, vKhglKrkhgl, vKhglKrkhgl.class, "小额农贷采集");
    }

    /**
    * 通过excel导入数据
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     return super.importExcel(request, response, vKhglKrkhgl.class);
    }

    /**
     * 通过id修改采集状态
     * @param id
     * @return
     */
    @GetMapping(value = "/updateCjzt")
    public Result<?> updateCjzt(@Param("id") String id) {
        System.out.println("id---"+id);
        zcsxNhcjxxService.updateCjzt(id);
        return Result.ok("修改成功!");
    }

    /**
     * 农户采集信息查看
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/nhcjxx", method = RequestMethod.PUT)
    public Result<ZcsxNhcjxx> nhcjxx(@RequestBody JSONObject jsonObject ) {
        Result<ZcsxNhcjxx> result = new Result<ZcsxNhcjxx>();
        try {
            ZcsxNhcjxx zcsxNhcjxx = new ZcsxNhcjxx();
            zcsxNhcjxx.setZjhm(jsonObject.getString("zjhm"));
            Map<String,String[]> map = new HashMap<>();
            QueryWrapper<ZcsxNhcjxx> queryWrapper = QueryGenerator.initQueryWrapper(zcsxNhcjxx,map);
            List<ZcsxNhcjxx> zcsxNhcjxxList = zcsxNhcjxxService.list(queryWrapper);
            if(zcsxNhcjxxList.size()!=0) {
                result.setResult(zcsxNhcjxxList.get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return	result;
    }

    /**
     * 农户采集信息查看
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/nhcjxxPad", method = RequestMethod.PUT)
    public Result<?> nhcjxxPad(@RequestBody JSONObject jsonObject ) {
        Result<?> result = new Result<>();
        try {
            vKhglKrkhgl xendcjxx = new vKhglKrkhgl();
            xendcjxx.setZjhm(jsonObject.getString("zjhm"));
            Map<String,String[]> map = new HashMap<>();
            QueryWrapper<vKhglKrkhgl> queryWrapper = QueryGenerator.initQueryWrapper(xendcjxx,map);
            Page<vKhglKrkhgl> page = new Page<vKhglKrkhgl>(1, 10);
            IPage<vKhglKrkhgl> pageList = vKhglKrkhglService.page(page, queryWrapper);
            if(pageList!=null&& pageList.getSize()>0) {
                return Result.ok(pageList);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
        }
        return	result;
    }

    /**
     * 家庭成员信息查看
     * @param jsonObject
     * @return
     */
    @RequestMapping(value="/jtcyxx",method = RequestMethod.PUT)
    public Result<?> jtcyxx(@RequestBody JSONObject jsonObject){
        JSONObject jsonObject1 = new JSONObject();
        try{
           Map<String,String[]> map = new HashMap<>();
            Khhmcxx khhmcxx = new Khhmcxx();
           khhmcxx.setZjhm(jsonObject.getString("zjhm"));
           QueryWrapper<Khhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx,map);
           List<Khhmcxx> khhmcxxList = khhmcxxService.list(queryWrapper);
           if(khhmcxxList.size()!=0){
               List<Khhmcxx> khhmcjtcyxxList= khhmcxxService.selectByhhbm(khhmcxxList.get(0).getHhbm(),jsonObject.getString("zjhm"));
               jsonObject1.put("jtcylist",khhmcjtcyxxList);
               jsonObject1.put("jbxx",khhmcxxList.get(0));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return Result.ok(jsonObject1);
    }

    /**
     * 家庭成员信息查看
     * @param
     * @return
     */
    @GetMapping(value = "/jtcyxxPad")
    public Result<List<Khhmcxx>> jtcyxxPad(@Param("id") String id){
        Result<List<Khhmcxx>> result = new Result<>();
        try{
            QueryWrapper<vKhglKrkhgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            vKhglKrkhgl nhcjxx= vKhglKrkhglService.getOne(queryWrapper);
            if(nhcjxx!=null){
                List<Khhmcxx> khhmcjtcyxxList= khhmcxxService.selectByhhbm(nhcjxx.getHhbm(),nhcjxx.getZjhm());
                result.setResult(khhmcjtcyxxList);
                result.setSuccess(true);
                result.setCode(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取家庭成员信息
     * @param hhbm
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/family", method = RequestMethod.GET)
    public Result<?> nhxxcjb(@RequestParam(name = "hhbm",required = true) String hhbm,
                             @RequestParam(name = "zjhm",required = true) String zjhm) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<Khhmcxx> khhmcjtcyxxList= khhmcxxService.selectByhhbm(hhbm,zjhm);
            if(khhmcjtcyxxList!=null&&khhmcjtcyxxList.size()>0){
                for (Khhmcxx khglKhhmcxx1 : khhmcjtcyxxList) {
                    if ( khglKhhmcxx1.getYhzgx().equals("2")){
                        jsonObject.put("xm",khglKhhmcxx1.getKhmc());
                        jsonObject.put("zjhm",khglKhhmcxx1.getZjhm());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 家庭资产情况
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/jtzcqk",method = RequestMethod.GET)
    public Result<?> jtzcqk(@RequestParam(name = "zjhm",required = true) String zjhm){
        Result<List<CamsZcsxGrpjsxxx>> result = new Result<>();
        JSONObject jsonObject = new JSONObject();
        try{
             Map<String,String[]> map = new HashMap<>();
            Khhmcxx khhmcxx = new Khhmcxx();
            khhmcxx.setZjhm(zjhm);
            QueryWrapper<Khhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx,map);
            List<Khhmcxx> khhmcxxList = khhmcxxService.list(queryWrapper);
            if (khhmcxxList.size()!=0){
                CamsZcsxGrpjsxxx camsZcsxGrpjsxxx1 = new CamsZcsxGrpjsxxx();
                camsZcsxGrpjsxxx1.setHhbm(khhmcxxList.get(0).getHhbm());
                  QueryWrapper<CamsZcsxGrpjsxxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxx1,map);
                List<CamsZcsxGrpjsxxx> GrpjsxxxList = camsZcsxGrpjsxxxService.list(queryWrapper1);
                if (GrpjsxxxList.size()!=0) {
                    jsonObject.put("list", GrpjsxxxList);
                    jsonObject.put("ldzcSfthkh", GrpjsxxxList.get(0).getLdzcSfthkh());
                    jsonObject.put("ldzcSfthyck", GrpjsxxxList.get(0).getLdzcSfthyck());
                    jsonObject.put("objet", GrpjsxxxList.get(0));
                    jsonObject.put("khmc",khhmcxxList.get(0).getKhmc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 家庭资产情况2
     * @param id
     * @return
     */
    @RequestMapping(value = "/jtzcqkPad",method = RequestMethod.GET)
    public Result<?> jtzcqkPad(@RequestParam(name = "id",required = true) String id){
        Result<List<CamsZcsxGrpjsxxx>> result = new Result<>();
        JSONObject jsonObject = new JSONObject();
        try{
            QueryWrapper<vKhglKrkhgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            vKhglKrkhgl nhcjxx= vKhglKrkhglService.getOne(queryWrapper);

            Map<String,String[]> map = new HashMap<>();
            Khhmcxx khhmcxx = new Khhmcxx();
            khhmcxx.setZjhm(nhcjxx.getZjhm());
            QueryWrapper<Khhmcxx> queryWrapperHmcxx = QueryGenerator.initQueryWrapper(khhmcxx,map);
            List<Khhmcxx> khhmcxxList = khhmcxxService.list(queryWrapperHmcxx);
            if (khhmcxxList.size()!=0){
                CamsZcsxGrpjsxxx camsZcsxGrpjsxxx1 = new CamsZcsxGrpjsxxx();
                camsZcsxGrpjsxxx1.setHhbm(khhmcxxList.get(0).getHhbm());
                QueryWrapper<CamsZcsxGrpjsxxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxx1,map);
                List<CamsZcsxGrpjsxxx> GrpjsxxxList = camsZcsxGrpjsxxxService.list(queryWrapper1);
                if (GrpjsxxxList.size()!=0) {
                    jsonObject.put("list", GrpjsxxxList);
                    jsonObject.put("ldzcSfthkh", GrpjsxxxList.get(0).getLdzcSfthkh());
                    jsonObject.put("ldzcSfthyck", GrpjsxxxList.get(0).getLdzcSfthyck());
                    Pjsxxx pjsxxx=new Pjsxxx();
                    BeanUtils.copyProperties(GrpjsxxxList.get(0),pjsxxx);
                    jsonObject.put("objet", pjsxxx);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 家庭资产情况2
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteJtcyxx",method = RequestMethod.GET)
    public Result<?> deleteJtcyxx(@RequestParam(name = "id",required = true) String id){
        Result<List<CamsZcsxGrpjsxxx>> result = new Result<>();
        try{
            QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            Khhmcxx khhmc= khhmcxxService.getOne(queryWrapper);
            if(khhmc!=null){
                khhmc.setHhbm("");
                khhmcxxService.updateById(khhmc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok();
    }

    /**
     * 签名Base64
     * @param id
     * @return
     */
    @RequestMapping(value = "/signBase64",method = RequestMethod.GET)
    public Result<?> signBase64(@RequestParam(name = "id",required = true) String id){
        Result<List<CamsZcsxGrpjsxxx>> result = new Result<>();
        JSONObject jsonObject = new JSONObject();
        try{
            QueryWrapper<vKhglKrkhgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            vKhglKrkhgl nhcjxx= vKhglKrkhglService.getOne(queryWrapper);
            if(nhcjxx!=null){
                if(nhcjxx.getSign1()!=null&&nhcjxx.getSign1().length()>0){
                   jsonObject.put("sign1",getImgBase64Str(uploadpath+File.separator+nhcjxx.getSign1()));
               }else{
                   jsonObject.put("sign1","");
               }
                if(nhcjxx.getSign1()!=null&&nhcjxx.getSign1().length()>0){
                    jsonObject.put("sign2",getImgBase64Str(uploadpath+File.separator+nhcjxx.getSign2()));
                }else{
                    jsonObject.put("sign2","");
                }
                if(nhcjxx.getSign1()!=null&&nhcjxx.getSign1().length()>0){
                    jsonObject.put("sign3",getImgBase64Str(uploadpath+File.separator+nhcjxx.getSign3()));
                }else{
                    jsonObject.put("sign3","");
                }
            }else{
                jsonObject.put("sign2","");
                jsonObject.put("sign3","");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    @RequestMapping(value = "/signBase64020",method = RequestMethod.GET)
    public Result<?> signBase64020(@RequestParam(name = "id",required = true) String id){
        if (org.apache.commons.lang3.StringUtils.isBlank(id))
            return Result.ok();

        Nhxq byId = nhxqService.getById(id);

        JSONObject jsonObject = new JSONObject();
        try{

            if(byId!=null){
                if(byId.getSign1()!=null&&byId.getSign1().length()>0){
                    jsonObject.put("sign1",getImgBase64Str(uploadpath+File.separator+byId.getSign1()));
                }else{
                    jsonObject.put("sign1","");
                }
                if(byId.getSign2()!=null&&byId.getSign2().length()>0){
                    jsonObject.put("sign2",getImgBase64Str(uploadpath+File.separator+byId.getSign2()));
                }else{
                    jsonObject.put("sign2","");
                }
                if(byId.getSign3()!=null&&byId.getSign3().length()>0){
                    jsonObject.put("sign3",getImgBase64Str(uploadpath+File.separator+byId.getSign3()));
                }else{
                    jsonObject.put("sign3","");
                }
                if (byId.getKhjlqz()!=null&&byId.getKhjlqz().length()>0){
                    jsonObject.put("khjlqz",getImgBase64Str(uploadpath+File.separator+byId.getKhjlqz()));
                }else{
                    jsonObject.put("khjlqz","");
                }
                if (byId.getFxjlqz()!=null&&byId.getFxjlqz().length()>0){
                    jsonObject.put("fxjlqz",getImgBase64Str(uploadpath+File.separator+byId.getFxjlqz()));
                }else{
                    jsonObject.put("fxjlqz","");
                }
                if (byId.getZhhzqz()!=null&&byId.getZhhzqz().length()>0){
                    jsonObject.put("zhhzqz",getImgBase64Str(uploadpath+File.separator+byId.getZhhzqz()));
                }else{
                    jsonObject.put("zhhzqz","");
                }
            }else{
                jsonObject.put("sign1","");
                jsonObject.put("sign2","");
                jsonObject.put("sign3","");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgBase64Str(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data==null){
            return new String();
        }else{
            return "data:image/png;base64,"+new String(Base64.encodeBase64(data));
        }
    }

    /**
     * 基本信息
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/jbxx",method = RequestMethod.GET)
    public Result<?> jbxx (@RequestParam(name="zjhm",required = true) String zjhm){
          JSONObject jsonObject = new JSONObject();
       try{
           vKhglKrkhgl vKhglKrkhgl = new vKhglKrkhgl();
           vKhglKrkhgl.setZjhm(zjhm);
           Map<String,String[]> map = new HashMap<>();
           QueryWrapper<vKhglKrkhgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglKrkhgl,map);
           List<vKhglKrkhgl> khglKrkhglList = vKhglKrkhglService.list(queryWrapper);
           if (khglKrkhglList.size()!=0){
               jsonObject.put("zkhjl_dictText",khglKrkhglList.get(0).getKhjl()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",khglKrkhglList.get(0).getKhjl()));
               jsonObject.put("yjyxdybh_dictText",khglKrkhglList.get(0).getYjyxdybh()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("YXDYGL_YJYXDYGL","dymc","dybh",khglKrkhglList.get(0).getYjyxdybh()));
               jsonObject.put("ejyxdybh_dictText",khglKrkhglList.get(0).getEjyxdybh()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("YXDYGL_EJYXDYGL","dymc","dybh",khglKrkhglList.get(0).getEjyxdybh()));
               jsonObject.put("sjyxdybh_dictText",khglKrkhglList.get(0).getSjyxdybh()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("YXDYGL_SJYXDYGL","dymc","dybh",khglKrkhglList.get(0).getSjyxdybh()));
               jsonObject.put("jbxx",khglKrkhglList.get(0));
           }
           jsonObject.put("hhbm",sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));

       } catch (Exception e) {
          e.printStackTrace();
        }
          return	Result.ok(jsonObject);
       }

    /**
     * 基本信息
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/jbxxPad",method = RequestMethod.GET)
    public Result<?> jbxxPad (@RequestParam(name="zjhm",required = true) String zjhm){
        JSONObject jsonObject = new JSONObject();
        try{
            Khhmcxx khhmcxx = new Khhmcxx();
            khhmcxx.setZjhm(zjhm);
            Map<String,String[]> map = new HashMap<>();
            QueryWrapper<Khhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx,map);
            List<Khhmcxx> khhmcxxList = khhmcxxService.list(queryWrapper);
            if (khhmcxxList!=null&khhmcxxList.size()!=0){
                List<Khhmcxx> khhmcjtcyxxList= khhmcxxService.selectByhhbm(khhmcxxList.get(0).getHhbm(),khhmcxxList.get(0).getZjhm());
                jsonObject.put("jbxx",khhmcxxList.get(0));
                jsonObject.put("jtcyxx",khhmcjtcyxxList);
                jsonObject.put("sfcz",true);
            }else{
                jsonObject.put("sfcz",false);
                jsonObject.put("hhbm",sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 附件信息
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public Result<?> img(@RequestParam(name = "zjhm",required = true)String zjhm){
        JSONObject json = new JSONObject();

        try {
           Map<String, String[]> map = new HashMap<>();
           JSONArray brsfzArray = new JSONArray();
           JSONArray posfzArray = new JSONArray();
           JSONArray jhzArray = new JSONArray();
           JSONArray rxzpArray = new JSONArray();
           JSONArray fwpzArray  = new JSONArray();
           JSONArray qzpzArray = new JSONArray();
           JSONArray qczpArray  = new JSONArray();
           JSONArray qtzcArray = new JSONArray();
           JSONArray jycsArray  = new JSONArray();
           JSONArray cjzpzArray  = new JSONArray();

           Fjgl fjgl = new Fjgl();
           fjgl.setZjhm(zjhm);
           QueryWrapper<Fjgl> queryWrapper = QueryGenerator.initQueryWrapper(fjgl, map);
           List<Fjgl> fjglList = fjglService.list(queryWrapper);
           for (Fjgl fjgl1 : fjglList) {
               if(fjgl1.getZllx()!=null) {
                   JSONObject jsonObject = new JSONObject();
                   if (fjgl1.getZllx().equals("1")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       brsfzArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("2")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       posfzArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("3")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       jhzArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("4")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       rxzpArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("5")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       fwpzArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("6")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       qzpzArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("7")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       qczpArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("8")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       qtzcArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("9")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       jycsArray.add(jsonObject);
                   } else if (fjgl1.getZllx().equals("10")) {
                       jsonObject.put("uid", fjgl1.getZlbh());
                       jsonObject.put("name", fjgl1.getZlmc());
                       jsonObject.put("status", "done");
                       jsonObject.put("url", fjgl1.getFwlj());
                       cjzpzArray.add(jsonObject);
                   }
               }
           }
            json.put("brsfzArray",brsfzArray);
            json.put("posfzArray",posfzArray);
            json.put("jhzArray",jhzArray);
            json.put("rxzpArray",rxzpArray);
            json.put("fwpzArray",fwpzArray);
            json.put("qzpzArray",qzpzArray);
            json.put("qczpArray",qczpArray);
            json.put("qtzcArray",qtzcArray);
            json.put("jycsArray",jycsArray);
            json.put("cjzpzArray",cjzpzArray);
        } catch (Exception e) {
           e.printStackTrace();
       }
        return Result.ok(json);
    }

    /**
     * 基本信息
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "hhbm",method = RequestMethod.GET)
    public Result<?> hhbm (@RequestParam(name="zjhm",required = true) String zjhm){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("hhbm",sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

}
