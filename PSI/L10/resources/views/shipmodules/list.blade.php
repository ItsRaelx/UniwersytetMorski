<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Ship Modules List</h2>
    <div class="card">
        <div class="mb-4">
            <a href="<?=config('app.url'); ?>/shipmodules/add" class="btn btn-success">Add New Module</a>
        </div>
        <table id="modules-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Module Name</th>
                <th>Is Workable</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/shipmodules.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => ShipModules.loadList());
</script>
</body>
</html>
