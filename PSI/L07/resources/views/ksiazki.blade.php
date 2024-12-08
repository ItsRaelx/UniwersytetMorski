<!DOCTYPE html>
<html lang="pl">
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Lista książek</h2>
    <table>
        <thead>
        <tr>
            <th>Tytuł</th>
            <th>Kategoria</th>
            <th>Wydawnictwo</th>
            @auth
                <th>Akcje</th>
            @endauth
        </tr>
        </thead>
        <tbody>
        @if($ksiazki->count() > 0)
            @foreach($ksiazki as $el)
                <tr>
                    <td>{{$el->tytul}}</td>
                    <td>{{$el->kategoria->opis ?? 'Brak kategorii'}}</td>
                    <td>{{$el->wydawnictwo->nazwa ?? 'Brak wydawnictwa'}}</td>
                    @auth
                        <td>
                            <form action="{{ url('/edytuj_ksiazke/'.$el->id) }}" method="POST" style="display: inline;">
                                @csrf
                                @method('PUT')
                                <input type="text" name="tytul" value="{{$el->tytul}}" required>
                                <select name="idkat" required>
                                    @foreach($kategorie as $kat)
                                        <option value="{{$kat->id}}" {{ $el->idkat == $kat->id ? 'selected' : '' }}>
                                            {{$kat->opis}}
                                        </option>
                                    @endforeach
                                </select>
                                <select name="idwyd" required>
                                    @foreach($wydawnictwa as $wyd)
                                        <option value="{{$wyd->id}}" {{ $el->idwyd == $wyd->id ? 'selected' : '' }}>
                                            {{$wyd->nazwa}}
                                        </option>
                                    @endforeach
                                </select>
                                <button type="submit">Edytuj</button>
                            </form>
                            <form action="{{ url('/usun_ksiazke/'.$el->id) }}" method="POST" style="display: inline;">
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
                <td colspan="@auth 4 @else 3 @endauth">Brak książek w bazie danych</td>
            </tr>
        @endif
        </tbody>
    </table>

    @auth
        <h3>Dodaj nową książkę</h3>
        <form action="{{ url('/dodaj_ksiazke') }}" method="POST">
            @csrf
            <p>
                <label for="tytul">Tytuł książki:</label>
                <input type="text" id="tytul" name="tytul" required>
            </p>
            <p>
                <label for="idkat">Kategoria:</label>
                <select name="idkat" id="idkat" required>
                    <option value="">Wybierz kategorię</option>
                    @foreach($kategorie as $kat)
                        <option value="{{$kat->id}}">{{$kat->opis}}</option>
                    @endforeach
                </select>
            </p>
            <p>
                <label for="idwyd">Wydawnictwo:</label>
                <select name="idwyd" id="idwyd" required>
                    <option value="">Wybierz wydawnictwo</option>
                    @foreach($wydawnictwa as $wyd)
                        <option value="{{$wyd->id}}">{{$wyd->nazwa}}</option>
                    @endforeach
                </select>
            </p>
            <p>
                <button type="submit">Dodaj książkę</button>
            </p>
        </form>
    @endauth
</div>
</body>
</html>
