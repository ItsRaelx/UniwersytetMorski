<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Edit Crew Member</h2>
    <div class="card">
        <form onsubmit="ModuleCrew.update(event, {{$crew->id}})">
            <div class="form-group">
                <label for="ship_module_id">Module:</label>
                <select id="ship_module_id" name="ship_module_id" required></select>
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
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/modulecrew.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', async () => {
        await ModuleCrew.loadModules();
        await ModuleCrew.loadForEdit({{$crew->id}});
    });
</script>
</body>
</html>
