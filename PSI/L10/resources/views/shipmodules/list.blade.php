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
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Module Name</th>
                <th>Is Workable</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            @foreach($ship_modules as $module)
                <tr>
                    <td>{{ $module->id }}</td>
                    <td>{{ $module->module_name }}</td>
                    <td>{{ $module->is_workable ? 'Yes' : 'No' }}</td>
                    <td><a href="<?=config('app.url'); ?>/shipmodules/edit/{{$module->id}}" class="btn btn-primary">Edit</a></td>
                    <td><a href="<?=config('app.url'); ?>/shipmodules/show/{{$module->id}}" class="btn btn-danger">Del</a></td>
                    <td><a href="<?=config('app.url'); ?>/shipmodules/crew/{{$module->id}}">{{ $module->module_name }}</a></td>
                </tr>
            @endforeach
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
