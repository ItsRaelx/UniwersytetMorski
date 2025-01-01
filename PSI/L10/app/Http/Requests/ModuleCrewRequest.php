<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Http\Exceptions\HttpResponseException;

class ModuleCrewRequest extends FormRequest
{
    public function rules(): array
    {
        return [
            'ship_module_id' => 'required|exists:ship_modules,id',
            'nick' => 'required|min:3|max:10|unique:module_crew,nick,' . $this->id,
            'gender' => 'required|in:F,M,N',
            'age' => 'required|integer|min:18|max:85'
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
