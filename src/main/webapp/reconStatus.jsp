<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/css/tutuka.css"/>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>

<h3>Transactions Recon Status</h3>
<div class="bigwrapper datagrid">
  <b>Tutuka Transaction Recon Summary</b> <br><br>
   <label>Total Match Transactions &nbsp;</label> <b>${reconResult.totalMatchTxn} </b><br><br>
   <label>Partial Match Transactions &nbsp;</label> <b>${reconResult.totalPartialMatchTxn} </b><br><br>
    <div style="width:50%;float:left;display:table-cell; border-right:solid black 1;"  align="left" >
   <b>Tutuka Transaction Un-Match Summary</b> <br>   
    <label>Total Un-matched Tutuka Transactions</label> <b>${reconResult.totalUnMatchTTxn} </b><br>
    <label>Total Possible Duplicate Tutuka Transactions</label> <b>${reconResult.totalDuplicateTTxn} </b><br>
	</div>
    <div style="width:50%;float:right;display:table-cell;">
     <b>Client Transaction Un-Match Summary</b> <br>
     <label>Total Un-matched Client Transactions</label> <b>${reconResult.totalUnMatchCTxn} </b><br>
    <label>Total Possible Duplicate Tutuka Transactions</label> <b>${reconResult.totalDuplicateCTxn} </b><br>
    </div>
</div>

<c:if test="${not empty reconResult.partialMatchTutuka}">
<span class="coloredback">Partial Match Transactions</span> <br>
<div class="bigwrapper datagrid">
<div style="width:50%;float:left;display:table-cell; border-right:solid black 1;"  align="left" >
<span>Tutuka Transactions</span>
<table>
<tr><th>Transaction ID</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    </tr>
<c:forEach items="${reconResult.partialMatchTutuka}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    </tr>
</c:forEach>
</table>
</div>
<div style="width:50%;float:right;display:table-cell;">
<span>Client Transactions</span>
<table>
<tr><th>Transaction ID</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    </tr>
<c:forEach items="${reconResult.partialMatchClient}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    </tr>
</c:forEach>
</table>
</div>
</div>
</c:if>

<br>

<c:if test="${not empty reconResult.unmatchedTutuka}">
<span class="coloredback">Unmatched Tutuka Transactions</span>
<div class="datagrid">

<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${reconResult.unmatchedTutuka}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.transactionDescription}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    <td>${trans.value.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>
<br>

<c:if test="${not empty reconResult.unmatchedClient}">
<span class="coloredback">Unmatched Client Transactions</span>
<div class="datagrid" >
<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${reconResult.unmatchedClient}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.transactionDescription}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    <td>${trans.value.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>
<c:if test="${not empty reconResult.possibleDuplicateTutukaTransactions}">
<span class="coloredback">Possible Duplicate Tutuka Transactions</span>
<div class="datagrid" >
<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${reconResult.possibleDuplicateTutukaTransactions}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.transactionDescription}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    <td>${trans.value.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>

<c:if test="${not empty reconResult.possibleDuplicateClientTransactions}">
<span class="coloredback">Possible Duplicate Client Transactions</span>
<div class="datagrid" >
<table>
<tr><th>Transaction ID</th>
    <th>Transaction Description</th>
    <th>Wallet Reference</th>
    <th>Transaction Date</th>
    <th>Transaction Amount</th>
    <th>Transaction Type</th>
    </tr>
<c:forEach items="${reconResult.possibleDuplicateClientTransactions}" var="trans">
    <tr><td>${trans.value.transactionID}</td>
    <td>${trans.value.transactionDescription}</td>
    <td>${trans.value.walletRefernce}</td>
    <td>${trans.value.transactionDate}</td>
    <td>${trans.value.transactionAmount}</td>
    <td>${trans.value.transactionType}</td>
    </tr>
</c:forEach>
</table>
</div>
</c:if>

</body>
</html>