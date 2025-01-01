class ModuleCrew {
    static #handleError(error, message) {
        console.error(error);
        Api.showError(`${message}: ${error.message}`);
    }

    static async loadList() {
        try {
            const response = await Api.get('/modulecrew/list');
            const tbody = document.querySelector('#crew-table tbody');
            tbody.innerHTML = '';

            response.data.forEach(member => {
                tbody.innerHTML += `
                    <tr>
                        <td>${member.id}</td>
                        <td>${member.ship_module?.module_name || 'N/A'}</td>
                        <td>${member.nick}</td>
                        <td>${member.gender}</td>
                        <td>${member.age}</td>
                        <td class="actions">
                            <button onclick="ModuleCrew.edit(${member.id})" class="btn btn-primary">Edit</button>
                            <button onclick="ModuleCrew.confirmDelete(${member.id})" class="btn btn-danger">Delete</button>
                        </td>
                    </tr>
                `;
            });
        } catch (error) {
            this.#handleError(error, 'Error loading crew members');
        }
    }

    static async loadModules() {
        try {
            const response = await Api.get('/shipmodules/list');
            const select = document.getElementById('ship_module_id');
            select.innerHTML = '';

            response.data.forEach(module => {
                const option = document.createElement('option');
                option.value = module.id;
                option.textContent = module.module_name;
                select.appendChild(option);
            });
        } catch (error) {
            this.#handleError(error, 'Error loading modules');
        }
    }

    static async create(event) {
        event.preventDefault();
        try {
            const formData = {
                ship_module_id: document.getElementById('ship_module_id').value,
                nick: document.getElementById('nick').value,
                gender: document.querySelector('input[name="gender"]:checked').value,
                age: parseInt(document.getElementById('age').value)
            };

            const response = await Api.post('/modulecrew/create', formData);
            if (response.data) {
                window.location.href = '/modulecrew/list';
            }
        } catch (error) {
            this.#handleError(error, 'Error creating crew member');
        }
    }

    static async loadForEdit(id) {
        try {
            const response = await Api.get(`/modulecrew/find/${id}`);
            const member = response.data;

            if (member) {
                document.getElementById('ship_module_id').value = member.ship_module_id;
                document.getElementById('nick').value = member.nick;
                document.querySelector(`input[name="gender"][value="${member.gender}"]`).checked = true;
                document.getElementById('age').value = member.age;
            }
        } catch (error) {
            this.#handleError(error, 'Error loading crew member data');
        }
    }

    static async update(event, id) {
        event.preventDefault();
        try {
            const formData = {
                ship_module_id: document.getElementById('ship_module_id').value,
                nick: document.getElementById('nick').value,
                gender: document.querySelector('input[name="gender"]:checked').value,
                age: parseInt(document.getElementById('age').value)
            };

            const response = await Api.patch(`/modulecrew/update/${id}`, formData);
            if (response.data) {
                window.location.href = '/modulecrew/list';
            }
        } catch (error) {
            this.#handleError(error, 'Error updating crew member');
        }
    }

    static confirmDelete(id) {
        if (confirm('Are you sure you want to delete this crew member?')) {
            this.delete(id);
        }
    }

    static async delete(id) {
        try {
            await Api.delete(`/modulecrew/delete/${id}`);
            await this.loadList();
        } catch (error) {
            this.#handleError(error, 'Error deleting crew member');
        }
    }

    static edit(id) {
        window.location.href = `/modulecrew/edit/${id}`;
    }
}
