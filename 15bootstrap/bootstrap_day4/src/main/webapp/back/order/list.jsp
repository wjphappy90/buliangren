<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

 <!--中心内容-->
<div class="col-sm-10">

            <!--页头-->
            <div class="page-header" style="margin-top: -20px">
                <h1>订单列表</h1>
            </div>

            <!--标签页组件-->
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" >订单列表</a></li>
                <li role="presentation"><a href="javascript:void(0);" data-toggle="modal" data-target="#saveOrderDialog" >订单添加</a></li>
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
                            <th>订单名称</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>超短裙</td>
                            <td>已提交</td>
                            <td>已支付</td>
                            <td><a href="" class="btn btn-success" data-toggle="modal" data-target="#editOrderDialog">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>超短裙</td>
                            <td>已提交</td>
                            <td>已支付</td>
                            <td><a href="" class="btn btn-success" data-toggle="modal" data-target="#editOrderDialog">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>超短裙</td>
                            <td>已提交</td>
                            <td>已支付</td>
                            <td><a href="" class="btn btn-success" data-toggle="modal" data-target="#editOrderDialog">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>超短裙</td>
                            <td>已提交</td>
                            <td>已支付</td>
                            <td><a href="" class="btn btn-success" data-toggle="modal" data-target="#editOrderDialog">修改</a><a href="" class="btn btn-danger">删除</a></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>超短裙</td>
                            <td>已提交</td>
                            <td>已支付</td>
                            <td><a href="" class="btn btn-success" data-toggle="modal" data-target="#editOrderDialog">修改</a><a href="" class="btn btn-danger">删除</a></td>
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
                <%--订单添加选项卡--%>
            </div>
        </div>



<%--保存订单模态框--%>

<div class="modal fade" id="saveOrderDialog" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">保存订单信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Remember me
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editOrderDialog" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改订单信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control"  placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control"  placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Remember me
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

