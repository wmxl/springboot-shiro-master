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
import com.zyd.shiro.business.entity.Pref;
import com.zyd.shiro.business.service.SysPrefService;
import com.zyd.shiro.business.vo.PrefConditionVO;
import com.zyd.shiro.persistence.beans.SysPref;
import com.zyd.shiro.persistence.mapper.SysPrefMapper;
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
public class SysPrefServiceImpl implements SysPrefService {

    @Autowired
    private SysPrefMapper sysPlanMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Pref> findPageBreakByCondition(PrefConditionVO vo) {
        System.out.println("用户偏好");
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysPref> sysPlans = sysPlanMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysPlans)) {
            return null;
        }

        List<Pref> users = new ArrayList<>();
        for (SysPref su : sysPlans) {
            users.add(new Pref(su));
        }
        System.out.println("222");
        PageInfo bean = new PageInfo<SysPref>(sysPlans);
        bean.setList(users);
        return bean;
    }

    @Override
    public Pref insert(Pref entity) {
        Assert.notNull(entity, "Pref不可为空！");
        sysPlanMapper.insert(entity.getSysPref());
        return entity;
    }

//    @Override
//    public void insertList(List<Pref> entities) {
//
//    }


    @Override
    public void insertList(List<Pref> entities) {

    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysPlanMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    public boolean update(Pref entity) {
        return false;
    }

//    @Override
//    public boolean update(Pref entity) {
////        Assert.notNull(entity, "Role不可为空！");
////        return sysPlanMapper.updateByPrimaryKey(entity.getSysPlan()) > 0;
//        return false;
//    }

    @Override
    public boolean updateSelective(Pref entity) {
//        Assert.notNull(entity, "Role不可为空！");
//        return sysPlanMapper.updateByPrimaryKeySelective(entity.getSysPlan()) > 0;
        return false;
    }

    @Override
    public Pref getByPrimaryKey(Long primaryKey) {
//        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
//        SysPlan sysRole = sysPlanMapper.selectByPrimaryKey(primaryKey);
//        return null == sysRole ? null : new Plan(sysRole);
        return null;
    }

    @Override
    public Pref getOneByEntity(Pref entity) {
        Assert.notNull(entity, "Pref不可为空！");
        SysPref sysRole = sysPlanMapper.selectOne(entity.getSysPref());
        return null == sysRole ? null : new Pref(sysRole);
    }

    @Override
    public List<Pref> listAll() {
        List<SysPref> sysRole = sysPlanMapper.selectAll();
        return getPref(sysRole);
    }

    @Override
    public List<Pref> listByEntity(Pref entity) {
        return null;
    }

    private List<Pref> getPref(List<SysPref> sysRole) {
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Pref> roleList = new ArrayList<>();
        for (SysPref r : sysRole) {
            roleList.add(new Pref(r));
        }
        return roleList;
    }
}
