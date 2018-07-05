<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Edytuj</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato|Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h2>Kontakty</h2>
    <div class="navbar">
        <%--check if user logged in and display his login--%>
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
        <h3>Dodaj kontakt</h3>
        <%--place for optional messages--%>
        ${message}
        ${errorMessage}
        <%--form for all data--%>
        <form action="/persist" method="POST" id="addForm">
            <%--name--%>
            <div class="form-group">
                <label>Imię: </label>
                <input type="text" class="form-control" name="name" required value="${contact.name}">
            </div>
            <%--surname--%>
            <div class="form-group">
                <label>Nazwisko: </label>
                <input type="text" class="form-control" name="surname" required value="${contact.surname}">
            </div>
            <%--email--%>
            <div class="form-group">
                <label>Email: </label>
                <input type="email" class="form-control" name="email" required value="${contact.email}">
            </div>
            <%--password--%>
            <div class="form-group">
                <label>Hasło: </label>
                <p id="passwordStrengthText" class="constraint"></p>
                <input id="password" type="text" class="form-control" name="password" required
                       value="${contact.password}">
            </div>
            <%--category--%>
            <div class="form-group">
                Kategoria:
                <label>Słuzbowy </label><input type="radio" name="category" id="business" value="Sluzbowy">
                <label>Prywatny </label><input type="radio" name="category" id="private" value="Prywatny">
                <label>Inny </label><input type="radio" name="category" id="other" value="Inny" checked>
            </div>

            <%--subcategory--%>
            <div class="form-group">

                <p id="subcategoryLabel">Podkategoria: </p>
                <%--default value for "other" subctegory--%>
                <input id="default" type="text" class="form-control" name="subcategory" hidden value="-"/>
                <%--when business contact choosen - display only business subcategories--%>
                <select id="businessSubcategory" name="subcategory" hidden>
                    <option value="Klient">Klient</option>
                    <option value="Szef">Szef</option>
                    <option value="Kierowca">Kierowca</option>
                </select>
                <%--... or type your own subcategory, if user has chosen private contact--%>
                <input id="privateText" type="text" class="form-control" name="subcategory" hidden
                       value="${contact.subcategory}"/>


            </div>

            <%--telephone--%>
            <div class="form-group">
                <label>Numer telefonu: </label>
                <p id="telephoneText" class="constraint"></p>
                <input id="telephone" type="text" class="form-control" name="telephone" required
                       value="${contact.telephone}">
            </div>
            <%--date of birth--%>
            <div class="form-group">
                <label>Data urodzenia: </label>
                <input type="date" class="form-control" name="dob" required value="${contact.dob}">
            </div>

            <button type="submit" id="registerButton" class="btn btn-primary">Zapisz</button>
        </form>

    </div>
    <ul>
        <li><a href="/contacts">Kontakty</a></li>
        <li><a href="/index.jsp">Wróć do strony głównej</a></li>
    </ul>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>


<script src="scripts/addingContacts.js"></script>
</body>
</html>
