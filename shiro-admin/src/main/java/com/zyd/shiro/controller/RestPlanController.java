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
package com.zyd.shiro.controller;

import com.github.pagehelper.PageInfo;
import com.zyd.shiro.business.entity.Plan;
import com.zyd.shiro.business.enums.ResponseStatus;
import com.zyd.shiro.business.service.SysPlanService;
import com.zyd.shiro.business.vo.PlanConditionVO;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预案管理
 *
 * @author wmxl
 * @version 1.0
 * @website https://www.zhyd.me
 * @since 1.0
 */
@RestController
@RequestMapping("/plan")
public class RestPlanController {
    @Autowired
    private SysPlanService planService;

    @RequiresPermissions("druid")
    @PostMapping("/list")
    public PageResult list(PlanConditionVO vo) {
        PageInfo<Plan> pageInfo = planService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

//    /**
//     * 保存用户角色
//     *
//     * @param userId
//     * @param roleIds
//     *         用户角色
//     *         此处获取的参数的角色id是以 “,” 分隔的字符串
//     * @return
//     */
//    @RequiresPermissions("user:allotRole")
//    @PostMapping("/saveUserRoles")
//    public ResponseVO saveUserRoles(Long userId, String roleIds) {
//        if (StringUtils.isEmpty(userId)) {
//            return ResultUtil.error("error");
//        }
//        userRoleService.addUserRole(userId, roleIds);
//        return ResultUtil.success("成功");
//    }

    @RequiresPermissions("druid")
    @PostMapping(value = "/add")
    public ResponseVO add(Plan user) {
        try {
            planService.insert(user);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }
//    @RequiresPermissions("druid")
    @RequiresPermissions(value = {"druid"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            planService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个预案");
    }

    @RequiresPermissions("druid")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.planService.getByPrimaryKey(id));
    }

    @RequiresPermissions("druid")
    @PostMapping("/edit")
    public ResponseVO edit(Plan user) {
        try {
            planService.updateSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("预案修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
