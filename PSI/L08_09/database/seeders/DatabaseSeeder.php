<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    public function run()
    {
        $this->call([
            ShipModulesSeeder::class,
            ModuleCrewSeeder::class,
            CrewSkillsSeeder::class,
        ]);
    }
}
