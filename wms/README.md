# Warehouse-management-system
一个Java实战项目 SpringBoot+Vue前后端分离
## 项目概述
**后端**：SpringBoot、mybatis-plus

**前端**：nodejs、vue脚手架(Vue2)、element-ui

**数据库**：mysql

一、创建后端项目

二、加入mybatis-plus支持

三、自动生成代码

四、增删改查的实现

五、分页的实现

六、后端返给前端数据的封装

七、创建Vue项目

八、Vue项目导入Idea中

九、加入Element-ui支持

十、前端
## 框架知识
### 一些注解
**@RestController**：结合了 @Controller 和 @ResponseBody 注解的功能，使得编写 RESTful 控制器更加方便。当在一个**类**上使用 @RestController 注解时，Spring 将自动将方法的返回值转换为 JSON 格式，并将其作为 HTTP 响应的内容返回给客户端。

**@GetMapping**：当在一个方法上使用 @GetMapping 注解时，该方法将会映射到指定的 URL 路径，并且当客户端发送 HTTP GET 请求到该 URL 时，Spring 将会调用这个方法来处理请求并返回响应。

下面是一个简单的示例，展示了如何使用 @RestController 来创建一个返回 JSON 数据的 **RESTful 控制器**：

```java
@RestController
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User("John", "Doe");
        return user;
    }
}
```

在这个示例中，由于类上使用了 @RestController 注解，Spring 将会自动将这些返回值转换为 JSON 格式，并将其作为响应发送给客户端。

当客户端发送 GET 请求到 "/hello" 路径时，Spring 将调用sayHello()方法，并将方法的返回值作为 HTTP 响应返回给客户端。在这里，返回的字符串 "Hello, World!" 将成为响应的内容。

### mybatis-plus
官网：https://baomidou.com/
版本兼容问题：springboot:3.1.2 mybatis-plus:3.5.3.1 mysql:8.0.33

### 前端
node:16.12.0

npm工具:8.1.0

vue脚手架:5.0.8

vue:2.6.14

elementUI:2.15.7

element官网：https://element.eleme.cn/#/zh-CN

- vue2搭配element-ui
- 使用开发者工具调试（单击右键->审查元素）
- Axios是一个流行的JavaScript库，用于在浏览器和Node.js环境中进行HTTP请求。在前端，你可以使用Axios发送HTTP请求到后端服务器，请求数据或向服务器提交数据。这可以是GET请求用于获取数据，也可以是POST、PUT、DELETE等请求用于提交数据或更新资源。Axios支持处理复杂的HTTP请求，并提供了易于使用的API。在后端，如果你使用Node.js作为后端服务器，你同样可以使用Axios来与其他服务器进行数据交互。Axios可以发起对其他API或服务器的HTTP请求，获取数据或处理其他后端服务。由于Axios支持浏览器和Node.js环境，它成为了前后端交互的通用工具，使得前端和后端开发人员能够在不同的环境中共享相似的代码和数据交互逻辑
- 后端和前端的端口分别是8090和8080，所以需要设置全局跨域

### 踩坑汇总
1.版本兼容调了好久

2.用代码生成器后，注意调一下各个类的注解，比如@controller应该改成@restcontroller

3.@PostMapping需要搭配@RequestBody，注意不是@ResponseBody

4.表格一直渲染失败，解决方法是重新安装了elementUI，安装在wms-web下，node-modules里面能找到elementUI则说明安装成功，可以在package.json中查看版本号

