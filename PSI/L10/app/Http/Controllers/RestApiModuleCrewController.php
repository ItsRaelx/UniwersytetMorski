<?php

namespace App\Http\Controllers;

use App\Http\Requests\ModuleCrewRequest;
use App\Models\ModuleCrew;
use Illuminate\Http\JsonResponse;

class RestApiModuleCrewController extends Controller
{
    public function list(): JsonResponse
    {
        $crew = ModuleCrew::all();
        return response()->json(['data' => $crew]);
    }

    public function find($id): JsonResponse
    {
        $crew = ModuleCrew::find($id);

        if (!$crew) {
            return response()->json([
                'error' => true,
                'message' => 'Crew member not found'
            ], 404);
        }

        return response()->json(['data' => $crew]);
    }

    public function create(ModuleCrewRequest $request): JsonResponse
    {
        $validated = $request->validated();
        $crew = ModuleCrew::create($validated);

        return response()->json([
            'message' => 'Crew member created successfully',
            'data' => $crew
        ], 201);
    }

    public function update(ModuleCrewRequest $request, $id): JsonResponse
    {
        $crew = ModuleCrew::find($id);

        if (!$crew) {
            return response()->json([
                'error' => true,
                'message' => 'Crew member not found'
            ], 404);
        }

        $validated = $request->validated();
        $crew->update($validated);

        return response()->json([
            'message' => 'Crew member updated successfully',
            'data' => $crew
        ]);
    }

    public function delete($id): JsonResponse
    {
        $crew = ModuleCrew::find($id);

        if (!$crew) {
            return response()->json([
                'error' => true,
                'message' => 'Crew member not found'
            ], 404);
        }

        $crew->delete();

        return response()->json([
            'message' => 'Crew member deleted successfully'
        ]);
    }
}
