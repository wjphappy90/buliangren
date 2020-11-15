<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>


    <h1>用来测试文件上传</h1>
    <form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="aaa"> <br>
        <input type="submit" value="上传文件">
    </form>


    <h1>测试下载文件处理</h1>
    <a href="${pageContext.request.contextPath}/file/down?fileName=小黑.txt">小黑.txt</a>
    <a href="${pageContext.request.contextPath}/file/down?fileName=init.sql">init.sql</a>
    <a href="${pageContext.request.contextPath}/file/down?fileName=web.xml">web.xml</a>
</body>
</html>