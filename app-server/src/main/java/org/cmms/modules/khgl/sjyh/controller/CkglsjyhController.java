package org.cmms.modules.khgl.sjyh.controller;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.sjyh.entity.Ckglsjyh;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;
import org.cmms.modules.khgl.sjyh.service.ICkglsjyhService;
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
 * @Description: 客户管理_手机银行
 * @Author: jeecg-boot
 * @Date: 2022-03-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户管理_手机银行")
@RestController
@RequestMapping("/sjyh/ckglsjyh")
public class CkglsjyhController extends JeecgController<Ckglsjyh, ICkglsjyhService> {
    @Autowired
    private ICkglsjyhService ckglsjyhService;

    @AutoLog(value = "客户管理_手机银行-分页列表查询")
    @ApiOperation(value = "客户管理_手机银行-分页列表查询", notes = "客户管理_手机银行-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   String qmyeS, String qmyeE, String namecn, Integer type,
                                   HttpServletRequest req) {
//		QueryWrapper<Ckglsjyh> queryWrapper = QueryGenerator.initQueryWrapper(ckglsjyh, req.getParameterMap());
        QueryWrapper<Ckglsjyh> queryWrapper = new QueryWrapper<>();
        //替换金额
        if (org.apache.commons.lang3.StringUtils.isNotBlank(qmyeS) && StringUtils.isNotBlank(qmyeE)) {
            if (new BigDecimal(qmyeE).compareTo(new BigDecimal(qmyeS)) < 0) {
                String temp = qmyeS;
                qmyeS = qmyeE;
                qmyeE = temp;
            }
        }
        if (StringUtils.isNotBlank(namecn))
            queryWrapper.like("namecn", namecn);
        if (StringUtils.isNotBlank(qmyeS))
            queryWrapper.ge("tranno_yy", qmyeS);
        if (StringUtils.isNotBlank(qmyeE))
            queryWrapper.le("tranno_yy", qmyeE);
        if (1 == type) {
            queryWrapper.eq("openteller", getRealname());
        }
        Page<Ckglsjyh> page = new Page<Ckglsjyh>(pageNo, pageSize);
        IPage<Ckglsjyh> pageList = ckglsjyhService.page(page, queryWrapper);
        for (int i = 0; i < pageList.getRecords().size(); i++) {
            String replace = pageList.getRecords().get(i).getOrgName().substring(1);
            int index = replace.indexOf("]");
            String org = replace.substring(0, index);
            pageList.getRecords().get(i).setOrgNameShow(org);
        }
        if (CollUtil.isNotEmpty(pageList.getRecords())) {
            for (int i = 0; i < pageList.getRecords().size(); i++) {
                String zjhm = pageList.getRecords().get(i).getNo();
                //校验证件号码
                if (IdcardUtil.isValidCard(zjhm)) {
                    //根据证件号码获取性别
                    int genderByIdCard = IdcardUtil.getGenderByIdCard(zjhm);
                    pageList.getRecords().get(i).setXb(genderByIdCard);
                }
            }
        }
        System.out.println(pageList.getRecords()+"==============");
        return Result.ok(pageList);
    }

    /**
     * 我的客户
     */
    @GetMapping(value = "/mycustomer")
    public Result<?> mycustomer() {
        QueryWrapper<Ckglsjyh> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openteller", getRealname());
        List<Ckglsjyh> list = ckglsjyhService.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping(value = "/getQb")
    public Result<?> queryPageList2(int jx, int px, String ssmc
            , @RequestParam(name = "start", defaultValue = "1") Integer start,
                                    @RequestParam(name = "end", defaultValue = "10") Integer end) {

        Page<KhzlbVo> page = new Page<KhzlbVo>(start, end);
        IPage<KhzlbVo> pageList = ckglsjyhService.getPageList(page, jx, px, ssmc);
//        List<KhzlbVo> result = ckglsjyhService.getList(start, end, jx, px, ssmc);
//        if (CollUtil.isNotEmpty(result)) {
//            for (int i = 0; i < result.size(); i++) {
//                String zjhm = result.get(i).getCustrNbr();
//                if (IdcardUtil.isValidCard(zjhm)) {
//                    int genderByIdCard = IdcardUtil.getGenderByIdCard(zjhm);
//                    result.get(i).setXb(genderByIdCard);
//                }
//            }
//        }
        return Result.ok(pageList);
    }


    /**
     * 添加
     *
     * @param ckglsjyh
     * @return
     */
    @AutoLog(value = "客户管理_手机银行-添加")
    @ApiOperation(value = "客户管理_手机银行-添加", notes = "客户管理_手机银行-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Ckglsjyh ckglsjyh) {
        ckglsjyhService.save(ckglsjyh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ckglsjyh
     * @return
     */
    @AutoLog(value = "客户管理_手机银行-编辑")
    @ApiOperation(value = "客户管理_手机银行-编辑", notes = "客户管理_手机银行-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Ckglsjyh ckglsjyh) {
        ckglsjyhService.updateById(ckglsjyh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户管理_手机银行-通过id删除")
    @ApiOperation(value = "客户管理_手机银行-通过id删除", notes = "客户管理_手机银行-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ckglsjyhService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户管理_手机银行-批量删除")
    @ApiOperation(value = "客户管理_手机银行-批量删除", notes = "客户管理_手机银行-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ckglsjyhService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户管理_手机银行-通过id查询")
    @ApiOperation(value = "客户管理_手机银行-通过id查询", notes = "客户管理_手机银行-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Ckglsjyh ckglsjyh = ckglsjyhService.getById(id);
        return Result.ok(ckglsjyh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ckglsjyh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ckglsjyh ckglsjyh) {
        return super.exportXls(request, ckglsjyh, Ckglsjyh.class, "客户管理_手机银行");
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
        return super.importExcel(request, response, Ckglsjyh.class);
    }

}
