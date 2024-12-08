<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        @if(Request::is('/'))
            Strona Główna
        @elseif(Request::is('ksiazki'))
            Lista Książek
        @elseif(Request::is('kategorie'))
            Kategorie
        @elseif(Request::is('wydawnictwa'))
            Wydawnictwa
        @else
            Aplikacja Książki
        @endif
    </title>
    <link rel="stylesheet" href="{{ URL::asset('styles.css') }}" />
    <style>
        .current {
            background-color: #fdc153;
            font-weight: bold;
        }
    </style>
</head>
