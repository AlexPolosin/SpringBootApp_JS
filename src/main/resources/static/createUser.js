document.getElementById('createUser').addEventListener('submit', event => {
    event.preventDefault();

    const username = document.getElementById('createUsername').value;
    const name = document.getElementById('createName').value;
    const email = document.getElementById('createEmail').value;
    const password = document.getElementById('createPassword').value;
    const roles = Array.from(document.getElementById('createRoles').selectedOptions)
        .map(option => option.value);

    const user = {
        username: username,
        name: name,
        email: email,
        password: password,
        roles: roles
    };

    fetch('/api/admin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            getUsers();
            document.getElementById('allUsersPage').click();

        })
});