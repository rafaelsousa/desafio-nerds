<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Telephone extends Model
{

    use SoftDeletes;

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
