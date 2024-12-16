<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Edit Crew Skill</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/crewskills/update/{{$skill->id}}" method="post">
            @csrf
            <div class="form-group">
                <label for="module_crew_id">Crew Member:</label>
                <select id="module_crew_id" name="module_crew_id" required>
                    @foreach($crew as $member)
                        <option value="{{ $member->id }}" {{ $skill->module_crew_id == $member->id ? 'selected' : '' }}>
                            {{ $member->nick }}
                        </option>
                    @endforeach
                </select>
            </div>
            <div class="form-group">
                <label for="name">Skill Name:</label>
                <input id="name" name="name" type="text" value="{{ $skill->name }}" required minlength="3" maxlength="15">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>

        @if ($errors->any())
            <div class="alert alert-danger mt-4">
                <ul>
                    @foreach ($errors->all() as $error)
                        <li>{{ $error }}</li>
                    @endforeach
                </ul>
            </div>
        @endif
    </div>
</div>
</body>
</html>
