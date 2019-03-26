####MVC模式
  
#####1、概念
>MVC模式（Model–view–controller）是软件工程中的一种软件架构模式，把软件系统分为三个基本部分：模型（Model）、视图（View）和控制器（Controller）。
		
>其中M层处理数据，业务逻辑等；V层处理界面的显示结果；C层起到桥梁的作用，来控制V层和M层通信以此来达到分离视图显示和业务逻辑层。

#####2.1 MVC流程
![MVC模式图](https://raw.githubusercontent.com/ProgressiveDevelop/MVCDemo/master/img/mvc2.png)
>1、View接受用户的交互请求
  
>2、View将请求转交给Controller
    
>3、Controller操作Model进行数据更新

>4、数据更新之后，Model通知View数据变化

>5、View显示更新之后的数据

>View和Controller使用策略模式(Strategy)实现，View使用组合模式(Composite)，View和Model通过观察者模式(Observer)同步信息。Controller不知道任何View的细节，一个Controller能被多个View使用。MVC的一个缺点是很难对Controller进行单元测试，Controller操作数据，但是如何从View上断言这些数据的变化呢？例如，点击一个View的按钮，提交一个事件给Controller，Controller修改Model的值。这个值反映到View上是字体和颜色的变化。测试这个Case还是有点困难的。
		
#####2.2 Android中的MVC
  
>视图层(View)
  
>一般采用XML文件进行界面的描述，这些XML可以理解为App的View。使用的时候可以非常方便的引入。同时便于后期界面的修改。逻辑中与界面对应的id不变化则代码不用修改，大大增强了代码的可维护性。

>控制层(Controller)
  
>Android的控制层的重任通常落在了众多的Activity的肩上。这句话也就暗含了不要在Activity中写代码，要通过Activity交割Model业务逻辑层处理，这样做的另外一个原因是Android中的Actiivity的响应时间是5s，如果耗时的操作放在这里，程序就很容易被回收掉。

>模型层(Model)
  
>我们针对业务模型，建立的数据结构和相关的类，就可以理解为App的Model，Model是与View无关，而与业务相关的。对数据库的操作、对网络等的操作都应该在Model里面处理，当然对业务计算等操作也是必须放在的该层的。
  
#####2.3 实例
![MVC模式图](https://raw.githubusercontent.com/ProgressiveDevelop/MVCDemo/master/img/mvc_demo.png)
  
>模拟用户登录：当手机界面上，用户点击登录按钮，获取用户输入的账号和密码后，提交数据到服务器，服务器处理完成后响应，显示用户登录的结果。
```
//定义接口，监听登录响应回调
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

```
```
//定义Model,处理用户登录请求
package yuekaoti.mvcdemo.model.interFace;

import yuekaoti.mvcdemo.custominterface.LoginListener;

/**
 * @version 1.0
 * @auth JackHappiness
 * @date 2019/3/26
 * @summary : User Model
 */
public interface UserInterface {
    void login(String userPhone, String userPass, LoginListener listener);
}

//定义Model实现
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

```
```
//定义Controller
package yuekaoti.mvcdemo.controller;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import yuekaoti.mvcdemo.R;
import yuekaoti.mvcdemo.custominterface.LoginListener;
import yuekaoti.mvcdemo.bean.User;
import yuekaoti.mvcdemo.model.modelImpl.UserModelImpl;

/**
 * MVC Controller
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    //View
    private View view;
    private EditText etPhone, etPass;
    private TextView tvResult;
    //Model
    private UserModelImpl userModel;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建model User对象
        userModel = new UserModelImpl();
        //初始化控件
        etPhone = view.findViewById(R.id.etPhone);
        etPass = view.findViewById(R.id.etPass);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        tvResult = view.findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录 view
            case R.id.btnLogin:
                String phone = etPhone.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                //调用登录
                userModel.login(phone, pass, new LoginListener() {
                    @Override
                    public void success(String result) {
                        updateResult(result);
                    }

                    @Override
                    public void fail(String errorMsg) {
                        updateResult(errorMsg);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 更新结果
     *
     * @param msg 结果信息
     */
    @SuppressLint("SetTextI18n")
    private void updateResult(String msg) {
        tvResult.setText(getString(R.string.text_login_result) + ":" + msg);
    }
}

```
>从代码中可以看出,Activity持有了UserModelImpl模型的对象，当用户有点击Button交互的时候，Activity作为Controller控制层读取View视图层EditText View的数据，然后向Model模型发起数据请求，也就是调用UserModelImpl对象的方法 login()方法。当Model模型处理数据结束后，通过接口LoginListener通知View视图层数据处理完毕，然后View视图层调用updateResult方法更新UI。至此，整个MVC框架流程就在Activity中完成。
		
>代码中设计了一个UserInterface模型接口，然后实现接口的UserModelImpl类。Controller控制器Activity调用UserModelImpl类中的方法发起网络请求，然后通过注册LoginListener接口来获得网络请求的结果,通知View视图层更新UI和显示提示。至此，Activity就将View视图显示和Model模型数据处理隔离开了。Activity担当Contronller完成了Model和View之间的协调作用。
  
实例分析：在Android开发中，Activity并不是一个标准的MVC模式中的Controller，它的首要职责是加载应用的布局和初始化用户 界面，并接受并处理来自用户的操作请求，进而作出响应。随着界面及其逻辑的复杂度不断提升，Activity类的职责不断增加，以致变得庞大臃肿。Activity中的控件tvResult必须关心业务和数据，才能知道自己该怎么展示(比如成功显示绿色，失败显示红色)。很难在不沟通的情况下一个负责获取数据，一个负责展示UI，完成这个功能！并且逻辑都在Activity里面，Model和Controller根本没有分开，并且数据和View严重耦合。MVC的真实存在是MC(V)!
  
#####2.3主要缺点
>主要缺点有两个：
  
>1、View对Model的依赖，会导致View也包含了业务逻辑；
  
>2、Controller会变得很复杂。

####2.4演变MVP
>对MVC的演变模式，主要解决MVC第一个缺点：将View和Model解耦。
  
###2.5演变MVVP
>对MVP的优化模式，采用双向绑定，View的变动，自动反映在ViewModel,反之亦然。
  
