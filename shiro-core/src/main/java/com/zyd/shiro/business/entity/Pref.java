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
package com.zyd.shiro.business.entity;

import com.zyd.shiro.framework.object.AbstractBO;
import com.zyd.shiro.persistence.beans.SysPref;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Pref extends AbstractBO {
    private SysPref sysPlan;

    public Pref() {
        this.sysPlan = new SysPref();
    }

    public Pref(SysPref sysPlan) {
        this.sysPlan = sysPlan;
    }
    public SysPref getSysPref() {
        return this.sysPlan;
    }

    public Long getId() {
        return this.sysPlan.getId();
    }
    public void setId(Long id) {
        this.sysPlan.setId(id);
    }

    public String getCar() {
        return this.sysPlan.getCar();
    }
    public void setCar(String nickname) {
        this.sysPlan.setCar(nickname);
    }

    public String getTab1() {
        return this.sysPlan.getTab1();
    }
    public String getTab2() {
        return this.sysPlan.getTab2();
    }

    public void setTab1(String mobile) {
        this.sysPlan.setTab1(mobile);
    }
    public void setTab2(String mobile) {
        this.sysPlan.setTab2(mobile);
    }

}
