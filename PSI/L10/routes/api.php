<?php

use App\Http\Controllers\RestApiController;
use App\Http\Controllers\RestApiCrewSkillsController;
use App\Http\Controllers\RestApiModuleCrewController;
use App\Http\Controllers\RestApiShipModulesController;
use Illuminate\Support\Facades\Route;

// ENDP1 (your existing welcome endpoint)
Route::get('/welcome', [RestApiController::class, 'showWelcome']);

// ENDP2 - URL Parameters
Route::get('/full-name/first-name/{first_name}/last-name/{last_name}',
    [RestApiController::class, 'showFullNameFromPath']);

// ENDP3 - Query Parameters
Route::get('/full-name',
    [RestApiController::class, 'showFullNameFromQueryParams']);

// ENDP4 - POST with Form Data
Route::post('/full-name',
    [RestApiController::class, 'showFullNameFromAttributesData']);

// Ship Modules routes
Route::prefix('shipmodules')->group(function () {
    Route::get('/list', [RestApiShipModulesController::class, 'ListShipmodules']);
    Route::post('/new', [RestApiShipModulesController::class, 'NewShipModule']);
    Route::patch('/update/{id}', [RestApiShipModulesController::class, 'UpdateShipModule']);
    Route::delete('/delete/{id}', [RestApiShipModulesController::class, 'DeleteShipModule']);
    Route::get('/find/{id}', [RestApiShipModulesController::class, 'FindShipModule']);
});

// Module Crew routes
Route::prefix('modulecrew')->group(function () {
    Route::get('/list', [RestApiModuleCrewController::class, 'list']);
    Route::get('/find/{id}', [RestApiModuleCrewController::class, 'find']);
    Route::post('/create', [RestApiModuleCrewController::class, 'create']);
    Route::patch('/update/{id}', [RestApiModuleCrewController::class, 'update']);
    Route::delete('/delete/{id}', [RestApiModuleCrewController::class, 'delete']);
});

// Crew Skills routes
Route::prefix('crewskills')->group(function () {
    Route::get('/list', [RestApiCrewSkillsController::class, 'list']);
    Route::get('/find/{id}', [RestApiCrewSkillsController::class, 'find']);
    Route::post('/create', [RestApiCrewSkillsController::class, 'create']);
    Route::patch('/update/{id}', [RestApiCrewSkillsController::class, 'update']);
    Route::delete('/delete/{id}', [RestApiCrewSkillsController::class, 'delete']);
});
