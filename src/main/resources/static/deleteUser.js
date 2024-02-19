const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
const deleteForm = document.getElementById('deleteForm');

const deleteUsername = document.getElementById('deleteUsername');
const deleteName = document.getElementById('deleteName');
const deleteEmail = document.getElementById('deleteEmail');
let idDelete = 0;
on(document, 'click', '.btnDelete', e => {
    const line = e.target.parentNode.parentNode;
    idDelete = line.children[0].innerHTML;
    const usernameDelete = line.children[1].innerHTML;
    const nameDelete = line.children[2].innerHTML;
    const emailDelete = line.children[3].innerHTML;
    const roleDelete = line.children[4].innerHTML;
    deleteUsername.value = usernameDelete;
    deleteName.value = nameDelete;
    deleteEmail.value = emailDelete;
    option = "delete";
    deleteModal.show();
})
deleteForm.addEventListener('submit', e => {
    e.preventDefault();
    fetch(`/admin/api/${idDelete}`, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then((data) => {
            console.log('User deleted: ' + data);
            getUsers();
        })
    deleteModal.hide();

})