<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/css/tutuka.css">

<%@ include file="/fileinput.jsp" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>
<br> 

<h3>Transactions Upload Status</h3><br>
<div class="bigwrapper datagrid">
    <div style="width:50%;float:left;display:table-cell; border-right:solid black 1;"  align="left" >
    <b>Tutuka Transaction Upload Summary</b> <br>
    <label>Total Transactions </label> <b>${uploadResult.totalTutukaTxn} </b><br>
    <label>Total Valid Transactions</label> <b>${uploadResult.totalValidTTxn} </b><br>
    <label>Total Ignored(Duplicate) Transactions</label> <b>${uploadResult.totalIgnoredTTxn} </b><br>
    <label>Total Invalid Transactions </label> <b>${uploadResult.totalInValidTTxn} </b><br>
	</div>
    <div style="width:50%;float:right;display:table-cell;">
     <b>Client Transaction Upload Summary</b> <br>
    <label>Total Transactions</label> <b>${uploadResult.totalClientTxn}</b> <br>
    <label>Total Valid Transactions</label><b>${uploadResult.totalValidCTxn}</b> <br>
    <label>Total Ignored(Duplicate) Transactions</label> <b>${uploadResult.totalIgnoredCTxn}</b> <br>
    <label>Total Invalid Transactions </label> <b>${uploadResult.totalInValidCTxn} </b><br>
    </div>
</div>

<br>
<c:if test="${not empty uploadResult.duplicateTutuka}">

<span class="coloredback">Ignored Tutuka's Duplicate Transaction</span>
<div class="datagrid">

<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Narrative</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${uploadResult.duplicateTutuka}" var="trans">
    <tr><td>${trans.transactionID}</td>
    <td>${trans.transactionDescription}</td>
    <td>${trans.walletRefernce}</td>
    <td>${trans.transactionDate}</td>
    <td>${trans.transactionAmount}</td>
    <td>${trans.transactionNarrative}</td>
    <td>${trans.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>
<br>
<br>
<c:if test="${not empty uploadResult.duplicateClient}">

<span class="coloredback">Ignored Client's Duplicate Transaction</span>
<div class="datagrid">

<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Narrative</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${uploadResult.duplicateClient}" var="trans">
    <tr><td>${trans.transactionID}</td>
    <td>${trans.transactionDescription}</td>
    <td>${trans.walletRefernce}</td>
    <td>${trans.transactionDate}</td>
    <td>${trans.transactionAmount}</td>
    <td>${trans.transactionNarrative}</td>
    <td>${trans.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>

<br>
<br>
<c:if test="${not empty uploadResult.invalidTutuka}">

<span class="coloredback">Ignored Tutuka's Invalid Transaction</span>
<div class="datagrid">
<c:forEach items="${uploadResult.invalidTutuka}" var="trans">
   <p> ${trans}</p>
</c:forEach>
</div>
</c:if>

<br>
<br>
<c:if test="${not empty uploadResult.invalidClient}">

<span class="coloredback">Ignored Client's Invalid Transaction</span>
<div class="datagrid">

<c:forEach items="${uploadResult.invalidClient}" var="trans">
         <p> ${trans}</p>
</c:forEach>
</div>
</c:if>

<br>
<br>
<form action="/recon" method="post">
    <div> <input type="submit" value="Recon Transactions" /></div>
</form> 

</body>
</html>