<?php

namespace App\Http\Controllers;

use App\Models\ModuleCrew;
use App\Models\ShipModules;
use Illuminate\Http\Request;

class ShipModulesController extends Controller
{
    public function index()
    {
        $myShipModules = ShipModules::all();
        return view('shipmodules.list', ['ship_modules' => $myShipModules]);
    }

    public function create()
    {
        return view('shipmodules.add');
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'module_name' => 'required|min:3|max:25|unique:ship_modules',
            'is_workable' => 'required',
        ]);

        if ($validated) {
            $mod_ship = new ShipModules();
            $mod_ship->module_name = $request->module_name;
            $mod_ship->is_workable = $request->is_workable;
            $mod_ship->save();

            return redirect('/shipmodules/list');
        }
    }

    public function edit($id)
    {
        $myShipModule = ShipModules::find($id);

        if ($myShipModule == null) {
            $error_message = "Ship module id=".$id." not found";
            return view('shipmodules.message', ['message' => $error_message, 'type_of_message' => 'Error']);
        }

        return view('shipmodules.edit', ['shipmodule' => $myShipModule]);
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'module_name' => 'required|min:3|max:25|unique:ship_modules,module_name,'.$id,
            'is_workable' => 'required',
        ]);

        if ($validated) {
            $mod_ship = ShipModules::find($id);

            if ($mod_ship != null) {
                $mod_ship->module_name = $request->module_name;
                $mod_ship->is_workable = $request->is_workable;
                $mod_ship->save();

                return redirect('/shipmodules/list');
            } else {
                $error_message = "Ship module id=".$id." not found";
                return view('shipmodules.message', ['message' => $error_message, 'type_of_message' => 'Error']);
            }
        }
    }

    public function show($id)
    {
        $myShipModule = ShipModules::find($id);

        if ($myShipModule == null) {
            $error_message = "Ship module id=".$id." not found";
            return view('shipmodules.message', ['message' => $error_message, 'type_of_message' => 'Error']);
        }

        return view('shipmodules.show', ['shipmodule' => $myShipModule]);
    }

    public function destroy($id)
    {
        $mod_ship = ShipModules::find($id);

        if ($mod_ship != null) {
            $mod_ship->delete();
            return redirect('/shipmodules/list');
        } else {
            $error_message = "Delete Ship module id=".$id." not found";
            return view('shipmodules.message', ['message' => $error_message, 'type_of_message' => 'Error']);
        }
    }

    public function showCrew($id)
    {
        $module = ShipModules::find($id);
        if (!$module) {
            return view('shipmodules.message', [
                'message' => "Module id=$id not found",
                'type_of_message' => 'Error'
            ]);
        }

        $crew = ModuleCrew::where('ship_module_id', $id)->get();
        return view('shipmodules.crew', [
            'module' => $module,
            'crew' => $crew
        ]);
    }
}
