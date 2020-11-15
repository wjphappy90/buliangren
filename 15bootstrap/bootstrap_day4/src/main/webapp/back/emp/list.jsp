<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {


        //初始化表格 并查询所有
        $("#empList").jqGrid({
            styleUI:"Bootstrap",
            autowidth:true,
           // height:400,
            url:"${pageContext.request.contextPath}/emp/findAll",
            datatype:"json",
            colNames:["编号","姓名","年龄","生日","部门","操作"],
            colModel:[
                {name:"id",search:false},
                {name:"name",editable:true},
                {name:"age",editable:true},
                {name:"bir",editable:true},
                {name:"dept.id",editable:true,edittype:"select",editoptions:{
                    multiple:false,
                    dataUrl:"${pageContext.request.contextPath}/dept/findAll",//select
                },
                    formatter:function(value,options,row){
                        if(row.dept)return row.dept.name;
                    }
                },
                {name:"options",
                    formatter:function(value,options,row){
                        return ``+
                            `<button class="btn btn-primary" onclick="editRow('`+row.id+`');">修改</button>&nbsp;&nbsp;`+
                            `<button class="btn btn-danger" onclick="delRow('`+row.id+`');">删除</button>`;
                    },search:false
                },
            ],
            pager:"#pager",
            viewrecords:true,
            rowNum:2,
            rowList:[2,5,10],
            editurl:"${pageContext.request.contextPath}/emp/edit",
            toolbar:[true,'top'],
            gridComplete:function () {
                $("#t_empList").empty().append("<button class='btn btn-warning' onclick='saveRow();'>添加</button>")
            }

        }).navGrid(
            '#pager',//参数1: 分页工具栏id
            {add:true},   //参数2:开启工具栏编辑按钮
            {closeAfterEdit:true,reloadAfterSubmit:true},//编辑面板的配置
            {closeAfterAdd:true,reloadAfterSubmit:true},//添加面板的配置
            {},//删除的配置
            {
                sopt:['eq','ne','cn']
            },//搜索的配置
        );


    });

    //添加一行
    function saveRow(){
        $("#empList").jqGrid('editGridRow', "new", {
            height : 300,
            reloadAfterSubmit : true,
            closeAfterAdd:true
        });
    }

    //删除一行
    function delRow(id){
        if(window.confirm("您确定要删除吗?")){
            //发送ajax请求删除
            $.post("${pageContext.request.contextPath}/emp/edit",{id:id,oper:"del"},function(){
                //刷新表格
                $("#empList").trigger('reloadGrid');//刷新表格
            });
        }

    }
    //修改一行
    function editRow(id){
        console.log(id);
        $("#empList").jqGrid('editGridRow', id, {
            height : 300,
            reloadAfterSubmit : true,
            closeAfterEdit:true,
        });
    }
</script>

<div class="col-sm-10">

    <div class="page-header">
        <h1>员工列表</h1>
    </div>

    <%--员工列表--%>
    <table id="empList"></table>

    <%--分页工具栏--%>
    <div id="pager"></div>

</div>
