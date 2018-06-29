package com.example.yang2.service.UserOperation;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2.service.UserOperation
 * @Description: 用户操作接口
 * @Date: Created in 16:08 2018/6/6
 */

public interface UserOperationService {

    /**
     * 用户借书接口
     *
     * @param booId
     * @param userName
     * @param bookName
     * @return Boolean值
     */
    void userBorrow(Integer booId, String userName, String bookName);

    /**
     * 用户续期操作
     * @param id
     * @param backTime
     */
    void userRenew(Integer id, String backTime);

    /**
     * 判断用户是否可以续借
     * @param id
     * @return
     */
    boolean userIsRenew(Integer id);

}
