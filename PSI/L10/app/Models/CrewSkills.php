<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class CrewSkills extends Model
{
    public $timestamps = false;

    protected $table = 'crew_skills';
    protected $primaryKey = 'id';

    protected $fillable = [
        'module_crew_id',
        'name'
    ];

    public function moduleCrew()
    {
        return $this->belongsTo(ModuleCrew::class, 'module_crew_id');
    }
}
