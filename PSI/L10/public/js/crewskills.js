class CrewSkills {
    static #handleError(error, message) {
        console.error(error);
        Api.showError(`${message}: ${error.message}`);
    }

    static async loadList() {
        try {
            const response = await Api.get('/crewskills/list');
            const tbody = document.querySelector('#skills-table tbody');
            tbody.innerHTML = '';

            response.data.forEach(skill => {
                tbody.innerHTML += `
                    <tr>
                        <td>${skill.id}</td>
                        <td>${skill.module_crew?.nick || 'N/A'}</td>
                        <td>${skill.name}</td>
                        <td class="actions">
                            <button onclick="CrewSkills.edit(${skill.id})" class="btn btn-primary">Edit</button>
                            <button onclick="CrewSkills.confirmDelete(${skill.id})" class="btn btn-danger">Delete</button>
                        </td>
                    </tr>
                `;
            });
        } catch (error) {
            this.#handleError(error, 'Error loading skills');
        }
    }

    static async loadCrewMembers() {
        try {
            const response = await Api.get('/modulecrew/list');
            const select = document.getElementById('module_crew_id');
            select.innerHTML = '';

            response.data.forEach(member => {
                const option = document.createElement('option');
                option.value = member.id;
                option.textContent = member.nick;
                select.appendChild(option);
            });
        } catch (error) {
            this.#handleError(error, 'Error loading crew members');
        }
    }

    static async create(event) {
        event.preventDefault();
        try {
            const formData = {
                module_crew_id: document.getElementById('module_crew_id').value,
                name: document.getElementById('name').value
            };

            const response = await Api.post('/crewskills/create', formData);
            if (response.data) {
                window.location.href = '/crewskills/list';
            }
        } catch (error) {
            this.#handleError(error, 'Error creating skill');
        }
    }

    static async loadForEdit(id) {
        try {
            const response = await Api.get(`/crewskills/find/${id}`);
            const skill = response.data;

            if (skill) {
                document.getElementById('module_crew_id').value = skill.module_crew_id;
                document.getElementById('name').value = skill.name;
            }
        } catch (error) {
            this.#handleError(error, 'Error loading skill data');
        }
    }

    static async update(event, id) {
        event.preventDefault();
        try {
            const formData = {
                module_crew_id: document.getElementById('module_crew_id').value,
                name: document.getElementById('name').value
            };

            const response = await Api.patch(`/crewskills/update/${id}`, formData);
            if (response.data) {
                window.location.href = '/crewskills/list';
            }
        } catch (error) {
            this.#handleError(error, 'Error updating skill');
        }
    }

    static confirmDelete(id) {
        if (confirm('Are you sure you want to delete this skill?')) {
            this.delete(id);
        }
    }

    static async delete(id) {
        try {
            await Api.delete(`/crewskills/delete/${id}`);
            await this.loadList();
        } catch (error) {
            this.#handleError(error, 'Error deleting skill');
        }
    }

    static edit(id) {
        window.location.href = `/crewskills/edit/${id}`;
    }
}
