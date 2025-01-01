<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Http\Exceptions\HttpResponseException;

class ShipModulesRequest extends FormRequest
{
    // public function authorize(): bool
    // {
    //     return true;
    // }

    public function rules(): array
    {
        return [
            'module_name' => 'required|string|min:3|max:25|unique:ship_modules,module_name,' . $this->id,
            'is_workable' => 'required|boolean'
        ];
    }

    public function messages(): array
    {
        return [
            'module_name.required' => 'Module name is required',
            'module_name.min' => 'Module name must be at least 3 characters',
            'module_name.max' => 'Module name cannot exceed 25 characters',
            'module_name.unique' => 'Module name must be unique',
            'is_workable.required' => 'Workable status is required',
            'is_workable.boolean' => 'Workable status must be true or false'
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
