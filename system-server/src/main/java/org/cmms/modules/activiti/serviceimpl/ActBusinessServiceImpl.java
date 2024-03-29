package org.cmms.modules.activiti.serviceimpl;


import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.activiti.dao.ActBusinessDao;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 业务申请接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class ActBusinessServiceImpl implements ActBusinessService {

    @Autowired
    private ActBusinessDao actBusinessDao;

    @Autowired
    private ISysUserService sysUserService;


    @Override
    public ActBusinessDao getRepository() {
        return actBusinessDao;
    }

    @Override
    public Page<ActBusiness> findByCondition(ActBusiness actBusiness, SearchVo searchVo, Pageable pageable) {

        return actBusinessDao.findAll(new Specification<ActBusiness>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ActBusiness> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("title");
                Path<Integer> statusField = root.get("status");
                Path<Integer> resultField = root.get("result");
                Path<Date> createTimeField = root.get("createTime");
                Path<String> userIdField = root.get("userId");
                Path<String> procDefIdField = root.get("procDefId");

                List<Predicate> list = new ArrayList<Predicate>();

                // 模糊搜素
                if(StrUtil.isNotBlank(actBusiness.getTitle())){
                    list.add(cb.like(titleField,'%'+actBusiness.getTitle()+'%'));
                }

                // 状态
                if(actBusiness.getStatus()!=null){
                    list.add(cb.equal(statusField, actBusiness.getStatus()));
                }
                // 结果
                if(actBusiness.getResult()!=null){
                    list.add(cb.equal(resultField, actBusiness.getResult()));
                }

                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&& StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                SysUser user =sysUserService.getUserByName(sysUser.getUsername());
                String userId = user.getId();
                // 用户
               list.add(cb.equal(userIdField, userId));

/*
               list.add(cb.notEqual(procDefIdField, ""));
*/

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public List<ActBusiness> findByProcDefId(String procDefId) {

        return actBusinessDao.findByProcDefId(procDefId);
    }

    @Override
    public List<ActBusiness> findByTitleLike(String title) {

        return actBusinessDao.findByTitleLike(title);
    }

    /**
     * 通过tableId获取
     * @param tableId
     * @return
     */
    @Override
    public List<ActBusiness> findByTableId(String tableId) {
        return actBusinessDao.findByTableId(tableId);
    }

    @Override
    public void deleteByTableId(String tableId) {
        actBusinessDao.deleteByTableId(tableId);
    }
}