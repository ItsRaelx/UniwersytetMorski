<?php

namespace App\Http\Controllers;

use App\Models\ModuleCrew;
use App\Models\ShipModules;
use Illuminate\Http\Request;

class ModuleCrewController extends Controller
{
    public function index()
    {
        $crew = ModuleCrew::all();
        return view('modulecrew.list', ['crew' => $crew]);
    }

    public function create()
    {
        $modules = ShipModules::all();
        return view('modulecrew.add', ['modules' => $modules]);
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'ship_module_id' => 'required|exists:ship_modules,id',
            'nick' => 'required|min:3|max:10|unique:module_crew',
            'gender' => 'required|in:F,M,N',
            'age' => 'required|integer|min:18|max:85',
        ]);

        if ($validated) {
            ModuleCrew::create($request->all());
            return redirect('/modulecrew/list');
        }
    }

    public function edit($id)
    {
        $crew = ModuleCrew::find($id);
        $modules = ShipModules::all();

        if (!$crew) {
            return view('modulecrew.message', [
                'message' => "Crew member id=$id not found",
                'type_of_message' => 'Error'
            ]);
        }

        return view('modulecrew.edit', [
            'crew' => $crew,
            'modules' => $modules
        ]);
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'ship_module_id' => 'required|exists:ship_modules,id',
            'nick' => 'required|min:3|max:10|unique:module_crew,nick,'.$id,
            'gender' => 'required|in:F,M,N',
            'age' => 'required|integer|min:18|max:85',
        ]);

        if ($validated) {
            $crew = ModuleCrew::find($id);
            if ($crew) {
                $crew->update($request->all());
                return redirect('/modulecrew/list');
            }
        }
    }

    public function destroy($id)
    {
        $crew = ModuleCrew::find($id);
        if ($crew) {
            $crew->delete();
            return redirect('/modulecrew/list');
        }

        return view('modulecrew.message', [
            'message' => "Crew member id=$id not found",
            'type_of_message' => 'Error'
        ]);
    }
}
