package yuekaoti.mvcdemo.model.modelImpl;

import android.text.TextUtils;

import yuekaoti.mvcdemo.custominterface.LoginListener;
import yuekaoti.mvcdemo.model.interFace.UserInterface;

/**
 * @version 1.0
 * @auth JackHappiness
 * @date 2019/3/26
 * @summary : User Model 实现类
 */
public class UserModelImpl implements UserInterface {
    /**
     * 用户登录
     *
     * @param userPhone 手机
     * @param userPass  密码
     * @param listener  监听器
     */
    @Override
    public void login(String userPhone, String userPass, LoginListener listener) {
        if (TextUtils.isEmpty(userPhone)) {
            listener.fail("手机号不能为空");
        } else {
            if (TextUtils.isEmpty(userPass)) {
                listener.fail("密码不能为空不能为空");
            } else {
                //网络登录
                int code = postLogin(userPhone, userPass);
                if (code == 200) {
                    listener.success("登录成功");
                } else {
                    listener.fail("登录失败,请稍候重试");
                }
            }
        }
    }

    /**
     * 网络登录
     *
     * @param userPhone 手机
     * @param userPass  密码
     * @return 结果码
     */
    private int postLogin(String userPhone, String userPass) {
        return 0;
    }
}
