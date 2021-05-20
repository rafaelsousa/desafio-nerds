<?php

namespace App\Http\Controllers;

use App\Models\Person;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class PersonController extends Controller
{
    public function index()
    {
        $persons = (new Person())->paginate();
        return response()->json($persons);
    }

    public function show($id)
    {
        $person = Person::findOrFail($id);

        return response()->json($person);
    }

    public function store(Request $request)
    {
        $this->validate($request, [
            'name' => 'required',
            'email' => 'required|unique:persons',
            'birthday' => 'required|date_format:Y-m-d',
        ]);

        $person = (new Person)
            ->create($request->all());

        return response()->json($person);
    }

    public function update(Request $request, $id)
    {
        $this->validate($request, [
            'email' => "unique:persons,email,$id",
            'birthday' => 'date_format:Y-m-d',
        ]);

        $person = Person::findOrFail($id);

        $person->update($request->all());

        return response()->json(null);
    }

    public function destroy($id)
    {
        $person = Person::findOrFail($id);

        $person->delete();

        return response()->json(null);
    }
}
