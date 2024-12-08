<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('ksiazka', function (Blueprint $table) {
            $table->id();
            $table->string('tytul');
            $table->foreignId('idwyd')->constrained('wydawnictwo');
            $table->foreignId('idkat')->constrained('kategoria');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('kasiazka');
    }
};
