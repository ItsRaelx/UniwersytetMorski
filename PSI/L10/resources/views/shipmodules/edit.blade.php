<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Edit module</h2>
    <div class="card">
        <form class="form-inline" action="<?=config('app.url'); ?>/shipmodules/update/{{$shipmodule->id}}" method="post">
            @csrf
            <div class="form-group">
                <label for="id">Id:</label>
                <input id="id" name="id" value="{{$shipmodule->id}}" readonly>
            </div>
            <div class="form-group">
                <label for="module_name">Module Name:</label>
                <input id="module_name" name="module_name" value="{{$shipmodule->module_name}}" size="25" required>
            </div>
            <div class="form-group">
                <label>Is Workable:</label>
                <div>
                    <input type="radio" name="is_workable" id="workable_yes" value="1" @if ($shipmodule->is_workable) checked @endif required>
                    <label for="workable_yes">True</label>
                    <input type="radio" name="is_workable" id="workable_no" value="0" @if (!$shipmodule->is_workable) checked @endif required>
                    <label for="workable_no">False</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>

        @if ($errors->any())
            <div class="alert alert-danger">
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
