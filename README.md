_自动在日志中打印输入输出参数，快捷方便，无需任何配置项，依赖少，开箱即用。_

    无中文乱码，可设置拦截路径和排除路径，可配置是否打印输入和输出。
    可以用于测试和开发环境排除bug查找问题。也可以直接用于生产环境记录请求。

使用方法：

1.clone代码到本地
    
    git clone git@github.com:sjlian/printparam.git

2.安装到本地maven库，cd 到项目目录下
    
    cd printparam
    maven install
    
3.引入到springboot项目依赖。

         <dependency>
               <groupId>com.github.printparam</groupId>
               <artifactId>printparam-spring-boot-starter</artifactId>
               <version>0.0.1-SNAPSHOT</version>
          </dependency> 

4.可选，配置参数
    
    print-param.enableInputParam=true 是否在日志中打印输入信息，默认true
    print-param.enableOutputResult=false  是否在日志中打印输出信息，默认false
    print-param.filterIncludePattern=/*  拦截哪些url进行日志打印 默认/*表示全部拦截，正则表达式
    print-param.filterExcludePattern=(/webjars/.*|/css/.*|/images/.*|/fonts/.*|/js/.*)  正则表达式描述不拦截哪些url打印日志，默认如左

5.日志输入格式如下：

    对于request：
        uri表示请求接口
        header表示请求头
        form是表单数据
        body是json数据
    对于response：
        return data表示返回数据
        
    request uri:/api/user1/add
    request header:{Origin=http://localhost:8081, Accept=*/*, Connection=keep-alive, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36, Referer=http://localhost:8081/api/swagger-ui.html, Host=localhost:8081, Accept-Encoding=gzip, deflate, br, Accept-Language=zh-CN,zh;q=0.9, Content-Length=34, Content-Type=application/json}
    request form:{}
    request body:{  "age": 26,  "name": "hello"}
    response return data:{"code":0,"message":"success","url":null,"data":{"id":null,"name":"hello","age":26,"version":null}}
    request uri:/api/user1/add
    request header:{Origin=http://localhost:8081, Accept=*/*, Connection=keep-alive, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36, Referer=http://localhost:8081/api/swagger-ui.html, Host=localhost:8081, Accept-Encoding=gzip, deflate, br, Accept-Language=zh-CN,zh;q=0.9, Content-Length=35, Content-Type=application/json}
    request form:{}
    request body:{  "age": 16,  "name": "张三"}
    response return data:{"code":0,"message":"success","url":null,"data":{"id":null,"name":"张三","age":16,"version":null}}
    request uri:/api/user1/get/1
    request header:{Accept=*/*, Connection=keep-alive, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36, Referer=http://localhost:8081/api/swagger-ui.html, Host=localhost:8081, Accept-Encoding=gzip, deflate, br, Accept-Language=zh-CN,zh;q=0.9}
    request form:{}
    request body:
    response return data:{"code":0,"message":"success","url":null,"data":null}
    request uri:/api/user1/list
    request header:{Accept=*/*, Connection=keep-alive, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36, Referer=http://localhost:8081/api/swagger-ui.html, Host=localhost:8081, Accept-Encoding=gzip, deflate, br, Accept-Language=zh-CN,zh;q=0.9}
    request form:{}
    request body:
    response return data:{"code":0,"message":"success","url":null,"data":[]}
    request uri:/api/demo/getHello
    request header:{Accept=*/*, Connection=keep-alive, User-Agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36, Referer=http://localhost:8081/api/swagger-ui.html, Host=localhost:8081, Accept-Encoding=gzip, deflate, br, Accept-Language=zh-CN,zh;q=0.9}
    request form:{}
    request body:
    response return data:hello