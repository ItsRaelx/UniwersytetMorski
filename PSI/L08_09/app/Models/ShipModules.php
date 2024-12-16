<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class ShipModules extends Model
{
    public $timestamps = false;

    public const FIELD_ID = 'id';
    public const FIELD_MODULE_NAME = 'module_name';
    public const FIELD_IS_WORKABLE = 'is_workable';

    protected $table = 'ship_modules';
    protected $primaryKey = self::FIELD_ID;

    protected $fillable = [
        self::FIELD_MODULE_NAME,
        self::FIELD_IS_WORKABLE,
    ];

    public function moduleCrew()
    {
        return $this->hasMany(ModuleCrew::class, 'ship_module_id');
    }
}
