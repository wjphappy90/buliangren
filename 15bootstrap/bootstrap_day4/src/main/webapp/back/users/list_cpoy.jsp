<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script>
    $(function(){
        //console.log("${param.name}");
        //通过组件方法调用形式展示某个具体标签页
        $("#${param.name}").tab('show');

    });
</script>
 <!--中心内容-->
<div class="col-sm-10">

            <!--页头-->
            <div class="page-header" style="margin-top: -20px">
                <h1>用户列表</h1>
            </div>

            <!--标签页组件-->
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" id="userList">用户列表</a></li>
                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" id="userAdd">用户添加</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">

                        <!--inline from表单-->
                        <div class="col-sm-8 col-sm-offset-2" style="margin-top: 10px;">
                            <form action="" class="form-inline">
                                <div class="form-group">
                                    <label >姓名</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label >手机</label>
                                    <input type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label >激活状态</label>
                                    <select name="" class="form-control" id="">
                                        <option>---请选择---</option>
                                    </select>
                                </div>
                                <button class="btn btn-primary">搜索</button>
                            </form>
                        </div>


                    <!--table-->
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>张三</td>
                            <td>23</td>
                            <td>男</td>
                            <td><a href="" class="btn btn-success">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>张三</td>
                            <td>23</td>
                            <td>男</td>
                            <td><a href="" class="btn btn-success">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>

                        <tr>
                            <td>1</td>
                            <td>张三</td>
                            <td>23</td>
                            <td>男</td>
                            <td><a href="" class="btn btn-success">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>

                        <tr>
                            <td>1</td>
                            <td>张三</td>
                            <td>23</td>
                            <td>男</td>
                            <td><a href="" class="btn btn-success">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        </tbody>
                    </table>
                    <!--简单分页-->
                    <nav aria-label="...">
                        <ul class="pager">
                            <li><a href="#">Previous</a></li>
                            <li><a href="#">Next</a></li>
                        </ul>
                    </nav>
                    <!--警告信息-->
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Warning!</strong> Better check yourself, you're not looking too good.
                    </div>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Warning!</strong> Better check yourself, you're not looking too good.
                    </div>

                </div>
                <div role="tabpanel" class="tab-pane" id="profile">
                    <form>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputFile">File input</label>
                            <input type="file" id="exampleInputFile">
                            <p class="help-block">Example block-level help text here.</p>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Check me out
                            </label>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>




        </div>
