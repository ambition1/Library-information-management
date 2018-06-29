package com.example.yang2.service.UserOperation.TimeOperation;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.UserOperation.TimeOperation
 * @Description: 用户借书时间操作接口
 * @Date: Created in 16:20 2018/6/6
 */

public interface TimeOperationService {

    /**
     * 获取用户借书当前时间
     *
     * @return 当前时间字符串 yyyy年MM月dd日
     */
    public String getNowTime();

    /**
     * 获取用户当前借书日期后三十天的日期
     *
     * @return
     */
    public String getBackTime();

    /**
     * 用户点击续借后再原归还时间上增加三十天
     *
     * @return
     * @date
     */
    public String setBackTime(String date);

}
