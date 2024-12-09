<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('crew_skills', function (Blueprint $table) {
            $table->id();
            $table->foreignId('module_crew_id')->constrained()->onDelete('cascade');
            $table->string('name', 15)->unique();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('crew_skills');
    }
};
