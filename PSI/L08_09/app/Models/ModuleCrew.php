<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;

class ModuleCrew extends Model
{
    protected $fillable = ['ship_module_id', 'nick', 'gender', 'age'];

    public static array $rules = [
        'ship_module_id' => 'required|exists:ship_modules,id',
        'nick' => 'required|string|min:3|max:10|unique:module_crews',
        'gender' => 'required|in:F,M,N',
        'age' => 'required|integer|min:18|max:85'
    ];

    public function module(): BelongsTo
    {
        return $this->belongsTo(ShipModule::class, 'ship_module_id');
    }

    public function skills(): HasMany
    {
        return $this->hasMany(CrewSkill::class);
    }
}
