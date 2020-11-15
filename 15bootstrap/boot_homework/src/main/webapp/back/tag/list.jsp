<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<!--中心内容-->
<div class="col-sm-10">
    <!--页头-->
    <div class="page-header" style="margin-top: -20px">
        <h1>标签列表</h1>
    </div>

    <%--form表单--%>
    <form action="" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2">标签名称:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" name="name">
            </div>
            <div class="col-sm-2">
                <button class="btn btn-primary">添加</button>
            </div>
        </div>
    </form>

    <%--标签列表--%>
    <div class="col-sm-8 col-sm-offset-2">
        <p>
            <div class="alert alert-info pull-left" style="width: 120px; margin: 0px 10px 10px 0px;">
                <button type="button" class="close"> <span >&times;</span></button>
                <p>干净</p>
            </div>

            <div class="alert alert-info pull-left" style="width: 120px;margin: 0px 10px 10px 0px;">
                <button type="button" class="close"> <span >&times;</span></button>
                <p>威武帅气</p>
            </div>

        </p>
    </div>



</div>


