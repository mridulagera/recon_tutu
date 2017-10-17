<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reconcile Transactions</title>
</head>

<link rel="stylesheet" type="text/css" href="/css/tutuka.css"/>
<body>

<br>
<div class="datagrid">
<h3> Choose Transaction files to Re-concile</h3>
<br>
   <form action="/upload" method="post" enctype="multipart/form-data">
 
   <div style="margin-left: 5px" ><lable>Client Transactions&nbsp;</lable> <input type="file" name="clientFile" required="required"/><br/><br/></div>
   <div style="margin-left: 5px"> <lable>Tutuka Transactions&nbsp;</lable> <input type="file" name="tutukaFile" required="required"/><br/><br/></div>
   <div style="margin-left: 25px;margin-bottom: 5px"> <input type="submit" value="upload"/></div>

            </form> 
</div>
</body>
</html>