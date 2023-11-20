package org.cmms.modules.jtxx.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.base.entity.Dict;
import org.cmms.modules.jtxx.entity.Jtxx;
import org.cmms.modules.jtxx.entity.JtxxList;
import org.cmms.modules.jtxx.service.IJtxxService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date: 2020-10-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "家庭信息")
@RestController
@RequestMapping("/Jtxx/jtxx")
public class JtxxController extends JeecgController<Jtxx, IJtxxService> {
    @Autowired
    private IJtxxService jtxxService;

    /**
     * 分页列表查询
     *
     * @param jtxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "家庭信息-分页列表查询")
    @ApiOperation(value = "家庭信息-分页列表查询", notes = "家庭信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Jtxx jtxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Jtxx> queryWrapper = QueryGenerator.initQueryWrapper(jtxx, req.getParameterMap());
        queryWrapper.eq("khsf", "1");
        Page<Jtxx> page = new Page<Jtxx>(pageNo, pageSize);
        IPage<Jtxx> pageList = jtxxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "家庭信息-添加")
    @ApiOperation(value = "家庭信息-添加", notes = "家庭信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JtxxList list) {
        Jtxx jtxx = null;
        int count = 0;
        boolean b = false;
        String name = "";
        String hhbm = IdUtil.simpleUUID();
        jtxx = jtxxService.selectByZjhm(list.getJtxx().getZjhm());
        if (jtxx != null) {
            name = jtxx.getName();
        }
        if (!list.getJtxxList().isEmpty()) {//判断家庭成员信息是否为空
            for (Jtxx jtxx1 : list.getJtxxList()) {
                jtxx = jtxxService.selectByZjhm(jtxx1.getZjhm());
                if (jtxx != null) {
                    name = jtxx1.getName();
                    //如果户号编码为空就直接set户号编码
                    if (jtxx.getHhbm() == null) {
                        list.getJtxx().setHhbm(hhbm);
                        jtxxService.deleteByzjhm(jtxx1.getZjhm());
                    }
                }
            }
        }
        list.getJtxx().setHhbm(hhbm);
        list.getJtxx().setKhsf("1");
        if (jtxx != null) {
            if (jtxx.getHhbm() == null) {
                b = jtxxService.save(list.getJtxx());
                count = jtxxService.insertJtxxList(list.getJtxxList(), hhbm);
            }
        }
        if (jtxx == null) {
            b = jtxxService.save(list.getJtxx());
            count = jtxxService.insertJtxxList(list.getJtxxList(), hhbm);
        }
        if (count == 1 || b == true) {
            return Result.ok("添加成功！");
        } else {
            Jtxx jtxx2 = jtxxService.getName(jtxx.getZjhm());
            return Result.error(name + "成员的证件号码已被" + jtxx2.getName() + "关联");
        }
    }

    @PostMapping(value = "/insert")
    public Result<?> insert(@RequestBody Jtxx jtxx) {
        System.out.println("jtxx----" + jtxx);
        jtxxService.save(jtxx);
        return Result.ok("添加成功！");

    }

    /**
     * 编辑
     *
     * @param jtxxList
     * @return
     */
    @AutoLog(value = "家庭信息-编辑")
    @ApiOperation(value = "家庭信息-编辑", notes = "家庭信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JtxxList jtxxList) {
        Jtxx jtcyxx = null;
        String name = "";
        if (jtxxList.getJtcyxx() != null) {//新增的家庭成员信息不为空
            for (Jtxx jtxx1 : jtxxList.getJtcyxx()) {
                jtcyxx = jtxxService.selectByZjhm(jtxx1.getZjhm());//根据证件号码查询是否已存在
                if (jtcyxx != null) {
                    name = jtxx1.getName();
                    if (jtcyxx.getHhbm() == null) {
                        jtxx1.setHhbm(jtxxList.getJtxx().getHhbm());
                        jtxxService.deleteByzjhm(jtxx1.getZjhm());
                    }
                }
            }
        }
        if (jtcyxx != null) {
            if (jtcyxx.getHhbm() == null) {
                jtxxService.updateJtxx(jtxxList);
                return Result.ok("编辑成功!");
            }
        }
        if (jtcyxx == null) {
            jtxxService.updateJtxx(jtxxList);
            return Result.ok("编辑成功!");
        } else {
            Jtxx jtxx2 = jtxxService.getName(jtcyxx.getZjhm());
            return Result.error(name + "成员的证件号码已被" + jtxx2.getName() + "关联");
        }


    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "家庭信息-通过id删除")
    @ApiOperation(value = "家庭信息-通过id删除", notes = "家庭信息-通过id删除")
    @GetMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        jtxxService.deleteById(id);
        //jtxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    @GetMapping(value = "/updateHhbm")
    public Result<?> updateHhbm(@RequestParam(name = "id", required = true) String id, @RequestParam("sign") String sign) {
        jtxxService.updateHhbm(id, sign);
        return Result.ok("成功!");
    }


    @GetMapping(value = "/queryDict")
    public Result<?> queryDict(@RequestParam(name = "dictCode", required = true) String dictCode) {
        Dict dict = jtxxService.queryDict(dictCode);
        return Result.ok(dict);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "家庭信息-批量删除")
    @ApiOperation(value = "家庭信息-批量删除", notes = "家庭信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.jtxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "家庭信息-通过id查询")
    @ApiOperation(value = "家庭信息-通过id查询", notes = "家庭信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Jtxx jtxx = jtxxService.getById(id);
        return Result.ok(jtxx);
    }

    /**
     * 通过hhbm查询家庭信息 排除ID
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryJtxx")
    public Result<?> queryJtxx(@RequestParam("id") String id, @RequestParam("hhbm") String hhbm) {
        List<Jtxx> list = jtxxService.queryJtxx(hhbm, id);
        return Result.ok(list);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jtxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Jtxx jtxx) {
        return super.exportXls(request, jtxx, Jtxx.class, "家庭信息");
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
        return super.importExcel(request, response, Jtxx.class);
    }

}
