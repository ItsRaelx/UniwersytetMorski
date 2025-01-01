<!DOCTYPE html>
<html>
@include('partials.head')
<body>
@include('partials.navi')
<div id="zawartosc">
    <h2>Edit Module</h2>
    <div class="card">
        <form id="edit-form" onsubmit="updateModule(event)">
            <div class="form-group">
                <label for="module_name">Module Name:</label>
                <input id="module_name" name="module_name" required>
            </div>
            <div class="form-group">
                <label>Is Workable:</label>
                <div>
                    <input type="radio" name="is_workable" value="1" required>
                    <label>Yes</label>
                    <input type="radio" name="is_workable" value="0" required>
                    <label>No</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>

<script src="/js/api.js"></script>
<script>
    const moduleId = {{ $shipmodule->id }};

    async function loadModule() {
        try {
            const response = await apiGet(`/shipmodules/find/${moduleId}`);
            const module = response.data;

            document.getElementById('module_name').value = module.module_name;
            document.querySelector(`input[name="is_workable"][value="${module.is_workable ? '1' : '0'}"]`).checked = true;
        } catch (error) {
            console.error('Error loading module:', error);
        }
    }

    async function updateModule(event) {
        event.preventDefault();

        const formData = {
            module_name: document.getElementById('module_name').value,
            is_workable: document.querySelector('input[name="is_workable"]:checked').value === '1'
        };

        try {
            await apiPatch(`/shipmodules/update/${moduleId}`, formData);
            window.location.href = '/shipmodules/list';
        } catch (error) {
            console.error('Error updating module:', error);
        }
    }

    document.addEventListener('DOMContentLoaded', loadModule);
</script>
</body>
</html>
