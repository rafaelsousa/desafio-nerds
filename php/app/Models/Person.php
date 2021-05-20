<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Person extends Model
{
    use HasFactory;

    protected $table = 'persons';

    protected $fillable = [
        'name',
        'email',
        'birthday',
    ];

    public function addresses()
    {
        return $this->hasMany(Address::class);
    }

    public function telephones()
    {
        return $this->hasMany(Telephone::class);
    }
}
