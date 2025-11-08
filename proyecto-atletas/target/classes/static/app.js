document.addEventListener('DOMContentLoaded', () => {
    // URL base de tu API de Spring Boot
    const API_URL = 'http://localhost:8080/api/atletas'; // Esta URL es correcta

    const form = document.getElementById('athlete-form');
    const messageDiv = document.getElementById('message');
    const loadButton = document.getElementById('load-athletes');
    const listDiv = document.getElementById('athletes-list');

    // Función para mostrar un mensaje de éxito o error
    const displayMessage = (msg, isSuccess = true) => {
        messageDiv.textContent = msg;
        messageDiv.style.backgroundColor = isSuccess ? '#d4edda' : '#f8d7da';
        messageDiv.style.color = isSuccess ? '#155724' : '#721c24';
    };

    // ----------------------------------------------------
    // LÓGICA DE REGISTRO (POST)
    // ----------------------------------------------------
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const newAthlete = {
            nombre: document.getElementById('nombre').value,
            edad: parseInt(document.getElementById('edad').value),
            disciplina: document.getElementById('disciplina').value,
            departamento: document.getElementById('departamento').value
        };

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newAthlete)
            });

            if (response.ok) {
                const athlete = await response.json();
                displayMessage(`Atleta "${athlete.nombre}" registrado con éxito (ID: ${athlete.id})`, true);
                form.reset();
                loadAthletes();
            } else {
                // Si tienes un error 404, el @RequestMapping en Java está mal
                // Si tienes un error 500, la base de datos falló
                displayMessage(`Error al registrar: ${response.status} ${response.statusText}`, false);
            }
        } catch (error) {
            // Este es el error que estás viendo: Fallo de conexión (CORS o servidor apagado)
            displayMessage(`Error de conexión con la API. Asegúrate que Spring Boot esté corriendo.`, false);
            console.error('Fetch Error:', error);
        }
    });

    // ----------------------------------------------------
    // LÓGICA DE LECTURA (GET)
    // ----------------------------------------------------
    const loadAthletes = async () => {
        listDiv.innerHTML = 'Cargando...';
        try {
            const response = await fetch(API_URL);

            if (response.ok) {
                const athletes = await response.json();
                renderAthletesList(athletes);
            } else {
                listDiv.innerHTML = `Error: ${response.status} ${response.statusText}`;
            }
        } catch (error) {
            listDiv.innerHTML = `Error: No se pudo conectar al servidor.`;
            console.error('Fetch Error:', error);
        }
    };

    // Función para renderizar la lista en el HTML
    const renderAthletesList = (athletes) => {
        if (athletes.length === 0) {
            listDiv.innerHTML = '<p>No hay atletas registrados aún.</p>';
            return;
        }

        const ul = document.createElement('ul');
        athletes.forEach(a => {
            const li = document.createElement('li');
            li.textContent = `ID: ${a.id} | Nombre: ${a.nombre} | Disciplina: ${a.disciplina}`;
            ul.appendChild(li);
        });
        listDiv.innerHTML = '';
        listDiv.appendChild(ul);
    };

    // Enlazar la función de carga al botón
    loadButton.addEventListener('click', loadAthletes);

    // Cargar la lista al iniciar la página
    loadAthletes();
});