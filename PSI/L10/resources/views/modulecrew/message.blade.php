<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <div class="alert alert-{{ $type_of_message == 'Error' ? 'danger' : 'success' }}">
        {{ $message }}
    </div>
    <div class="mt-4">
        <a href="<?=config('app.url'); ?>/modulecrew/list" class="btn btn-primary">Back to List</a>
    </div>
</div>
</body>
</html>
