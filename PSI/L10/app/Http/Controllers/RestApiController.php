<?php

namespace App\Http\Controllers;

use App\Http\Requests\FullNameRequest;
use Illuminate\Http\Request;

class RestApiController extends Controller
{
    public function showWelcome()
    {
        return response()->json([
            'message' => 'Welcome to the API'
        ]);
    }

    private function createFullNameResponse($first_name, $last_name)
    {
        return response()->json([
            'data' => [
                'full_name' => $first_name . ' ' . $last_name
            ]
        ]);
    }

    public function showFullNameFromPath($first_name, $last_name)
    {
        // Validate URL parameters
        $validator = validator(['first_name' => $first_name, 'last_name' => $last_name], [
            'first_name' => 'required|string|min:2|max:50',
            'last_name' => 'required|string|min:2|max:50'
        ]);

        if ($validator->fails()) {
            return response()->json([
                'error' => $validator->errors()
            ], 422);
        }

        return $this->createFullNameResponse($first_name, $last_name);
    }

    public function showFullNameFromQueryParams(FullNameRequest $request)
    {
        $data = $request->validated();
        return $this->createFullNameResponse($data['first_name'], $data['last_name']);
    }

    public function showFullNameFromAttributesData(FullNameRequest $request)
    {
        $data = $request->validated();
        return $this->createFullNameResponse($data['first_name'], $data['last_name']);
    }
}
