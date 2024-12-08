<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Logowanie</h2>
    <!-- Session Status -->
    @if (session('status'))
        <div class="alert">
            {{ session('status') }}
        </div>
    @endif

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

    <form method="POST" action="{{ route('login') }}" class="auth-form">
        @csrf
        <div class="form-group">
            <label for="email">Adres e-mail</label>
            <input id="email" type="email" name="email" value="{{ old('email') }}" required autofocus />
        </div>
        <div class="form-group">
            <label for="password">Hasło</label>
            <input id="password" type="password" name="password" required />
        </div>
        <div class="form-group">
            <label for="remember_me" class="checkbox-label">
                <input id="remember_me" type="checkbox" name="remember">
                <span>Zapamiętaj mnie</span>
            </label>
        </div>
        <div class="form-buttons">
            <button type="submit" class="btn-primary">Zaloguj się</button>
            @if (Route::has('password.request'))
                <a href="{{ route('password.request') }}" class="btn-link">Zapomniałeś hasła?</a>
            @endif
        </div>
    </form>
</div>
</body>
</html>
