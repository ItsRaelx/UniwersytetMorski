<?php

use App\Http\Controllers\HomeController;
use App\Http\Controllers\ShipModulesController;
use App\Http\Controllers\ModuleCrewController;
use App\Http\Controllers\CrewSkillsController;
use Illuminate\Support\Facades\Route;

// Home route
Route::get('/', [HomeController::class, 'index']);

// ShipModules routes
Route::get('/shipmodules/list', [ShipModulesController::class, 'index']);
Route::get('/shipmodules/add', [ShipModulesController::class, 'create']);
Route::post('/shipmodules/save', [ShipModulesController::class, 'store']);
Route::get('/shipmodules/edit/{id}', [ShipModulesController::class, 'edit']);
Route::post('/shipmodules/update/{id}', [ShipModulesController::class, 'update']);
Route::get('/shipmodules/show/{id}', [ShipModulesController::class, 'show']);
Route::post('/shipmodules/delete/{id}', [ShipModulesController::class, 'destroy']);
Route::get('/shipmodules/crew/{id}', [ShipModulesController::class, 'showCrew']);

// ModuleCrew routes
Route::get('/modulecrew/list', [ModuleCrewController::class, 'index']);
Route::get('/modulecrew/add', [ModuleCrewController::class, 'create']);
Route::post('/modulecrew/save', [ModuleCrewController::class, 'store']);
Route::get('/modulecrew/edit/{id}', [ModuleCrewController::class, 'edit']);
Route::post('/modulecrew/update/{id}', [ModuleCrewController::class, 'update']);
Route::get('/modulecrew/show/{id}', [ModuleCrewController::class, 'show']);
Route::post('/modulecrew/delete/{id}', [ModuleCrewController::class, 'destroy']);

// CrewSkills routes
Route::get('/crewskills/list', [CrewSkillsController::class, 'index']);
Route::get('/crewskills/add', [CrewSkillsController::class, 'create']);
Route::post('/crewskills/save', [CrewSkillsController::class, 'store']);
Route::get('/crewskills/edit/{id}', [CrewSkillsController::class, 'edit']);
Route::post('/crewskills/update/{id}', [CrewSkillsController::class, 'update']);
Route::get('/crewskills/show/{id}', [CrewSkillsController::class, 'show']);
Route::post('/crewskills/delete/{id}', [CrewSkillsController::class, 'destroy']);
