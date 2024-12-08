<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Ksiazka extends Model
{
    protected $table = 'ksiazka';
    protected $fillable = ['tytul', 'idkat', 'idwyd'];

    /**
     * @return BelongsTo<Kategoria>
     */
    public function kategoria(): BelongsTo
    {
        return $this->belongsTo(Kategoria::class, 'idkat');
    }

    /**
     * @return BelongsTo<Wydawnictwo>
     */
    public function wydawnictwo(): BelongsTo
    {
        return $this->belongsTo(Wydawnictwo::class, 'idwyd');
    }

    protected $casts = [
        'idkat' => 'integer',
        'idwyd' => 'integer',
    ];
}
