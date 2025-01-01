<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Module Crew List</h2>
    <div class="card">
        <div class="mb-4">
            <a href="<?=config('app.url'); ?>/modulecrew/add" class="btn btn-success">Add New Crew Member</a>
        </div>
        <table id="crew-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Module</th>
                <th>Nick</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/modulecrew.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => ModuleCrew.loadList());
</script>
</body>
</html>
