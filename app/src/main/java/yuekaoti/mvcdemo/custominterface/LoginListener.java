package yuekaoti.mvcdemo.custominterface;

/**
 * @version 1.0
 * @auth JackHappiness
 * @date 2019/3/26
 * @summary : 登录接口
 */
public interface LoginListener {
    /**
     * 登录成功
     *
     * @param result 结果信息
     */
    void success(String result);

    /**
     * 登录失败
     *
     * @param errorMsg 错误信息
     */
    void fail(String errorMsg);
}
