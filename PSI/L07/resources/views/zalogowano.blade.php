<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Zalogowano pomyślnie</h2>
    <p>Witaj, {{ Auth::user()->name }}! Zostałeś poprawnie zalogowany.</p>
</div>
</body>
</html>
