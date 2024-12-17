<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Confirmation - Delete Crew Member</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/modulecrew/delete/{{$crew->id}}" method="post">
            @csrf
            <div class="form-group">
                <label>ID:</label>
                <input value="{{ $crew->id }}" readonly>
            </div>
            <div class="form-group">
                <label>Module:</label>
                <input value="{{ $crew->shipModule->module_name }}" readonly>
            </div>
            <div class="form-group">
                <label>Nick:</label>
                <input value="{{ $crew->nick }}" readonly>
            </div>
            <div class="form-group">
                <label>Gender:</label>
                <input value="{{ $crew->gender }}" readonly>
            </div>
            <div class="form-group">
                <label>Age:</label>
                <input value="{{ $crew->age }}" readonly>
            </div>
            <button type="submit" class="btn btn-danger">Confirm Delete</button>
        </form>
    </div>
</div>
</body>
</html>
