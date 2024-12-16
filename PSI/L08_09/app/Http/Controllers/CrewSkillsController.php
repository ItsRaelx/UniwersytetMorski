<?php

namespace App\Http\Controllers;

use App\Models\CrewSkills;
use App\Models\ModuleCrew;
use Illuminate\Http\Request;

class CrewSkillsController extends Controller
{
    public function index()
    {
        $skills = CrewSkills::all();
        return view('crewskills.list', ['skills' => $skills]);
    }

    public function create()
    {
        $crew = ModuleCrew::all();
        return view('crewskills.add', ['crew' => $crew]);
    }

    public function store(Request $request)
    {
        $validated = $request->validate([
            'module_crew_id' => 'required|exists:module_crew,id',
            'name' => 'required|min:3|max:15|unique:crew_skills',
        ]);

        if ($validated) {
            CrewSkills::create($request->all());
            return redirect('/crewskills/list');
        }
    }

    public function edit($id)
    {
        $skill = CrewSkills::find($id);
        $crew = ModuleCrew::all();

        if (!$skill) {
            return view('crewskills.message', [
                'message' => "Skill id=$id not found",
                'type_of_message' => 'Error'
            ]);
        }

        return view('crewskills.edit', [
            'skill' => $skill,
            'crew' => $crew
        ]);
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'module_crew_id' => 'required|exists:module_crew,id',
            'name' => 'required|min:3|max:15|unique:crew_skills,name,'.$id,
        ]);

        if ($validated) {
            $skill = CrewSkills::find($id);
            if ($skill) {
                $skill->update($request->all());
                return redirect('/crewskills/list');
            }
        }
    }

    public function destroy($id)
    {
        $skill = CrewSkills::find($id);
        if ($skill) {
            $skill->delete();
            return redirect('/crewskills/list');
        }

        return view('crewskills.message', [
            'message' => "Skill id=$id not found",
            'type_of_message' => 'Error'
        ]);
    }

    public function show($id)
    {
        $skill = CrewSkills::find($id);

        if (!$skill) {
            return view('crewskills.message', [
                'message' => "Skill id=$id not found",
                'type_of_message' => 'Error'
            ]);
        }

        return view('crewskills.show', ['skill' => $skill]);
    }
}
