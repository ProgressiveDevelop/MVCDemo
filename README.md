####MVC模式
#####1、概念
>MVC模式（Model–view–controller）是软件工程中的一种软件架构模式，把软件系统分为三个基本部分：模型（Model）、视图（View）和控制器（Controller）。
  
#####2.1 MVC
>1、View接受用户的交互请求
  
>2、View将请求转交给Controller
    
>3、Controller操作Model进行数据更新

>4、数据更新之后，Model通知View数据变化

>5、View显示更新之后的数据

>View和Controller使用Strategy模式实现，View使用Composite模式，View和Model通过Observer模式同步信息。Controller不知道任何View的细节，一个Controller能被多个View使用。MVC的一个缺点是很难对Controller进行单元测试，Controller操作数据，但是如何从View上断言这些数据的变化呢？例如，点击一个View的按钮，提交一个事件给Controller，Controller修改Model的值。这个值反映到View上是字体和颜色的变化。测试这个Case还是有点困难的。