<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Rejestracja</h2>
    <!-- Validation Errors -->
    @if ($errors->any())
        <div class="alert alert-error">
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif

    <form method="POST" action="{{ route('register') }}" class="auth-form">
        @csrf
        <div class="form-group">
            <label for="name">Imię i nazwisko</label>
            <input id="name" type="text" name="name" value="{{ old('name') }}" required autofocus />
        </div>
        <div class="form-group">
            <label for="email">Adres e-mail</label>
            <input id="email" type="email" name="email" value="{{ old('email') }}" required />
        </div>
        <div class="form-group">
            <label for="password">Hasło</label>
            <input id="password" type="password" name="password" required />
        </div>
        <div class="form-group">
            <label for="password_confirmation">Potwierdź hasło</label>
            <input id="password_confirmation" type="password" name="password_confirmation" required />
        </div>
        <div class="form-buttons">
            <button type="submit" class="btn-primary">Zarejestruj się</button>
        </div>
    </form>
</div>
</body>
</html>
