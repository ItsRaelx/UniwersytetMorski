<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <div class="alert alert-{{ $type_of_message == 'Error' ? 'danger' : 'success' }}">
        {{ $message }}
    </div>
</div>
</body>
</html>
