/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.shiro.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.business.entity.Plan;
import com.zyd.shiro.business.service.SysPlanService;
import com.zyd.shiro.business.vo.PlanConditionVO;
import com.zyd.shiro.persistence.beans.SysPlan;
import com.zyd.shiro.persistence.mapper.SysPlanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class SysPlanServiceImpl implements SysPlanService {

    @Autowired
    private SysPlanMapper sysPlanMapper;


    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Plan> findPageBreakByCondition(PlanConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysPlan> sysPlans = sysPlanMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysPlans)) {
            return null;
        }
        System.out.println("1111111111");
        List<Plan> users = new ArrayList<>();
        for (SysPlan su : sysPlans) {
            users.add(new Plan(su));
        }
        System.out.println("222");

        System.out.println(users);

        for (Plan a : users){
            System.out.println(a.getName());
            System.out.println(a.getType());
            System.out.println(a.getLevel());
        }
        PageInfo bean = new PageInfo<SysPlan>(sysPlans);
        bean.setList(users);
        return bean;
    }


    @Override
    public Plan insert(Plan entity) {
        Assert.notNull(entity, "Plan不可为空！");
        sysPlanMapper.insert(entity.getSysPlan());
        return entity;
    }

    @Override
    public void insertList(List<Plan> entities) {

    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysPlanMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    public boolean update(Plan entity) {
        Assert.notNull(entity, "Role不可为空！");
        return sysPlanMapper.updateByPrimaryKey(entity.getSysPlan()) > 0;
    }

    @Override
    public boolean updateSelective(Plan entity) {
        Assert.notNull(entity, "Role不可为空！");
        return sysPlanMapper.updateByPrimaryKeySelective(entity.getSysPlan()) > 0;
    }

    @Override
    public Plan getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysPlan sysRole = sysPlanMapper.selectByPrimaryKey(primaryKey);
        return null == sysRole ? null : new Plan(sysRole);
    }

    @Override
    public Plan getOneByEntity(Plan entity) {
        Assert.notNull(entity, "User不可为空！");
        SysPlan sysRole = sysPlanMapper.selectOne(entity.getSysPlan());
        return null == sysRole ? null : new Plan(sysRole);
    }

    @Override
    public List<Plan> listAll() {
        List<SysPlan> sysRole = sysPlanMapper.selectAll();
        return getPlan(sysRole);
    }
    private List<Plan> getPlan(List<SysPlan> sysRole) {
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Plan> roleList = new ArrayList<>();
        for (SysPlan r : sysRole) {
            roleList.add(new Plan(r));
        }
        return roleList;
    }
    @Override
    public List<Plan> listByEntity(Plan entity) {
        return null;
    }
}
