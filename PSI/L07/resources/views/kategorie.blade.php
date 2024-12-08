<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Lista kategorii</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Opis</th>
            @auth
                <th>Akcje</th>
            @endauth
        </tr>
        </thead>
        <tbody>
        @foreach($kategorie as $kat)
            <tr>
                <td>{{$kat->id}}</td>
                <td>{{$kat->opis}}</td>
                @auth
                    <td>
                        <form action="{{ url('/edytuj_kategorie/'.$kat->id) }}" method="POST" style="display: inline;">
                            @csrf
                            @method('PUT')
                            <input type="text" name="opis" value="{{$kat->opis}}" required>
                            <button type="submit">Edytuj</button>
                        </form>
                        <form action="{{ url('/usun_kategorie/'.$kat->id) }}" method="POST" style="display: inline;">
                            @csrf
                            @method('DELETE')
                            <button type="submit" onclick="return confirm('Czy na pewno usunąć?')">Usuń</button>
                        </form>
                    </td>
                @endauth
            </tr>
        @endforeach
        </tbody>
    </table>

    @auth
        <h3>Dodaj nową kategorię</h3>
        <form action="{{ url('/dodaj_kategorie') }}" method="POST">
            @csrf
            <p>
                <label for="opis">Opis kategorii:</label>
                <input type="text" id="opis" name="opis" required>
            </p>
            <p>
                <button type="submit">Dodaj kategorię</button>
            </p>
        </form>
    @endauth
</div>
</body>
</html>
