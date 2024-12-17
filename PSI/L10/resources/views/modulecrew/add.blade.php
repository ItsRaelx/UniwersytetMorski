<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Add New Crew Member</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/modulecrew/save" method="post">
            @csrf
            <div class="form-group">
                <label for="ship_module_id">Module:</label>
                <select id="ship_module_id" name="ship_module_id" required>
                    @foreach($modules as $module)
                        <option value="{{ $module->id }}">{{ $module->module_name }}</option>
                    @endforeach
                </select>
            </div>
            <div class="form-group">
                <label for="nick">Nick:</label>
                <input id="nick" name="nick" type="text" required minlength="3" maxlength="10">
            </div>
            <div class="form-group">
                <label>Gender:</label>
                <div class="radio-group">
                    <input type="radio" id="gender_f" name="gender" value="F" required>
                    <label for="gender_f">Female</label>
                    <input type="radio" id="gender_m" name="gender" value="M">
                    <label for="gender_m">Male</label>
                    <input type="radio" id="gender_n" name="gender" value="N">
                    <label for="gender_n">Non-Binary</label>
                </div>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input id="age" name="age" type="number" required min="18" max="85">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
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
