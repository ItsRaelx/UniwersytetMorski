<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Crew Skills List</h2>
    <div class="card">
        <div class="mt-4">
            <a href="<?=config('app.url'); ?>/crewskills/add" class="btn btn-success">Add New Skill</a>
        </div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Crew Member</th>
                <th>Skill Name</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            @foreach($skills as $skill)
                <tr>
                    <td>{{ $skill->id }}</td>
                    <td>{{ $skill->moduleCrew->nick }}</td>
                    <td>{{ $skill->name }}</td>
                    <td><a href="<?=config('app.url'); ?>/crewskills/edit/{{$skill->id}}" class="btn btn-primary">Edit</a></td>
                    <td><a href="<?=config('app.url'); ?>/crewskills/show/{{$skill->id}}" class="btn btn-danger">Del</a></td>
                </tr>
            @endforeach
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
