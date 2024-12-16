<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class CrewSkillsSeeder extends Seeder
{
    public function run()
    {
        DB::table('crew_skills')->insert([
            ['module_crew_id' => 1, 'name' => 'Navigation'],
            ['module_crew_id' => 2, 'name' => 'Engineering'],
            ['module_crew_id' => 3, 'name' => 'Maintenance'],
        ]);
    }
}
