class ShipModules {
    static async loadList() {
        try {
            const response = await Api.get('/shipmodules/list');
            const tbody = document.querySelector('#modules-table tbody');
            tbody.innerHTML = '';

            response.data.forEach(module => {
                tbody.innerHTML += `
                    <tr>
                        <td>${module.id}</td>
                        <td>${module.module_name}</td>
                        <td>${module.is_workable ? 'Yes' : 'No'}</td>
                        <td>
                            <button onclick="ShipModules.edit(${module.id})" class="btn btn-primary">Edit</button>
                            <button onclick="ShipModules.delete(${module.id})" class="btn btn-danger">Delete</button>
                            <button onclick="ShipModules.viewCrew(${module.id})" class="btn btn-info">Crew</button>
                        </td>
                    </tr>
                `;
            });
        } catch (error) {
            Api.showError('Error loading modules: ' + error.message);
        }
    }

    static async create(event) {
        event.preventDefault();
        const form = event.target;
        try {
            const data = {
                module_name: form.module_name.value,
                is_workable: form.is_workable.value === '1'
            };
            await Api.post('/shipmodules/new', data);
            window.location.href = '/shipmodules/list';
        } catch (error) {
            Api.showError('Error creating module: ' + error.message);
        }
    }

    static async update(event, id) {
        event.preventDefault();
        const form = event.target;
        try {
            const data = {
                module_name: form.module_name.value,
                is_workable: form.is_workable.value === '1'
            };
            await Api.patch(`/shipmodules/update/${id}`, data);
            window.location.href = '/shipmodules/list';
        } catch (error) {
            Api.showError('Error updating module: ' + error.message);
        }
    }

    static async delete(id) {
        if (confirm('Are you sure you want to delete this module?')) {
            try {
                await Api.delete(`/shipmodules/delete/${id}`);
                this.loadList();
            } catch (error) {
                Api.showError('Error deleting module: ' + error.message);
            }
        }
    }

    static async loadCrew(moduleId) {
        try {
            const response = await Api.get(`/shipmodules/crew/${moduleId}`);
            const tbody = document.querySelector('#crew-table tbody');
            tbody.innerHTML = '';

            response.data.forEach(member => {
                tbody.innerHTML += `
                    <tr>
                        <td>${member.nick}</td>
                        <td>${member.gender}</td>
                        <td>${member.age}</td>
                    </tr>
                `;
            });
        } catch (error) {
            Api.showError('Error loading crew: ' + error.message);
        }
    }

    static edit(id) {
        window.location.href = `/shipmodules/edit/${id}`;
    }

    static viewCrew(id) {
        window.location.href = `/shipmodules/crew/${id}`;
    }
}
