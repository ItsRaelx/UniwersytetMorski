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
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Module</th>
                <th>Nick</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            @foreach($crew as $member)
                <tr>
                    <td>{{ $member->id }}</td>
                    <td>{{ $member->shipModule->module_name }}</td>
                    <td>{{ $member->nick }}</td>
                    <td>{{ $member->gender }}</td>
                    <td>{{ $member->age }}</td>
                    <td><a href="<?=config('app.url'); ?>/modulecrew/edit/{{$member->id}}" class="btn btn-primary">Edit</a></td>
                    <td><a href="<?=config('app.url'); ?>/modulecrew/show/{{$member->id}}" class="btn btn-danger">Del</a></td>
                </tr>
            @endforeach
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
