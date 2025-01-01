<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Crew Skills List</h2>
    <div class="card">
        <div class="mb-4">
            <a href="<?=config('app.url'); ?>/crewskills/add" class="btn btn-success">Add New Skill</a>
        </div>
        <table id="skills-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Crew Member</th>
                <th>Skill Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/crewskills.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => CrewSkills.loadList());
</script>
</body>
</html>
