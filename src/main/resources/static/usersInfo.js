const url = 'http://localhost:8080/admin/api';
const conteiner = document.getElementById('usersForm');

const getUsers = () => {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let result = '';
            conteiner.innerHTML = '';
            data.forEach(user => {
                result += ` <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.roles.map(r => r.name.substring(5))}</td>
                    <td>
                    <a class="btnEdit btn btn-info">Edit</a>
                    </td>
                    <td>
                    <a class="btnDelete btn btn-danger">Delete</a>
                    </td>
                    </tr>`
        })
            conteiner.innerHTML = result;
    })

}
getUsers();

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e);
        }
    })
}
