<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Http\Exceptions\HttpResponseException;

class CrewSkillsRequest extends FormRequest
{
    public function rules(): array
    {
        return [
            'module_crew_id' => 'required|exists:module_crew,id',
            'name' => 'required|min:3|max:15|unique:crew_skills,name,' . $this->id
        ];
    }

    protected function failedValidation(Validator $validator)
    {
        throw new HttpResponseException(response()->json([
            'error' => true,
            'message' => 'Validation errors',
            'data' => $validator->errors()
        ], 422));
    }
}
