package org.cmms.modules.khgl.nh.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.NhbkbpyFictitious;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.pad.nhxxgl.entity.KhglKhhmcxx;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglKhhmcxxPadService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.INhbkbpyfsxxService;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
* @Description: 评议员信息
* @Author: jeecg-boot
* @Date:   2020-07-24
* @Version: V1.0
*/
@Slf4j
@Api(tags="评议信息")
@RestController
@RequestMapping("/nh/pyxx")
public class PyxxController extends JeecgController<Pyyxx, IPyyxxService> {
   @Autowired
    private IPyyxxService pyyxxService;

    @Autowired
    private INhbkbpyService nhbkbpyService;

    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;

    @Autowired
    private IKhglKhhmcxxPadService khhmcService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private INhbkbpyfsxxService nhbkbpyfsxxService;
    @Autowired
    private IPyyxxService iPyyxxService;
    @Autowired
    private IKhglKhhmcxxPadService khglKhhmcxxPadService;

   /**
    * 分页列表查询
    *
    * @param pyyxx
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "评议员信息-分页列表查询")
   @ApiOperation(value="评议员信息-分页列表查询", notes="评议员信息-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Pyyxx pyyxx,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<Pyyxx> queryWrapper = QueryGenerator.initQueryWrapper(pyyxx, req.getParameterMap());
       Page<Pyyxx> page = new Page<Pyyxx>(pageNo, pageSize);
       IPage<Pyyxx> pageList = pyyxxService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

   /**
    * 添加
    *
    * @param pyyxx
    * @return
    */
   @AutoLog(value = "评议员信息-添加")
   @ApiOperation(value="评议员信息-添加", notes="评议员信息-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Pyyxx pyyxx) {
       pyyxxService.save(pyyxx);
       return Result.ok("添加成功！");
   }

   /**
    * 编辑
    *
    * @param pyyxx
    * @return
    */
   @AutoLog(value = "评议员信息-编辑")
   @ApiOperation(value="评议员信息-编辑", notes="评议员信息-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Pyyxx pyyxx) {
       pyyxxService.updateById(pyyxx);
       return Result.ok("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "评议员信息-通过id删除")
   @ApiOperation(value="评议员信息-通过id删除", notes="评议员信息-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       pyyxxService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "评议员信息-批量删除")
   @ApiOperation(value="评议员信息-批量删除", notes="评议员信息-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.pyyxxService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "评议员信息-通过id查询")
   @ApiOperation(value="评议员信息-通过id查询", notes="评议员信息-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       Pyyxx pyyxx = pyyxxService.getById(id);
       return Result.ok(pyyxx);
   }


 /**
  * 导出excel
  *
  * @param request
  * @param pyyxx
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, Pyyxx pyyxx) {
     return super.exportXls(request, pyyxx, Pyyxx.class, "评议员信息");
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
     return super.importExcel(request, response, Pyyxx.class);
 }



    @RequestMapping(value = "/savePyxx",method = RequestMethod.POST)
    public Result<?> savePyxx(NhbkbpyFictitious nhbkbbkbpy) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(nhbkbbkbpy.getPyyid())) {
                 //ID不为空，则为选择已有的评议员信息
                //根据评议员ID查询评议员信息
                QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("pyyid", nhbkbbkbpy.getPyyid());
                Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                nhbkbbkbpy.setPyyxm(pyyxx.getPyyxm());
                nhbkbbkbpy.setPyyzjhm(pyyxx.getPyyzjhm());
            } else {
                //如果ID为空，判断是否需要增加记录
                QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("pyyzjhm", nhbkbbkbpy.getPyyzjhm());
                queryWrapper.eq("qydm", nhbkbbkbpy.getQydm());
                Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                if (pyyxx == null) {
                    Pyyxx pyyxxAdd = new Pyyxx();
                    pyyxxAdd.setQydm(nhbkbbkbpy.getQydm());
                    pyyxxAdd.setPyyxm(nhbkbbkbpy.getPyyxm());
                    pyyxxAdd.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
                    pyyxxAdd.setPyyid(IdUtil.simpleUUID());
                    pyyxxAdd.setLrbz(1);
                    pyyxxAdd.setLrr(loginUser.getUsername());
                    pyyxxAdd.setLrsj(new Date());
                    iPyyxxService.save(pyyxxAdd);
                }
            }
            String id = UUIDGenerator.generate();
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy,bkbpy);

            /*QueryWrapper<KhglNhhzxxgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",nhbkbbkbpy.getHzid());
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(queryWrapper);*/

            //通过id查询授信对象证件号码
            QueryWrapper<KhglKhhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",nhbkbbkbpy.getZjhm());
            KhglKhhmcxx  khhmcxx = khhmcService.getOne(queryWrapper);

            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user =sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setId(id);
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            nhbkbpyService.save(bkbpy);
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            //BeanUtils.copyProperties(nhbkbbkbpy,nhbkbpyfsxx);
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk()!=null){
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null){
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null){
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null ){
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() !=null){
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加评议信息失败", e);
            return  Result.error("系统错误，添加失败！");
        }
    }



    //祁阳地区特殊处理
    @RequestMapping(value = "/saveQyPyxx",method = RequestMethod.POST)
    public Result<?> saveQyPyxx(NhbkbpyFictitious nhbkbbkbpy) {
        try {
            String id = UUIDGenerator.generate();
            //通过id查询授信对象证件号码
            QueryWrapper<KhglKhhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",nhbkbbkbpy.getZjhm());
            KhglKhhmcxx  khhmcxx = khhmcService.getOne(queryWrapper);

            //保存背靠背信息
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy,bkbpy);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user =sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setId(id);
            bkbpy.setKhmc(khhmcxx.getKhmc());
            bkbpy.setZjhm(khhmcxx.getZjhm());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            nhbkbpyService.save(bkbpy);

            //更新户主表授信对象证件号
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            khglNhhzxxgl.setSxdxzjh(khhmcxx.getZjhm());
            khglNhhzxxgl.setSxdx(khhmcxx.getKhmc());
            UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",nhbkbbkbpy.getHzid());
            khglNhhzxxglService.update(khglNhhzxxgl,updateWrapper);

            //保存附属信息表数据
            Nhbkbpyfsxx nhbkbpyfsxx = new Nhbkbpyfsxx();
            //BeanUtils.copyProperties(nhbkbbkbpy,nhbkbpyfsxx);
            nhbkbpyfsxx.setId(id);
            nhbkbpyfsxx.setQydm(nhbkbbkbpy.getQydm());
            nhbkbpyfsxx.setZjhm(khhmcxx.getZjhm());
            nhbkbpyfsxx.setKhmc(nhbkbbkbpy.getKhmc());
            nhbkbpyfsxx.setHhbm(nhbkbbkbpy.getHhbm());
            nhbkbpyfsxx.setPyyxm(nhbkbbkbpy.getPyyxm());
            nhbkbpyfsxx.setPyyzjhm(nhbkbbkbpy.getPyyzjhm());
            nhbkbpyfsxx.setPysj(nhbkbbkbpy.getPysj());
            if (nhbkbbkbpy.getFwjzqk()!=null){
                nhbkbpyfsxx.setFcjz(BigDecimal.valueOf(nhbkbpyfsxxService.fcjz(nhbkbbkbpy.getFwjzqk())));
            }
            if (nhbkbbkbpy.getDznyxfpqk() != null){
                nhbkbpyfsxx.setDznyxfpqk(BigDecimal.valueOf(nhbkbpyfsxxService.dznyxfpqk(nhbkbbkbpy.getDznyxfpqk())));
            }
            if (nhbkbbkbpy.getJtcsrqk() != null){
                nhbkbpyfsxx.setJtcsrqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtcsrqk(nhbkbbkbpy.getJtcsrqk())));
            }
            if (nhbkbbkbpy.getNjjqk() != null ){
                nhbkbpyfsxx.setNjjqk(BigDecimal.valueOf(nhbkbpyfsxxService.njjqk(nhbkbbkbpy.getNjjqk())));
            }
            if (nhbkbbkbpy.getJtysgjqk() !=null){
                nhbkbpyfsxx.setJtysgjqk(BigDecimal.valueOf(nhbkbpyfsxxService.jtysgjqk(nhbkbbkbpy.getJtysgjqk())));
            }

            nhbkbpyfsxxService.save(nhbkbpyfsxx);
            return Result.ok("添加成功");
        }catch (Exception e){
            log.error("添加评议信息失败", e);
            return  Result.error("系统错误，添加失败！");
        }
    }




    @RequestMapping(value = "/updatePyxx",method = RequestMethod.POST)
    public Result<?> updatePyxx(NhbkbpyFictitious nhbkbbkbpy) {
        try {
            Nhbkbpy bkbpy = new Nhbkbpy();
            BeanUtils.copyProperties(nhbkbbkbpy,bkbpy);
             QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",bkbpy.getId());
            Nhbkbpy nhbkbpy =  nhbkbpyService.getOne(queryWrapper);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user =sysUserService.getUserByName(sysUser.getUsername());
            bkbpy.setLrr(user.getUsername());
            bkbpy.setLrsj(new Date());
            bkbpy.setLrbz("1");
            bkbpy.setPyyzjhm(nhbkbpy.getPyyzjhm());
            bkbpy.setZjhm(nhbkbpy.getZjhm());
            QueryWrapper<Nhbkbpy> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",bkbpy.getId());
            nhbkbpyService.update(bkbpy,queryWrapper1);
        }catch (Exception e){
            e.printStackTrace();
            log.error("编辑评议信息失败", e);
            return  Result.error("系统错误，修改失败！");
        }
        return Result.ok("添加成功");
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评议员信息-通过id删除")
    @ApiOperation(value="评议员信息-通过id删除", notes="评议员信息-通过id删除")
    @DeleteMapping(value = "/deletePyxx")
    public Result<?> deletePyxx(@RequestParam(name="id",required=true) String id) {
        try {
            QueryWrapper<Nhbkbpy> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("id",id);
            nhbkbpyService.remove(queryWrapper);

            QueryWrapper<Nhbkbpyfsxx> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",id);
            nhbkbpyfsxxService.remove(queryWrapper1);

        }catch (Exception e){
            e.printStackTrace();
            log.error("删除评议信息失败", e);
            return  Result.error("删除评议信息失败！");
        }
        return Result.ok("删除成功");
    }

    /*
      通过证件号码去花名册查询家庭信息
       */
    @GetMapping(value = "/queryPyxx")
    public Result<?> queryPyxx(@Param("id") String id) {
        System.out.println("======= id ======>");
        System.out.println(id);
        QueryWrapper<KhglKhhmcxx> hmcxxQueryWrapper = new QueryWrapper<KhglKhhmcxx>();
        hmcxxQueryWrapper.eq("id",id);
        KhglKhhmcxx khglKhhmcxx = khglKhhmcxxPadService.getOne(hmcxxQueryWrapper);

        QueryWrapper<KhglNhhzxxgl> nhhzxxglWrapper = new QueryWrapper<KhglNhhzxxgl>();
        nhhzxxglWrapper.eq("hzzjhm",khglKhhmcxx.getZjhm());
        KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglWrapper);
        List<Nhbkbpy> result = new ArrayList<>();

        try {
            if (khglNhhzxxgl.getSxdxzjh()!= null){
                QueryWrapper<Nhbkbpy> pyxxQueryWrapper = new QueryWrapper<Nhbkbpy>();
                pyxxQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                pyxxQueryWrapper.eq("hhbm", khglKhhmcxx.getHhbm());
                pyxxQueryWrapper.orderByDesc("lrsj");
                List<Nhbkbpy> list = nhbkbpyService.list(pyxxQueryWrapper);
                System.out.println(list.size());
                if (CollUtil.isNotEmpty(list)){
                    for (int i = 0; i < list.size(); i++) {
                        Nhbkbpy nhbkbpy = list.get(i);
                        System.out.println("=======================================>");
                        System.out.println(nhbkbpy);
                        System.out.println("=======================================>");
                        if (StringUtils.isNotEmpty(nhbkbpy.getPylx())){
                            Integer pyls = nhbkbpy.getPyls();
                            String s = Convert.numberToChinese(pyls, false);
                            nhbkbpy.setPylsVal("第"+s+"轮");
                        }else {
                            String s = Convert.numberToChinese(i+1, false);
                            nhbkbpy.setPylsVal("第"+s+"轮");
                        }
                        result.add(nhbkbpy);
                    }
                }
            }
            return Result.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询评议信息错误！", e);
            return  Result.error("查询评议信息错误！");
        }
    }
}
