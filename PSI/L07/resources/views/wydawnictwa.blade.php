<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Lista wydawnictw</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th>Adres</th>
            @auth
                <th>Akcje</th>
            @endauth
        </tr>
        </thead>
        <tbody>
        @if($wydawnictwa->count() > 0)
            @foreach($wydawnictwa as $wyd)
                <tr>
                    <td>{{$wyd->id}}</td>
                    <td>{{$wyd->nazwa}}</td>
                    <td>{{$wyd->adres}}</td>
                    @auth
                        <td>
                            <form action="{{ url('/edytuj_wydawnictwo/'.$wyd->id) }}" method="POST" style="display: inline;">
                                @csrf
                                @method('PUT')
                                <input type="text" name="nazwa" value="{{$wyd->nazwa}}" required>
                                <input type="text" name="adres" value="{{$wyd->adres}}" required>
                                <button type="submit">Edytuj</button>
                            </form>
                            <form action="{{ url('/usun_wydawnictwo/'.$wyd->id) }}" method="POST" style="display: inline;">
                                @csrf
                                @method('DELETE')
                                <button type="submit" onclick="return confirm('Czy na pewno usunąć?')">Usuń</button>
                            </form>
                        </td>
                    @endauth
                </tr>
            @endforeach
        @else
            <tr>
                <td colspan="@auth 4 @else 3 @endauth">Brak wydawnictw w bazie danych</td>
            </tr>
        @endif
        </tbody>
    </table>

    @auth
        <h3>Dodaj nowe wydawnictwo</h3>
        <form action="{{ url('/dodaj_wydawnictwo') }}" method="POST">
            @csrf
            <p>
                <label for="nazwa">Nazwa wydawnictwa:</label>
                <input type="text" id="nazwa" name="nazwa" required>
            </p>
            <p>
                <label for="adres">Adres:</label>
                <input type="text" id="adres" name="adres" required>
            </p>
            <p>
                <button type="submit">Dodaj wydawnictwo</button>
            </p>
        </form>
    @endauth
</div>
</body>
</html>
