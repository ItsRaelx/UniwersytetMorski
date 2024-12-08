<?php

namespace App\Http\Controllers;

use App\Models\Ksiazka;
use App\Models\Kategoria;
use App\Models\Wydawnictwo;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class PodstawowyKontroler extends Controller
{
    public function zwrocStroneDomowa()
    {
        return view('domowa');
    }

    public function zwrocListeKsiazek()
    {
        $ksiazki = Ksiazka::with(['kategoria', 'wydawnictwo'])->get();
        $kategorie = Kategoria::all();
        $wydawnictwa = Wydawnictwo::all();
        return view('ksiazki', [
            'ksiazki' => $ksiazki,
            'kategorie' => $kategorie,
            'wydawnictwa' => $wydawnictwa
        ]);
    }

    public function dodajKsiazke(Request $request)
    {
        Ksiazka::create([
            'tytul' => $request->tytul,
            'idkat' => $request->idkat,
            'idwyd' => $request->idwyd,
        ]);
        return redirect('/ksiazki');
    }

    public function zwrocListeKategorii()
    {
        $kategorie = Kategoria::withCount('ksiazki')->get();
        return view('kategorie', ['kategorie' => $kategorie]);
    }

    public function zwrocListeWydawnictw()
    {
        $wydawnictwa = Wydawnictwo::withCount('ksiazki')->get();
        return view('wydawnictwa', ['wydawnictwa' => $wydawnictwa]);
    }

    public function dodajKategorie(Request $request)
    {
        Kategoria::create([
            'opis' => $request->opis
        ]);
        return redirect('/kategorie');
    }

    public function dodajWydawnictwo(Request $request)
    {
        Wydawnictwo::create([
            'nazwa' => $request->nazwa,
            'adres' => $request->adres
        ]);
        return redirect('/wydawnictwa');
    }

    public function usunKategorie($id)
    {
        $kategoria = Kategoria::findOrFail($id);
        if ($kategoria->ksiazki()->count() === 0) {
            $kategoria->delete();
        }
        return redirect('/kategorie');
    }

    public function usunWydawnictwo($id)
    {
        $wydawnictwo = Wydawnictwo::findOrFail($id);
        if ($wydawnictwo->ksiazki()->count() === 0) {
            $wydawnictwo->delete();
        }
        return redirect('/wydawnictwa');
    }

    public function usunKsiazke($id)
    {
        Ksiazka::findOrFail($id)->delete();
        return redirect('/ksiazki');
    }

    public function edytujKategorie(Request $request, $id)
    {
        $kategoria = Kategoria::findOrFail($id);
        $kategoria->update([
            'opis' => $request->opis
        ]);
        return redirect('/kategorie');
    }

    public function edytujWydawnictwo(Request $request, $id)
    {
        $wydawnictwo = Wydawnictwo::findOrFail($id);
        $wydawnictwo->update([
            'nazwa' => $request->nazwa,
            'adres' => $request->adres
        ]);
        return redirect('/wydawnictwa');
    }

    public function edytujKsiazke(Request $request, $id)
    {
        $ksiazka = Ksiazka::findOrFail($id);
        $ksiazka->update([
            'tytul' => $request->tytul,
            'idkat' => $request->idkat,
            'idwyd' => $request->idwyd,
        ]);
        return redirect('/ksiazki');
    }
    // Add this method to the controller
    public function zmienStanUwierzytelnienia()
    {
        if(auth()->check()){
            $user = auth()->user();
            Auth::logout();
            return view('wylogowano');
        }
        else{
            return redirect('/login');
        }
    }

    public function pokazZalogowano()
    {
        return view('zalogowano');
    }
}
