<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class FullNameRequest extends FormRequest
{
//    public function authorize(): bool
//    {
//        return true;
//    }

    public function rules(): array
    {
        return [
            'first_name' => 'required|string|min:2|max:50',
            'last_name' => 'required|string|min:2|max:50'
        ];
    }
}
