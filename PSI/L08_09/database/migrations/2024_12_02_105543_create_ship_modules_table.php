<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('ship_modules', function (Blueprint $table) {
            $table->id();
            $table->string('module_name', 25)->unique();
            $table->boolean('is_workable');
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('ship_modules');
    }
};
