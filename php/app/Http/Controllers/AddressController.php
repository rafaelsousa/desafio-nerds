<?php

namespace App\Http\Controllers;

use App\Models\Address;
use App\Models\Person;
use Illuminate\Http\Request;

class AddressController extends Controller
{
    public function index($person)
    {
        $person = Person::findOrFail($person);

        $addresses = $person->addresses;
        return response()->json($addresses);
    }

    public function show($person, $id)
    {
        $person = Person::findOrFail($person);
        $address = $person->addresses()->findOrFail($id);

        return response()->json($address);
    }

    public function store(Request $request, $person)
    {
        $this->validate($request, [
            "type" => "required",
            "address" => "required",
            "number" => "required|numeric",
            "suite" => "required",
            "state" => "required",
            "country" => "required",
            "zipCode" => "required",
        ]);
        $person = Person::findOrFail($person);
        $address = $person
            ->addresses()
            ->create($request->all());

        return response()->json($address);
    }

    public function update(Request $request, $person, $id)
    {
        $this->validate($request, [
            'number' => "numeric"
        ]);
        $person = Person::findOrFail($person);
        $address = $person
            ->addresses()
            ->findOrFail($id);
        $address->update($request->all());

        return response()->json($address);
    }

    public function destroy($person, $id)
    {
        $person = Person::findOrFail($person);
        $address = $person
            ->addresses()
            ->findOrFail($id);
        $address->delete();

        return response()->json(null);
    }
}
