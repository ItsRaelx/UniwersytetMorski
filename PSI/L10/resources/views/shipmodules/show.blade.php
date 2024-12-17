<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Confirmation - Delete Id: {{$shipmodule->id}}</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/shipmodules/delete/{{$shipmodule->id}}" method="post">
            @csrf
            <div class="form-group">
                <label for="id">Id:</label>
                <input id="id" name="id" value="{{$shipmodule->id}}" readonly>
            </div>
            <div class="form-group">
                <label for="module_name">Module Name:</label>
                <input id="module_name" name="module_name" value="{{$shipmodule->module_name}}" size="25" readonly required>
            </div>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
</body>
</html>
