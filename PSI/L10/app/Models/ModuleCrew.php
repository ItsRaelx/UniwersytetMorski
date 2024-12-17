<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class ModuleCrew extends Model
{
    public $timestamps = false;

    protected $table = 'module_crew';
    protected $primaryKey = 'id';

    protected $fillable = [
        'ship_module_id',
        'nick',
        'gender',
        'age'
    ];

    public function shipModule()
    {
        return $this->belongsTo(ShipModules::class, 'ship_module_id');
    }

    public function crewSkills()
    {
        return $this->hasMany(CrewSkills::class, 'module_crew_id');
    }
}
