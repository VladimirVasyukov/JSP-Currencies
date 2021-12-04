<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>

<html>
    <head>
        <h1>Exchange Rates for ${param.from}</h1>
    </head>
    <body style = "font-family: Courier New, serif; font-size: 16pt;">
        <ul>
            <c:forEach items="${currencies.getExchangeRates(param.from)}" var="curs">
                <c:if test = "${curs.key != param.from}">
                    <li>1 ${param.from} = ${curs.value} ${curs.key}</li>
                </c:if>
            </c:forEach>
        </ul>
    </body>
</html>
