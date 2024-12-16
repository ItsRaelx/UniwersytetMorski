<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use App\Models\ShipModules;

class ShipModulesSeeder extends Seeder
{
    public function run()
    {
        DB::table('ship_modules')->insert([
            ['module_name' => 'engines', 'is_workable' => true],
            ['module_name' => 'lights', 'is_workable' => true],
            ['module_name' => 'air_condition', 'is_workable' => false],
        ]);
    }
}

