<?php

namespace App\Http\Controllers;

use App\Http\Requests\ShipModulesRequest;
use App\Models\ShipModules;
use Illuminate\Http\JsonResponse;

class RestApiShipModulesController extends Controller
{
    public function ListShipmodules(): JsonResponse
    {
        $modules = ShipModules::all();
        return response()->json([
            'data' => $modules
        ]);
    }

    public function NewShipModule(ShipModulesRequest $request): JsonResponse
    {
        $validated = $request->validated();
        $module = ShipModules::create($validated);

        return response()->json([
            'message' => 'Module created successfully',
            'data' => $module
        ], 201);
    }

    public function UpdateShipModule(ShipModulesRequest $request, $id): JsonResponse
    {
        $module = ShipModules::find($id);

        if (!$module) {
            return response()->json([
                'error' => true,
                'message' => 'Module not found'
            ], 404);
        }

        $validated = $request->validated();
        $module->update($validated);

        return response()->json([
            'message' => 'Module updated successfully',
            'data' => $module
        ]);
    }

    public function DeleteShipModule($id): JsonResponse
    {
        $module = ShipModules::find($id);

        if (!$module) {
            return response()->json([
                'error' => true,
                'message' => 'Module not found'
            ], 404);
        }

        $module->delete();

        return response()->json([
            'message' => 'Module deleted successfully'
        ]);
    }

    public function FindShipModule($id): JsonResponse
    {
        $module = ShipModules::find($id);

        if (!$module) {
            return response()->json([
                'error' => true,
                'message' => 'Module not found'
            ], 404);
        }

        return response()->json([
            'data' => $module
        ], 200);
    }
}
