const userUrl = '/user/current/';

const currentUser = fetch(userUrl).then(response => response.json())

currentUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += ' '
            roles += role.name.replace("ROLE_", "")
        })
        document.getElementById("navbar-email").innerHTML = user.email
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

currentUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += ' '
            roles += role.name.replace("ROLE_", "")
        })

        let result = ''
        result += `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td>
                   </tr>`
        document.getElementById("user-info-table").innerHTML = result
    }
)