<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Address extends Model
{

    use SoftDeletes;

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
