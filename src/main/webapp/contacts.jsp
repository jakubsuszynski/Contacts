<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Kontakty</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Lato|Open+Sans" rel="stylesheet">

</head>
<body>
<div class="container">
    <h2>Kontakty</h2>
    <div class="navbar">
        <c:choose>
            <c:when test="${not empty user}">
                Zalogowano jako ${user.login}
                <a href="/logout">Wyloguj</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Rejestracja</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="content">
        <h3>Kontakty</h3>
        ${message}
        ${errorMessage}
        <%--If user is logged in - show options to modify contacts base. If not - don't display them. User was set in session during loging in.--%>

        <c:forEach var="entry" items="${contacts}">
            <details>
                <summary>
                    <span class="toHide" name="name">${entry.name} </span>
                    <span class="toHide" name="surname">${entry.surname} </span>
                </summary>


                <ul>
                    <div class="toHide">
                        <li>Email: ${entry.email}</li>
                        <li>Hasło: ${entry.password}</li>
                        <li>Kategoria: ${entry.category}</li>
                        <li>Podkategoria: ${entry.subcategory}</li>
                        <li>Numer telefonu: ${entry.telephone}</li>
                        <li>Data urodzenia: ${entry.dob}</li>
                    </div>
                    <c:choose>
                        <c:when test="${not empty user}">
                            <div class="toEdit" hidden>
                                <li>Email: <input type="text" name="email" value="${entry.email}"/></li>
                                <li>Hasło: <input type="text" name="password" value="${entry.password}"/></li>
                                <li>Kategoria: <input type="text" name="category" value="${entry.category}"/></li>
                                <li>Podkategoria: <input type="text" name="subcategory" value="${entry.subcategory}"/>
                                </li>
                                <li>Numer telefonu: <input type="text" name="telephone" value="${entry.telephone}"/>
                                </li>
                                <li>Data urodzenia: <input type="date" name="dob" value="${entry.dob}"/></li>
                            </div>
                            <li><a href="/delete?id=${entry.id}">Usuń wpis</a></li>
                            <li><a href="#" id="begin">Edytuj wpis</a></li>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </details>


        </c:forEach>

        <ul>
            <c:choose>
                <c:when test="${not empty user}">
                    <li><a href="/addContact.jsp">Dodaj nowy wpis</a></li>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <li><a href="/contacts">Kontakty</a></li>
            <li><a href="/index.jsp">Wróć do strony głównej</a></li>
        </ul>

    </div>

</div>

</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="scripts/modifyContact.js"></script>
</body>
</html>
