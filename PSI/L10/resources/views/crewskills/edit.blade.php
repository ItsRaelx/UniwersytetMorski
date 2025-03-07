<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Edit Crew Skill</h2>
    <div class="card">
        <form onsubmit="CrewSkills.update(event, {{$skill->id}})">
            <div class="form-group">
                <label for="module_crew_id">Crew Member:</label>
                <select id="module_crew_id" name="module_crew_id" required></select>
            </div>
            <div class="form-group">
                <label for="name">Skill Name:</label>
                <input id="name" name="name" type="text" required minlength="3" maxlength="15">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/crewskills.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', async () => {
        await CrewSkills.loadCrewMembers();
        await CrewSkills.loadForEdit({{$skill->id}});
    });
</script>
</body>
</html>
