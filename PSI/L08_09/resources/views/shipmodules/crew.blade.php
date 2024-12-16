<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Crew Members for Module: {{ $module->module_name }}</h2>
    <div class="card">
        @if($crew->count() > 0)
            <table>
                <thead>
                <tr>
                    <th>Nick</th>
                    <th>Gender</th>
                    <th>Age</th>
                </tr>
                </thead>
                <tbody>
                @foreach($crew as $member)
                    <tr>
                        <td>{{ $member->nick }}</td>
                        <td>{{ $member->gender }}</td>
                        <td>{{ $member->age }}</td>
                    </tr>
                @endforeach
                </tbody>
            </table>
        @else
            <p>No crew members assigned to this module.</p>
        @endif
    </div>
</div>
</body>
</html>
