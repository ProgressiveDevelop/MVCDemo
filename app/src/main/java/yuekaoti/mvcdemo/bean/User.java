package yuekaoti.mvcdemo.bean;

/**
 * @version 1.0
 * @auth JackHappiness
 * @date 2019/3/25
 * @summary : 用户
 */
public class User {
    /**
     * 手机
     */
    private String userPhone;
    /**
     * 密码
     */
    private String userPass;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

}
