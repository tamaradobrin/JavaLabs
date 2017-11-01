<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped">
    <thead>
    <th>Country</th>
    <th>Capital</th>
    <th>Continent</th>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.countries}" var="country">
        <tr>
            <td>${country.countryName}</td>
            <td>${country.capital}</td>
            <td>${country.continent}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>