<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Add Ship Module</h2>
    <div class="card">
        <form onsubmit="ShipModules.create(event)">
            <div class="form-group">
                <label for="module_name">Module Name:</label>
                <input id="module_name" name="module_name" required minlength="3" maxlength="25">
            </div>
            <div class="form-group">
                <label>Is Workable:</label>
                <div>
                    <input type="radio" name="is_workable" value="1" checked required>
                    <label>Yes</label>
                    <input type="radio" name="is_workable" value="0">
                    <label>No</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>
<script src="<?=config('app.url'); ?>/js/api.js"></script>
<script src="<?=config('app.url'); ?>/js/shipmodules.js"></script>
</body>
</html>
