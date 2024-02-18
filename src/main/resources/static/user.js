
const userConteiner = document.getElementById('userTable');
    let userResult = '';
const getUser = user => {
    userResult += ` <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.roles.map(r => r.name.substring(5))}</td>
                    </tr>`

    userConteiner.innerHTML = userResult;
}
fetch("/api/user")
    .then(response => response.json())
    .then(data => getUser(data))
    .catch(error => console.log(error))


