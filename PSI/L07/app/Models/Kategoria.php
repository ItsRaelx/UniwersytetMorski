<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Kategoria extends Model
{
    protected $table = 'kategoria';
    protected $fillable = ['opis'];

    /**
     * @return HasMany<Ksiazka>
     */
    public function ksiazki(): HasMany
    {
        return $this->hasMany(Ksiazka::class, 'idkat');
    }

    protected static function boot()
    {
        parent::boot();

        static::deleting(function ($kategoria) {
            if ($kategoria->ksiazki()->count() > 0) {
                return false;
            }
        });
    }
}
