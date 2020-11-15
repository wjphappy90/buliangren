<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

 <!--中心内容-->
<div class="col-sm-10">

            <!--页头-->
            <div class="page-header" style="margin-top: -20px">
                <h1>用户列表</h1>
            </div>

            <!--标签页组件-->
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab" id="deptList">部门列表</a></li>
                <li role="presentation"><a  data-toggle="modal" data-target="#myModal" >部门添加</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">

                    <!--table-->
                    <table class="table table-bordered" style="margin-top: 20px;">
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
                        <c:forEach items="${requestScope.depts}" var="dept">
                            <tr>
                                <td>${dept.id}</td>
                                <td>${dept.name}</td>
                                <td><a href="" class="btn btn-success">修改</a>&nbsp;&nbsp;<a href="" class="btn btn-danger">删除</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>


                </div>
            </div>




</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">保存部门信息</h4>
            </div>
            <div class="modal-body">
                <form id="inputForm" action="${pageContext.request.contextPath}/dept/save" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="name" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="btn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#btn").click(function () {

            $("#inputForm").submit();
        });
    })
</script>
