<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>系统主页</title>
    <!--引入bootstrap css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/statics/bootstrap/css/bootstrap.min.css">
    <!--引入jqgrid的bootstrap css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/statics/jqgrid/css/ui.jqgrid-bootstrap.css">
    <!--引入jquery核心js-->
    <script src="${pageContext.request.contextPath}/back/statics/js/jquery-3.4.1.min.js"></script>
    <!--引入jqgrid核心js-->
    <script src="${pageContext.request.contextPath}/back/statics/jqgrid/js/jquery.jqGrid.min.js"></script>
    <!--引入jqgrid国际化js-->
    <script src="${pageContext.request.contextPath}/back/statics/jqgrid/i18n/grid.locale-cn.js"></script>
    <!--引入bootstrap组件js-->
    <script src="${pageContext.request.contextPath}/back/statics/bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function(){
           /* $("#btn").click(function(){
                //用来根据指定url,将页面源码包含到当前指定选择器中  被包含的页面中不能有其他额外标签或样式
                $("#content").load("${pageContext.request.contextPath}/back/users/list.jsp");
            });*/

           //切换样式
            $(".list-group").on("click",".list-group-item",function(){
                //去掉所有
               $(".list-group-item").removeClass().addClass("list-group-item");
                //点击激活
               $(this).addClass("active");
            });

        })
    </script>
</head>
<body>
<!--导航栏-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/back/home.jsp">用户管理系统 <small>V1.0</small></a>
        </div>


        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li ><a href="#">欢迎:小黑</a></li>
                <li ><a href="#">退出 <span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>

<!--布局系统 中心内容-->
<div class="container-fluid">
    <div class="row">
        <!--菜单-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                               部门管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/dept/findAllDepts');" id="btn" class="list-group-item active">
                                    部门列表
                                </a>
                               <%-- <a href="javascript:$('#content').load('${pageContext.request.contextPath}/back/dept/list.jsp?name=deptAdd');" class="list-group-item">
                                    部门添加
                                </a>--%>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                               标签管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/back/tag/list.jsp');" class="list-group-item active">
                                    标签列表
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                                员工管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="javascript:$('#content').load('${pageContext.request.contextPath}/back/emp/list.jsp');" class="list-group-item active">
                                    员工列表
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--中心内容-->
        <div id="content">
            <div class="col-sm-10">

                <!--巨幕-->
                <div class="jumbotron">
                    <h1>Hello, world!</h1>
                    <p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
                    <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
                </div>

                <!--带有按钮警告框-->
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3>Warning!</h3>
                    <p>Better check yourself, you're not looking too good.</p>
                    <p>
                        <button class="btn btn-danger">立即处理</button>
                        <button class="btn btn-default">稍后修复</button>
                    </p>
                </div>

                <!--面板 带有标题面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">系统状态:</div>
                    <div class="panel-body">
                        <!--进度条-->
                        <label for="">系统使用率:</label>
                        <div class="progress">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                40%<span class="sr-only">40% Complete (success)</span>
                            </div>
                        </div>
                        <label for="">CPU使用率:</label>
                        <div class="progress">
                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                20%<span class="sr-only">20% Complete</span>
                            </div>
                        </div>
                        <label for="">磁盘使用率:</label>
                        <div class="progress">
                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                60%<span class="sr-only">60% Complete (warning)</span>
                            </div>
                        </div>
                        <label for="">数据库使用率:</label>
                        <div class="progress">
                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                80%<span class="sr-only">80% Complete (danger)</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</div>
</body>
</html>