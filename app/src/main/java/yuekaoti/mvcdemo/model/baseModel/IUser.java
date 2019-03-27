package yuekaoti.mvcdemo.model.baseModel;

import yuekaoti.mvcdemo.custominterface.LoginListener;

/**
 * @version 1.0
 * @auth JackHappiness
 * @date 2019/3/26
 * @summary : User Model
 */
public interface IUser {
    void login(String userPhone, String userPass, LoginListener listener);
}
