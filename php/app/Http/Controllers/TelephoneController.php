<?php

namespace App\Http\Controllers;

use App\Models\Person;
use Illuminate\Http\Request;

class TelephoneController extends Controller
{
    public function index($person)
    {
        $person = Person::findOrFail($person);

        $telephones = $person->telephones;
        return response()->json($telephones);
    }

    public function show($person, $id)
    {
        $person = Person::findOrFail($person);
        $telefones = $person->telephones()->findOrFail($id);

        return response()->json($telefones);
    }

    public function store(Request $request, $person)
    {
        $this->validate($request, [
            "number" => "required|numeric",
            "type" => "required"
        ]);
        $person = Person::findOrFail($person);
        $telefones = $person
            ->telephones()
            ->create($request->all());

        return response()->json($telefones);
    }

    public function update(Request $request, $person, $id)
    {
        $this->validate($request, [
            'number' => "numeric"
        ]);
        $person = Person::findOrFail($person);
        $telefones = $person
            ->telephones()
            ->findOrFail($id);
        $telefones->update($request->all());

        return response()->json($telefones);
    }

    public function destroy($person, $id)
    {
        $person = Person::findOrFail($person);
        $telefones = $person
            ->telephones()
            ->findOrFail($id);
        $telefones->delete();

        return response()->json(null);
    }
}
