<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Wydawnictwo extends Model
{
    protected $table = 'wydawnictwo';
    protected $fillable = ['nazwa', 'adres'];

    /**
     * @return HasMany<Ksiazka>
     */
    public function ksiazki(): HasMany
    {
        return $this->hasMany(Ksiazka::class, 'idwyd');
    }

    protected static function boot()
    {
        parent::boot();

        static::deleting(function ($wydawnictwo) {
            if ($wydawnictwo->ksiazki()->count() > 0) {
                return false;
            }
        });
    }
}
