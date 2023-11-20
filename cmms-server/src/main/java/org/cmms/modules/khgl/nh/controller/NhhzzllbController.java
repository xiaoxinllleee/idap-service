package org.cmms.modules.khgl.nh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzzllbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @Description: 1
* @Author: jeecg-boot
* @Date:   2020-08-02
* @Version: V1.0
*/
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/Nhhzzllb")
public class NhhzzllbController extends JeecgController<KhglNhhzzllb, IKhglNhhzzllbService> {
   @Autowired
   private IKhglNhhzzllbService khglNhhzzllbService;


    @Value(value = "${common.path.upload}")
    private String uploadpath;
   /**
    * 分页列表查询
    *
    * @param khglNhhzzllb
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "1-分页列表查询")
   @ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(KhglNhhzzllb khglNhhzzllb,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<KhglNhhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(khglNhhzzllb, req.getParameterMap());
       Page<KhglNhhzzllb> page = new Page<KhglNhhzzllb>(pageNo, pageSize);
       IPage<KhglNhhzzllb> pageList = khglNhhzzllbService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

    @RequestMapping(value = "/queryNhhzFjxx",method = RequestMethod.GET)
    public Result<?> queryNhhzFjxx(@RequestParam(name = "hhbm") String hhbm) {
        try {

            JSONArray rhzpArray = new JSONArray();
            JSONArray fwzpArray = new JSONArray();
            JSONArray ykhhyArray = new JSONArray();
            JSONArray snzpArray = new JSONArray();
            JSONArray hkbArray  = new JSONArray();
            JSONArray qtfjArray = new JSONArray();
            JSONObject json = new JSONObject();


            QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm",hhbm);
            List<KhglNhhzzllb> list = khglNhhzzllbService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                for (KhglNhhzzllb khglNhhzzllb : list) {
                    if(khglNhhzzllb.getZllx()!=null) {
                        JSONObject jsonObject = new JSONObject();
                        if (khglNhhzzllb.getZllx().equals("1")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            rhzpArray.add(jsonObject);
                        } else if (khglNhhzzllb.getZllx().equals("2")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            fwzpArray.add(jsonObject);
                        } else if (khglNhhzzllb.getZllx().equals("3")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            ykhhyArray.add(jsonObject);
                        } else if (khglNhhzzllb.getZllx().equals("4")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            snzpArray.add(jsonObject);
                        } else if (khglNhhzzllb.getZllx().equals("5")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            hkbArray.add(jsonObject);
                        } else if (khglNhhzzllb.getZllx().equals("6")) {
                            jsonObject.put("uid", khglNhhzzllb.getZlbh());
                            jsonObject.put("name", khglNhhzzllb.getZlmc());
                            jsonObject.put("status", "done");
                            jsonObject.put("url", khglNhhzzllb.getFwlj());
                            qtfjArray.add(jsonObject);
                        }
                    }
                }
                json.put("rhzpArray",rhzpArray);
                json.put("fwzpArray",fwzpArray);
                json.put("ykhhyArray",ykhhyArray);
                json.put("snzpArray",snzpArray);
                json.put("hkbArray",hkbArray);
                json.put("qtfjArray",qtfjArray);
             return Result.ok(json);
            }
        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }


    @RequestMapping(value = "/saveNhhzfjImage",method = RequestMethod.POST)
    public Result<?> saveNhhzfjImage(@RequestBody  List<KhglNhhzzllb>  jsonObject) {
        try {
            if (jsonObject!=null  && jsonObject.size()>0){
               LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
               for (int i = 0; i < jsonObject.size(); i++) {
                   if (StringUtils.isEmpty(jsonObject.get(i).getId())) {
                       KhglNhhzzllb fjgl = new KhglNhhzzllb();
                       fjgl.setQydm(jsonObject.get(i).getQydm());
                       fjgl.setHhbm(jsonObject.get(i).getHhbm());
                       fjgl.setZlbh(UUID.randomUUID().toString().substring(0, 14));
                       fjgl.setZllx(jsonObject.get(i).getZllx());
                       fjgl.setZldx(jsonObject.get(i).getZldx());
                       fjgl.setFwlj(jsonObject.get(i).getFwlj());
                       fjgl.setZlmc(jsonObject.get(i).getZlmc());
                       fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
                       fjgl.setScsj(new Date());
                       fjgl.setScr(sysUser.getUsername());
                       fjgl.setLrsj(new Date());
                       fjgl.setLrr(sysUser.getUsername());
                       khglNhhzzllbService.save(fjgl);
                   }
               }
           }
        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/deleteNhhzfjImage",method = RequestMethod.POST)
    public Result<?> deleteNhhzfjImage(@RequestBody  List<KhglNhhzzllb> jsonObject) {
        try {
            if (jsonObject!=null  && jsonObject.size()>0){
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (!StringUtils.isEmpty(jsonObject.get(i).getZlbh())){
                        UpdateWrapper<KhglNhhzzllb> khglNhzllbPadUpdateWrapper=new UpdateWrapper<>();
                        khglNhzllbPadUpdateWrapper.eq("zlbh",jsonObject.get(i).getZlbh());
                        khglNhhzzllbService.remove(khglNhzllbPadUpdateWrapper);
                    }
                }
            }

        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }




   /**
    * 添加
    *
    * @param khglNhhzzllb
    * @return
    */
   @AutoLog(value = "1-添加")
   @ApiOperation(value="1-添加", notes="1-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody KhglNhhzzllb khglNhhzzllb) {
       khglNhhzzllbService.save(khglNhhzzllb);
       return Result.ok("添加成功！");
   }


   /**
    * 编辑
    *
    * @param khglNhhzzllb
    * @return
    */
   @AutoLog(value = "1-编辑")
   @ApiOperation(value="1-编辑", notes="1-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody KhglNhhzzllb khglNhhzzllb) {
       khglNhhzzllbService.updateById(khglNhhzzllb);
       return Result.ok("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "1-通过id删除")
   @ApiOperation(value="1-通过id删除", notes="1-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       khglNhhzzllbService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "1-批量删除")
   @ApiOperation(value="1-批量删除", notes="1-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.khglNhhzzllbService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "1-通过id查询")
   @ApiOperation(value="1-通过id查询", notes="1-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       KhglNhhzzllb khglNhhzzllb = khglNhhzzllbService.getById(id);
       return Result.ok(khglNhhzzllb);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param khglNhhzzllb
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, KhglNhhzzllb khglNhhzzllb) {
     return super.exportXls(request, khglNhhzzllb, KhglNhhzzllb.class, "1");
 }

 /**
  * 通过excel导入数据
  *
  * @param request
  * @param response
  * @return
  */
 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     return super.importExcel(request, response, KhglNhhzzllb.class);
 }

}
