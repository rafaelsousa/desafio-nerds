<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Address extends Model
{
    protected $fillable = [
        'type',
        'address',
        'number',
        'suite',
        'state',
        'country',
        'zipCode',
    ];

    public function person()
    {
        return $this->belongsTo(Person::class);
    }
}
