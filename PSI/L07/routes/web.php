<?php

use App\Http\Controllers\PodstawowyKontroler;
use Illuminate\Support\Facades\Route;

// Home page
Route::get('/', [PodstawowyKontroler::class, 'zwrocStroneDomowa']);

// Books routes
Route::get('/ksiazki', [PodstawowyKontroler::class, 'zwrocListeKsiazek']);
Route::get('/dodaj_ksiazke', [PodstawowyKontroler::class, 'zwrocDodajKsiazke'])->middleware('auth');
Route::post('/dodaj_ksiazke', [PodstawowyKontroler::class, 'dodajKsiazke'])->middleware('auth');
Route::delete('/usun_ksiazke/{id}', [PodstawowyKontroler::class, 'usunKsiazke'])->middleware('auth');
Route::put('/edytuj_ksiazke/{id}', [PodstawowyKontroler::class, 'edytujKsiazke'])->middleware('auth');

// Categories routes
Route::get('/kategorie', [PodstawowyKontroler::class, 'zwrocListeKategorii']);
Route::post('/dodaj_kategorie', [PodstawowyKontroler::class, 'dodajKategorie'])->middleware('auth');
Route::delete('/usun_kategorie/{id}', [PodstawowyKontroler::class, 'usunKategorie'])->middleware('auth');
Route::put('/edytuj_kategorie/{id}', [PodstawowyKontroler::class, 'edytujKategorie'])->middleware('auth');

// Publishers routes
Route::get('/wydawnictwa', [PodstawowyKontroler::class, 'zwrocListeWydawnictw']);
Route::post('/dodaj_wydawnictwo', [PodstawowyKontroler::class, 'dodajWydawnictwo'])->middleware('auth');
Route::delete('/usun_wydawnictwo/{id}', [PodstawowyKontroler::class, 'usunWydawnictwo'])->middleware('auth');
Route::put('/edytuj_wydawnictwo/{id}', [PodstawowyKontroler::class, 'edytujWydawnictwo'])->middleware('auth');

// Auth routes
Route::get('/loguj', [PodstawowyKontroler::class, 'zmienStanUwierzytelnienia']);
Route::get('/wyloguj', [PodstawowyKontroler::class, 'zmienStanUwierzytelnienia']);

Route::get('/dashboard', function () {
    return redirect('/');
})->middleware(['auth'])->name('dashboard');

require __DIR__.'/auth.php';
