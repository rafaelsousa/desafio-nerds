<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Telephone extends Model
{
    protected $fillable = [
        'person_id',
        'number',
        'type',
    ];

    public function person()
    {
        return $this->belongsTo(Person::class);
    }
}
