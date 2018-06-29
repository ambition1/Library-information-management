package com.example.yang2.service.UserOperation.TimeOperation.impl;

import com.example.yang2.service.UserOperation.TimeOperation.TimeOperationService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.UserOperation.TimeOperation.impl
 * @Description: 实现用户借书时间操作接口
 * @Date: Created in 16:25 2018/6/6
 */

@Service
public class TimeOperationImpl implements TimeOperationService {

    /**
     * SimpleDateFormat在高并发的情况下回产生线程安全问题
     * 最简单解决方式 在使用的时候使用同步锁：会相对占用资源
     */
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");

    /**
     * 获取用户借书当前时间
     *
     * @return
     */
    @Override
    public String getNowTime() {
        Calendar calendar = Calendar.getInstance();
        synchronized (simpleDateFormat) {
            return simpleDateFormat.format(calendar.getTime());
        }
    }

    /**
     * 获取用户最初的还书时间
     *
     * @return
     */
    @Override
    public String getBackTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        synchronized (simpleDateFormat) {
            return simpleDateFormat.format(calendar.getTime());
        }
    }

    @Override
    public String setBackTime(String date) {
        synchronized (simpleDateFormat) {
            try {
                Date time = simpleDateFormat.parse(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(time);
                calendar.add(Calendar.DATE, 30);
                return simpleDateFormat.format(calendar.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
