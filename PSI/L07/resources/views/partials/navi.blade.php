<nav>
    <div>
        <div>
            <a href="{{ url('/') }}" @if(Request::is('/')) class="current" @endif>
                Strona domowa
            </a>
            <a href="{{ url('/ksiazki') }}" @if(Request::is('ksiazki')) class="current" @endif>
                Książki
            </a>
            <a href="{{ url('/kategorie') }}" @if(Request::is('kategorie')) class="current" @endif>
                Kategorie
            </a>
            <a href="{{ url('/wydawnictwa') }}" @if(Request::is('wydawnictwa')) class="current" @endif>
                Wydawnictwa
            </a>
            @auth
                <a href="{{ url('/wyloguj') }}">Wyloguj ({{ Auth::user()->name }})</a>
            @else
                <a href="{{ url('/login') }}" @if(Request::is('login')) class="current" @endif>
                    Zaloguj
                </a>
                <a href="{{ url('/register') }}" @if(Request::is('register')) class="current" @endif>
                    Zarejestruj
                </a>
            @endauth
        </div>
    </div>
</nav>
