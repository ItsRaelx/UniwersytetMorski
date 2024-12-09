<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class ShipModule extends Model
{
    protected $fillable = ['module_name', 'is_workable'];

    public static array $rules = [
        'module_name' => 'required|string|min:3|max:25|unique:ship_modules',
        'is_workable' => 'required|boolean'
    ];

    public function crews(): HasMany
    {
        return $this->hasMany(ModuleCrew::class);
    }
}
