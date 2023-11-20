package org.cmms.modules.khgl.etckh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.models.auth.In;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlVO;
import org.cmms.modules.khgl.etckh.entity.DjkdkjlbVO;
import org.cmms.modules.khgl.etckh.entity.Dkjl;
import org.cmms.modules.khgl.etckh.service.IDkjlService;
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
 * @Description: ETC贷记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC贷记卡垫款记录表")
@RestController
@RequestMapping("/dkjl/dkjl")
public class DkjlController extends JeecgController<Dkjl, IDkjlService> {
    @Autowired
    private IDkjlService djkdkjlbService;

    /**
     * 分页列表查询
     *
     * @param
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
//    @AutoLog(value = "ETC贷记卡垫款记录表-分页列表查询")
//    @ApiOperation(value="ETC贷记卡垫款记录表-分页列表查询", notes="ETC贷记卡垫款记录表-分页列表查询")
//    @GetMapping(value = "/list")
//    public Result<?> queryPageList(Dkjl dkjl,
//                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//                                   HttpServletRequest req) {
//        QueryWrapper<Dkjl> queryWrapper = QueryGenerator.initQueryWrapper(dkjl, req.getParameterMap());
//        IPage pageList=org.cmms.common.utils.PageUtil.toPage(djkdkjlbService,pageNo,pageSize,queryWrapper);
//        return Result.ok(pageList);
//    }

    /**
     * 垫款记录
     */
    @GetMapping(value = "/getDjkDkjlList")
    public Result<?> getDjkDkjlList(DjkdkjlbVO djkdkjlbVO,
                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    String zh,
                                    HttpServletRequest req){
        Page<Object> page = new Page<>(pageNo, pageSize);
        IPage<DjkdkjlVO> djkDkjlList = service.getDjkDkjlList(page, zh);
        return Result.ok(djkDkjlList);
    }

    /**
     * 是否催收
     */
    @GetMapping(value = "/sfcs")
    public Result<?> sfcs(int start,int end,String namecn,Integer flag){
        if (1 == flag){
            List<DjkdkjlbVO> list = djkdkjlbService.getSfcs(start, end, namecn);
            return Result.ok(list);
        }else {
            List<DjkdkjlbVO> list = djkdkjlbService.getAll(start, end, namecn);
            return Result.ok(list);
        }
    }



    /**
     * 是否催收
     */
//	 @GetMapping(value = "/sfcs")
//	 public Result<?> sfcs(DjkdkjlbVO djkdkjlbVO,
//						   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//						   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//						   Integer flag,String namecn,
//						   HttpServletRequest req) {
//		 System.out.println(flag);
//		 Page page = new Page(pageNo, pageSize);
//		 QueryWrapper<DjkdkjlbVO> queryWrapper = new QueryWrapper<DjkdkjlbVO>();
//		 if (StringUtils.isNotBlank(namecn)){
//			 queryWrapper.like("hm",namecn);
//		 }
//		 IPage<DjkdkjlbVO> pageList = null;
//		 if (1 == flag) {
//			 pageList = service.getSfcs(page, namecn);
//		 }else {
//		 	 pageList = service.getAll(page,namecn);
//		 }
//
//		 List<DjkdkjlbVO> records = pageList.getRecords();
//
//		 if (CollUtil.isNotEmpty(records)){
//			 for (int i = 0; i < records.size(); i++) {
//				 DjkdkjlbVO djkdkjlbVO1 = records.get(i);
//				 LambdaQueryWrapper<Djkdkjlb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//				 lambdaQueryWrapper.eq(Djkdkjlb::getHm,djkdkjlbVO1.getHm());
//				 lambdaQueryWrapper.gt(Djkdkjlb::getDkje,0);
//				 long count = djkdkjlbService.count(lambdaQueryWrapper);
//				 if (count > 0){
//					 records.get(i).setSfcs("1");
//				 }
//			 }
//		 }
//		 return Result.ok(pageList);
//	 }

    @GetMapping("/getDkjlList")
    public Result<?> queryPageList2(int start,int end,String namecn){
        List<DjkdkjlbVO> list = djkdkjlbService.getDkjlList(start, end, namecn);
        return Result.ok(list);
    }


    /**
     *是否有垫款
     */
//	@GetMapping("/getYdk")
//	public Boolean getYdk(String dkje){
//		QueryWrapper<DjkdkjlbVO> queryWrapper = new QueryWrapper<>();
//		queryWrapper.ge("dkje",0);
//		List<DjkdkjlbVO> list = djkdkjlbService.getYdk();
//		if (list.size()>0)
//	}



}
