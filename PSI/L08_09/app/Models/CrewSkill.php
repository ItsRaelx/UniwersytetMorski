<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class CrewSkill extends Model
{
    protected $fillable = ['module_crew_id', 'name'];

    public static array $rules = [
        'module_crew_id' => 'required|exists:module_crews,id',
        'name' => 'required|string|min:3|max:15|unique:crew_skills'
    ];

    public function crew(): BelongsTo
    {
        return $this->belongsTo(ModuleCrew::class, 'module_crew_id');
    }
}
