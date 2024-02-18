const editModal = new bootstrap.Modal(document.getElementById('editModal'));
const editForm = document.getElementById('editForm');

const updateUsername = document.getElementById('updateUsername');
const updateName = document.getElementById('updateName');
const updateEmail = document.getElementById('updateEmail');

let idEdit = 0;
on(document, 'click', '.btnEdit', e => {
    const line = e.target.parentNode.parentNode;
    idEdit = line.children[0].innerHTML;
    const usernameEdit = line.children[1].innerHTML;
    const nameEdit = line.children[2].innerHTML;
    const emailEdit = line.children[3].innerHTML;
    const roleEdit = line.children[4].innerHTML;
    updateUsername.value = usernameEdit;
    updateName.value = nameEdit;
    updateEmail.value = emailEdit;
    option = "edit";
    editModal.show();

})

editForm.addEventListener('submit', e => {
    e.preventDefault();
    const updateRoles = Array.from(document.getElementById('updateRole').selectedOptions)
        .map(option => option.value);
    if (updateRoles.length === 0) {
        updateRoles.push('ROLE_USER');
    }
    fetch(`/api/admin/${idEdit}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: updateUsername.value,
            name: updateName.value,
            email: updateEmail.value,
            roles: updateRoles
        })
    })
        .then(response => response.json())
        .then(response => getUsers())
    editModal.hide();

})