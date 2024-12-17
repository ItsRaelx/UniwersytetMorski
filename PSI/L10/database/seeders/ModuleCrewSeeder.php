<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ModuleCrewSeeder extends Seeder
{
    public function run()
    {
        DB::table('module_crew')->insert([
            ['ship_module_id' => 1, 'nick' => 'pilot1', 'gender' => 'M', 'age' => 30],
            ['ship_module_id' => 2, 'nick' => 'tech1', 'gender' => 'F', 'age' => 25],
            ['ship_module_id' => 3, 'nick' => 'eng1', 'gender' => 'N', 'age' => 45],
        ]);
    }
}
