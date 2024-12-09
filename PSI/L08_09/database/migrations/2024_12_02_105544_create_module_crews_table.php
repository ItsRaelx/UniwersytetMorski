<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('module_crews', function (Blueprint $table) {
            $table->id();
            $table->foreignId('ship_module_id')->constrained()->onDelete('cascade');
            $table->string('nick', 10)->unique();
            $table->char('gender', 1);
            $table->unsignedInteger('age');
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('module_crews');
    }
};
