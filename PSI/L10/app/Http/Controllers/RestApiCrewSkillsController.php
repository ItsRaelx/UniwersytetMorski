<?php

namespace App\Http\Controllers;

use App\Http\Requests\CrewSkillsRequest;
use App\Models\CrewSkills;
use Illuminate\Http\JsonResponse;

class RestApiCrewSkillsController extends Controller
{
    public function list(): JsonResponse
    {
        $skills = CrewSkills::all();
        return response()->json(['data' => $skills]);
    }

    public function find($id): JsonResponse
    {
        $skill = CrewSkills::find($id);

        if (!$skill) {
            return response()->json([
                'error' => true,
                'message' => 'Skill not found'
            ], 404);
        }

        return response()->json(['data' => $skill]);
    }

    public function create(CrewSkillsRequest $request): JsonResponse
    {
        $validated = $request->validated();
        $skill = CrewSkills::create($validated);

        return response()->json([
            'message' => 'Skill created successfully',
            'data' => $skill
        ], 201);
    }

    public function update(CrewSkillsRequest $request, $id): JsonResponse
    {
        $skill = CrewSkills::find($id);

        if (!$skill) {
            return response()->json([
                'error' => true,
                'message' => 'Skill not found'
            ], 404);
        }

        $validated = $request->validated();
        $skill->update($validated);

        return response()->json([
            'message' => 'Skill updated successfully',
            'data' => $skill
        ]);
    }

    public function delete($id): JsonResponse
    {
        $skill = CrewSkills::find($id);

        if (!$skill) {
            return response()->json([
                'error' => true,
                'message' => 'Skill not found'
            ], 404);
        }

        $skill->delete();

        return response()->json([
            'message' => 'Skill deleted successfully'
        ]);
    }
}
