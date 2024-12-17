<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Confirmation - Delete Skill</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/crewskills/delete/{{$skill->id}}" method="post">
            @csrf
            <div class="form-group">
                <label>ID:</label>
                <input value="{{ $skill->id }}" readonly>
            </div>
            <div class="form-group">
                <label>Crew Member:</label>
                <input value="{{ $skill->moduleCrew->nick }}" readonly>
            </div>
            <div class="form-group">
                <label>Skill Name:</label>
                <input value="{{ $skill->name }}" readonly>
            </div>
            <button type="submit" class="btn btn-danger">Confirm Delete</button>
        </form>
    </div>
</div>
</body>
</html>
