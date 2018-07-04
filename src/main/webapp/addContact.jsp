<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Dodaj</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Lato|Open+Sans" rel="stylesheet">

</head>
<body>
<div class="container">
    <h2>Kontakty</h2>
    <div class="navbar">
        <c:choose>
            <c:when test="${not empty user}">
                ${user.login}
                <a href="/logout">Wyloguj</a>
            </c:when>
            <c:otherwise>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Rejestracja</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="content">
        <h2>Dodaj kontakt</h2>
        ${message}
        ${errorMessage}
        <form action="/addContact" method="POST">
            <div class="form-group">
                <label>Imię</label>
                <input type="text" class="form-control" name="name" required>
            </div>
            <div class="form-group">
                <label>Nazwisko</label>
                <input type="text" class="form-control" name="surname" required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="form-group">
                <label>Hasło</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="form-group">
                <label>Kategoria</label>
                <input type="text" class="form-control" name="category" required>
            </div>
            <div class="form-group">
                <label>Podkategoria</label>
                <input type="text" class="form-control" name="subcategory" required>
            </div>
            <div class="form-group">
                <label>Numer telefonu</label>
                <input type="tel" class="form-control" name="telephone" required>
            </div>
            <div class="form-group">
                <label>Data urodzenia</label>
                <input type="date" class="form-control" name="dob" required>
            </div>
            <button type="submit" class="btn btn-primary">Dodaj kontakt</button>
        </form>

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
</body>
</html>
